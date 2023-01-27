package sorting;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

class BucketSortTests {

  @Property
  void test(@ForAll("doubleArrays") double[] doubles) {
    BucketSort.bucketSort(doubles);

    var sortedCopy = Arrays.copyOf(doubles, doubles.length);
    BucketSort.bucketSort(doubles);

    assertThat(sortedCopy).isEqualTo(doubles);
  }

  @Property
  void inOrderProperty(@ForAll("doubleArrays") double[] doubles) {
    BucketSort.bucketSort(doubles);

    assertThat(Arrays.stream(doubles).boxed().toList())
        .isInOrder();
  }

  @Provide
  Arbitrary<double[]> doubleArrays() {
    return Arbitraries.doubles()
        .between(0.0, true, 1.0, false)
        .array(double[].class);
  }
}
