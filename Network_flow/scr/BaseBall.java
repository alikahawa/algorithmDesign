package sdas.algorithmDesign.Network_flow.scr;

import java.io.*;
import java.util.*;

/**
 * The Supreme League is the highest level baseball competition. In September the best m teams play are selected to play
in the playoffs to compete for the Cup. The winner of the Cup is the team with the highest number of wins.

You are given a list of m baseball teams that are competing for the Cup. For each team i, the current number of wins,
and the number of games left to play is given, and it is already decided against whom each team will play their last games.

We now ask you to decide for team x whether that team is still able to win the Cup.

The input will look as follows: the first line will contain the number of teams, m.
The team numbers range from 1 to m. Then 2m lines follow, for each team their team number, i*, the number of wins, w_i,
and the number of games left to play, g_i, are printed on one line. The line below will contain g_i numbers
separated by spaces, which are the teams that team i still has to play against. Keep in mind that one game is displayed
in the lists of both teams.

The first team that is entered is team x, i.e. the team of which we want to find out if it can still win.

4 
4 10 2
2 3
1 12 2
2 3 
2 11 3
1 3 4
3 11 3
1 2 4

Your algorithm should output whether it is possible for team x to still be the winner of Cup, e.g. end up with the highest number of wins, possibly in a tie. In this example the output should be
false
 */
public class BaseBall {

  /**
   * Returns true if team x can still win the Cup.
   * @param in
   * @return
   */
  public static boolean solve(InputStream in) {
    Scanner sc = new Scanner(in);
    int n = sc.nextInt();
    
    Node source = new Node(0);
    Node sink = new Node(-1);
    ArrayList<Node> nodes = new ArrayList<>();
    nodes.add(source);
    
    for(int i = 1; i <= n; i++){
      nodes.add(new Node(i));
    }
    nodes.add(sink);
    int teamX = sc.nextInt();
    int winX = sc.nextInt() + sc.nextInt();
    sc.nextLine();
    sc.nextLine();
    
    for(int i = 1; i < n; i++){
      int team = sc.nextInt();
      int teamWins = sc.nextInt();
      int teamGames = sc.nextInt();
      
      if(winX < teamWins){
        return false;
      }
      Map<Integer, Integer> games = new HashMap<>();  
      for(int z = 0; z < teamGames; z++){
        int gameVs = sc.nextInt();
        if(gameVs < team && gameVs != teamX){
          int random = games.getOrDefault(gameVs, 0);
          games.put(gameVs, random+1);
        }
      }
      for(Integer k: games.keySet()){
        Node game = new Node(nodes.size());
        nodes.add(game);
        source.addEdge(game, games.get(k));
        game.addEdge(nodes.get(k), Integer.MAX_VALUE/2);
        game.addEdge(nodes.get(team), Integer.MAX_VALUE/2);
      }
      // Here we set the capacity of the edges from teams to sink with the value of 
      // #wins team x have - # wins current team already has 
      nodes.get(team).addEdge(sink, winX-teamWins);
    }
    sc.close();
    Graph g = new Graph(nodes, source, sink);
    MaxFlow.maximizeFlow(g);
  
    for(Edge e: g.getSource().getEdges()){
      if(e.getCapacity() != e.getFlow()){
        return false;
      }
    }
    
    return true;
  }
}


class MaxFlow {

    /**
     * 
     * @param g
     * @param start
     * @param end
     * @return
     */
    private static List<Edge> findPath(Graph g, Node start, Node end) {
      Map<Node, Edge> mapPath = new HashMap<Node, Edge>();
      Queue<Node> sQueue = new LinkedList<Node>();
      Node currentNode = start;
      sQueue.add(currentNode);
      while (!sQueue.isEmpty() && currentNode != end) {
        currentNode = sQueue.remove();
        for (Edge e : currentNode.getEdges()) {
          Node to = e.getTo();
          if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
            sQueue.add(e.getTo());
            mapPath.put(to, e);
          }
        }
      }
      if (sQueue.isEmpty() && currentNode != end)
        return null;
      LinkedList<Edge> path = new LinkedList<Edge>();
      Node current = end;
      while (mapPath.get(current) != null) {
        Edge e = mapPath.get(current);
        path.addFirst(e);
        current = e.getFrom();
      }
      return path;
    }
  
    /**
     * 
     * @param g
     */
    public static void maximizeFlow(Graph g) {
      Node sink = g.getSink();
      Node source = g.getSource();
      List<Edge> path;
      while ((path = findPath(g, source, sink)) != null) {
        int r = Integer.MAX_VALUE;
        for (Edge e : path) {
          r = Math.min(r, e.getResidual());
        }
        for (Edge e : path) {
          e.augmentFlow(r);
        }
      }
    }
  }
  
  class Graph {
  
    private List<Node> nodes;
  
    private Node source;
  
    private Node sink;
  
    /**
     * 
     * @param nodes
     * @param source
     * @param sink
     */
    public Graph(List<Node> nodes, Node source, Node sink) {
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
  
    public List<Node> getNodes() {
      return nodes;
    }
  
    public boolean equals(Object other) {
      if (other instanceof Graph) {
        Graph that = (Graph) other;
        return this.nodes.equals(that.nodes);
      }
      return false;
    }
  
    public void maximizeFlow() {
      MaxFlow.maximizeFlow(this);
    }
  }
  
  class Node {
  
    protected int id;
  
    protected Collection<Edge> edges;
  
    public Node(int id) {
      this.id = id;
      this.edges = new ArrayList<Edge>();
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
  
    protected Edge(int capacity, Node from, Node to) {
      this.capacity = capacity;
      this.from = from;
      this.to = to;
      this.flow = 0;
      this.backwards = new Edge(this);
    }
  
    public void augmentFlow(int add) {
      assert (flow + add <= capacity);
      flow += add;
      backwards.setFlow(getResidual());
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
  
    public int getResidual() {
      return capacity - flow;
    }
  
    public Node getTo() {
      return to;
    }
  
    private void setFlow(int f) {
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
  