package Greedy.scr;

import java.io.*;
import java.util.*;

public class GettingOutMaze {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
  public static String run(InputStream in) {
    return new GettingOutMaze().solve(in);
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

    yes

   * @param in
   * @return
   */
  public String solve(InputStream in) {

    Scanner sc = new Scanner(in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int s = sc.nextInt();
    int t = sc.nextInt();
    ArrayList<Node> nodes = new ArrayList<>();
    for(int i = 0; i<=n; i++){
        nodes.add(new Node());
    }
    // Map<Integer, Node> 
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
        Node curr = q.poll();
        if(curr == nodes.get(t)){
        return "yes";
        }
        for(Node node : curr.outgoingEdges){
        q.add(node);
        }
    }
    return "no";
  }
}

class Node {

    List<Node> outgoingEdges;
  
    boolean marked;
  
    public Node() {
      this.outgoingEdges = new ArrayList<>();
      this.marked = false;
    }
  }

  /**
   * A maze is represented by a directed (and weighted, but you only need that for the next problem) graph G=(V,E), 
   * where V denotes the set containing n vertices and E the set containing m directed edges.
    Each vertex represents an intersection or end point in the maze and the edges represent paths between them.
    A directed edge is used for (downhill) tunnels and holes that you can jump into, 
    but where it is impossible to get back.
    Because of this, it may become impossible to reach the exit.

    Design and implement an algorithm that determines whether it is possible to get from a given point s
    to the exit of the maze t.
    Your algorithm should print yes or no and run in O(n+m)

    time.

    Some exercises (like this one) require you to think about how to store your input data in an efficient format. 
    In some of these exercises we will therefore ask you to read the data from files yourself. 
    Below we give some explanation as to how reading such files works in WebLab.
    Reading input and writing output

    The input for this exercise will be read from a file that is stored on WebLab
    and passed to your solve method as an InputStream.
    You can wrap this stream in a Scanner to make reading easier.
    This has already been done in the solution template on the right.
    The output should be returned as a String.
    Description of input and output

    The first line of the input contains four integers separated by a space: n
    , m, s and t. The integers n and m are defined as above and you may assume the vertices are labelled 1 up to and including n. 
    E.g. if n equals 6, the vertices are labelled 1,2,3,4,5 and 6. 
    The integers s and t are the numbers of the vertices representing your starting point and the exit of the maze, 
    respectively.
    The next m lines are the directed edges. 
    Each line contains three integers: the two numbers of the vertices associated with this edge and the length (i.e., positive weight) of the edge. 
    For example, a line containing the integers 3, 6 and 9 indicates that there is an edge from vertex 3 to vertex 6 with length 9.
   * 
   */