package Divide_and_Conquer.test;

import Divide_and_Conquer.scr.BinaryTree;
import Divide_and_Conquer.scr.DivideAndConquer;
import Greedy.test.Test;

public class TestDivideAndConquer {
  
    @Test(timeout = 200)
  public void example() {
    DivideAndConquer s = new DivideAndConquer();
    BinaryTree tree = new BinaryTree(42, new BinaryTree(1337), new BinaryTree(39));
    assertTrue(s.search(tree, 42));
    assertFalse(s.search(tree, 100));
  }

  @Test(timeout = 100)
  public void testSort() {
    int[] input = { 4, 2, 5, 1, 3 };
    new DivideAndConquer().sort(input);
    assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, input);
  }

  
}
