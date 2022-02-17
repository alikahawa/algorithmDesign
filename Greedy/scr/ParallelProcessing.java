package Greedy.scr;

import java.util.*;

/**
 * 
 */
public class ParallelProcessing {
    
    
/**
 * Create a greedy algorithm to determine a schedule that has the smallest maximum lateness. 
 * Given 2 processors and the following 5 deadlines:

 * 3, 1, 1, 1, 2

    This algorithm returns the minimized maximum lateness, in this case: 1.
   * @param n the number of jobs
   * @param m the number of processors
   * @param deadlines the deadlines of the jobs 1 through n. NB: you should ignore deadlines[0]
   * @return the minimised maximum lateness.
   */
  public static int solve(int n, int m, int[] deadlines) {
    Arrays.sort(deadlines);
    int t = 0;
    int p = 1;
    int laten = Integer.MIN_VALUE;
    for(int i = 1; i <= n; i++){
      int late = t + 1 - deadlines[i];
      if(p+1 <= m){
        p++;
      }else{
        t++;
        p = 1;
      }
      laten = Integer.max(laten, late);
    }
    return laten;
    }
/**
 * We have m processors and n jobs. Each job i in {1,…,n} has a processing time of exactly 1 hour. 
 * Furthermore, each job i has an integer deadline di in hours. 
 * The aim is to find a start time si and processor pi for each job 
 * such that no jobs are run at the same processor at the same time and such that the maximum lateness over all jobs is minimized. 
 * The lateness is the time a job finishes compared to its deadline, 
 * defined here by si + 1 - di. The objective thus is to minimize maxi{si+1−di}.
 */
}
