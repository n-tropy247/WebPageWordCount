package internetcounter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Count occurrences of "block" in a website.
 *
 * @author Ryan
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
