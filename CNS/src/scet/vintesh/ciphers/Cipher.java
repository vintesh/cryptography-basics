/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.ciphers;

import scet.vintesh.util.FileHandler;
import scet.vintesh.util.FileNameList;

/**
 *
 * @author Vintesh
 */
public abstract class Cipher {

    public abstract void preProcessing();

    public abstract char[] getDecryptedData(char[] encryptedData);

    public abstract char[] getEncryptedData(char[] inputData);

    public void performCiphering(String encryptedFileName, String decrptedFileNamed) {
        preProcessing();
        try {
            // Encrypting...
            System.out.println("Reading Input File...");
            char[] inputData = FileHandler.readFile(FileNameList.inputFile);
            System.out.println("Converting to CipherText...");
            char[] encryptedData = getEncryptedData(inputData);
            System.out.println("CipherText is: EncryptedData: " + String.valueOf(encryptedData));
            FileHandler.writeFile(encryptedFileName, encryptedData);

            // Decrypting...
            System.out.println("Reading Encrypted File...");
            encryptedData = FileHandler.readFile(encryptedFileName);
            System.out.println("Converting to PlainText...");
            char[] plainData = getDecryptedData(encryptedData);
            System.out.println("CipherText is: EncryptedData: " + String.valueOf(plainData));
            FileHandler.writeFile(decrptedFileNamed, plainData);
        } catch (Exception e) {
            System.out.println("From MonoAlphabatic Cipher: " + e);
            e.printStackTrace();
        }
    }
}
