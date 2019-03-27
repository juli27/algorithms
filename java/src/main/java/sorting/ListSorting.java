package sorting;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implementations of various sorting algorithms on a generic list containing elements with a
 * natural ordering.
 */
public final class ListSorting {

  private ListSorting() {
  }

  /**
   * Sorts the given list in place according to natural ordering of its elements using insertion
   * sort. Insertion sort is stable.
   *
   * @param list the list to sort
   * @param <T>  Comparable type of elements in the list
   * @throws NullPointerException if list is null
   */
  public static <T extends Comparable<T>> void insertionSort(List<T> list) {
    Objects.requireNonNull(list, "can't sort null");

    for (int i = 1; i < list.size(); ++i) {
      T key = list.get(i);

      int j = i - 1;
      while (j >= 0 && list.get(j).compareTo(key) > 0) {
        list.set(j + 1, list.get(j));
        --j;
      }

      list.set(j + 1, key);
    }
  }

  /**
   * Sorts the given list in place according to natural ordering of its elements using insertion
   * sort. Insertion sort is not stable.
   *
   * @param list the list to sort
   * @param <T>  Comparable type of elements in the list
   * @throws NullPointerException if list is null
   */
  public static <T extends Comparable<T>> void selectionSort(List<T> list) {
    Objects.requireNonNull(list, "can't sort null");

    // list[0..i-1] contains the i-1 smallest elements of list[0..len] in sorted order
    for (int i = 0; i < list.size() - 1; ++i) {
      int minIndex = i;
      T min = list.get(i);

      // find the minimum of the remaining unsorted list
      for (int j = i + 1; j < list.size(); ++j) {
        T elem = list.get(j);
        if (elem.compareTo(min) < 0) {
          minIndex = j;
          min = elem;
        }
      }

      Collections.swap(list, i, minIndex);
    }

    // list[0..len-2] contains he len-1 smallest elements of the original sequence in sorted order
    // list[len-1] is the largest element -> list[0..len-1] is sorted
  }
}
