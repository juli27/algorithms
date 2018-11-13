/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package sorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArraySortingTests {

  public static void main(String[] args) {
    System.out.println("insertion sort");
    testInsertionSort();
    System.out.println();

    System.out.println("selection sort");
    testSelectionSort();
    System.out.println();

    System.out.println("merge sort");
    testMergeSort();
    System.out.println();
  }

  private static void testInsertionSort() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    int size = random.nextInt(10, 50);
    int[] array = new int[size];

    for (int i = 0; i < array.length; ++i) {
      array[i] = random.nextInt(10000);
    }

    System.out.println("unsorted: " + Arrays.toString(array));

    ArraySorting.insertionSort(array);

    System.out.println("sorted  : " + Arrays.toString(array));
  }

  private static void testSelectionSort() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    int size = random.nextInt(10, 50);
    int[] array = new int[size];

    for (int i = 0; i < array.length; ++i) {
      array[i] = random.nextInt(10000);
    }

    System.out.println("unsorted: " + Arrays.toString(array));

    ArraySorting.selectionSort(array);

    System.out.println("sorted  : " + Arrays.toString(array));
  }

  private static void testMergeSort() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    int size = random.nextInt(10, 50);
    int[] array = new int[size];

    for (int i = 0; i < array.length; ++i) {
      array[i] = random.nextInt(10000);
    }

    System.out.println("unsorted: " + Arrays.toString(array));

    ArraySorting.mergeSort(array);

    System.out.println("sorted  : " + Arrays.toString(array));
  }
}
