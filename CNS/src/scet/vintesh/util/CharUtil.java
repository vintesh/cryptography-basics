/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.util;

import java.util.ArrayList;

/**
 *
 * @author Vintesh
 */
public class CharUtil {

    /**
     * Converting the capital Characters to the small alphabates & replace non
     * alphabates by the space. TEST OK!
     */
    public static char[] convertToSmallAlphabates(char[] inputData) {
        // Converting All the characters into the small alphabates
        for (int i = 0; i < inputData.length; i++) {
            if (((int) inputData[i]) > 64
                    && ((int) inputData[i]) < 91) {
                inputData[i] = (char) (inputData[i] + 32);
            } else if (((int) inputData[i]) > 96
                    && ((int) inputData[i]) < 123) {
                // Doing Nothing
                continue;
            } else {
                inputData[i] = new Character(' ').charValue();
            }
        }
        return inputData;
    }

    /**
     * Position here is encountering like array only. Considering from 0 only.
     * TEST OK
     */
    public static char[] insertCharInCharArray(char[] inputData, int position, char fillerChar) {
        char[] newCharArray = new char[inputData.length + 1];
        for (int i = 0; i < inputData.length; i++) {
            if (i < position) {
                newCharArray[i] = inputData[i];
            } else if (i == position) {
                newCharArray[i] = fillerChar;
                newCharArray[i + 1] = inputData[i];
            } else {
                newCharArray[i + 1] = inputData[i];
            }
        }
        return newCharArray;
    }

    /**
     * Removes all space Chars from the given data. TEST OK!
     */
    public static char[] removeSpaceChar(char[] inputData) {
        ArrayList<Character> charList = new ArrayList<Character>();
        for (int i = 0; i < inputData.length; i++) {
            charList.add(new Character(inputData[i]));
        }
        while (charList.remove(new Character(' '))) {
        }
        char[] newCharArray = new char[charList.size()];
        int j = 0;
        for (int i = 0; i < newCharArray.length; i++) {
            newCharArray[j++] = charList.get(i);
        }
        return newCharArray;
    }
}
