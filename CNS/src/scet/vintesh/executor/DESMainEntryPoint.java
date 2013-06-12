/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.executor;

import scet.vintesh.des.SimplifiedDES;

/**
 *
 * @author Vintesh
 */
public class DESMainEntryPoint {

    public static void main(String[] args) {

        // Applying Key Value - 10 bits here
        int K = Integer.parseInt("0010100001", 2);
        SimplifiedDES A = new SimplifiedDES(K);

        // Applying Message Bits - 8 bits here
        int m = Integer.parseInt("10101010", 2);
        System.out.print("Key K1: ");
        SimplifiedDES.printb(A.K1, 8);

        System.out.print("\nKey K2: ");
        SimplifiedDES.printb(A.K2, 8);

        m = A.encrypt(m);
        System.out.print("\nEncrypted Message: ");
        SimplifiedDES.printb(m, 8);

        m = A.decrypt(m);
        System.out.print("\nDecrypted Message: ");
        SimplifiedDES.printb(m, 8);
        System.out.println("");
    }
}
