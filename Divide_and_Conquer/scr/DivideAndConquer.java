package Divide_and_Conquer.scr;

public class DivideAndConquer {
    
/**
   * Recursively searches for the element.
   * Returns true if element can be found, else false.
   * Is done in a depth first manner
   * 
   * @param tree
   *     - tree that you need to look in.
   * @param element
   *     - the element that you are looking for.
   * @return true if found, else false.
   */
  public boolean search(BinaryTree tree, int element) {
     return tree == null ? false : tree.getKey() == element || search(tree.getLeft(), element) || search(tree.getRight(), element);
    }


 /**
   * Takes an array and sorts it in an ascending order.
   *
   * @param arr
   *     - the array that needs to be sorted.
   */
  public void sort(int[] arr) {
    partition(arr, new int[arr.length], 0, arr.length-1);
  }
  
  public void partition(int[] arr, int[] temp, int low, int high){
    if(low >= high){
      return;
    }
    int mid = (low + high) / 2;
    partition(arr, temp, low, mid);
    partition(arr, temp, mid + 1, high);
    mergeHalves(arr, temp, low, high);
  }
  
  public void mergeHalves(int[] arr, int[] temp, int low, int high){
    int leftEnd = (low+high)/2, rightStart = leftEnd+1, size = high-low+1;
    int i = low, k = low, j = rightStart;
    while(i <= leftEnd && j <= high){
      if(arr[i] <= arr[j]){
        temp[k] = arr[i++];
      }else{
        temp[k] = arr[j++];
      }
      k++;
    }
    while(i <= leftEnd){
      temp[k++] = arr[i++];
    }
    while(j <= high){
      temp[k++] = arr[j++];
    }
    System.arraycopy(temp, low, arr, low, size);
  }

  /**
   * Description
    Implement the findMin method, 
    this method should use divide and conquer to find the value x for which the 
    difference between two discrete functions is minimal.

    Take for example the linear equation f(x)=8x−240
    and g(x)=6x−156, these two lines will intersect each other in the point (42,96)
    , so the result of this algorithm should be 42.

    Take note that we are using discrete quadratic functions
   */
  /**
   * Finds the x coordinate with the smallest distance between two linear equations, by recursively evaluating the median of the range and that integer + 1.
   * Depending on the value, a new evaluation is made with a smaller range to find the x coordinate with the smallest distance.
   * @param e1 the first equation to evaluate
   * @param e2 the second equation to evaluate
   * @param low the lower boundary of the range
   * @param high the upper boundary of the range
   * @return the x coordinate with the minimum difference between e1 and e2
   */
  public static long findMin(Equation e1, Equation e2, long low, long high) {
    if(low == high){
      return low;
    }
    long mid = (high+low)/2;
    if(Math.abs(e1.evaluate(mid) - e2.evaluate(mid)) <= Math.abs(e1.evaluate(mid+1) - e2.evaluate(mid+1))){
      return findMin(e1, e2, low, mid);
    }
    return findMin(e1, e2, mid+1, high);
  }

  
}
