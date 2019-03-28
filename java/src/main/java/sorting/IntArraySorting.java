/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package sorting;

import java.util.Objects;

/**
 * Implementations of various sorting algorithms on an integer array.
 */
public final class IntArraySorting {

  private IntArraySorting() {
  }

  /**
   * Sorts the given array in place using insertion sort. Insertion sort is stable.
   *
   * @param array the array of integers to be sorted
   * @throws NullPointerException if array is null
   */
  public static void insertionSort(int[] array) {
    Objects.requireNonNull(array, "can't sort array null");

    // array[0..i-1] is sorted, array[i] to be inserted
    for (int i = 1; i < array.length; ++i) {
      int key = array[i];

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
   * @throws NullPointerException if array is null
   */
  public static void selectionSort(int[] array) {
    Objects.requireNonNull(array, "array can't be null");

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
   * Sorts the given array using merge sort. Merge sort is stable.
   * This implementation of merge sort is not in-place and requires O(n) space.
   *
   * @param array the array of integers to be sorted
   * @throws NullPointerException if array is null
   */
  public static void mergeSort(int[] array) {
    Objects.requireNonNull(array, "array can't be null");

    mergeSort(array, 0, array.length - 1);
  }

  private static void mergeSort(int[] array, int start, int end) {
    if (start >= end) {
      return;
    }

    int leftEnd = (start + end) / 2;

    mergeSort(array, start, leftEnd);
    mergeSort(array, leftEnd + 1, end);
    merge(array, start, leftEnd, end);
  }

  /**
   * Merges two sorted subsequences within the given array into one sorted sequence.
   *
   * @param array     the array
   * @param leftStart the leftmost element of the first subsequence within the array
   * @param leftEnd   the last element of the left subsequence within the array
   * @param rightEnd  the last element of the right subsequence within the array
   */
  private static void merge(int[] array, int leftStart, int leftEnd, int rightEnd) {
    int leftSize = leftEnd - leftStart + 1;
    int rightSize = rightEnd - leftEnd;
    int[] left = new int[leftSize];
    int[] right = new int[rightSize];

    System.arraycopy(array, leftStart, left, 0, leftSize);
    System.arraycopy(array, leftEnd + 1, right, 0, rightSize);

    int leftIndex = 0;
    int rightIndex = 0;

    int i = leftStart;
    while (leftIndex < leftSize && rightIndex < rightSize) {
      if (left[leftIndex] <= right[rightIndex]) {
        array[i++] = left[leftIndex++];
      } else {
        array[i++] = right[rightIndex++];
      }
    }

    System.arraycopy(left, leftIndex, array, i, leftSize - leftIndex);
    System.arraycopy(right, rightIndex, array, i, rightSize - rightIndex);
  }
}
