package de.waschnick.kata;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class QuickSortTest {

  @Test
  public void sorting() throws Exception {
    int[] arr = {3, 5, 1, 4, 2};
    int[] expected = {1, 2, 3, 4, 5};
    QuickSort.sort(arr);
    assertArrayEquals(expected, arr);
  }
}