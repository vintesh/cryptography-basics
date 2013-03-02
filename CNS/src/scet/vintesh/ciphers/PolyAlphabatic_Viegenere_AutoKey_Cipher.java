/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.ciphers;

import java.util.Arrays;
import scet.vintesh.util.CharUtil;

/**
 *
 * @author Vintesh
 */
public class PolyAlphabatic_Viegenere_AutoKey_Cipher extends Cipher {

    private char[] key = "vintesh".toCharArray();
    private boolean isAutoKey = false;

    /**
     * @param isAutoKey - Specify TRUE if you want the output as AUTOKEY Cipher
     * & FALSE if you want the output as VIGENERE Cipher - Default - it is FALSE
     */
    public PolyAlphabatic_Viegenere_AutoKey_Cipher(boolean isAutoKey) {
        this.isAutoKey = isAutoKey;
    }

    @Override
    public char[] getEncryptedData(char[] inputData) {
        inputData = CharUtil.removeExceptAlphabates(inputData);

        if (isAutoKey) {
            char[] newKey = new char[inputData.length];
            if (key.length < inputData.length) {
                System.arraycopy(key, 0, newKey, 0, key.length);
                System.arraycopy(inputData, 0, newKey, key.length, inputData.length - key.length);
                key = newKey;
            }
            System.out.println("The KEY Array: " + Arrays.toString(key));
        }

        char[] encryptedData = new char[inputData.length];
        for (int i = 0, keyI = 0; i < inputData.length; i++, keyI++) {
            if (!isAutoKey) {
                keyI %= key.length;
            }
            int encryptedCharIndex = CharUtil.getIndex(inputData[i]) + CharUtil.getIndex(key[keyI]) % 26;
            encryptedData[i] = CharUtil.indexToChar(encryptedCharIndex);
        }
        return encryptedData;
    }

    @Override
    public char[] getDecryptedData(char[] encryptedData) {
        char[] plainTExtData = new char[encryptedData.length];
        for (int i = 0, keyI = 0; i < encryptedData.length; i++, keyI++) {
            if (!isAutoKey) {
                keyI %= key.length;
            }
            int encryptedCharIndex = CharUtil.getIndex(encryptedData[i]) - CharUtil.getIndex(key[keyI]) % 26;
            encryptedCharIndex = (encryptedCharIndex < 0) ? encryptedCharIndex + 26 : encryptedCharIndex % 26;
            plainTExtData[i] = CharUtil.indexToChar(encryptedCharIndex);
        }
        return plainTExtData;
    }

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String cipherName;
        if (isAutoKey) {
            cipherName = "AUTOKEY Cipher";
        } else {
            cipherName = "VEGENERE Ciphter";
        }
        return "===================================================== PolyAlphabatic " + cipherName + " ===================================================================";
    }

    public static void main(String[] args) {
        new PolyAlphabatic_Viegenere_AutoKey_Cipher(true).getEncryptedData("BHURO_LAVJO_JDU".toCharArray());
    }
}
