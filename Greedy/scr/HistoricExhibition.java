package Greedy.scr;

import java.io.*;
import java.util.*;

public class HistoricExhibition {
    public static void run(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
        new HistoricExhibition().solve(sc, out);
        sc.close();
      }
    
      /**
       * Examples

        For each example, the first block is the input and the second block is the corresponding output.
        Example 1

        4 3
        1 2
        4 5
        2 3
        2 2
        1 2 3

        1
        4
        3

        Example 2

        2 2
        1 1
        2 3
        1 1

        impossible

        Example 3

        2 3
        9 8
        4 5
        4 9 5

        impossible

        Source: BAPC 2019
       */
      /**
       * 
       * @param sc
       * @param out
       */
      public void solve(Scanner sc, PrintStream out) {
        int C = 10000;
        int n = sc.nextInt();
        int k = sc.nextInt();
        /*ArrayList<Stack<Integer>> cc = new ArrayList<Stack<Integer>>();
            ArrayList<Stack<Integer>> cc = new ArrayList<Stack<Integer>>();*/
        Stack<Integer>[] cc = new Stack[C + 1];
        Stack<Integer>[] cc2 = new Stack[C + 1];
        Stack<Integer>[] needed = new Stack[C + 1];
        for (int i = 0; i <= C; i++) {
          cc[i] = new Stack<Integer>();
          cc2[i] = new Stack<Integer>();
          needed[i] = new Stack<Integer>();
        }
        for (int i = 1; i <= n; i++) {
          int a = sc.nextInt();
          int b = sc.nextInt();
          if (a == b) {
            cc[a].push(i);
          } else if (a > b) {
            cc2[b].push(i);
          } else {
            cc2[a].push(i);
          }
        }
        int[] ans = new int[k];
        int maxi = -1;
        for (int i = 0; i < k; i++) {
          int c = sc.nextInt();
          needed[c].push(i);
          if (i > maxi)
            maxi = i;
        }
        for (int c = 1; c <= C; c++) {
          while (!needed[c].empty()) {
            if (!cc[c].empty()) {
              ans[needed[c].pop()] = cc[c].pop();
            } else if (!cc2[c - 1].empty()) {
              ans[needed[c].pop()] = cc2[c - 1].pop();
            } else if (!cc2[c].empty()) {
              ans[needed[c].pop()] = cc2[c].pop();
            } else {
              out.println("impossible");
              return;
            }
          }
        }
        for (int i = 0; i < k; i++) {
          out.println(ans[i]);
        }
      }
}

/**
 * The Benelux Artistic Pottery Consortium is preparing for an exhibit of its most
prized urns and vases at a gallery in Nijmegen.
Due to the sheer number of vases to be put on display the gallery has trouble
finding a pedestal of the right size for every single vase. 
They have pedestals available that can either be placed normally or upside down and 
can be characterised by the diameter of their top and bottom surface. 
Moreover, the diameter of the top and bottom varies by at most
one unit length.

For artistic reasons, it is important that the diameter of the base of a vase matches the diameter of the
surface of the pedestal it is placed on. 
You have been asked to find a way to place all the vases on available pedestals. 
In order to make this work, you might need to turn some of the pedestals upside down. 
For example, Figure 1 shows a possible assignment of pedestals to vases for sample input 1. 
Assist the gallery by writing a program to compute such an assignment.

Input
    The first line contains two integers
    0≤p,v≤104

the number of pedestals and the
number of vases.
Then follow p
lines, the i-th of which contains two
integers 1≤ai,bi≤104 denoting the diameters of the different sides of pedestal i.
It is given that |ai−bi|≤1

Then follows a single line containing v
integers 1≤c1,…,cv≤104, where ci denotes the diameter of vase i

Output
    Output v

distinct integers 1≤x1,…,xv≤p such
that vase i can stand on pedestal xi

, or print `impossible if no assignment of vases to pedestals exists`.

If there are multiple possible solutions, you may output any one of them.
 */
