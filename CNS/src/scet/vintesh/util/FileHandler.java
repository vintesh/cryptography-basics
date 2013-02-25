/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author me12co18
 */
public class FileHandler {

    private static char[] bufferContent = new char[5000];

    public static char[] readFile(String fileName) throws IOException {
        FileReader fr = new FileReader(new File(fileName));
        int inputContentSize = fr.read(bufferContent);
        fr.close();
        char[] inputBufferContent = new char[inputContentSize];
        System.arraycopy(bufferContent, 0, inputBufferContent, 0, inputContentSize);
        System.out.println("Content Read: " + String.valueOf(inputBufferContent));
        return inputBufferContent;
    }

    public static void writeFile(String fileName, char[] data) throws IOException {
        FileWriter fw = new FileWriter(new File(fileName));
        fw.write(data);
        fw.close();
    }
}
