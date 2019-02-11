package sorting;

import java.util.List;
import java.util.Objects;

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
}
