/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.executor;

import java.math.BigInteger;
import scet.vintesh.rsa.Entity;
import scet.vintesh.rsa.RSAPerfomer;

/**
 *
 * @author Vintesh
 */
public class RSAMainEntryPoint {

    public static void main(String[] args) {
        Entity entity = new Entity();
        System.out.println("M: " + 12543);
        BigInteger C = RSAPerfomer.performEncryption(entity.getPrivateKey(), new BigInteger("12543"));
        System.out.println("C: " + C);
        BigInteger M = RSAPerfomer.performDecryption(entity.getPublicKey(), C);
        System.out.println("M: " + M);
    }
}
