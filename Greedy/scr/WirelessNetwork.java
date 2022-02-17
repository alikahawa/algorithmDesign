package Greedy.scr;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class WirelessNetwork {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
  public static String run(InputStream in) {
    return new WirelessNetwork().solve(in);
  }

  /**
   * Example input:

    4 5 8
    0 1 6
    1 2 9
    0 2 7
    1 3 2
    0 3 8

    Example output:

    15 2

   * @param in
   * @return
   */
  public String solve(InputStream in) {
    Scanner sc = new Scanner(in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int b = sc.nextInt();
    
    Edge[] edges = new Edge[m];
    Edge[] tree = new Edge[n-1];
    
    for(int i = 0; i < m; i++){
      edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }
    sc.close();
    Arrays.sort(edges);
    UnionFind un = new UnionFind(n);
    
    // Now we form our MST!!!
    int start = 0;
    for(Edge e: edges){
      if(un.join(e.s, e.t)){
        tree[start++] = e;
      }
      if(start == n-1){
        break;
      }
    }
    
    long sum = 0;
    long budget = 0;
    for(Edge e:tree){
      sum += e.c;
      if(sum <= b){
        budget++;
      }
    }
    
    return sum + " " + budget;
  }
  
  class UnionFind{
      
    private int[] parent;
    private int[] rank;
    
    public UnionFind(int n) {
      this.parent = new int[n];
      this.rank = new int[n];
      for(int i = 0; i < n; i++){
        parent[i] = i;
      }
    }
    
    private boolean join(int x, int y) {
      int p = find(x);
      int p1 = find(y);
      if(p == p1){
        return false;
      }
      if(rank[p] > rank[p1]){
        parent[p1] = p;
      }
      if(rank[p1] > rank[p]){
        parent[p] = p1;
      }else{
        rank[parent[p1] = p]++;
      }
      return true;
    }
    
    private int find(int i){
      return parent[i] == i ? i: (parent[i] = find(parent[i]));
    }
  }
}

class Edge implements Comparable<Edge>{
    int s,t,c;
    public Edge(int s, int t, int c){
      this.s = s;
      this.t = t;
      this.c = c;
    }
    
    public int compareTo(Edge other){
      return Integer.compare(this.c, other.c);
    }
  }

  /**
   * To set up internet connections in a large area in Cittagazze,
stations are planned at locations with a high population density.
These n stations are connected through pairs of quite expensive directional antennas.
The price of (a pair of) antennas depends on the required range,
and for each pair of locations (u,v) these costs are given by c(u,v)>0

.
The main question of this exercise is which (undirected) connections to set up
such that all locations are connected and the installation costs are as low as possible.

Unfortunately, there is insufficient budget
for setting up the connected network computed above at once.
The government decides to make as many connections of the optimal network as possible,
given the available budget B
(and to complete the network in upcoming years).
For example, if the network looks like this,
a budget of 3 would select (a,b) and (d,e) and (f,g)

.

     1       2       3       1       2       1
(a)-----(b)-----(c)-----(d)-----(e)-----(f)-----(g)

Your task in this exercise is to read the cost of all possible connections
from the input Scanner in the following format.

First read the number of locations n
(2≤n≤10000),
the number of connections m (1≤m≤100000),
and the budget B (1≤B≤109

),
followed by a new line character.

Then read m
lines with three values:
the identifier of the first location u (0≤u<n),
the identifier of the second location v (0≤v<n),
and the costs of connecting these through a pair of antennas
c(u,v) (1≤c(u,v)≤100000

),
followed by a new line.

For any pair of locations that is not listed,
the costs of connecting can be assumed to be infinite.
It is guaranteed that the network can be set up using finite costs.

Your output should be a String that has two numbers,
separated by a space.
The first number is the minimum total costs to connect all locations.
The second number is the number of connections that can be built using the budget.
   */