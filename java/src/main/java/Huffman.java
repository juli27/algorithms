import codes.HuffmanCharacter;
import codes.HuffmanCode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Huffman text en- and decoder.
 *
 * <p>TODO: some kind of eof char for encoding and decoding
 */
final class Huffman {

  private Huffman() {
  }

  public static void main(String[] args) {
    if (args.length == 3) {
      switch (args[0]) {
        case "-e":
        case "--encode":
          encodeTextFile(args[1], args[2]);
          break;

        case "-d":
        case "--decode":
          decodeTextFile(args[1], args[2]);
          break;

        default:
          printUsage();
      }
    } else {
      printUsage();
    }
  }

  private static void printUsage() {
    System.out.println(
        "-e, --encode <in file> <out file> : encode the content of <in file> and save to <out "
            + "file>");
    System.out.println(
        "-d, --decode <in file> <out file> : decode the content of <in file> and save to <out "
            + "file>");
  }

  private static void encodeTextFile(String inputFileName, String outputFileName) {
    Path inputFile;
    Path outputFile;
    try {
      inputFile = Paths.get(inputFileName);
      outputFile = Paths.get(outputFileName);
    } catch (InvalidPathException ex) {
      System.err.println(ex.getMessage());

      return;
    }

    HuffmanCode huffmanCode;
    try (Reader in = Files.newBufferedReader(inputFile)) {
      huffmanCode = getHuffmanCodeForContents(in);
    } catch (IOException ex) {
      System.err.println(ex.getMessage());

      return;
    }

    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(outputFile))) {
      writeHuffmanTree(out, huffmanCode.getRoot());

      try (Reader in = Files.newBufferedReader(inputFile)) {
        writeEncodedChars(out, in, huffmanCode);
      }

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  private static void decodeTextFile(String inputFileName, String outputFileName) {
    Path inputFile;
    Path outputFile;

    try {
      inputFile = Paths.get(inputFileName);
      outputFile = Paths.get(outputFileName);
    } catch (InvalidPathException ex) {
      System.err.println(ex.getMessage());

      return;
    }

    try (InputStream in = new BufferedInputStream(Files.newInputStream(inputFile))) {
      HuffmanCharacter root = readHuffmanTree(in);

      try (Writer out = Files.newBufferedWriter(outputFile)) {
        writeDecodedChars(out, in, root);
      }
    } catch (IOException | ClassNotFoundException ex) {
      System.err.println(ex.getMessage());
    }
  }

  private static HuffmanCode getHuffmanCodeForContents(Reader in) throws IOException {
    return HuffmanCode.fromFrequencies(getCharFrequencies(in));
  }

  private static Collection<HuffmanCharacter> getCharFrequencies(Reader in) throws IOException {
    Map<Character, HuffmanCharacter> chars = new HashMap<>();
    for (int read = in.read(); read != -1; read = in.read()) {
      chars.computeIfAbsent((char) read, HuffmanCharacter::new)
           .incrementFrequency();
    }

    return chars.values();
  }

  private static void writeEncodedChars(OutputStream out, Reader in, HuffmanCode huffmanCode)
      throws IOException {
    byte b = 0;
    int bitIndex = 0;

    for (int read = in.read(); read != -1; read = in.read()) {
      char[] code = huffmanCode.encode((char) read)
                               .toCharArray();

      for (char c : code) {
        if (c == '1') {
          b |= 1 << bitIndex++;
        } else {
          bitIndex++;
        }

        // flush the bitset every 64 bits
        if (bitIndex == Byte.SIZE) {
          out.write(b);
          b = 0;
          bitIndex = 0;
        }
      }
    }

    if (bitIndex != 0) {
      out.write(b);
    }
  }

  private static void writeDecodedChars(Writer out, InputStream in, HuffmanCharacter root)
      throws IOException {
    HuffmanCharacter hc = root;

    for (int read = in.read(); read != -1; read = in.read()) {
      // TODO: support for a single leaf huffman char
      // iterate through the bits
      for (int i = 0; i < Byte.SIZE; ++i) {
        int bit = read & (1 << i);
        if (bit != 0) {
          hc = hc.right();
        } else {
          hc = hc.left();
        }

        if (hc.isLeaf()) {
          out.write(hc.character());
          hc = root;
        }
      }
    }
  }

  private static void writeHuffmanTree(OutputStream out, HuffmanCharacter root) throws IOException {
    ObjectOutputStream objectOut = new ObjectOutputStream(out);
    objectOut.writeObject(root);
  }

  private static HuffmanCharacter readHuffmanTree(InputStream in)
      throws ClassNotFoundException, IOException {
    ObjectInputStream objectIn = new ObjectInputStream(in);

    return (HuffmanCharacter) objectIn.readObject();
  }
}
