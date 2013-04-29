/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.rsa;

import java.math.BigInteger;

/**
 *
 * @author Vintesh
 */
public class RSAPerfomer {

    public static BigInteger performEncryption(KeyPair key, BigInteger M) {
        return M.modPow(key.getEncrypterKey(), key.getN());
    }

    public static BigInteger performDecryption(KeyPair key, BigInteger C) {
        return C.modPow(key.getEncrypterKey(), key.getN());
    }
}
