package sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntArraySortingTests {

  @ParameterizedTest
  @MethodSource("sortingFunctionProvider")
  void testSortNull(Consumer<int[]> sortingFunction) {
    assertThrows(NullPointerException.class, () -> sortingFunction.accept(null));
  }

  @ParameterizedTest()
  @MethodSource("sortingFunctionProvider")
  void testSort(Consumer<int[]> sortingFunction) {
    assertDoesNotThrow(() -> sortingFunction.accept(new int[] {}));

    assertDoesNotThrow(() -> sortingFunction.accept(new int[] {0}));

    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    sortingFunction.accept(array);
    assertThat(array).asList()
                     .isInOrder();

    array = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    sortingFunction.accept(array);
    assertThat(array).asList()
                     .isInOrder();

    array = new int[] {-2, 10, 8, 8, 44, 4, 6, 2, 8, 99, -1};
    sortingFunction.accept(array);
    assertThat(array).asList()
                     .isInOrder();
  }

  static Stream<Consumer<int[]>> sortingFunctionProvider() {
    return Stream.of(IntArraySorting::insertionSort,
                     IntArraySorting::selectionSort,
                     IntArraySorting::mergeSort);
  }
}
