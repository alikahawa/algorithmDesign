package Greedy.scr;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class RoutingTrains {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
  public static String run(InputStream in) {
    return new RoutingTrains().solve(in);
  }

  /**
   * Example Input:

    5 6 1
    1 2 2
    3 2 100
    1 3 10
    4 5 10
    2 4 10
    5 3 10

    Example Output:

    yes

   * @param in
   * @return
   */
  public String solve(InputStream in) {

    Scanner sc = new Scanner(in);
    int n =  sc.nextInt();
    int m = sc.nextInt();
    int s = sc.nextInt();
    ArrayList<Node> nodes = new ArrayList<>();
    for(int i = 0; i <= n; i++){
        nodes.add(new Node(i));
    }
    for(int i = 0; i < m; i++){
        int from = sc.nextInt();
        int to = sc.nextInt();
        sc.nextInt();
        nodes.get(from).outgoingEdges.add(nodes.get(to));
    }
    sc.close();
    Queue<Node> q = new LinkedList<>();
    q.add(nodes.get(s));
    
    while(!q.isEmpty()){
        Node cur = q.poll();
        for( Node node : cur.outgoingEdges){
        if(node == nodes.get(s)){
            return "yes";
        }
        q.add(node);
        }
    }
    return "no";
  }
}

class Node {

    List<Node> outgoingEdges;
    
    int id;
  
    boolean marked;
  
    public Node(int id) {
      this.outgoingEdges = new ArrayList<>();
      this.marked = false;
      this.id = id;
    }
    
    public String toString() {
      return Integer.toString(id);
    }
    
    @Override
    public int hashCode() {
        return id;
    }
  }
  
  /**
   * The train company that you work for is looking to have trains run in cycles instead of between two endpoints.
    To speed up the decision process, 
    they asked you to create an algorithm to determine quickly if it is even possible for a piece of map to create a cyclic route.

    The map is represented by a directed graph G=(V,E)
    , where V denotes the set containing n vertices and E the set containing m directed edges.
    Each vertex represents a station on the map and the edges represent railways between them.
    The company also provides a central station, s

    , from which it should be possible to reach the cycle, otherwise they will not consider it.

    Design and implement an algorithm that determines whether there exist a cycle reachable from s
    in the map.
    Your algorithm should print yes or no and run in O(n+m)time.

    The first line of the input contains three integers separated by a space: n
    , m and s. The integers n and m are defined as above and you may assume the vertices are labelled 1 up to and including n. 
    E.g. if n equals 6, the vertices are labelled 1,2,3,4,5 and 6. The integer s is the number of the vertex representing the central station.
    The next m lines are the directed edges. 
    Each line contains three integers: the two numbers of the vertices associated with this edge and the length (i.e., positive weight) of the edge. For example, 
    a line containing the integers 3, 6 and 9 indicates that there is an edge from vertex 3 to vertex 6 with length 9.
   * 
   */