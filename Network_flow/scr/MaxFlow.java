package sdas.algorithmDesign.Network_flow.scr;

import java.util.*;

public class MaxFlow {
 /**
   * Execute the Ford-Fulkerson algorithm on the given graph and return its maximum flow.
   * Find the maximum flow in the given network.
   *
   * @param g Graph representing the network.
   * @return The maximum flow of the network.
   */
  static int maxFlow(Graph g) {
    maximizeFlow(g);
    int flow = 0;
    for(Edge e: g.getSource().getEdges()){
      flow += e.getFlow();
    }
    return flow;
    }
    
    static List<Edge> findPath(Node source, Node sink) {
      Map<Node, Edge> map = new HashMap<>();
      Queue<Node> q = new LinkedList<>();
      Node cur = source;
      q.add(cur);
      
      while(!q.isEmpty() && cur != sink){
        cur = q.poll();
        for(Edge e: cur.getEdges()){
          Node to = e.getTo();
          if(map.get(to) == null && e.getCapacity() - e.getFlow() > 0 && to != source){
            map.put(to, e);
            q.add(to);
          }
        }
      }
      if(q.isEmpty() && cur != sink){
        return null;
      }
      LinkedList<Edge> path = new LinkedList<>();
      cur = sink;
      while(map.get(cur) != null){
        Edge e = map.get(cur);
        path.addFirst(e);
        cur = e.getFrom();
      }
      return path;
    }
    
    static void maximizeFlow(Graph g) {
      Node source = g.getSource();
      Node sink = g.getSink();
      List<Edge> path;
      while((path = findPath(source, sink)) != null ) {
        int r = Integer.MAX_VALUE/2;
        for(Edge e: path){
          r = Math.min(r, e.getCapacity()-e.getFlow());
        }
        for(Edge e: path){
          e.setFlow(e.getFlow()+r);
          e.getBackwards().setFlow(e.getCapacity()-e.getFlow());
        }
      }
    }
}


class Graph {

    private Collection<Node> nodes;
  
    private Node source;
  
    private Node sink;
  
    public Graph(Collection<Node> nodes, Node source, Node sink) {
      this.nodes = nodes;
      this.source = source;
      this.sink = sink;
    }
  
    public Node getSink() {
      return sink;
    }
  
    public Node getSource() {
      return source;
    }
  
    public boolean equals(Object other) {
      if (other instanceof Graph) {
        Graph that = (Graph) other;
        return this.nodes.equals(that.nodes);
      }
      return false;
    }
  }
  
  class Node {
  
    protected int id;
  
    protected Collection<Edge> edges;
  
    public Node(int id) {
      this.id = id;
      this.edges = new ArrayList<>();
    }
  
    public void addEdge(Node to, int capacity) {
      Edge e = new Edge(capacity, this, to);
      edges.add(e);
      to.getEdges().add(e.getBackwards());
    }
  
    public Collection<Edge> getEdges() {
      return edges;
    }
  
    public int getId() {
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
  }
  
  class Edge {
  
    protected int capacity;
  
    protected int flow;
  
    protected Node from;
  
    protected Node to;
  
    protected Edge backwards;
  
    private Edge(Edge e) {
      this.flow = e.getCapacity();
      this.capacity = e.getCapacity();
      this.from = e.getTo();
      this.to = e.getFrom();
      this.backwards = e;
    }
  
    public Edge(int capacity, Node from, Node to) {
      this.capacity = capacity;
      this.from = from;
      this.to = to;
      this.flow = 0;
      this.backwards = new Edge(this);
    }
  
    public Edge getBackwards() {
      return backwards;
    }
  
    public int getCapacity() {
      return capacity;
    }
  
    public int getFlow() {
      return flow;
    }
  
    public Node getFrom() {
      return from;
    }
  
    public Node getTo() {
      return to;
    }
  
    public void setFlow(int f) {
      assert (f <= capacity);
      this.flow = f;
    }
  
    public boolean equals(Object other) {
      if (other instanceof Edge) {
        Edge that = (Edge) other;
        return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
      }
      return false;
    }
  }
  