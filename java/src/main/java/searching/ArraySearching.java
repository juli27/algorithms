/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package searching;

import java.util.OptionalInt;

/**
 * Implementations of various searching algorithms on an integer array.
 * <p>
 * All methods in this class may throw a {@link NullPointerException} if null is passed in any
 * parameter unless noted otherwise.
 */
public final class ArraySearching {

  /**
   * Searches for the first occurrence of {@code value} in the given array using linear search.
   *
   * @param array the array to search through
   * @param value the value to find
   * @return The index of the first occurrence of value or empty if not found
   */
  public static OptionalInt linearSearch(int[] array, int value) {
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == value) {
        return OptionalInt.of(i);
      }
    }

    return OptionalInt.empty();
  }

  /**
   * Searches for the value in the given sorted array using binary search. The behaviour is
   * undefined if array is not sorted.
   *
   * @param array the sorted array to search through
   * @param value the value to find
   * @return The index of value or empty if not found
   */
  public static OptionalInt binarySearch(int[] array, int value) {
    int low = 0;
    int high = array.length - 1;
    while (low <= high) {
      final int mid = (low + high) / 2;
      final int elem = array[mid];
      final int cmp = Integer.compare(elem, value);

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
