package sdas.algorithmDesign.Network_flow.scr;

public class Edge {
  
    private int capacity;
  
    private int flow;
  
    private Node from;
  
    private Node to;
  
    private boolean backwards;
  
    /**
     * THis should be protected
     * @param capacity
     * @param from
     * @param to
     * @param backwards
     */
    public Edge(int capacity, Node from, Node to, boolean backwards) {
      this.capacity = capacity;
      this.from = from;
      this.to = to;
      this.flow = 0;
      this.backwards = backwards;
    }
  
    /**
     * THis should be protected
     * @param capacity
     * @param from
     * @param to
     * @param flow
     * @param backwards
     */
    public Edge(int capacity, Node from, Node to, int flow, boolean backwards) {
      this.capacity = capacity;
      this.from = from;
      this.to = to;
      this.flow = flow;
      this.backwards = backwards;
    }
  
    int getCapacity() {
      return capacity;
    }
  
    int getFlow() {
      return flow;
    }
  
    Node getFrom() {
      return from;
    }
  
    Node getTo() {
      return to;
    }
  
    void setFlow(int f) {
      assert (f <= capacity);
      this.flow = f;
    }
  
    void setCapacity(int capacity) {
      assert (this.flow <= capacity);
      this.capacity = capacity;
    }
  
    boolean isBackwards() {
      return this.backwards;
    }
  
    public boolean equals(Object other) {
      if (other instanceof Edge) {
        Edge that = (Edge) other;
        return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
      }
      return false;
    }
  }
