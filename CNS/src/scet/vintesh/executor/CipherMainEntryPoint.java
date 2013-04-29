/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.executor;

import java.io.IOException;
import scet.vintesh.ciphers.*;
import scet.vintesh.util.FileNameList;

/**
 *
 * @author Vintesh
 */
public class CipherMainEntryPoint {

    public static void main(String[] args) throws IOException {
        new CaesarCipher().performCiphering(FileNameList.caesarCipherE, FileNameList.caesarCipherD);
        new MonoAlphabaticCipher().performCiphering(FileNameList.monoAlphabaticCipherE, FileNameList.monoAlphabaticCipherD);
        new PlayFair().performCiphering(FileNameList.playFairE, FileNameList.playFairD);
        new HillCipher().performCiphering(FileNameList.hillCipherE, FileNameList.hillCipherD);
        // For VIGENERE Cipher
        new PolyAlphabatic_Viegenere_AutoKey_Cipher(false).performCiphering(FileNameList.autoKeyCipherE, FileNameList.autoKeyCipherD);
        // For AUTOKEY Cipher
        new PolyAlphabatic_Viegenere_AutoKey_Cipher(true).performCiphering(FileNameList.vegenereCipherE, FileNameList.vegenereCipherD);
        new RailFenceCipher().performCiphering(FileNameList.railFenceE, FileNameList.railFenceD);
        new TranspositionCipher().performCiphering(FileNameList.transPositionE, FileNameList.transPositionD);
    }
}
