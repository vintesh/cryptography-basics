/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Vintesh
 */
public class CharacterMap {

    private char keyChar;
    private char valueChar;
    private static final ArrayList<CharacterMap> list = new ArrayList();

    public CharacterMap() {
    }

    public CharacterMap(Character keyChar, Character valueChar) {
        this.keyChar = keyChar.charValue();
        this.valueChar = valueChar.charValue();
    }

    static {
        char[] characterList = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        ArrayList<Character> keyCharList = new ArrayList<Character>();
        for (int i = 0; i < characterList.length; i++) {
            keyCharList.add(Character.valueOf(characterList[i]));
        }
        ArrayList<Character> valueCharList = new ArrayList<Character>(keyCharList);
        Collections.shuffle(keyCharList);
        Collections.shuffle(valueCharList);

        for (int i = 0; i < characterList.length; i++) {
            list.add(new CharacterMap(keyCharList.get(i), valueCharList.get(i)));
        }
    }

    public static char getEncryptedChar(char keyChar) {
        for (CharacterMap characterMap : list) {
            if (String.valueOf(characterMap.getKeyChar()).compareToIgnoreCase(String.valueOf(keyChar)) == 0) {
                return characterMap.getValueChar();
            }
        }
        return '-';
    }

    public static char getDecryptedChar(char valueChar) {
        for (CharacterMap characterMap : list) {
            if (String.valueOf(characterMap.getValueChar()).compareToIgnoreCase(String.valueOf(valueChar)) == 0) {
                return characterMap.getKeyChar();
            }
        }
        return '-';
    }

    public char getKeyChar() {
        return keyChar;
    }

    public char getValueChar() {
        return valueChar;
    }

    public void setKeyChar(char keyChar) {
        this.keyChar = keyChar;
    }

    public void setValueChar(char valueChar) {
        this.valueChar = valueChar;
    }

    @Override
    public String toString() {
        StringBuffer keyString = new StringBuffer("Key:   ");
        StringBuffer valString = new StringBuffer("Value:  ");
        for (CharacterMap characterMap : list) {
            keyString.append(characterMap.getKeyChar());
            valString.append(characterMap.getValueChar());
        }
        return keyString.append("\n").append(valString).toString();
    }
}
