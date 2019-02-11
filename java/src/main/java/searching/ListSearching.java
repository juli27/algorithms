package searching;

import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

/**
 * Implementations of various searching algorithms on a generic list.
 */
public final class ListSearching {

  /**
   * Searches for the first occurrence of value in the given list using linear search. Comparisons
   * are as if by calling {@link Objects#equals(Object, Object)}. If value is null, searches for the
   * first null element in the list.
   *
   * @param list  the list to search through
   * @param value the value to find
   * @param <T>   the type of the elements in the list
   * @return An optional containing the index of the first occurrence of value or empty if not found
   */
  public static <T> OptionalInt linearSearch(List<T> list, T value) {
    for (int i = 0; i < list.size(); ++i) {
      if (Objects.equals(value, list.get(i))) {
        return OptionalInt.of(i);
      }
    }

    return OptionalInt.empty();
  }
}
