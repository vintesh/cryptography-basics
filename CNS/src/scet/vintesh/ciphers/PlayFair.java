/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.ciphers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import scet.vintesh.util.CharUtil;

/**
 *
 * @author Vintesh
 */
public class PlayFair extends Cipher {

    private Character[] key = {'v', 'i', 'n', 't', 'e', 's', 'h'};
    private Character[][] playFairMatrix = new Character[5][5];
    private ArrayList<Character> alphabateSet = new ArrayList<Character>();
    private char fillerChar = 'x';

    @Override
    public char[] getEncryptedData(char[] inputData) {
        inputData = CharUtil.convertToSmallAlphabates(inputData);
//        System.out.println("Removed Small Alphabates: " + Arrays.toString(inputData));
        inputData = CharUtil.removeSpaceChar(inputData);
        System.out.println("Text Removing Space: " + Arrays.toString(inputData));
        for (int i = 1; i < inputData.length; i++) {
            if (inputData[i] == inputData[i - 1]
                    || (inputData[i] == 'i' && inputData[i - 1] == 'j')
                    || (inputData[i] == 'j' && inputData[i - 1] == 'i')) {
                inputData = CharUtil.insertCharInCharArray(inputData, i, fillerChar);
            }
        }
        System.out.println("Removing the Reduendent Chars: " + Arrays.toString(inputData));
        if ((inputData.length % 2) == 1) {
            inputData = CharUtil.insertCharInCharArray(inputData, inputData.length - 1, fillerChar);
        }

        System.out.println("Modified InputText: " + Arrays.toString(inputData));

        char[] returnData = new char[inputData.length];
        for (int i = 0; i < inputData.length; i += 2) {
            char[] encryptedChar = new char[2];
            encryptedChar = findEncryptedCharFromMatrix(inputData[i], inputData[i + 1]);
            returnData[i] = encryptedChar[0];
            returnData[i + 1] = encryptedChar[1];
        }
        return returnData;
    }

    @Override
    public char[] getDecryptedData(char[] encryptedData) {
        char[] returnData = new char[encryptedData.length];

        encryptedData = CharUtil.convertToSmallAlphabates(encryptedData);
        for (int i = 0; i < encryptedData.length; i += 2) {
            char[] encryptedChar = new char[2];
            encryptedChar = findDecryptedCharFromMatrix(encryptedData[i], encryptedData[i + 1]);
            returnData[i] = encryptedChar[0];
            returnData[i + 1] = encryptedChar[1];
        }

        System.out.println("PlainText: " + Arrays.toString(returnData));
        return returnData;
    }

    private void createPlayFairMatrix() {
        List<Character> keyCharsAsList = Arrays.asList(key);
        alphabateSet.removeAll(keyCharsAsList);

        if (keyCharsAsList.contains(new Character('i')) || keyCharsAsList.contains(new Character('j'))) {
            alphabateSet.remove(new Character('i'));
            alphabateSet.remove(new Character('j'));
        } else {
            alphabateSet.remove(new Character('j'));
        }

        int k = 0, l = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (k < key.length) {
                    playFairMatrix[i][j] = key[k++];
                } else {
                    playFairMatrix[i][j] = alphabateSet.get(l++).charValue();
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Matrix Created: " + Arrays.toString(playFairMatrix[i]));
        }
    }

    private void initAlphabateSet() {
        int j = 97;
        for (int i = 0; i < 26; i++) {
            alphabateSet.add(i, new Character((char) (j + i)));
        }
        System.out.println("Generated AphabateSet: " + Arrays.toString(alphabateSet.toArray()));
    }

    private char[] findEncryptedCharFromMatrix(char char1, char char2) {
        char[] returnData = new char[2];
        int[] rowPosition = new int[2];
        int[] colPosition = new int[2];

        // Finding Row & Column Position from the PlayFairMatrix
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (char1 == playFairMatrix[i][j].charValue()) {
                    rowPosition[0] = i;
                    colPosition[0] = j;
                } else if (char2 == playFairMatrix[i][j].charValue()) {
                    rowPosition[1] = i;
                    colPosition[1] = j;
                }
            }
        }

        // If Both Characters are in the same Row
        if (rowPosition[0] == rowPosition[1]) {
            returnData[0] = playFairMatrix[rowPosition[0]][(colPosition[0] + 1) % 5];
            returnData[1] = playFairMatrix[rowPosition[0]][(colPosition[1] + 1) % 5];
        } // If Both Characters are in the same Column
        else if (colPosition[0] == colPosition[1]) {
            returnData[0] = playFairMatrix[(rowPosition[0] + 1) % 5][colPosition[0]];
            returnData[1] = playFairMatrix[(rowPosition[1] + 1) % 5][colPosition[1]];
        } // If Both Forming Rect by their position
        else {
            returnData[0] = playFairMatrix[rowPosition[0]][colPosition[1]];
            returnData[1] = playFairMatrix[rowPosition[1]][colPosition[0]];
        }
        return returnData;
    }

    private char[] findDecryptedCharFromMatrix(char char1, char char2) {
        char[] returnData = new char[2];
        int[] rowPosition = new int[2];
        int[] colPosition = new int[2];

        // Finding Row & Column Position from the PlayFairMatrix
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (char1 == playFairMatrix[i][j].charValue()) {
                    rowPosition[0] = i;
                    colPosition[0] = j;
                } else if (char2 == playFairMatrix[i][j].charValue()) {
                    rowPosition[1] = i;
                    colPosition[1] = j;
                }
            }
        }

        // If Both Characters are in the same Row
        if (rowPosition[0] == rowPosition[1]) {
            returnData[0] = playFairMatrix[rowPosition[0]][Math.abs((colPosition[0] + 4) % 5)];
            returnData[1] = playFairMatrix[rowPosition[0]][Math.abs((colPosition[1] + 4) % 5)];
        } // If Both Characters are in the same Column
        else if (colPosition[0] == colPosition[1]) {
            returnData[0] = playFairMatrix[Math.abs((rowPosition[0] + 4) % 5)][colPosition[0]];
            returnData[1] = playFairMatrix[Math.abs((rowPosition[1] + 4) % 5)][colPosition[1]];
        } // If Both Forming Rect by their position
        else {
            returnData[0] = playFairMatrix[rowPosition[0]][colPosition[1]];
            returnData[1] = playFairMatrix[rowPosition[1]][colPosition[0]];
        }
        return returnData;
    }

    @Override
    public void preProcessing() {
        System.out.println(this.toString());
        initAlphabateSet();
        createPlayFairMatrix();
    }

    @Override
    public String toString() {
        return "===================================================== PlayFair Cipher ===================================================================";
    }
}
