/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package sorting;

import java.util.Arrays;

import static java.lang.System.arraycopy;

/**
 * Implementations of various sorting algorithms on an integer array.
 * <p>
 * All methods in this class may throw a {@link NullPointerException} if null is passed in any
 * parameter unless noted otherwise.
 */
public final class IntArraySorting {

  /**
   * Sorts the given array in place using insertion sort. Insertion sort is stable.
   *
   * @param array the array of integers to be sorted
   */
  public static void insertionSort(int[] array) {
    // array[0..i-1] is sorted, array[i] to be inserted
    for (int i = 1; i < array.length; ++i) {
      final int key = array[i];

      // move all elements in the sorted sequence greater than key up by one
      int j = i - 1;
      while (j >= 0 && array[j] > key) {
        array[j + 1] = array[j];
        --j;
      }

      // insert the key at its destination in the sorted sequence
      array[j + 1] = key;
    }
  }

  /**
   * Sorts the given array in place using selection sort. Selection sort is not stable.
   *
   * @param array the array of integers to be sorted
   */
  public static void selectionSort(int[] array) {
    // array[0..i-1] contains the i-1 smallest elements of array[0..len] in sorted order
    for (int i = 0; i < array.length - 1; ++i) {
      int minIndex = i;

      // find the minimum of the remaining unsorted array
      for (int j = i + 1; j < array.length; ++j) {
        if (array[j] < array[minIndex]) {
          minIndex = j;
        }
      }

      // swap
      int tmp = array[i];
      array[i] = array[minIndex];
      array[minIndex] = tmp;
    }

    // array[0..len-2] contains the len-1 smallest elements of the original sequence in sorted order
    // array[len-1] is the largest element -> array[0..len-1] is sorted
  }

  /**
   * Sorts the given array using merge sort. Merge sort is stable. This implementation of merge sort
   * is not in-place and requires O(n) space.
   *
   * @param array the array of integers to be sorted
   * @throws NullPointerException if array is null
   */
  public static void mergeSort(int[] array) {
    mergeSort(array, 0, array.length);
  }

  private static void mergeSort(int[] array, int start, int end) {
    final int rightStart = (start + end) / 2;
    if (start == rightStart) {
      return;
    }

    mergeSort(array, start, rightStart);
    mergeSort(array, rightStart, end);
    merge(array, start, rightStart, end);
  }

  /**
   * Merges two sorted subsequences within the given array into one sorted sequence.
   *
   * @param array      array to merge into
   * @param leftStart  first element of the left subsequence
   * @param rightStart first element of the right subsequence
   * @param rightEnd   last element of the right subsequence, exclusive (might lie outside the
   *                   array)
   */
  private static void merge(int[] array, int leftStart, int rightStart, int rightEnd) {
    final int[] left = Arrays.copyOfRange(array, leftStart, rightStart);
    final int[] right = Arrays.copyOfRange(array, rightStart, rightEnd);
    final int leftSize = left.length;
    final int rightSize = right.length;

    int leftIndex = 0;
    int rightIndex = 0;

    int i = leftStart;
    while (leftIndex < leftSize && rightIndex < rightSize) {
      final int leftElem = left[leftIndex];
      final int rightElem = right[rightIndex];
      if (leftElem <= rightElem) {
        array[i++] = leftElem;
        leftIndex++;
      } else {
        array[i++] = rightElem;
        rightIndex++;
      }
    }

    arraycopy(left, leftIndex, array, i, leftSize - leftIndex);
    arraycopy(right, rightIndex, array, i, rightSize - rightIndex);
  }
}
