package searching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static searching.ListSearching.binarySearch;
import static searching.ListSearching.linearSearch;

final class ListSearchingTests {
  @Test
  void testLinearSearch() {
    assertThrows(NullPointerException.class, () -> linearSearch(null, 0));

    List<Integer> list = Collections.emptyList();
    assertThat(linearSearch(list, 0)).isEmpty();

    list = Collections.singletonList(1);
    assertThat(linearSearch(list, 1)).hasValue(0);
    assertThat(linearSearch(list, 0)).isEmpty();

    list = Arrays.asList(-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1);
    assertThat(linearSearch(list, 1)).isEmpty();
    assertThat(linearSearch(list, -2)).hasValue(0);
    assertThat(linearSearch(list, 99)).hasValue(9);
    assertThat(linearSearch(list, 8)).hasValue(2);

    // list with nulls
    list = Arrays.asList(-2, 10, null, 8, 8, null, 44, 4, 6, 2, 8, 99, -1);
    assertThat(linearSearch(list, 1)).isEmpty();
    assertThat(linearSearch(list, null)).hasValue(2);
    assertThat(linearSearch(list, 8)).hasValue(3);
  }

  @Test
  void testBinarySearch() {
    assertThrows(NullPointerException.class, () -> binarySearch(null, 0));
    assertThrows(NullPointerException.class, () -> binarySearch(List.of(0), null));

    List<Integer> list = List.of();
    assertThat(binarySearch(list, 0)).isEmpty();

    list = List.of(1);
    assertThat(binarySearch(list, 1)).hasValue(0);
    assertThat(binarySearch(list, 0)).isEmpty();

    list = List.of(-2, -1, 2, 4, 6, 8, 8, 8, 10, 44, 99);
    assertThat(binarySearch(list, 1)).isEmpty();
    assertThat(binarySearch(list, -2)).hasValue(0);
    assertThat(binarySearch(list, 99)).hasValue(10);
    assertThat(binarySearch(list, 8)).hasValue(5);
  }
}
