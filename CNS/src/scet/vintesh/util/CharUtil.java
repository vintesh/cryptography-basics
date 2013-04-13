/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
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

    /**
     * Considering A/a = 00, B/b = 01 & so on upto Z/z = 25
     *
     * @param c - Of which you want to find the Index
     * @return - The index euivalent in int.
     */
    public static int getIndex(char c) {
        if (Character.isUpperCase(c)) {
            return c - 65;
        } else if (Character.isLowerCase(c)) {
            return c - 97;
        } else {
            throw new IllegalStateException("The Character is not alphabetic");
        }
    }

    public static char indexToChar(int eSum) {
        eSum %= 26;
        return (char) (eSum + 65);
    }

    /**
     * Returns the Array which contains the Alphabet Letters only.
     *
     * @param inputData - Array from which letters to be filtered.
     * @return - Array with only Alphabet Letters.
     */
    public static char[] removeExceptAlphabates(char[] inputData) {
        ArrayList<Character> listToReturn = new ArrayList<Character>();
        for (int i = 0; i < inputData.length; i++) {
            if ((((int) inputData[i]) > 64 && ((int) inputData[i]) < 91)
                    || (((int) inputData[i]) > 96 && ((int) inputData[i]) < 123)) {
                listToReturn.add(new Character(inputData[i]));
            }
        }
        char returnArray[] = new char[listToReturn.size()];
        int i = 0;
        for (Character character : listToReturn) {
            returnArray[i++] = character.charValue();
        }
        return returnArray;
    }
}
