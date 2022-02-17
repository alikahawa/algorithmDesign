package sdas.algorithmDesign.Network_flow.scr;

import java.io.*;
import java.util.*;

/**
 * Ellen is teaching elementary math to her students and the time for the final exam has
come. The exam consists of n questions. In each question the students have to
add (+), subtract (−) or multiply (∗) a pair of numbers.

Ellen has already chosen the n
pairs of numbers. All that remains is to decide
for each pair which of the three possible operations the students should perform.
To avoid students getting bored, Ellen wants to make sure that the n

correct answers
to her exam are all different.

Please help Ellen finish constructing the exam by automating this task.
Input

The input consists of:

    one line with one integer n

(1≤n≤2,500), the number of pairs of numbers;
n
lines each with two integers a and b (−106≤a,b≤106), a pair of numbers used.

Output

For each pair of numbers (a,b)
in the same order as in the input,
output a line containing a valid equation.
Each equation should consist of five parts: a, one of the three operators, b,
an equals sign (=), and the result of the expression.
All the n expression results must be different.

If there are multiple valid answers you may output any of them.
If there is no valid answer, output a single line with the string “impossible” instead.
Examples

For each example, the first block is the input and the second block is the corresponding output.
Example 1

4
1 5
3 3
4 5
-1 -6

1 + 5 = 6
3 * 3 = 9
4 - 5 = -1
-1 - -6 = 5

Example 2

4
-4 2
-4 2
-4 2
-4 2

impossible

Source: NWERC 2015
 */
public class ElementaryMath {
    public static void run(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
        new ElementaryMath().solve(sc, out);
        sc.close();
      }
    
      public void solve(Scanner sc, PrintStream out) {
        // Read input
        int n = sc.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextLong();
          b[i] = sc.nextLong();
        }
        // Compute possible answers
        int numAns = 0;
        HashSet<Long>[] anss = new HashSet[n];
        Map<Long, Integer> idx = new HashMap<Long, Integer>();
        for (int i = 0; i < n; i++) {
          anss[i] = new HashSet<Long>();
          anss[i].add(a[i] + b[i]);
          anss[i].add(a[i] - b[i]);
          anss[i].add(a[i] * b[i]);
          for (long ans : anss[i]) if (!idx.containsKey(ans))
            idx.put(ans, numAns++);
        }
        // Create matching problem using generic max flow code
        MaxFlow mf = new MaxFlow(n + numAns);
        for (int i = 0; i < n; i++) {
          mf.Add(mf.Source, i, 1);
          for (long ans : anss[i]) mf.Add(i, n + idx.get(ans), 1);
        }
        for (int i = 0; i < numAns; i++) mf.Add(n + i, mf.Sink, 1);
        // Is the problem solvable?
        if (mf.NetworkFlow() != n) {
          out.print("IMPOSSIBLE");
          return;
        }
        // Solvable, construct the output
        for (int i = 0; i < n; i++) {
          long res = -1;
          char op = '#';
          if (mf.Flow[i][n + idx.get(a[i] + b[i])] == 1) {
            op = '+';
            res = a[i] + b[i];
          } else if (mf.Flow[i][n + idx.get(a[i] - b[i])] == 1) {
            op = '-';
            res = a[i] - b[i];
          } else if (mf.Flow[i][n + idx.get(a[i] * b[i])] == 1) {
            op = '*';
            res = a[i] * b[i];
          } else {
            throw new Error("The impossible happened.");
          }
          out.println(String.format(" %d %c %d = %d", a[i], op, b[i], res));
        }
      }
    }
    
class MaxFlow {

    public int Nodes;

    public int Source;

    public int Sink;

    public int[][] Capacity;

    LinkedList<Integer>[] Neighbours;

    boolean[][] NeighbourAdded;

    public int[][] Flow;

    public MaxFlow(int Nodes) {
    this.Nodes = Nodes + 2;
    this.Source = Nodes;
    this.Sink = Nodes + 1;
    Neighbours = new LinkedList[this.Nodes];
    for (int i = 0; i < this.Nodes; i++) Neighbours[i] = new LinkedList<Integer>();
    Capacity = new int[this.Nodes][this.Nodes];
    NeighbourAdded = new boolean[this.Nodes][this.Nodes];
    }

    public void Add(int From, int To, int Cap) {
    Capacity[From][To] += Cap;
    if (!NeighbourAdded[From][To]) {
        NeighbourAdded[From][To] = true;
        NeighbourAdded[To][From] = true;
        Neighbours[From].addLast(To);
        Neighbours[To].addLast(From);
    }
    }

    public int NetworkFlow() {
    Flow = new int[Nodes][Nodes];
    int[] parent = new int[Nodes];
    int[] cap = new int[Nodes];
    int total = 0;
    while (true) {
        for (int i = 0; i < Nodes; i++) parent[i] = -1;
        parent[this.Source] = -2;
        cap[this.Source] = Integer.MAX_VALUE;
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.offer(this.Source);
        while (!Q.isEmpty() && parent[this.Sink] == -1) {
        int act = Q.poll();
        for (int next : Neighbours[act]) {
            if (parent[next] == -1 && Capacity[act][next] > Flow[act][next]) {
            parent[next] = act;
            cap[next] = Math.min(cap[act], Capacity[act][next] - Flow[act][next]);
            Q.offer(next);
            }
        }
        }
        if (parent[this.Sink] == -1)
        return total;
        total += cap[this.Sink];
        int j = this.Sink;
        while (j != this.Source) {
        Flow[parent[j]][j] += cap[this.Sink];
        Flow[j][parent[j]] -= cap[this.Sink];
        j = parent[j];
        }
    }
    }
}
