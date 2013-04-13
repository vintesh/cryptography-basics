/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.ciphers;

import java.util.Arrays;

/**
 *
 * @author Vintesh
 */
public class TranspositionCipher extends Cipher {

    private char[] key = "435261".toCharArray();
    private char fillerChar = 'X';

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
        System.out.println("Key Used is :\n" + key);
    }

    @Override
    public char[] getDecryptedData(char[] encryptedData) {
        // Creating Matrix
        char[][] matrix = new char[(int) encryptedData.length / key.length][key.length];
        for (int k = 0, i = 1; i <= key.length; i++) {
            int indexInKeyArray = String.valueOf(key).indexOf("" + i);
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][indexInKeyArray] = encryptedData[k++];
            }
        }
        printMatrix(matrix);

        char[] plainText = new char[encryptedData.length];
        // Creating PlainText by coping row by row values
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, plainText, i * key.length, matrix[i].length);
        }

        return plainText;
    }

    @Override
    public char[] getEncryptedData(char[] inputData) {
        // Creating the matrix
        char[][] matrix = new char[(int) (inputData.length / key.length) + 1][key.length];

        // Adding filler Characters to the input data if needed 
        if (inputData.length % key.length != 0) {
            char[] afterAddingFillerChar = new char[inputData.length + (key.length - (inputData.length % key.length))];
//            System.out.println("After adding Filler Char Length - " + afterAddingFillerChar.length);
            System.arraycopy(inputData, 0, afterAddingFillerChar, 0, inputData.length);
            for (int i = 0; i < key.length - inputData.length % key.length; i++) {
                afterAddingFillerChar[inputData.length + i] = fillerChar;
            }
            inputData = afterAddingFillerChar;
        }

        for (int i = 0, j = 0; i < inputData.length; i += key.length) {
            System.arraycopy(inputData, i, matrix[j++], 0, key.length);
        }

        printMatrix(matrix);

        // Taking the columns by the key value
        char[] encryptedData = new char[matrix.length * key.length];
        int indexPointerOfEncryptedData = 0;
        for (int i = 1; i <= key.length; i++) {
            int indexInKeyArray = String.valueOf(key).indexOf("" + i);
            for (int j = 0; j < matrix.length; j++) {
                encryptedData[indexPointerOfEncryptedData++] = matrix[j][indexInKeyArray];
            }
        }
        return encryptedData;
    }

    private void printMatrix(char[][] matrix) {
        // Printing Matrix Created
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
//        System.out.println(String.valueOf(new TranspositionCipher().key).indexOf("" + 1));
        System.out.println(new TranspositionCipher().getDecryptedData(new TranspositionCipher().getEncryptedData("VinteshPatelvintesh".toCharArray())));
    }

    @Override
    public String toString() {
        return "===================================================== Row Transposition Cipher " + " ===================================================================";
    }
}
