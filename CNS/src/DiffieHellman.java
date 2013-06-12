/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */


/**
 *
 * @author Vintesh
 */
import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter P:");
        BigInteger p = s.nextBigInteger();
        if (!p.isProbablePrime(1)) {
            System.out.println(p + " is not prime");
            return;
        } else {
            System.out.println("No. is prime");
        }
        System.out.print("List of Generators:");
        generators(p);
        System.out.println("Choose any one Generator...");

        BigInteger g = s.nextBigInteger();

        System.out.println("Enter two private keys..");
        BigInteger x = s.nextBigInteger();
        BigInteger y = s.nextBigInteger();

        BigInteger r1 = g.modPow(x, p);
        BigInteger r2 = g.modPow(y, p);

        System.out.println("R1: " + r1 + " R2: " + r2);

        BigInteger secret1 = r1.modPow(y, p);
        BigInteger secret2 = r2.modPow(x, p);

        if (secret1.equals(secret2)) {
            System.out.println("Shared Secret key is: " + secret1);
        }
    }

    static void generators(BigInteger p) {
        int count = 0;

        for (int g = 2; g <= p.intValue() - 2; g++) {
            int counter = 0, m = 1, i;
            boolean b[] = new boolean[p.intValue()];

            for (i = 0; i < p.intValue(); i++) {
                b[i] = false;
            }

            try {
                for (i = 1; i <= p.intValue() - 1; i++) {
                    m = (m * g) % p.intValue();
                    if (!b[m]) {
                        b[m] = true;
                        counter++;
                        if (counter == p.intValue() - 1) {
                            count++;
                        }
                    } else {
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // System.out.println("\nNo. of generators are " + count);
        }
    }
}
