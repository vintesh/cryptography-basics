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
public class RailFenceCipher extends Cipher {

    private int depth = 3;

    @Override
    public char[] getDecryptedData(char[] encryptedData) {
        int noOfCols = encryptedData.length / depth;
        if (encryptedData.length % depth != 0) {
            noOfCols++;
        }

        char[][] convertedJaggedArray = new char[depth][noOfCols];
        int k = 0;
        for (int j = 0; j < depth; j++) {
            for (int i = 0; i < noOfCols; i++) {
                if (k >= encryptedData.length) {
                    break;
                }
                convertedJaggedArray[j][i] = encryptedData[k++];
            }
        }

        char[] plainText = new char[encryptedData.length];
        k = 0;
        for (int i = 0; i < noOfCols; i++) {
            for (int j = 0; j < depth; j++) {
                if (k >= encryptedData.length) {
                    break;
                }
                plainText[k++] = convertedJaggedArray[j][i];
            }
        }

        return new String(plainText).toCharArray();
    }

    @Override
    public char[] getEncryptedData(char[] inputData) {
        inputData = CharUtil.removeExceptAlphabates(inputData);

        int noOfCols = (int) (inputData.length / depth);
        if (inputData.length % depth != 0) {
            noOfCols++;
        }
        char[][] convertedJaggedArray = new char[depth][noOfCols];
        int k = 0;
        for (int j = 0; j < noOfCols; j++) {
            for (int i = 0; i < depth; i++) {
                if (k >= inputData.length) {
                    break;
                }
                convertedJaggedArray[i][j] = inputData[k++];
            }
        }

        String st = "";
        for (int i = 0; i < depth; i++) {
            st += new String(convertedJaggedArray[i]);
        }
        return st.toCharArray();
    }

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "===================================================== Rail Fence Cipher ===================================================================";
    }

    public static void main(String[] args) {
        System.out.println("CipherText: " + Arrays.toString(new RailFenceCipher().getDecryptedData(new RailFenceCipher().getEncryptedData("vintesh patelXY".toCharArray()))));
    }
}
