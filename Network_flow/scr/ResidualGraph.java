package sdas.algorithmDesign.Network_flow.scr;

import java.io.*;
import java.util.*;

/**
 * All helping classes are at the end of this file.
 */
public class ResidualGraph {
    /**
   * Parses inputstream to graph.
   */
  public static Graph parse(InputStream in) {
     Scanner sc = new Scanner(in);
     int n = sc.nextInt();
     int m = sc.nextInt();
     int s = sc.nextInt();
     int t = sc.nextInt();
     ArrayList<Node> nodes = new ArrayList<Node>();
    // nodes.add(new Node(s));
    // nodes.add(new Node(t));
     for(int i = 0; i < n; i++){
        nodes.add(new Node(i));
     }
     for(int i = 0; i < m; i++){
       int from = sc.nextInt();
       int to = sc.nextInt();
       int cap = sc.nextInt();
       int flow = sc.nextInt();
       nodes.get(from).addEdge(nodes.get(to), cap, flow, false);
       nodes.get(to).addEdge(nodes.get(from), flow, 0, true);
     }
     sc.close();
     return new Graph(nodes, nodes.get(s), nodes.get(t));
    }
  
    /**
     * Augments the flow over the given path by 1 if possible.
     *
     * @param g    Graph to operate on.
     * @param path List of nodes to represent the path.
     * @throws IllegalArgumentException if augmenting the flow in the given path is impossible.
     */
    public static void augmentPath(Graph g, List<Integer> path) throws IllegalArgumentException {
    // TODO
      for(int i = 0; i < path.size() - 1; i++){
        Edge e = findEdge(g.getNodes().get(path.get(i)), g.getNodes().get(path.get(i+1)));
        if(e == null){
          throw new IllegalArgumentException("No path");
        }
        if(e.isBackwards() && e.getCapacity() == 0){
          throw new IllegalArgumentException("Cannot go furthur cap == 0");
        }
        if(!e.isBackwards() &&  e.getFlow() >= e.getCapacity()){
          throw new IllegalArgumentException("Full flow, cannot augment more");
        }
        if(e.isBackwards()){
          e.setCapacity(e.getCapacity()-1);
        }else{
         e.setFlow(e.getFlow()+1);
        }
        
        e = findEdge(g.getNodes().get(path.get(i+1)), g.getNodes().get(path.get(i)));
        if(e.isBackwards()){
          e.setCapacity(e.getCapacity()+1);
        }else{
          e.setFlow(e.getFlow()-1);
        }
      }
    }
    
    static Edge findEdge(Node from, Node to){
      for(Edge e: from.getEdges()){
        if(e.getTo().equals(to)){
          return e;
        }
      }
      return null;
    }

}
  
/**
 * In this first implementation task of the Network Flow module you are going to build a residual graph.
We assume a network with n nodes (IDs ranging from 0 to n−1) and m

edges.

    Task 1: implement the parse method so that it builds a residual graph from textual input.

    Task 2: implement the augmentPath method so that it augments the flow in the indicated path by 1

    or throws an IllegalArgumentException when this is impossible.

NB: As you may be aware, there are multiple ways to implement the residual edges. In this exercise you will build the method as outlined in section 7.1 from the book. In later exercises you need not worry about how our library models it, as you only need to create the original graph and our library will create the residual graph from this for you.

The input you are given contains m+1

lines.

    The first line of the input contains 4 integers separated by a space: n m s t.
    n is the amount of nodes, m the amount of edges, s is the ID of the source node and t is the ID of the sink node.

    The following m lines each represent one edge and contain 4 integers: from to cap flow.
    from and to are the IDs of the starting and ending note respectively, cap is the capacity of the edge and flow indicates the current flow.


 */