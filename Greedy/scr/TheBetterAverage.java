package Greedy.scr;

import java.io.*;
import java.util.*;

/**
 * In this exercise, you will receive a list of n

    elements.
    Your task is to sort them and return the median value.

    Note that if the list has an even length,
    the median value might be the average of the two middle elements in the array,
    as in the example below.

    You are free to choose any sorting algorithm that you like,
    but the input can be as large as one million numbers,
    so the running time of your algorithm should be O(nlogn)

    .
    We recommend choosing Quicksort or Merge Sort.

    In the upcoming exercises (level 1 and up),
    using the Java standard library for sorting will not be a problem.
    However, the purpose of this exercise is to refresh your knowledge.
    This means that actually implementing a sorting algorithm
    will likely be a better refresher than blindly using Arrays.sort().
    Example input:

    4 2 1 3

    Example output:

    2.5      
 */

public class TheBetterAverage {

  public static double solve(int n, double[] list) {
    Arrays.sort(list);
    if(list.length % 2 == 0){
        return (list[n/2] + list[n/2 -1]) / 2;
    }
    return list[n/2];
  }
}