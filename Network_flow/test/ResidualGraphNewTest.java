package sdas.algorithmDesign.Network_flow.test;

// import static org.junit.Assert.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
// import org.junit.*;
// import org.junit.rules.*;


import sdas.algorithmDesign.Network_flow.scr.Graph;
import sdas.algorithmDesign.Network_flow.scr.Node;
import sdas.algorithmDesign.Network_flow.scr.ResidualGraphNew;

public class ResidualGraphNewTest {
    private long time = 0;

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
    time = System.currentTimeMillis();
  }

  @After
  public void tearDown() {
    System.out.println("Test '" + name.getMethodName() + "' took " + (System.currentTimeMillis() - time) + "ms");
  }

  private Graph parse(InputStream in) {
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

  private ByteArrayInputStream getStream(String fileName) {
    return new ByteArrayInputStream(WebLab.getData(fileName).getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Tests the parse method on a simple network.
   */
  @Test(timeout = 100)
  public void testParser1() {
    assertEquals(parse(getStream("parser1.in")), ResidualGraphNew.parse(getStream("parser1.in")));
  }

  /**
   * Tests the parse method on a larger network.
   */
  @Test(timeout = 150)
  public void testParser2() {
    assertEquals(parse(getStream("parser2.in")), ResidualGraphNew.parse(getStream("parser2.in")));
  }

  /**
   * Tests augmenting a valid path in a small network.
   */
  @Test(timeout = 100)
  public void augment1() {
    Node node0 = new Node(0);
    Node source = new Node(1);
    Node sink = new Node(2);
    Node node3 = new Node(3);
    // Add edge between the source and node 3 with capacity 1 and no flow.
    source.addEdge(node3, 1);
    node3.addEdge(source, 0);
    // Add edge between node 3 and node 0 with capacity 1 and no flow.
    node3.addEdge(node0, 1);
    node0.addEdge(node3, 0);
    // Add edge between node 0 and the sink with capacity 1 and no flow.
    node0.addEdge(sink, 1);
    sink.addEdge(node0, 0);
    List<Node> nodes = new ArrayList();
    nodes.add(node0);
    nodes.add(source);
    nodes.add(sink);
    nodes.add(node3);
    Graph g = new Graph(nodes, source, sink);
    ResidualGraphNew.augmentPath(g, Arrays.asList(1, 3, 0, 2));
    assertEquals(parse(getStream("augment1.out")), g);
  }

  /**
   * Tests augmenting a valid path in a network of 200 nodes and 1000 edges
   */
  @Test(timeout = 100)
  public void augment2() {
    Graph g = ResidualGraphNew.parse(getStream("augment2.in"));
    ResidualGraphNew.augmentPath(g, Arrays.asList(0, 4, 5, 6, 7));
    assertEquals(parse(getStream("augment2.out")), g);
  }

  /**
   * Tests augmenting a valid path in a network of 100 nodes and 2000 edges.
   */
  @Test(timeout = 400)
  public void augment3() {
    Graph g = ResidualGraphNew.parse(getStream("augment3.in"));
    ResidualGraphNew.augmentPath(g, Arrays.asList(39, 31, 15, 72, 47));
    ResidualGraphNew.augmentPath(g, Arrays.asList(42, 85, 78, 53, 71));
    ResidualGraphNew.augmentPath(g, Arrays.asList(31, 15, 0, 92));
    assertEquals(parse(getStream("augment3.out")), g);
  }

  /**
   * Tests augmenting a path where one of the edges is at flow == cap after 1 augmentation.
   */
  @Test(timeout = 100)
  public void augmentMaxCapacity() {
    Graph g = ResidualGraphNew.parse(getStream("augment2.in"));
    ResidualGraphNew.augmentPath(g, Arrays.asList(0, 4, 6, 7));
    try {
      ResidualGraphNew.augmentPath(g, Arrays.asList(0, 4, 6, 7));
      fail();
    } catch (IllegalArgumentException e) {
    // Should throw an exception
    }
  }

  /**
   * Tests augmenting a path where an unused edge would be traversed backwards (residual edge with flow == cap).
   */
  @Test(timeout = 100)
  public void augmentNonExistingPath() {
    Graph g = ResidualGraphNew.parse(getStream("augment2.in"));
    try {
      ResidualGraphNew.augmentPath(g, Arrays.asList(0, 4, 2, 3, 5, 7));
      fail();
    } catch (IllegalArgumentException e) {
    // Should throw an exception
    }
  }
}
