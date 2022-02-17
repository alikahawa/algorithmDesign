package Divide_and_Conquer.scr;

import java.util.*;

/**
 * In this exercise you will have to implement an algorithm to find the closest pair of points.
One way to find the closest pair is by going over all possible pairs, which consists of n2
possibilities.

To make this more efficient you will have to implement an algorithm that has as worst case run-time complexity O(nlogn)
 */
public class SnuggleInTight {

    /**
   * Takes a list of points and returns the distance between the closest pair.
   * This is done with divide and conquer.
   *
   * @param points
   *     - list of points that need to be considered.
   * @return smallest pair-wise distance between points.
   */
  public static double closestPair(List<Point> points) {
    if (points.size() <= 1) {
      return Double.POSITIVE_INFINITY;
    }
    List<Point> pointsX = new ArrayList<>(points);
    Util.sortByX(pointsX);
    List<Point> pointsY = new ArrayList<>(points);
    Util.sortByY(pointsY);
    return closestPair(pointsX, pointsY);
  }

  /**
   * Helper method that recursively finds the smallest distance.
   *
   * @param pointsX
   *     - a list of points sorted on x (ascending).
   * @param pointsY
   *     - a list of points sorted on y (ascending).
   * @return smallest pair-wise distance between points.
   */
  private static double closestPair(List<Point> pointsX, List<Point> pointsY) {
    int size = pointsX.size();
    if (size <= 3) {
      return Util.bruteForce(pointsX);
    }
    int mid = size / 2;
    List<Point> leftField = pointsX.subList(0, mid);
    List<Point> rightField = pointsX.subList(mid, size);

    List<Point> sortedLeft = new ArrayList<>();
    List<Point> sortedRight = new ArrayList<>();
    for (Point point: pointsY) {
      if (point.x < pointsX.get(mid).x)   {
        sortedLeft.add(point);
      } else {
        sortedRight.add(point);
      }
    }
    double left = closestPair(leftField, sortedLeft);

    double right = closestPair(rightField, sortedRight);

    double delta = Math.min(left, right);
    List<Point> midStrip = new ArrayList<>();
    double midX = leftField.get(leftField.size() - 1).x;
    for (Point point : pointsY) if (Math.abs(midX - point.x) < delta)
      midStrip.add(point);
    for (int i = 0; i < midStrip.size() - 1; i++) {
      Point point1 = midStrip.get(i);
      for (int j = i + 1; j < midStrip.size() && (midStrip.get(j).y - midStrip.get(i).y < delta); j++) {
        Point point2 = midStrip.get(j);
        double distance = Util.distance(point1, point2);
        if (distance < delta) {
          delta = distance;
        }
      }
    }
    return delta;
  }
}

/**
 * Class representing a 2D point.
 */
class Point {

    public double x;
  
    public double y;
  
    public Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
  
  /**
   * Useful methods for this assignment.
   */
  class Util {
  
    /**
     * Takes two points and computes the euclidean distance between the two points.
     *
     * @param point1 - first point.
     * @param point2 - second point.
     * @return euclidean distance between the two points.
     * @see <a href="https://en.wikipedia.org/wiki/Euclidean_distance">https://en.wikipedia.org/wiki/Euclidean_distance</a>
     */
    public static double distance(Point point1, Point point2) {
      return Math.sqrt(Math.pow(point1.x - point2.x, 2.0D) + Math.pow(point1.y - point2.y, 2.0D));
    }
  
    /**
     * Takes a list of points and sorts it on x (ascending).
     *
     * @param points - points that need to be sorted.
     */
    public static void sortByX(List<Point> points) {
      Collections.sort(points, Comparator.comparingDouble(point -> point.x));
    }
  
    /**
     * Takes a list of points and sorts it on y (ascending) .
     *
     * @param points - points that need to be sorted.
     */
    public static void sortByY(List<Point> points) {
      Collections.sort(points, Comparator.comparingDouble(point -> point.y));
    }
  
    /**
     * Takes a list of points and returns the distance between the closest pair.
     * This is done by brute forcing.
     *
     * @param points - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double bruteForce(List<Point> points) {
      int size = points.size();
      if (size <= 1)
        return Double.POSITIVE_INFINITY;
      double bestDist = Double.POSITIVE_INFINITY;
      for (int i = 0; i < size - 1; i++) {
        Point point1 = points.get(i);
        for (int j = i + 1; j < size; j++) {
          Point point2 = points.get(j);
          double distance = Util.distance(point1, point2);
          if (distance < bestDist)
            bestDist = distance;
        }
      }
      return bestDist;
    }
  }