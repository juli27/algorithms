/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package searching;

import java.util.Objects;
import java.util.OptionalInt;

/**
 * Implementations of various searching algorithms on an integer array.
 */
public final class ArraySearching {

  /**
   * Searches for the first occurrence of value in the given array using linear search.
   *
   * @param array the array to search through
   * @param value the value to find
   * @return An optional containing the index of the first occurrence of value or empty if not found
   * @throws NullPointerException if array is null
   */
  public static OptionalInt linearSearch(int[] array, int value) {
    Objects.requireNonNull(array);

    for (int i = 0; i < array.length; ++i) {
      if (array[i] == value) {
        return OptionalInt.of(i);
      }
    }

    return OptionalInt.empty();
  }

  /**
   * Searches for the first occurrence of value in the given sorted array using binary search.
   *
   * @param array the sorted array to search through
   * @param value the value to find
   * @return An optional containing the index of the first occurrence of value or empty if not found
   * @throws NullPointerException if array is null
   */
  public static OptionalInt binarySearch(int[] array, int value) {
    Objects.requireNonNull(array);

    int low = 0;
    int high = array.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;

      if (array[mid] == value) {
        return OptionalInt.of(mid);
      } else if (value < array[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return OptionalInt.empty();
  }
}
