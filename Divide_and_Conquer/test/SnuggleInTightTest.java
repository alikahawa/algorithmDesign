package Divide_and_Conquer.test;

// import static org.junit.Assert.*;
// import org.junit.*;
// import org.junit.rules.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

import Divide_and_Conquer.scr.Point;
import Divide_and_Conquer.scr.SnuggleInTight;
/**
 * 
 */
public class SnuggleInTightTest {
    
    @Test(timeout = 1000)
  public void testTwoPoints() {
    List<Point> points = new ArrayList<>();
    points.add(new Point(1, 2));
    points.add(new Point(4, 6));
    assertEquals(5, SnuggleInTight.closestPair(points), 5e-6);
  }

  @Test(timeout = 1000)
  public void testSmall() {
    List<Point> points = new ArrayList<>();
    points.add(new Point(2, 3));
    points.add(new Point(12, 30));
    points.add(new Point(40, 50));
    points.add(new Point(5, 1));
    points.add(new Point(12, 10));
    points.add(new Point(3, 4));
    assertEquals(1.4142135623730951, SnuggleInTight.closestPair(points), 1e-6);
  }
}
