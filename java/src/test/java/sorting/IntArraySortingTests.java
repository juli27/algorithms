package sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

final class IntArraySortingTests {

  private void testSort(Consumer<int[]> sortingFunction) {
    assertThrows(NullPointerException.class, () -> sortingFunction.accept(null));

    // empty array
    assertDoesNotThrow(() -> sortingFunction.accept(new int[]{}));

    // one element
    assertDoesNotThrow(() -> sortingFunction.accept(new int[]{0}));

    // already sorted
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    sortingFunction.accept(array);
    assertThat(array).asList().isOrdered();

    // worst case
    array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    sortingFunction.accept(array);
    assertThat(array).asList().isOrdered();

    array = new int[]{-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1};
    sortingFunction.accept(array);
    assertThat(array).asList().isOrdered();
  }

  @Test
  void testInsertionSort() {
    testSort(IntArraySorting::insertionSort);
  }

  @Test
  void testSelectionSort() {
    testSort(IntArraySorting::selectionSort);
  }

  @Test
  void testMergeSort() {
    testSort(IntArraySorting::mergeSort);
  }
}
