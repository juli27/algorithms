/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package sorting;

/**
 * "reference" implementations of various sorting algorithms on an integer array.
 */
public final class IntArraySorting {

  private IntArraySorting() {
  }

  /**
   * property: stable
   * property: in place
   * property: loop invariant: array[0..i-1] contains the elements originally in array[0..i-1],
   * but in sorted order.
   * property: can sort as it gets new elements
   *
   * @param array the array of integers to be sorted
   */
  public static void insertionSort(int[] array) {
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
   * property: stable
   * property: in place
   * property: loop invariant:
   */
  public static void selectionSort(int[] array) {
    for (int i = 0; i < array.length - 1; ++i) {
      int minIndex = i;

      // find the minimum of the remaining unsorted array
      for (int j = i + 1; j < array.length; ++j) {
        if (array[minIndex] > array[j]) {
          minIndex = j;
        }
      }

      // swap
      int tmp = array[i];
      array[i] = array[minIndex];
      array[minIndex] = tmp;
    }
  }

  public static void mergeSort(int[] array) {
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
   * @param array     the array
   * @param leftStart the leftmost element of the first sub array
   * @param leftEnd   the last element of the left sub array
   * @param rightEnd  the last element of the right sub array
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
        array[i] = left[leftIndex++];
      } else {
        array[i] = right[rightIndex++];
      }

      ++i;
    }

    // TODO: replace with System.arraycopy()?
    for (; leftIndex < leftSize; ++leftIndex) {
      array[i++] = left[leftIndex];
    }

    for (; rightIndex < rightSize; ++rightIndex) {
      array[i++] = right[rightIndex];
    }
  }
}
