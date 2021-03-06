package Divide_and_Conquer.scr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Scanner;

public class CanyonCrossing {
    public static void run(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
        new CanyonCrossing().solve(sc, out);
        sc.close();
      }
      
      /**
       * Examples are at the end of this file
       * @param R
       * @param C
       * @param K
       * @param H
       * @param grid
       * @return
       */
      private static boolean isPossible(int R, int C, int K, int H, int[][] grid) {
        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        boolean[][] done = new boolean[R][C];
        ArrayDeque<Integer> work = new ArrayDeque<Integer>();
        ArrayDeque<Integer> nextWork = new ArrayDeque<Integer>();
        for (int i = 0; i <= K; i++) {
          if (i == 0) {
            // setup
            for (int j = 0; j < C; j++) {
              if (grid[i][j] >= H)
                work.add(j);
              else
                nextWork.add(j);
              done[0][j] = true;
            }
          }
          while (!work.isEmpty()) {
            int pos = work.remove();
            int x = pos / C;
            int y = pos % C;
            if (x == R - 1)
              return true;
            for (int j = 0; j < 4; j++) {
              if (x + dx[j] < 0 || x + dx[j] >= R || y + dy[j] < 0 || y + dy[j] >= C)
                continue;
              if (done[x + dx[j]][y + dy[j]])
                continue;
              if (grid[x + dx[j]][y + dy[j]] >= H)
                work.add((x + dx[j]) * C + y + dy[j]);
              else
                nextWork.add((x + dx[j]) * C + y + dy[j]);
              done[x + dx[j]][y + dy[j]] = true;
            }
          }
          ArrayDeque<Integer> t = work;
          work = nextWork;
          nextWork = t;
        }
        return false;
      }
    
      public void solve(Scanner sc, PrintStream out) {
        int R = sc.nextInt();
        int C = sc.nextInt();
        int K = sc.nextInt();
        int[][] grid = new int[R][C];
        for (int i = 0; i < R; i++) {
          for (int j = 0; j < C; j++) {
            grid[i][j] = sc.nextInt();
          }
        }
        int low = 0;
        int high = 1000000001;
        while (high - low > 1) {
          int mid = (high + low) / 2;
          if (isPossible(R, C, K, mid, grid))
            low = mid;
          else
            high = mid;
        }
        out.println(low);
      }
}

/**
 * The Bridge And Passageway Creators are responsible for making new paths through the
local mountains. They have approved your
plan to build a new route through your favorite canyon. You feverishly start
working on this beautiful new path, when you realize you failed to take into
account the flow of a nearby river: the canyon is flooded! Apparently
this happens once every blue moon, making some parts of the path inaccessible.
Because of this, you want to build a path such that the lowest point on the path
is as high as possible. You quickly return to the village and
use all of your money to buy rope bridges. You plan to use these to circumvent
the lowest parts of the canyon.

Your map of the canyon consists of a rectangular grid of cells, each containing
a number giving the height of the terrain at that cell. The path will go
from the south side of the canyon (bottom on your map) to the north side (top of
your map), moving through a connected sequence of cells. Two cells are
considered connected if and only if they share an edge. In particular, two
diagonally touching cells are not considered to be connected. This means that
for any cell not on the edge of the map, there are 4 other cells connected to
it. The left of figure 1 contains the map for the first sample input.
Figure 1: fig1.pdf

The path through the canyon can start on any of the bottom cells of the grid,
and end on any of the cells in the top tow, like the two paths on the right in
Figure 1.
The lowest height is given by the lowest
height of any of the cells the paths goes through. Each bridge can be used
to cross exactly one cell. This cell is then not taken into account
when calculating the minimal height of the path. Note that is allowed to chain
multiple bridges to use them to cross multiple cells,

Given the map of the canyon and the number of bridges available, find
the lowest height of an optimal path.

Figure 2: fig2.pdf
 */

 /**
  * Input

    A single line containing three integers: 1???R???1000

and
1???C???1000, the size of the map, and 0???K???R???1
,
the number of bridges you can build.
This is followed by R
lines each containing C integers. The
j-th integer on the i-th line corresponds to the height
0???Hi,j???109 of the canyon at point (i,j)

    . The first line
    corresponds to the northern edge of the canyon, the last line to the
    southern edge.

Output

Output a single integer, the lowest height of the optimal path.
Examples

For each example, the first block is the input and the second block is the corresponding output.
Example 1

5 3 1
1 1 3
3 3 3
0 0 0
2 2 1
1 2 1

2

Example 2

5 3 3
2 1 1
2 1 1
1 1 1
1 1 2
1 1 2

2

Example 3

3 2 2
1 1
4 4
1 2

4

  */