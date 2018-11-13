/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import java.util.OptionalInt;

public class ArraySearching {

  public OptionalInt linearSearch(int[] array, int value) {
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == value) {
        return OptionalInt.of(i);
      }
    }

    return OptionalInt.empty();
  }
}
