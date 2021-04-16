/*
 * Copyright (C) 2021 Ryan Castelli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package internetcounter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Count occurrences of "block" in a website.
 *
 * @author NTropy
 * @version 4.16.21
 */
public final class WordCounter {

    /**
     * Private constructor.
     */
    private WordCounter() {
    }

    /**
     * Main method.
     *
     * @param args command-line arguments; unused
     * @throws MalformedURLException URL separately verified
     */
    public static void main(final String[] args) throws MalformedURLException {
        try (Scanner usrInpt = new Scanner(System.in)) {
            String curInpt = "";
            while (!curInpt.equalsIgnoreCase("stop")) {
                System.out.print("\nEnter the word you wish to count: ");
                String word = usrInpt.nextLine();
                System.out.print("\nEnter a website URL from which to count \""
                        + word + "\" (enter \"stop\" to stop): ");
                curInpt = usrInpt.nextLine();
                if (!curInpt.equalsIgnoreCase("stop")) {
                    URL url = WebPull.verifyURL(curInpt);
                    if (url != null) {
                        int count = WebPull.pullData(url, word);
                        System.out.println("Occurences of \"" + word + "\" in "
                                + curInpt + ": " + count);
                    } else {
                        System.out.print("\nInvalid URL. Try again.");
                    }
                }
            }
        }
    }
}
