package sdas.algorithmDesign.Network_flow.test;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

import sdas.algorithmDesign.Network_flow.scr.Graph;
import sdas.algorithmDesign.Network_flow.scr.Node;
import sdas.algorithmDesign.Network_flow.scr.ResidualGraph;


public class ResidualGraphTest {
    private ByteArrayInputStream getStream(String fileName) {
        return new ByteArrayInputStream(WebLab.getData(fileName).getBytes(StandardCharsets.UTF_8));
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
        source.addEdge(node3, 1, 0, false);
        node3.addEdge(source, 0, 0, true);
        // Add edge between node 3 and node 0 with capacity 1 and no flow.
        node3.addEdge(node0, 1, 0, false);
        node0.addEdge(node3, 0, 0, true);
        // Add edge between node 0 and the sink with capacity 1 and no flow.
        node0.addEdge(sink, 1, 0, false);
        sink.addEdge(node0, 0, 0, true);
        List<Node> nodes = new ArrayList();
        nodes.add(node0);
        nodes.add(source);
        nodes.add(sink);
        nodes.add(node3);
        Graph g = new Graph(nodes, source, sink);
        ResidualGraph.augmentPath(g, Arrays.asList(1, 3, 0, 2));
        assertEquals(ResidualGraph.parse(getStream("augment1.out")), g);
      }
}
