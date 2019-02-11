package searching;

import static com.google.common.truth.Truth8.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

final class ListSearchingTests {

  @Test
  void testLinearSearch() {
    List<Integer> list = Collections.emptyList();
    assertThat(ListSearching.linearSearch(list, 0)).isEmpty();

    list = Collections.singletonList(1);
    assertThat(ListSearching.linearSearch(list, 1)).hasValue(0);
    assertThat(ListSearching.linearSearch(list, 0)).isEmpty();

    list = Arrays.asList(-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1);
    assertThat(ListSearching.linearSearch(list, 1)).isEmpty();
    assertThat(ListSearching.linearSearch(list, -2)).hasValue(0);
    assertThat(ListSearching.linearSearch(list, 99)).hasValue(9);
    assertThat(ListSearching.linearSearch(list, 8)).hasValue(2);

    // list with nulls
    list = Arrays.asList(-2, 10, null, 8, 8, null, 44, 4, 6, 2, 8, 99, -1);
    assertThat(ListSearching.linearSearch(list, 1)).isEmpty();
    assertThat(ListSearching.linearSearch(list, null)).hasValue(2);
    assertThat(ListSearching.linearSearch(list, 8)).hasValue(3);
  }
}
