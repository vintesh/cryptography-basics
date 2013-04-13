/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.ciphers;

import scet.vintesh.util.FileHandler;
import scet.vintesh.util.FileNameList;

/**
 *
 * @author Vintesh
 */
public abstract class Cipher {

    /**
     * Preprocessing steps to be done in order to do the encryption.
     */
    public abstract void preProcessing();

    /**
     * Simply Encrypt the Stream of data.
     *
     * @param encryptedData - Characters Stream to be encrypted
     * @return - The Encrypted Data.
     */
    public abstract char[] getDecryptedData(char[] encryptedData);

    /**
     * Simply Decrypt the Stream of data.
     *
     * @param inputData - Characters Stream to be decrypted
     * @return - The Decrypted Data.
     */
    public abstract char[] getEncryptedData(char[] inputData);

    /**
     * Common Method which performs the reading & writing of the files.
     *
     * @param encryptedFileName - FileName where encrypted Data will store.
     * @param decrptedFileNamed - FileName where decrypted Data will store.
     */
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
            System.out.println("PlainText is: DecryptedData: " + String.valueOf(plainData));
            FileHandler.writeFile(decrptedFileNamed, plainData);
        } catch (Exception e) {
            System.out.println("From MonoAlphabatic Cipher: " + e);
            e.printStackTrace();
        }
    }
}
