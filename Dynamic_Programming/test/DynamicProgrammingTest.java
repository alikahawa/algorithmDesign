package sdas.algorithmDesign.Dynamic_Programming.test;

import java.util.Scanner;
// import org.junit.*;
// import org.junit.rules.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.Comparator;

import Dynamic_Programming.scr.DynamicPrograming;
import Greedy.test.Test;

/**
 * 
 */
public class DynamicProgrammingTest {
    
    @Test(timeout = 100)
  public void example1() {
    int n = 5;
    int[] nodes = { 0, 2, 1, 6, 8, 9 };
    assertEquals(17, DynamicPrograming.weight(n, nodes));
  }

  @Test(timeout = 100)
  public void example2() {
    assertEquals(8, DynamicPrograming.fibonacci(6));
  }

  @Test(timeout = 100)
  public void test05() {
    assertEquals(5, DynamicPrograming.fibonacci(5));
  }

  @Test(timeout = 100)
  public void example21() {
    int[] s = { 0, 0, 1, 3 };
    int[] f = { 0, 3, 4, 8 };
    int[] v = { 0, 3, 5, 7 };
    int[] p = { 0, -1, -1, 1 };
    int[] solution = DynamicPrograming.whatThePreviousOne(3, s, f, v);
    solution[0] = 0;
    assertArrayEquals(p, solution);
  } 

  private static class ProblemInstance {

    int n;

    int[] s;

    int[] f;

    int[] v;

    int[] p;

    ProblemInstance(int[][] jobs, int[] p) {
      this.n = jobs.length;
      this.s = new int[this.n + 1];
      this.f = new int[this.n + 1];
      this.v = new int[this.n + 1];
      for (int i = 1; i <= this.n; i++) {
        this.s[i] = jobs[i - 1][0];
        this.f[i] = jobs[i - 1][1];
        this.v[i] = jobs[i - 1][2];
      }
      this.p = p;
    }
  }

  private static ProblemInstance parseInput(String in) {
    // Reading the input through the use of a Scanner.
    Scanner sc = new Scanner(in);
    // Read the amount of jobs.
    int n = sc.nextInt();
    int[][] jobs = new int[n][3];
    // Read the data for every job.
    for (int i = 0; i < n; i++) {
      jobs[i][0] = sc.nextInt();
      jobs[i][1] = sc.nextInt();
      jobs[i][2] = sc.nextInt();
    }
    // Close the scanner.
    sc.close();
    // Sort the jobs on ascending End time order.
    Arrays.sort(jobs, Comparator.comparingInt((int[] o) -> o[1]));
    // Find the predecessor for every job. If a job j has no predecessor then p[j] = -1
    int[] p = new int[n + 1];
    Arrays.fill(p, -1);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (jobs[i][0] >= jobs[j][1]) {
          p[i + 1] = j + 1;
        }
      }
    }
    return new ProblemInstance(jobs, p);
  }

  private static void runTestWithFileChoosBetweenWork(String fileName) {
    ProblemInstance x = parseInput(WebLab.getData(fileName + ".in"));
    int expected = Integer.parseInt(WebLab.getData(fileName + ".out").trim());
    assertEquals(expected, DynamicPrograming.chooseBetweenWork(x.n, x.s, x.f, x.v, x.p));
  }

  @Test(timeout = 100)
  public void example() {
    int[] s = { 0, 0, 1, 3 };
    int[] f = { 0, 3, 4, 8 };
    int[] v = { 0, 3, 5, 7 };
    int[] p = { 0, -1, -1, 1 };
    assertEquals(10, DynamicPrograming.chooseBetweenWork(3, s, f, v, p));
  }

  private static void runTestWithFileDinnerBudget(String fileName) {
    assertEquals(WebLab.getData(fileName + ".out").trim(), 
        DynamicPrograming.runDinnerBudget(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  @Test(timeout = 100)
  public void example4() {
    runTestWithFileDinnerBudget("example4");
  }

  @Test(timeout = 100)
  public void example5() {
    int n = 5;
    int[] P = { 0, 80, 30, 30, 70, 80 };
    int[] Q = { 0, 90, 60, 60, 50, 20 };
    assertEquals(300, DynamicPrograming.fishSaleman(n, P, Q));
  }

  
  @Test(timeout = 100)
  public void example6() {
    String a = "kitten";
    String b = "sitting";
    assertEquals(3, DynamicPrograming.alignSequence(a, b));
  }

  @Test(timeout = 100)
  public void example7() {
    int n = 2;
    int m = 3;
    int[][] graph = { { 3, 5, 6 }, { 4, 2, 1 } };
    assertEquals(1, DynamicPrograming.steroidsDijkstra(n, m, graph));
  }

}
