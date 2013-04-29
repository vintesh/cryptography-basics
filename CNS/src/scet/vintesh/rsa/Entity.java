/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.rsa;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Vintesh
 */
public class Entity {

    private BigInteger p;
    private BigInteger q;
    private BigInteger encryptionExponent;
    private BigInteger decryptionExponent;
    private BigInteger rsaModulo;
    private BigInteger eulerTotianFunction;

    public Entity() {
        while (true) {
            p = BigInteger.probablePrime(10, new Random());
            if (p.isProbablePrime(10)) {
                break;
            }
        }
        while (true) {
            q = BigInteger.probablePrime(10, new Random());
            if (q.isProbablePrime(10)) {
                break;
            }
        }

        rsaModulo = p.multiply(q);
        eulerTotianFunction = p.subtract(new BigInteger("1")).multiply(
                q.subtract(new BigInteger("1")));

        encryptionExponent = calculateEncryptionExponent();
        decryptionExponent = calculateDecryptionExponent();

        System.out.println("Entity Created: " + this);
    }

    public KeyPair getPrivateKey() {
        return new KeyPair(decryptionExponent, rsaModulo);
    }

    public KeyPair getPublicKey() {
        return new KeyPair(encryptionExponent, rsaModulo);
    }

    @Override
    public String toString() {
        return "p: " + p.toString()
                + " q: " + q.toString()
                + " e: " + encryptionExponent
                + " d: " + decryptionExponent
                + " N: " + rsaModulo.toString()
                + " Φ(n): " + eulerTotianFunction.toString();
    }

    /**
     * Calculte Decryption Exponent By formula, --------------------------------
     * ----- 1 + k.Φ(n) --------------------------------------------------------
     * - d = ========== --------------------------------------------------------
     * --------- e -------------------------------------------------------------
     *
     * @return decryptionExponent
     */
    private BigInteger calculateDecryptionExponent() {
        for (BigInteger i = new BigInteger("2"); i.compareTo(rsaModulo) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger numenator = i.multiply(eulerTotianFunction).add(BigInteger.ONE);
            if (numenator.mod(encryptionExponent).compareTo(BigInteger.ZERO) == 0) {
                return numenator.divide(encryptionExponent);
            }
        }
        throw new IllegalStateException("No value found for the Decryption Exponent.");
    }

    /**
     * Calculate Encryption Exponent such that GCD(e, Φ(n)) == 1
     *
     * @return encryptionExponent
     */
    private BigInteger calculateEncryptionExponent() {
        for (BigInteger i = new BigInteger("2"); i.compareTo(eulerTotianFunction) < 0; i = i.add(BigInteger.ONE)) {
            if (i.gcd(eulerTotianFunction).intValue() == 1) {
                return i;
            }
        }
        throw new IllegalStateException("No value found for the Encryption Exponent.");
    }

    /**
     * Tester
     *
     * @param args
     */
    public static void main(String[] args) {
        new Entity();
        System.out.println("" + new BigInteger("5").divide(new BigInteger("2")));
    }
}
