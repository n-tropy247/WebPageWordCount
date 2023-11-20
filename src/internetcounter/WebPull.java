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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Pulling text data from webpage.
 *
 * @author NTropy
 * @version 11.19.2023
 */
public final class WebPull {

    /**
     * Number of data pulls before StringBuilder reset.
     */
    private static final int RES_MGMT = 1000;

    /**
     * Private constructor.
     */
    private WebPull() {
    }

    /**
     * Verifies a URL name is valid.
     *
     * @param url
     *            URL name to check
     * @return URL if valid, null otherwise
     */
    public static URL verifyURL(final String url) {
        try {
            return new URI(url).toURL();
        } catch (MalformedURLException e) {
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pull text from website and count occurrences of given word.
     *
     * @param url
     *            URL of website
     * @param word
     *            word to count
     * @return word count
     */
    public static int pullData(final URL url, final String word) {
        StringBuilder sb = new StringBuilder();
        BufferedReader in;
        int res = 0;
        int count = 0;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            while (!(sb.append(in.readLine())).toString().equals("null")) {
                count++;
                if (count > RES_MGMT) {
                    res += sb.toString().toLowerCase().split(
                            word.toLowerCase()).length;
                    count = 0;
                    sb.delete(0, sb.length());
                }
            }
            in.close();
        } catch (IOException ie) {
            System.err.println("Web data pull unexpectedly closed.");
        }
        return res - 1;
    }
}
