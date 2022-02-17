package Greedy.scr;

import java.io.*;
import java.util.*;

public class GettingOutTheFastest {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
  public static String run(InputStream in) {
    return new GettingOutTheFastest().solve(in);
  }

  /**
   * Example Input:

    7 7 1 5
    1 2 2
    2 3 100
    3 4 10
    4 5 10
    2 6 10
    6 7 10
    7 4 80

    Example Output:

    118

   * @param in
   * @return
   */
  public String solve(InputStream in) {
    Scanner sc = new Scanner(in);
    /*
     * We already parse the input for you and should not need to make changes to this part of the code.
     * You are free to change this input format however.
     */
    int n = sc.nextInt();
    int m = sc.nextInt();
    int s = sc.nextInt();
    int t = sc.nextInt();
    ArrayList<HashMap<Integer, Integer>> nodes = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      nodes.add(new HashMap<>());
    }
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      int cost = sc.nextInt();
      nodes.get(u).put(v, cost);
    }
    sc.close();
    PriorityQueue<Node> q = new PriorityQueue<>();
    q.add(new Node(s, 0));
    Boolean[] visited = new Boolean[n+1];
    int[] cost = new int[n+1];
    Arrays.fill(cost, Integer.MAX_VALUE/2);
    Arrays.fill(visited, false);
    cost[s] = 0;
    while(!q.isEmpty()){
      Node cur = q.poll();
      if(cur.id == t){
        return Integer.toString(cost[cur.id]);
      }
      for(Integer i: nodes.get(cur.id).keySet()){
        int costI = cost[cur.id] + nodes.get(cur.id).size() + nodes.get(cur.id).get(i);
        if(cost[i] > costI){
          cost[i] = costI;
          q.add(new Node(i, cost[i]));
        }
      }
      visited[cur.id] = true;
    }
    return visited[t] ? Integer.toString(cost[t]) : "-1";
  }
}

class Node implements Comparable<Node>{
    int id, cost;
    public Node(int id, int cost){
      this.id = id;
      this.cost = cost;
    }
    
    public int compareTo(Node other){
      return Integer.compare(this.cost, other.cost);
    }
  }

/**
 * A maze is represented by a weighted directed graph G=(V,E), 
 * where V denotes the set containing n vertices and E the set containing m directed edges.
    Each vertex represents an intersection or end point in the maze and the edges represent paths between them.
    A directed edge is used for (downhill) tunnels and holes that you can jump into, 
    but where it is impossible to get back.
    Because of this, it may become impossible to reach the exit.

    Some edges take longer than others, which is expressed in their weight.

    Additionally the sheer number of options you can chose from in every vertex overwhelms you quite a bit, 
    so every vertex takes 1

    time step per outgoing edge (because you have to find out what is the correct one).

    Design and implement an algorithm that determines the path from s
    to t that takes the least amount of time (which is the sum of lengths of all edges plus for all vertices 
    (except t) the number of outgoing edges). Let the algorithm just print the total time of this path. 
    Aim for the most efficient algorithm you can think of. Extremely slow implementations will not be accepted.

    The input is structured the same as for the assignment “Getting out of the maze”:

    The first line of the input contains four integers separated by a space: n, m, s and t. 
    The integers n and m are defined as above and you may assume the vertices are labelled 1 up to and including n. 
    E.g. if n equals 6, the vertices are labelled 1,2,3,4,5 and 6. 
    The integers s and t are the numbers of the vertices representing your starting point and the exit of the maze, respectively.
    The next m lines are the directed edges. Each line contains three integers: 
    the two numbers of the vertices associated with this edge and the length (i.e., positive weight) of the edge. 
    For example, a line containing the integers 3, 6 and 9 indicates that there is an edge from vertex 3 to vertex 6 with length 9

    The output should be a single line with the time spent on the shortest path if one exists, or -1 otherwise.
 */
