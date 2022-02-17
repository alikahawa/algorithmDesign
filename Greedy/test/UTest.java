package Greedy.test;

import java.io.*;
import java.nio.charset.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import Greedy.scr.TheBetterAverage;
import Greedy.scr.DonutEmperium;
import Greedy.scr.GettingOutMaze;
import Greedy.scr.GettingOutTheFastest;
import Greedy.scr.OptimalWorkstationUse;
import Greedy.scr.PackingTrack;
import Greedy.scr.ParallelProcessing;
import Greedy.scr.PlanningBikeRepairs;
import Greedy.scr.RoutingTrains;
import Greedy.scr.UnionFind;
import Greedy.scr.WirelessNetwork;

public class UTest {
  
    @Test(timeout = 100)
  public void example_average() {
    int n = 4;
    double[] list = { 4, 2, 1, 3 };
    assertEquals(2.5, TheBetterAverage.solve(n, list), 1e-3);
  }

  private static void runTestWithStrings(String in, String out) {
    assertEquals(out.trim(), RoutingTrains.run(new ByteArrayInputStream(in.getBytes(StandardCharsets.UTF_8))).trim());
  }

  private static void runTestWithFile(String fileName) {
    assertEquals(WebLab.getData(fileName + ".out").trim(), RoutingTrains.run(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  @Test(timeout = 200)
  public void example() {
    runTestWithFile("example");
  }

  private static void runTestWithStrings0(String in, String out) {
    assertEquals(out.trim(), GettingOutMaze.run(new ByteArrayInputStream(in.getBytes(StandardCharsets.UTF_8))).trim());
  }
  
  private static void runTestWithFile0(String fileName) {
    assertEquals(WebLab.getData(fileName + ".out").trim(), Solution.run(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  @Test(timeout = 500)
  public void example0() {
    runTestWithFile("example");
  }

  @Test(timeout = 10000)
  public void set7() {
    runTestWithFile("dataset7");
  }

  /**
   * The next tests are for Getting ut the fastest
   */
  private static void runTestWithStrings1(String in, String out) {
    assertEquals(out.trim(), GettingOutTheFastest.run(new ByteArrayInputStream(in.getBytes(StandardCharsets.UTF_8))).trim());
  }

  private static void runTestWithFile1(String fileName) {
    assertEquals(WebLab.getData(fileName + ".out").trim(), Solution.run(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  @Test(timeout = 1000)
  public void example1() {
    runTestWithFile("example");
  }

  @Test(timeout = 10000)
  public void set71() {
    runTestWithFile("dataset7");
  }

  /**
   * Testing packing track
   * Connect with files example1.in and example1.out
  */
  @Test(timeout = 100)
  public void example2() {
    int n = 4;
    int[] weights = { 0, 41, 29, 12, 26 };
    int maxWeight = 48;
    assertEquals(3, PackingTrack.minAmountOfTrucks(n, weights, maxWeight));
  }

  /**
   * Test Planning Bike Repairs
   */
  private static void runTestWithStringsBikes(String in, String out) {
    assertEquals(out.trim(), PlanningBikeRepairs.run(new ByteArrayInputStream(in.getBytes(StandardCharsets.UTF_8))).trim());
  }

  private static void runTestWithFileBikes(String fileName) {
    assertEquals(WebLab.getData(fileName + ".out").trim(), Solution.run(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  @Test(timeout = 100)
  public void exampleBikes() {
    runTestWithFile("example");
  }

  /**
   * Test ParallelProcessing
   */
  private static void runTestWithFileParallelProcessing(String fileName) {
    Scanner sc = new Scanner(WebLab.getData(fileName + ".in"));
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] jobs = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      jobs[i] = sc.nextInt();
    }
    sc.close();
    int expected = Integer.parseInt(WebLab.getData(fileName + ".out"));
    assertEquals(expected, ParallelProcessing.solve(n, m, jobs));
  }

  @Test(timeout = 50)
  public void exampleParallelProcessing() {
    int n = 5;
    int m = 2;
    int[] deadlines = { 0, 3, 1, 1, 1, 2 };
    assertEquals(1, ParallelProcessing.solve(n, m, deadlines));
  }

  /**
   * Test OptimalWorkstationUse
   */
  @Test(timeout = 100)
  public void exampleOptimalWorkstationUse() {
    int n = 5;
    int m = 10;
    int[] start = { 0, 2, 1, 17, 3, 15 };
    int[] end = { 0, 6, 2, 7, 9, 6 };
    assertEquals(3, OptimalWorkstationUse.solve(n, m, start, end));
  }

  /**
   * Test UnionFined
   */
  @Test
  public void exampleUnionFined() {
    UnionFind uf = new UnionFind(10);
    assertEquals(9, uf.find(9));
    assertTrue(uf.union(1, 2));
    assertTrue(uf.union(2, 3));
    assertTrue(uf.union(0, 1));
    assertTrue(uf.union(3, 4));
    // Test that joining any combination will have no effect, as they are already joined
    for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) assertFalse("union(" + i + "," + j + ")", uf.union(i, j));
    // Test whether all first five entries have the same root
    for (int i = 0; i < 4; i++) assertEquals("find(" + i + ")", uf.find(i), uf.find(i + 1));
    // Test whether all last five entries have themselves as root
    for (int i = 5; i < 10; i++) assertEquals("find(" + i + ")", i, uf.find(i));
  }

  @Test
  public void rankNoUnionTest() {
    UnionFind uf = new UnionFind(10);
    // Test whether all entries have themselves as root
    for (int i = 0; i < 10; i++) assertEquals("find(" + i + ")", i, uf.find(i));
  }

  @Test
  public void findWithUnionTest() {
    UnionFind uf = new UnionFind(10);
    assertTrue(uf.union(4, 2));
    assertFalse(uf.union(2, 4));
    assertEquals(uf.find(2), uf.find(4));
  }

  @Test(timeout = 100)
  public void fastEnough() {
    int n = 1_000_000;
    UnionFind uf = new UnionFind(n);
    Random rng = new Random(1234);
    for (int i = 0; i < n / 100; i++) {
      int a = rng.nextInt(n);
      int b = rng.nextInt(n);
      uf.union(a, b);
    }
  }

  /**
   * Test WirelessNetwork
   */
  private static void runTestWithStringsWirelessNetwork(String in, String out) {
    assertEquals(out.trim(), WirelessNetwork.run(new ByteArrayInputStream(in.getBytes(StandardCharsets.UTF_8))).trim());
  }
  
  private static void runTestWithFileWirelessNetwork(String fileName) {
    assertEquals(WebLab.getData(fileName + ".out").trim(), WirelessNetwork.run(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  @Test(timeout = 100)
  public void budgetExampleWirelessNetwork() {
    runTestWithFile("budget_example");
  }

  @Test(timeout = 100)
  public void exampleWirelessNetwork() {
    runTestWithFile("example");
  }

  /**
   * Test DonutEmperium
   */
  static class Store implements Comparable<Store> {

    double x, y;

    public Store(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public Store(String s) {
      String[] split = s.split(" ");
      x = Double.parseDouble(split[0]);
      y = Double.parseDouble(split[1]);
    }

    @Override
    public int compareTo(Store store) {
      return Comparator.<Store>comparingDouble(s -> s.x).thenComparingDouble(s -> s.y).compare(this, store);
    }
  }

  private static void runTestWithFileDonutEmperium(String fileName) {
    assertApproximatelyEquals(WebLab.getData(fileName + ".out").trim(), DonutEmperium.run(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8))).trim());
  }

  // Sorts the resulting stores by coordinate (so order doesn't matter) and checks whether it's within 1e-6 error
  private static void assertApproximatelyEquals(String expected, String actual) {
    String[] expectedStrings = expected.split("\n");
    String[] actualStrings = actual.split("\n");
    if (expectedStrings.length != actualStrings.length)
      throw new ComparisonFailure("Lengths of lists different!", Integer.toString(expectedStrings.length), Integer.toString(actualStrings.length));
    Store[] expectedStores = Arrays.stream(expectedStrings).map(Store::new).sorted().toArray(Store[]::new);
    Store[] actualStores = Arrays.stream(actualStrings).map(Store::new).sorted().toArray(Store[]::new);
    for (int i = 0; i < actualStores.length; i++) {
      Store s1 = expectedStores[i], s2 = actualStores[i];
      assertEquals("x-coordinate of store " + i, s1.x, s2.x, s1.x * 1e-6);
      assertEquals("y-coordinate of store " + i, s1.y, s2.y, s1.y * 1e-6);
    }
  }

  @Test(timeout = 100)
  public void exampleDonutEmperium() {
    runTestWithFile("example");
  }
}


