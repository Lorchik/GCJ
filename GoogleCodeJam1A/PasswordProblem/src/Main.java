
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
        Scanner scanner = new Scanner(System.in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = scanner.nextInt();
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append("Case #"+tc+": ");
            result.append(oneTestCase(scanner));
            result.append('\n');
        }
        System.out.print(result);
    }

    private static double[] p = new double[100010];
    private static double[] x = new double[100010];
    private static StringBuilder oneTestCase(Scanner scanner)  throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder result = new StringBuilder();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        x[0] = 1;
        for (int i=1; i<=A; ++i) {
            p[i] = Double.parseDouble(scanner.next());            
            x[i] = x[i-1] * p[i];         
        }
        double r = Double.MAX_VALUE;
        for (int k=A; k>=0; --k) {
            int nbbsp = A-k;
            double e = x[k] * (B-A+1+2*nbbsp) + (1-x[k]) * (2*B-A+2+2*nbbsp);
            r = Math.min(r,e);
        }
        
        double es4 = (B+2);
        
        r = Math.min(r, es4);
        formatter.format("%.6f",r);
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
