package de.waschnick.ideas;

import java.io.BufferedReader;
import java.io.FileReader;

public class PrintCountCharsInFile {

    public static void main(String[] args) throws Exception {
        countChars("/Users/swaschni/Dropbox/Projekte/TestProject/src/main/java/A.java");

    }

    private static int countChars(String fileName) throws Exception {
        FileReader fReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fReader);
        String cursor; //
        String content = "";
        int lines = 0;
        int words = 0;
        int chars = 0;
        while ((cursor = reader.readLine()) != null) {
            // count lines
            lines += 1;
            content += cursor;

            // count words
            String[] _words = cursor.split(" ");
            for (String w : _words) {
                words++;
            }

        }
        chars = content.length();

        System.out.println("File " + fileName + " has ");
        System.out.println(chars + " Characters,");
        System.out.println(words + " words and " + lines + " lines.");

        return chars;
    }
}
