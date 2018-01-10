package de.waschnick.kata;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    sort(new int[]{7, 8, 4, 5, 6, 3, 9, 1, 2});
  }

  public static void sort(int[] arr) {
    // FIXME Remove System.out
    System.out.println("Starting with: " + Arrays.toString(arr));
    doQuicksort(arr, 0, arr.length - 1);
  }

  private static void doQuicksort(int[] arr, int from, int to) {
    int lastElement = arr[to];
    int currentPos = to;
    // FIXME Remove System.out
    System.out.println("Pivot is: " + lastElement);
    for (int i = from; i < to; i++) {
      if (lastElement < arr[i]) {
        changePositions(arr, currentPos, i);
        currentPos = i;
        print(arr);
      }
    }
  }

  private static void print(int[] arr) {
    // FIXME Remove System.out
    System.out.println(Arrays.toString(arr));
  }

  private static void changePositions(int[] arr, int one, int another) {
    int help = arr[one];
    arr[one] = arr[another];
    arr[another] = help;
  }
}
