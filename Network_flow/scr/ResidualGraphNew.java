package sdas.algorithmDesign.Network_flow.scr;

import java.io.*;
import java.util.*;

/** 
 * This version is outdated and need to be reconfigured to work. (Just update the Edge class)
 */
public class ResidualGraphNew {
     /**
   * Parses inputstream to graph.
   */
  static Graph parse(InputStream in) {
    Scanner sc = new Scanner(in);
    // no. of nodes
    int n = sc.nextInt();
    // no. of edges
    int m = sc.nextInt();
    // source
    int s = sc.nextInt();
    // sink
    int t = sc.nextInt();
    // Create all nodes
    ArrayList<Node> nodes = new ArrayList<>();
    for (int x = 0; x < n; x++) {
      nodes.add(new Node(x));
    }
    // Create all edges
    for (int x = 0; x < m; x++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cap = sc.nextInt();
      int flow = sc.nextInt();
      nodes.get(from).addEdge(nodes.get(to), cap - flow);
      nodes.get(to).addEdge(nodes.get(from), flow);
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
  static void augmentPath(Graph g, List<Integer> path) throws IllegalArgumentException {
    for (int x = 0; x < path.size() - 1; x++) {
      // Find edge between 2 path nodes
      Edge e = findEdge(g.getNodes().get(path.get(x)), g.getNodes().get(path.get(x + 1)));
      if (e == null)
        throw new IllegalArgumentException("Path doesn't exist.");
      if (e.getCapacity() == 0)
        throw new IllegalArgumentException("Path cannot be incremented.");
      // Edge loses 1 capacity
      e.setCapacity(e.getCapacity() - 1);
      // Find backwards edge
      e = findEdge(g.getNodes().get(path.get(x + 1)), g.getNodes().get(path.get(x)));
      // Backwards edges gain 1 capacity
      e.setCapacity(e.getCapacity() + 1);
    }
  }

  private static Edge findEdge(Node from, Node to) {
    for (Edge edge : from.getEdges()) {
      if (edge.getTo().equals(to)) {
        return edge;
      }
    }
    return null;
  }
}
