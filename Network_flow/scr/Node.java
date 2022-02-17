package sdas.algorithmDesign.Network_flow.scr;

import java.util.*;

public class Node {
  
    private int id;
  
    private Collection<Edge> edges;
  
    public Node(int id) {
      this.id = id;
      this.edges = new ArrayList<Edge>();
    }
  
    public void addEdge(Node to, int capacity, int flow, boolean backwards) {
      Edge e = new Edge(capacity, this, to, flow, backwards);
      edges.add(e);
    }
  
    Collection<Edge> getEdges() {
      return edges;
    }
  
    int getId() {
      return id;
    }
  
    public boolean equals(Object other) {
      if (other instanceof Node) {
        Node that = (Node) other;
        if (id == that.getId())
          return edges.equals(that.getEdges());
      }
      return false;
    }

    public void addEdge(Node node, int i) {
        Edge e = new Edge(i, this, node, Integer.MAX_VALUE/2, false);
      edges.add(e);
    }
  }
