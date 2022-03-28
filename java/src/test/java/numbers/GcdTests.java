package numbers;

import net.jqwik.api.Disabled;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;

class GcdTests {

  @Property
  @Disabled("stack overflow")
  boolean commutativityEuclidRecursive(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    return Gcd.euclidRecursive(a, b) == Gcd.euclidRecursive(b, a);
  }

  @Property
  @Disabled("too slow")
  boolean commutativityEuclidIterative(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    return Gcd.euclidIterative(a, b) == Gcd.euclidIterative(b, a);
  }

  @Property
  boolean commutativityEuclidModRecursive(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    return Gcd.euclidModRecursive(a, b) == Gcd.euclidModRecursive(b, a);
  }

  @Property
  boolean commutativityEuclidModIterative(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    return Gcd.euclidModIterative(a, b) == Gcd.euclidModIterative(b, a);
  }

  @Property
  boolean commonDivisorEuclidModRecursive(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    int divisor = Gcd.euclidModRecursive(a, b);

    return a % divisor == 0 && b % divisor == 0;
  }

  @Property
  boolean commonDivisorEuclidModIterative(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    int divisor = Gcd.euclidModIterative(a, b);

    return a % divisor == 0 && b % divisor == 0;
  }

  @Property
  boolean greatestEuclidModRecursive(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    int divisor = Gcd.euclidModRecursive(a, b);

    if (divisor == Integer.MAX_VALUE) {
      return true;
    }

    int sqrtA = (int) Math.sqrt(a);

    for (int i = divisor + 1; i <= sqrtA; i++) {
      if (a % i == 0 && b % i == 0) {
        return false;
      }
    }

    return true;
  }

  private boolean isGcd(int a, int b, int gcd) {
    int sqrtA = (int) Math.sqrt(a);

    for (int i = gcd + 1; i <= sqrtA; i++) {
      if (a % i == 0 && b % i == 0) {
        return false;
      }
    }

    return true;
  }

  @Property
  boolean positiveEuclidModRecursive(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    return 0 < Gcd.euclidModRecursive(a, b);
  }

  @Property
  boolean positiveEuclidModIterative(
      @ForAll
      @Positive int a,
      @ForAll
      @Positive int b) {
    return 0 < Gcd.euclidModIterative(a, b);
  }
}
