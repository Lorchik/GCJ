
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());
    private static int nbTC;
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append("Case #" + tc + ": ");
            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static int[] a = new int[1010];
    private static int[] b = new int[1010];
    private static int[] lvl = new int[1010];
    private static int nblvl2 = 0;
    private static int nbs = 0;
    private static int N;

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder result = new StringBuilder();
        N = readInt(reader);
        for (int i = 0; i < N; ++i) {
            a[i] = readInt(reader);
            b[i] = readInt(reader);
        }
        nblvl2 = 0;
        nbs = 0;
        Arrays.fill(lvl, 0);
        boolean moveb = true;
        boolean movea = true;
        int r = 0;
        whilen:
        while (movea || moveb) {
            do {
                moveb = false;
                for (int i = 0; i < N; ++i) {
                    if (nbs >= b[i]) {
                        if (lvl[i] < 2) {
                            nbs += (2 - lvl[i]);
                            ++nblvl2;
                            moveb = true;
                            ++r;
                            lvl[i] = 2;
                        }
                    }
                }
            } while (moveb);
            movea = false;
            int posmax = -1;
            int maxb = -1;
            fori:
            for (int i = 0; i < N; ++i) {
                if ((nbs >= a[i]) && (lvl[i] == 0)) {
                    if (b[i] > maxb) {
                        maxb = b[i];
                        posmax = i;
                    }
                }
            }
            if (posmax != -1) {
                ++r;
                ++nbs;
                movea = true;
                lvl[posmax] = 1;

            }
        }

        if (nblvl2 != N) {
            formatter.format("Too Bad");
        } else {
            formatter.format("%d", r);
        }

//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        result.append(formatter.out());
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
        boolean positive = true;
        char currentChar = (char) reader.read();

        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        if (currentChar == (char) -1) {
            throw new IOException("end of stream");
        }
        if (currentChar == '-') {
            positive = false;
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return result;
        } else {
            return -result;
        }
    }

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
