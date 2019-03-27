package sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class IntArraySortingTests {

  @Test
  void testInsertionSort() {
    assertThrows(NullPointerException.class, () -> IntArraySorting.insertionSort(null));

    // empty array
    assertDoesNotThrow(() -> IntArraySorting.insertionSort(new int[]{}));

    // one element
    assertDoesNotThrow(() -> IntArraySorting.insertionSort(new int[]{0}));

    // already sorted
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    IntArraySorting.insertionSort(array);
    assertThat(array).asList().isOrdered();

    // worst case
    array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    IntArraySorting.insertionSort(array);
    assertThat(array).asList().isOrdered();

    array = new int[]{-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1};
    IntArraySorting.insertionSort(array);
    assertThat(array).asList().isOrdered();
  }

  @Test
  void testSelectionSort() {
    assertThrows(NullPointerException.class, () -> IntArraySorting.selectionSort(null));

    // empty array
    assertDoesNotThrow(() -> IntArraySorting.selectionSort(new int[]{}));

    // one element
    assertDoesNotThrow(() -> IntArraySorting.selectionSort(new int[]{0}));

    // already sorted
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    IntArraySorting.selectionSort(array);
    assertThat(array).asList().isOrdered();

    // worst case
    array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    IntArraySorting.selectionSort(array);
    assertThat(array).asList().isOrdered();

    array = new int[]{-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1};
    IntArraySorting.selectionSort(array);
    assertThat(array).asList().isOrdered();
  }

  @Test
  void testMergeSort() {
    assertThrows(NullPointerException.class, () -> IntArraySorting.mergeSort(null));

    // empty array
    assertDoesNotThrow(() -> IntArraySorting.mergeSort(new int[]{}));

    // one element
    assertDoesNotThrow(() -> IntArraySorting.mergeSort(new int[]{0}));

    // already sorted
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    IntArraySorting.mergeSort(array);
    assertThat(array).asList().isOrdered();

    // worst case
    array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    IntArraySorting.mergeSort(array);
    assertThat(array).asList().isOrdered();

    array = new int[]{-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1};
    IntArraySorting.mergeSort(array);
    assertThat(array).asList().isOrdered();
  }
}
