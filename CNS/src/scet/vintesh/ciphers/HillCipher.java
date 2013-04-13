/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.ciphers;

import java.util.Arrays;
import scet.vintesh.util.CharUtil;

/**
 *
 * @author Vintesh
 */
public class HillCipher extends Cipher {

    /**
     * Change Matrix & keyLength accordingly. KeyLength = M, Where KeyMatrix
     * Dimention = M x M
     */
    private final int keyLength = 2;
    private final char fillerCharacter = 'x';
    private int[][] keyMatrixForEncryption = {{5, 8}, {17, 3}};
    private int[][] keyMatrixForDecryption = {{9, 2}, {1, 15}};

    @Override
    public char[] getEncryptedData(char[] inputData) {
        // Removing Space
        inputData = CharUtil.removeExceptAlphabates(inputData);

        // Allocating the "Array size which is of multiple of the keyLenth"
        char modifiedMatrix[] = new char[inputData.length + (inputData.length % keyLength)];
        System.arraycopy(inputData, 0, modifiedMatrix, 0, inputData.length);
        // Adding padding in the last places to make the "Array size which is of multiple of the keyLenth"
        for (int i = 0; i < modifiedMatrix.length - inputData.length; i++) {
            modifiedMatrix[i] = fillerCharacter;
        }

        char[] encryptedData = new char[modifiedMatrix.length];
        int encryptingDataArrayIndex = 0;

        // Taking the Chunk of the length = KEYLENGTH & apply encryption using Matrix Multiplication
        for (int i = 0; i < inputData.length; encryptingDataArrayIndex += keyLength) {
            // Making the character array of the length = KEYLENGTH
            char[] chunkPT = new char[keyLength];
            for (int j = 0; j < keyLength; j++) {
                chunkPT[j] = inputData[i++];
            }
            // Setting the CT Chunk Data in to the final Array
            System.arraycopy(encryptDataChunk(chunkPT), 0, encryptedData, encryptingDataArrayIndex, chunkPT.length);
        }

        return encryptedData;
    }

    /**
     * Multiplying the chunks with the keyMatrix & getting up the Encrypted data
     * chunk
     *
     * @param chunkPT - Chunk which need to be encrypted. - P.T. Chunk
     * @return - Encrypted data Chunk
     */
    private char[] encryptDataChunk(char[] chunkPT) {
        char[] encryptedData = new char[keyLength];
        for (int i = 0; i < keyLength; i++) {
            int eSum = 0;
            for (int j = 0; j < keyLength; j++) {
                eSum = eSum + keyMatrixForEncryption[i][j] * CharUtil.getIndex(chunkPT[j]);
            }
            encryptedData[i] = CharUtil.indexToChar(eSum);
        }
        return encryptedData;
    }

    /**
     * Multiplying the chunk with the (keyMatirx)^(-1) & getting up the Plain
     * Text
     *
     * @param chunkCT - Chunk which need to be decrypted. - C.T Chunk
     * @return - Decrypted data Chunk i.e. Plain Text
     */
    private char[] decryptDataChunk(char[] chunkCT) {
        char[] plainTextData = new char[keyLength];
        for (int i = 0; i < keyLength; i++) {
            int eSum = 0;
            for (int j = 0; j < keyLength; j++) {
                eSum = eSum + keyMatrixForDecryption[i][j] * CharUtil.getIndex(chunkCT[j]);
            }
            plainTextData[i] = CharUtil.indexToChar(eSum);
        }
        return plainTextData;
    }

    @Override
    public char[] getDecryptedData(char[] encryptedData) {
        char[] plainTextData = new char[encryptedData.length];
        int encryptingDataArrayIndex = 0;

        // Taking the Chunk of the length = KEYLENGTH & apply encryption using Matrix Multiplication
        for (int i = 0; i < encryptedData.length; encryptingDataArrayIndex += keyLength) {
            // Making the character array of the length = KEYLENGTH
            char[] chunkCT = new char[keyLength];
            for (int j = 0; j < keyLength; j++) {
                chunkCT[j] = encryptedData[i++];
            }
            // Setting the CT Chunk Data in to the final Array
            System.arraycopy(decryptDataChunk(chunkCT), 0, plainTextData, encryptingDataArrayIndex, chunkCT.length);
        }

        return plainTextData;
    }

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "===================================================== Hill Cipher ===================================================================";
    }

    public void main(String[] args) {
        char[] toEncrypt = {'n', 't'};
        System.out.println("Encrypted Data: " + Arrays.toString(new HillCipher().encryptDataChunk(toEncrypt)));
        System.out.println("Decrypted Data: " + Arrays.toString(new HillCipher().decryptDataChunk(new HillCipher().encryptDataChunk(toEncrypt))));
    }
}
