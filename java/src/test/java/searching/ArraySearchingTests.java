package searching;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ArraySearchingTests {
  @Test
  void testLinearSearch() {
    assertThrows(NullPointerException.class, () -> ArraySearching.linearSearch(null, 0));

    int[] array = {};
    assertThat(ArraySearching.linearSearch(array, 0)).isEmpty();

    array = new int[] {1};
    assertThat(ArraySearching.linearSearch(array, 1)).hasValue(0);
    assertThat(ArraySearching.linearSearch(array, 0)).isEmpty();

    array = new int[] {-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1};
    assertThat(ArraySearching.linearSearch(array, 1)).isEmpty();
    assertThat(ArraySearching.linearSearch(array, -2)).hasValue(0);
    assertThat(ArraySearching.linearSearch(array, 99)).hasValue(9);
    assertThat(ArraySearching.linearSearch(array, 8)).hasValue(2);
  }

  @Test
  void testBinarySearch() {
    assertThrows(NullPointerException.class, () -> ArraySearching.binarySearch(null, 0));

    int[] array = new int[] {};
    assertThat(ArraySearching.binarySearch(array, 0)).isEmpty();

    array = new int[] {1};
    assertThat(ArraySearching.binarySearch(array, 1)).hasValue(0);
    assertThat(ArraySearching.binarySearch(array, 0)).isEmpty();

    array = new int[] {-2, -1, 2, 4, 6, 8, 8, 8, 10, 44, 99};
    assertThat(ArraySearching.binarySearch(array, 1)).isEmpty();
    assertThat(ArraySearching.binarySearch(array, -2)).hasValue(0);
    assertThat(ArraySearching.binarySearch(array, 99)).hasValue(10);
    assertThat(ArraySearching.binarySearch(array, 8)).hasValue(5);
  }
}
