/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FractionTests {

  public static void main(String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    try {
      for (String line = in.readLine(); line != null; line = in.readLine()) {
        if (line.isEmpty()) {
          break;
        }

        long denominator = Long.parseLong(line);
        long nominator = denominator;

        // approximate sqrt(2)
        while ((nominator * nominator) < ((denominator * denominator) << 1)) {
          ++nominator;
        }

        System.out.println((nominator - 1) + "/" + denominator + " " + nominator + "/" + denominator);
        System.out.println(1.0 * (nominator - 1) / denominator + " " + 1.0 * nominator / denominator);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
