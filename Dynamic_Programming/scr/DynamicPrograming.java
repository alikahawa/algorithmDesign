package Dynamic_Programming.scr;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class DynamicPrograming {
    
    /**
     * Let G be an undirected graph with n nodes. 
     * A subset of the nodes is called an independent set if no
    two of them are joined by an edge. 
    Finding an independent set of maximum size (or weight) is generally difficult,
    but it can be done efficiently in special cases.

    Call a graph G
    a path if its nodes can be written as v1,v2,…,vn, with an edge between vi and vj if
    and only if |i−j|=1. Each node vi has a positive integer weight wi
     
     * Note that entry node[0] should be avoided, as nodes are labelled node[1] through node[n].
     * 
     * Given the following problem instance, with a path of nodes with weights:

        2 1 6 8 9

        We expect 17 as our output.
        See example1
     * @param n
     * @param nodes
     * @return
     */
  public static int weight(int n, int[] nodes) {
    int[] mem = new int[n + 1];
    mem[0] = 0;
    mem[1] = nodes[1];
    for (int i = 2; i <= n; i++) {
      mem[i] = Math.max(mem[i-1], nodes[i] + mem[i-2]);
    }
    return mem[n];
  }


  /**
   * A Fibonacci sequence is a series of numbers for which it holds that, with the exception of the first number, 
   * each subsequent number equals the sum of its two predecessors. 
   * An example of the first ten numbers in the sequence would be {0, 1, 1, 2, 3, 5, 8, 13, 21, 34} (note that we start counting at zero).

    A common method of finding Fibonacci numbers is by 
    utilizing a recursive method which tries to find then numbers by recursively finding the first two values, 
    and then summing them while backtracking. This does, however, 
    require the recalculation of the full series during each step.

    An implementation has been given which attempts to use memoization, 
    or the storing of results as they are calculated.
    Check example2 text files
   */

  /**
   * 
   * @param n
   * @return Returns the n'th Fibonacci number
   */
   public static int fibonacci(int n) {
    // The array in which you must implement your memoization.
    int[] fibonacci = new int[n + 1];
    fibonacci[0] = 0;
    fibonacci[1] = 1;
    // After that, iterate through all fibonacci numbers from index 2 up to n.
    for (int i = 2; i <= n; i++) {
      fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
    }
    // Returning the obtained fibonacci value at index n.
    return fibonacci[n];
  }

  /**
   * A weighted interval schedule is a schedule of jobs where each of the jobs has a weight attached. 
   * Every job ji for 0<i≤n has a start time si, an end time fi, and has a weight of vi. 
   * The goal is to find a schedule where all jobs are compatible, 
   * there are no two jobs that overlap, while maximizing the weight of the schedule.
   * Given the following jobs:

    s f v
    0 3 3
    1 4 5
    3 8 7

    We expect [??, -1, -1, 1] as our output, where ?? is ignored.
   * @param n
   * @param s
   * @param f
   * @param v
   * @return
   */
  public static int[] whatThePreviousOne(int n, int[] s, int[] f, int[] v) {
    Pair[] start = new Pair[n];
    Pair[] finish = new Pair[n];
    
    for(int i = 1; i <= n; i++){
      start[i-1] = new Pair(i, s[i]);
      finish[i-1] = new Pair(i, f[i]);
    }
    Arrays.sort(finish);
    Arrays.sort(start);
    // In this example we don't care about the value, we are only finding out the predecessors
    int[] res = new int[n+1];
    res[0] = -1;
    int j = 0;
    for(int i = 0; i < n; i++){
      if(finish[j].time > start[i].time){
        res[start[i].index] = -1;
      }else{
        for(; j < n-1; j++){
          if(finish[j+1].time > start[i].time) {
            break;
          }
        }
        res[start[i].index] = finish[j].index;
      }
    }
    return res;
  }

  /**
   * A weighted interval schedule is a schedule of jobs where each of the jobs has a weight attached. 
   * Every job ji for 0<i≤n has a start time si, an end time fi, and has a weight of vi. 
   * The goal is to find a schedule where all jobs are compatible, 
   * there are no two jobs that overlap, 
   * while maximizing the weight of the schedule.
   * Given the following schedule eight jobs:

    s f v
    0 3 3
    1 4 5
    3 8 7

    We expect 10 as our output, as we will execute jobs 1 and 3 for total value of 10, 
    rather than just job two with a total value of 5.
   * Come up with an iterative dynamic programming solution to the weighted interval scheduling problem.
   * NB: You may assume the jobs are sorted by ascending finishing time.
   * See example3
   * @param n the number of jobs
   * @param s the start times of the jobs for jobs 1 through n. Note that s[0] should be ignored.
   * @param f the finish times of the jobs for jobs 1 through n. Note that f[0] should be ignored.
   * @param v the values of the jobs for jobs 1 through n. Note that v[0] should be ignored.
   * @param p the predecessors of the jobs for jobs 1 through n. Note that p[0] should be ignored and that -1 represents there being no predecessor.
   * @return the weight of the maximum weight schedule.
   */
  public static int chooseBetweenWork(int n, int[] s, int[] f, int[] v, int[] p) {
    int[] mem = new int[n + 1];
    mem[0] = 0;
    
    for(int i =1; i <= n; i++){
      int c = p[i] > 0 ? mem[p[i]] : 0;
      if(c + v[i] > mem[i-1]){
        mem[i] = c + v[i];
      }else{
        mem[i] = mem[i-1];
      }
    }
    // Returning the obtained obtained value at index n.
    return mem[n];
  }

  /**
   * You are given a knapsack that you must fill optimally. 
   * Given a knapsack of weight W, and n items each with a weight and value, 
   * find a subset of items in the knapsack such that the value of the combined items is maximized. 
   * There are no precedence requirements or subset requirements between the subsets themselves, 
   * meaning you may add or remove any item as long as the final value is maximized. 
   * Implement an algorithm that finds this maximized value, 
   * utilizing dynamic programming, in at most O(nW) time.
   * 
   * @param in
   * @return
   */
  public static String runDinnerBudget(InputStream in) {
    return new DynamicPrograming().dinnerBudget(in);
  }

  /**
   * The first line contains the maximum knapsack weight W as a positive integer and the number of items n. 
   * Then, for each item n, 
   * there is a single line containing two positive integers separated by a space. 
   * These numbers represent the weight of that item, and its value, respectively. 
   * Your algorithm must output the maximum possible value for that instance. 
   * An example input is given below.

    10 3
    8 25    
    3 4    
    5 9    

    The output for this example is ‘25’, since the only other combination yields ‘4 + 9’, which is less than ‘25’.
    See example4
   * @param in
   * @return
   */
  public String dinnerBudget(InputStream in) {
    Scanner sc = new Scanner(in); 
    int w = sc.nextInt();
    int n = sc.nextInt();
    
    ArrayList<Item> items = new ArrayList<>();
    for(int i = 0; i < n; i++){
      items.add(new Item(sc.nextInt(), sc.nextInt()));
    }
    sc.close();
    int[][] mem = new int[n+1][w+1];
    // Three cases, just tgry to cover them all!
    for(int i = 1; i<=n; i++){
      for(int j = 1; j <= w; j++){
        if(i == 0 || j ==0 ){
          mem[i][j] = 0;
        }else if (items.get(i-1).w > j){
          mem[i][j] = mem[i-1][j];
        }else{
          mem[i][j] = Math.max(mem[i-1][j], items.get(i-1).v + mem[i-1][j-items.get(i-1).w]);
        }
      }
    }
    return Integer.toString(mem[n][w]);
  }

  /**
   * A fish salesman has determined there are two lucrative spots P and Q where he can set up his stand.
   * He has (perfectly) predicted the profits to be had during a period of n days on each spot, 
   * call them pi and qi for 1≤i≤n. 
   * The salesman obviously wants to maximize his profit, 
   * but he cannot be in both spots on one day, 
   * so he will have to decide where he is going to be on each day.
   * Breaking up his stand and setting it up again in the other spot is a difficult job, 
   * however, which takes a whole day, on which there will be no profits.
   * As an example consider the following instance:

    P  Q
    80 90
    30 60
    30 60
    70 50
    80 20

    We expect 300 as output here, 
    representing that we set up shop at location Q on days 1 and 2 and on location P on days 4 and 5 
    (with day 3 being the switch day).
    Check example5
   * @param n the number of days
   * @param P the profits that can be made on day 1 through n on location P are stored in P[1] through P[n].
   * @param Q the profits that can be made on day 1 through n on location Q are stored in Q[1] through Q[n].
   * @return the maximum obtainable profit.
   */
  public static int fishSaleman(int n, int[] P, int[] Q) {
    /*
    //
    // Come up with an iterative dynamic programming solution to the salesman problem.
    // TODO mem[0][0] = ...; // Base case
    // TODO mem[1][0] = ...;
    // TODO mem[0][1] = ...;
    // TODO mem[1][1] = ...;
    */
    int[][] mem = new int[2][n + 1];
    // TODO return 0
    mem[0][0]= 0;
    mem[1][0]= 0;
    
    mem[0][1] = P[1];
    mem[1][1] = Q[1];
    for(int i = 2; i <= n; i++){
      mem[0][i] = Math.max(mem[1][i-1], mem[0][i-1] + P[i]);
      mem[1][i] = Math.max(mem[0][i-1], mem[1][i-1] + Q[i]);
    }
    return Math.max(mem[0][n], mem[1][n]);
  }

  /**
   * The sequence alignment problem aims to find the optimal alignment of 
   * two strings such that the mismatch penalty is minimized. 
   * For each mismatched character, you are allowed to add a penalty of ‘1’.
   * An example input is given below.

    kitten
    sitting  

    This example should return ‘3’.
   * @param firstString
   * @param secondString
   * @return
   */
  public static int alignSequence(String firstString, String secondString) {
    int n = firstString.length();
    int m = secondString.length();
    int[][] align = new int[n+1][m+1];
    for(int i = 0; i <= n; i++){
      align[i][0] = i;
    }
    for(int i = 0; i <= m; i++){
      align[0][i] = i;
    }
    for(int i = 1; i <= n; i++){
      for(int j = 1; j <= m; j++){
        int identical = firstString.charAt(i-1) == secondString.charAt(j-1) ? 0 : 1;
        int left = align[i][j-1]+1;
        int above = align[i-1][j]+1;
        int diagonal = identical + align[i-1][j-1];
        align[i][j] = Math.min(above, Math.min(left, diagonal));
      }
    }
    return align[n][m];
  }

  /**
   * You are playing a game and you are faced with a puzzle. 
   * In this puzzle, you have to ﬁnd your way past a group of towers. 
   * Each tower has a different height, 
   * and there is no way to get past it other than by using a ladder to climb to the top. 
   * (From the top of the tower, you can then jump down or climb up to the next tower, or the ground if you are at the end.)

    The towers are arranged in a rectangular n x m grid and you can only move from a tower to the one north, 
    west, south or east of you. 
    If you want to go up any height different N, you will need a ladder of length N as well. 
    If you want to go down, you can simply jump without the use of a ladder. 
    A ladder’s cost increases linearly with its length and you need to get from the northwest tower to the southeast one. 
    Of course, you want the cheapest ladder possible, which means finding the minimum length of ladder needed. 
    You may assume you are dropped on top of the northwest tower at the start 
    of the puzzle and want to get to the southeast tower at the end of the puzzle.
   * Given the following problem instance, of size 2 x 3:

    3 5 6
    4 2 1

    we expect 1 as our output.
   * @param n
   * @param m
   * @param graph
   * @return
   */
  public static int steroidsDijkstra(int n, int m, int[][] graph) {
    int[][] mem = new int[n][m];
    {
      for (int[] row : mem) Arrays.fill(row, Integer.MAX_VALUE);
      // Base case
      mem[0][0] = 0;
      // Iterate over the entire grid n*m times, since it can take that long for the result to propagate to the end.
      for (int k = 0; k < n * m; k++) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (i == 0 && j == 0)
              continue;
            // Try all 4 cases: left, right, up and down.
            // Do an out-of-bounds check before indexing the array.
            // Check whether we found a shorter path to our current node, by taking the min of the current value
            // And the neighboring nodes. Keep in mind we might have to jump up from this node
            // and therefore we compare the graph values.
            mem[i][j] = j - 1 < 0 ? mem[i][j] : Integer.max(mem[i][j - 1], graph[i][j] - graph[i][j - 1]);
            mem[i][j] = i - 1 < 0 ? mem[i][j] : Integer.min(mem[i][j], Integer.max(mem[i - 1][j], graph[i][j] - graph[i - 1][j]));
            mem[i][j] = j + 1 >= m ? mem[i][j] : Integer.min(mem[i][j], Integer.max(mem[i][j + 1], graph[i][j] - graph[i][j + 1]));
            mem[i][j] = i + 1 >= n ? mem[i][j] : Integer.min(mem[i][j], Integer.max(mem[i + 1][j], graph[i][j] - graph[i + 1][j]));
          }
        }
      }
      return mem[n - 1][m - 1];
    }


}

/**
 * 
 */
class Pair implements Comparable<Pair> {
    int index, time;
    
    public Pair(int index, int time){
      this.index = index;
      this.time = time;
    }
    
    public int compareTo(Pair other){
      return Integer.compare(this.time, other.time);
    }
  }

/**
 * 
 */
class Item{
    int w,v;
    public Item(int w, int v){
      this.w = w;
      this.v = v;
    }
  }