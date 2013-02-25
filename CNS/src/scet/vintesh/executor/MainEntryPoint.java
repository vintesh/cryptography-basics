/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.executor;

import java.io.IOException;
import scet.vintesh.ciphers.CaesarCipher;
import scet.vintesh.ciphers.MonoAlphabaticCipher;
import scet.vintesh.ciphers.PlayFair;
import scet.vintesh.util.FileNameList;

/**
 *
 * @author me12co18
 */
public class MainEntryPoint {

    public static void main(String[] args) throws IOException {
        new CaesarCipher().performCiphering(FileNameList.caesarCipherE, FileNameList.caesarCipherD);
        new MonoAlphabaticCipher().performCiphering(FileNameList.monoAlphabaticCipherE, FileNameList.monoAlphabaticCipherD);
        new PlayFair().performCiphering(FileNameList.playFairE, FileNameList.playFairD);
    }
}
