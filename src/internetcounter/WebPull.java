package internetcounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Pulling text data from webpage.
 *
 * @author NTropy
 *
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
     * @return true if valid, false otherwise
     */
    public static URL verifyURL(final String url) {
        try {
            URL test = new URL(url);
            return test;
        } catch (MalformedURLException e) {
            return null;
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
            System.err.println(ie);
        }
        return res - 1;
    }
}
