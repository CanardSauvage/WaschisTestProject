package de.waschnick.challenge.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Qualify2 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = "/tmp/del.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        long res;

        int _arr_size = Integer.parseInt(in.nextLine());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for (int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine());
            _arr[_arr_i] = _arr_item;
        }

        res = sumOfIntegers(_arr);
        // FIXME Remove System.out
        System.out.println(res);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }

    static long sumOfIntegers(int[] arr) {
        int result = 0;
        for (Integer listOfInt : arr) {
            result += listOfInt;
        }
        return result;
    }
}
