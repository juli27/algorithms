package searching;

import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

/**
 * Implementations of various searching algorithms on a generic list.
 * <p>
 * All methods in this class may throw a {@link NullPointerException} if null is passed in any
 * parameter unless noted otherwise.
 */
public final class ListSearching {

  /**
   * Searches for the first occurrence of value in the given list using linear search. Comparisons
   * are as if by calling {@link Objects#equals(Object, Object)}. If value is null, searches for the
   * first null element in the list.
   *
   * @param list  the list to search through
   * @param value the value to find (can be null)
   * @return The index of the first occurrence of value or empty if not found
   */
  public static OptionalInt linearSearch(List<?> list, Object value) {
    for (int i = 0; i < list.size(); ++i) {
      if (Objects.equals(value, list.get(i))) {
        return OptionalInt.of(i);
      }
    }

    return OptionalInt.empty();
  }

  /**
   * Searches for the value in the given sorted {@link List} using binary search. The behaviour is
   * undefined if the list is not sorted.
   *
   * @param list  the list to search through
   * @param value the value to find
   * @param <T>   the type of value to which the list elements must be comparable to
   * @return The index of value or empty if not found
   */
  public static <T> OptionalInt binarySearch(List<? extends Comparable<? super T>> list, T value) {
    int low = 0;
    int high = list.size() - 1;
    while (low <= high) {
      final int mid = (low + high) / 2;
      final var elem = list.get(mid);
      final int cmp = elem.compareTo(value);

      if (cmp < 0) {
        low = mid + 1;
      } else if (cmp > 0) {
        high = mid - 1;
      } else {
        return OptionalInt.of(mid);
      }
    }

    return OptionalInt.empty();
  }
}
