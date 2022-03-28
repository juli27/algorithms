package numbers;

/**
 * This class contains Euclid's algorithm to determine the greatest common divisor (gcd) of two
 * positive integers {@code > 0}.
 */
public final class Gcd {

  /**
   * Returns the greatest common divisor (gcd) of the provided positive integers. The behaviour is
   * undefined if a or b less than 1. This version of Euclid's algorithm is very slow in comparison
   * to {@link #euclidModRecursive(int, int)}. Consider using that function instead.
   *
   * <p>This function is very likely to cause a {@link StackOverflowError}.
   *
   * @param a {@code > 0}
   * @param b {@code > 0}
   * @return {@code gcd(a,b)}
   */
  public static int euclidRecursive(int a, int b) {
    int cmp = Integer.compare(a, b);

    if (cmp == 0) {
      return a;
    }

    if (cmp > 0) {
      return euclidRecursive(a - b, b);
    }

    // cmp < 0
    return euclidRecursive(a, b - a);
  }

  /**
   * Returns the greatest common divisor (gcd) of the provided positive integers. The behaviour is
   * undefined if a or b less than 1. This version of Euclid's algorithm is very slow in comparison
   * to {@link #euclidModIterative(int, int)}. Consider using that function instead.
   *
   * @param a {@code > 0}
   * @param b {@code > 0}
   * @return {@code gcd(a,b)}
   */
  public static int euclidIterative(int a, int b) {
    while (a != b) {
      if (a > b) {
        a -= b;
      } else {
        b -= a;
      }
    }

    return a;
  }

  /**
   * Returns the greatest common divisor (gcd) of the provided positive integers. The behaviour is
   * undefined if a or b less than 1.
   *
   * <p>This function may cause a {@link StackOverflowError}.
   *
   * @param a {@code > 0}
   * @param b {@code > 0}
   * @return {@code gcd(a,b)}
   */
  public static int euclidModRecursive(int a, int b) {
    int aModB = a % b;

    if (aModB == 0) {
      return b;
    }

    return euclidModRecursive(b, aModB);
  }

  /**
   * Returns the greatest common divisor (gcd) of the provided positive integers. The behaviour is
   * undefined if a or b less than 1.
   *
   * @param a {@code > 0}
   * @param b {@code > 0}
   * @return {@code gcd(a,b)}
   */
  public static int euclidModIterative(int a, int b) {
    int c = a % b;

    while (c != 0) {
      a = b;
      b = c;
      c = a % b;
    }

    return b;
  }
}
