package sorting;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

final class ListSortingTests {
  @Test
  void testInsertionSort() {
    assertThrows(NullPointerException.class, () -> ListSorting.insertionSort(null));

    // empty list
    assertDoesNotThrow(() -> ListSorting.insertionSort(List.<Integer>of()));

    // one element
    assertDoesNotThrow(() -> ListSorting.insertionSort(new ArrayList<Integer>()));

    // already sorted
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    ListSorting.insertionSort(list);
    assertThat(list).isOrdered();

    // worst case
    list = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    ListSorting.insertionSort(list);
    assertThat(list).isOrdered();

    list = Arrays.asList(-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1);
    ListSorting.insertionSort(list);
    assertThat(list).isOrdered();
  }

  @Test
  void testSelectionSort() {
    assertThrows(NullPointerException.class, () -> ListSorting.selectionSort(null));

    // empty list
    assertDoesNotThrow(() -> ListSorting.selectionSort(List.<Integer>of()));

    // one element
    assertDoesNotThrow(() -> ListSorting.selectionSort(new ArrayList<Integer>()));

    // already sorted
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    ListSorting.selectionSort(list);
    assertThat(list).isOrdered();

    // worst case
    list = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
    ListSorting.selectionSort(list);
    assertThat(list).isOrdered();

    list = Arrays.asList(-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1);
    ListSorting.selectionSort(list);
    assertThat(list).isOrdered();
  }
}
