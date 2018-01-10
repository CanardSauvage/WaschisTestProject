package de.waschnick.challenge.hackerrank;

import java.util.Scanner;

public class Qualify {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        int height = Integer.valueOf(input);
        for (int line = 0; line < height; line++) {
            System.out.println(String.format("%1$" + height + "s", hashChars(line + 1)));
        }
    }

    private static String hashChars(int numberOfHashes) {
        String s = "";
        for (int i = 0; i < numberOfHashes; i++) {
            s += "#";
        }
        return s;
    }
}
