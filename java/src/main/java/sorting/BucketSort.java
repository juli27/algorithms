package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BucketSort {

  /**
   * Sorts the provided array of doubles into ascending numerical order.
   *
   * @param doubles array of doubles in the range [0,1)
   */
  public static void bucketSort(double[] doubles) {
    List<List<Double>> buckets = new ArrayList<>(doubles.length);
    for (int i = 0; i < doubles.length; ++i) {
      buckets.add(new ArrayList<>());
    }

    for (double d : doubles) {
      int listIdx = (int) Math.floor(d * doubles.length);

      buckets.get(listIdx).add(d);
    }

    int i = 0;
    for (var bucket : buckets) {
      bucket.sort(Comparator.naturalOrder());

      for (var d : bucket) {
        doubles[i++] = d;
      }
    }
  }
}
