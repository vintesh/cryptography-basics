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
public class KeyPair {

    private BigInteger encrypterKey;
    private BigInteger n;

    public KeyPair(BigInteger encrypterKey, BigInteger n) {
        this.encrypterKey = encrypterKey;
        this.n = n;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getEncrypterKey() {
        return encrypterKey;
    }
}
