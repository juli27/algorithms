import org.junit.jupiter.api.Test;
import sorting.IntArraySorting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

final class IntArraySortingTests {

  @Test
  void testInsertionSort() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    int size = random.nextInt(10, 50);
    int[] array = new int[size];

    for (int i = 0; i < array.length; ++i) {
      array[i] = random.nextInt(10000);
    }

    System.out.println("unsorted: " + Arrays.toString(array));

    IntArraySorting.insertionSort(array);

    System.out.println("sorted  : " + Arrays.toString(array));
  }
}
