package codes;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A {@code HuffmanCode} is a huffman tree with its associated prefix code. A huffman tree can be
 * created from characters and their absolute frequencies using {@link
 * #fromFrequencies(Collection)}.
 */
public final class HuffmanCode {

  private final HuffmanCharacter root;
  private final Map<Character, String> encode = new HashMap<>();

  private HuffmanCode(HuffmanCharacter root) {
    this.root = root;

    traverse(root, "");
  }

  /**
   * Returns the root node of this huffman tree.
   *
   * @return the root node
   */
  public HuffmanCharacter getRoot() {
    return root;
  }

  /**
   * Returns the code for the specified character for this huffman tree or null if the character is
   * not part of this tree.
   *
   * @param c the character to encode
   * @return the code or null
   */
  public String encode(char c) {
    return encode.get(c);
  }

  /**
   * Returns a new huffman code constructed from the specified characters and their absolute
   * frequencies.
   *
   * @param chars the collection of characters and their frequencies
   * @return a new huffman code for the specified characters
   */
  public static HuffmanCode fromFrequencies(Collection<HuffmanCharacter> chars) {
    Queue<HuffmanCharacter> tree =
        new PriorityQueue<>(chars.size(), Comparator.comparingInt(HuffmanCharacter::frequency));
    tree.addAll(chars);

    while (tree.size() > 1) {
      tree.add(new HuffmanCharacter(tree.remove(), tree.remove()));
    }

    return new HuffmanCode(tree.poll());
  }

  private void traverse(HuffmanCharacter node, String code) {
    if (node == null) {
      return;
    }

    if (node.isLeaf()) {
      encode.put(node.character(), code);

      return;
    }

    traverse(node.left(), code + '0');
    traverse(node.right(), code + '1');
  }
}
