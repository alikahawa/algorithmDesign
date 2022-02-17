package Divide_and_Conquer.test;

import Divide_and_Conquer.scr.SortingAndCounting;

public class UTest {
    
    /**
     * 
     */
    @Test(timeout = 100)
  public void countInversions() {
    int[] input = { 2, 1, 0, 8 };
    assertEquals(3, SortingAndCounting.countInversions(input));
  }
}
