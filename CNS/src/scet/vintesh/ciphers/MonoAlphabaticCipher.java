/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.ciphers;

import scet.vintesh.util.CharacterMap;

/**
 *
 * @author me12co18
 */
public class MonoAlphabaticCipher extends Cipher {

    // Necessary for Creating the random KEY
    private CharacterMap map = new CharacterMap();

    public char[] getEncryptedData(char[] inputData) {
        char[] encryptedData = new char[inputData.length];
        int i = 0;
        for (char c : inputData) {
            encryptedData[i++] = CharacterMap.getEncryptedChar(c);
        }
        return encryptedData;
    }

    public char[] getDecryptedData(char[] encryptedData) {
        char[] decryptedData = new char[encryptedData.length];
        int i = 0;
        for (char c : encryptedData) {
            decryptedData[i++] = CharacterMap.getDecryptedChar(c);
        }
        return decryptedData;
    }

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
        System.out.println("Character Map Key Used is :\n" + map.toString());
    }

    @Override
    public String toString() {
        return "===================================================== MonoAlphabatic Cipher ===================================================================";
    }
}
