package codes;

import java.io.Serializable;

/**
 * A {@code HuffmanCharacter} is a node in a huffman tree. It associates a {@code char} with its
 * frequency. Non-leaf nodes have {@code '\0'} as its char.
 */
public final class HuffmanCharacter implements Serializable {

  private final char c;
  private int frequency = 0;

  private final HuffmanCharacter left;
  private final HuffmanCharacter right;

  /**
   * Constructs a huffman tree leaf from the specified character with frequency 0.
   *
   * @param c the character
   */
  public HuffmanCharacter(char c) {
    this.c = c;
    this.left = null;
    this.right = null;
  }

  HuffmanCharacter(HuffmanCharacter left, HuffmanCharacter right) {
    c = '\0';
    frequency = left.frequency + right.frequency;
    this.left = left;
    this.right = right;
  }

  /**
   * Returns the character of this {@code HuffmanCharacter}.
   *
   * @return the character
   */
  public char character() {
    return c;
  }

  /**
   * Returns the absolute frequency of this {@code HuffmanCharacter}.
   *
   * @return the absolute frequency
   */
  public int frequency() {
    return frequency;
  }

  /**
   * Returns the left child node of this {@code HuffmanCharacter} or null if this is a leaf node.
   *
   * @return the left child node or null
   */
  public HuffmanCharacter left() {
    return left;
  }

  /**
   * Returns the right child node of this {@code HuffmanCharacter} or null if this is a leaf node.
   *
   * @return the right child node or null
   */
  public HuffmanCharacter right() {
    return right;
  }

  /**
   * Increments the absolute frequency of this {@code HuffmanCharacter} by one.
   */
  public void incrementFrequency() {
    ++frequency;
  }

  /**
   * Returns true if this {@code HuffmanCharacter} is a leaf node.
   *
   * @return true if this huffman character is a leaf node, false otherwise
   */
  public boolean isLeaf() {
    return left == null && right == null;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    HuffmanCharacter that = (HuffmanCharacter) obj;

    return c == that.c;
  }

  @Override
  public int hashCode() {
    return Character.hashCode(c);
  }

  @Override
  public String toString() {
    return "HuffmanCharacter{" + "ch=" + c + ", freq=" + frequency + '}';
  }
}
