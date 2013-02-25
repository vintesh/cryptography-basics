/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.ciphers;

/**
 *
 * @author me12co18
 */
public class CaesarCipher extends Cipher {

    public static final int key = 3;

    // A = 65 , Z = 90, a = 97, z = 122, 0 = 48 , 9 = 57
    public char[] getEncryptedData(char[] plainText) {
        char[] cipherText = new char[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            int cipherInt = (int) plainText[i];
            if ((cipherInt >= 65 && cipherInt <= 90)
                    || (cipherInt >= 97 && cipherInt <= 122)
                    || (cipherInt >= 48 && cipherInt <= 57)) {
                cipherInt = (int) plainText[i] + key;
                if (cipherInt > 57 && cipherInt < 65) {
                    cipherInt -= 10;
                } else if (cipherInt > 90 && cipherInt < 97) {
                    cipherInt -= 26;
                } else if (cipherInt > 122) {
                    cipherInt -= 26;
                }

                cipherText[i] = (char) cipherInt;
            } else {
                cipherText[i] = plainText[i];
            }
        }
        return cipherText;
    }

    // A = 65 , Z = 90, a = 97, z = 122, 0 = 48 , 9 = 57
    public char[] getDecryptedData(char[] cipherData) {
        char[] plainData = new char[cipherData.length];
        for (int i = 0; i < cipherData.length; i++) {
            int cipherInt = (int) cipherData[i];
            if ((cipherInt >= 65 && cipherInt <= 90)
                    || (cipherInt >= 97 && cipherInt <= 122)
                    || (cipherInt >= 48 && cipherInt <= 57)) {
                cipherInt = (int) cipherData[i] - key;
                if (cipherInt < 48) {
                    cipherInt += 10;
                } else if (cipherInt < 65 && cipherInt > 57) {
                    cipherInt += 26;
                } else if (cipherInt < 97 && cipherInt > 90) {
                    cipherInt += 26;
                }
                plainData[i] = (char) cipherInt;
            } else {
                plainData[i] = cipherData[i];
            }
        }
        return plainData;
    }

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "===================================================== Caesar Cipher ===================================================================";
    }
}
