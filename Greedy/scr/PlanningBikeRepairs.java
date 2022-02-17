package Greedy.scr;

import java.io.*;
import java.util.*;

/**
 * Mike runs a bike repair service for students.
    Students can plan a time to bring in their bike on his website, where they also enter a description of what is broken.
    Mike is very good at his job and he can flawlessly predict how long each repair will cost.
    To cater the need of students to have a working bike at all time, he promises his customers to finish the repair as soon as possible after the bike is brought in.

    Now Mike wants to know how many of his employees to schedule for a day.
    He has the list of repairs for this day and he wants you to develop an algorithm to tell him how many people he needs.

    You get the list in the following format: On the first line is the amount of repairs.
    The next lines list the repairs with on each line the time the repair starts followed by the time the repair will take.
 */
public class PlanningBikeRepairs {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
  public static String run(InputStream in) {
    return new PlanningBikeRepairs().solve(in);
  }

  /**
   * Example input:

    6
    6 1
    3 5
    1 2
    6 3
    8 3
    1 4

    Example output:

    3

   * @param in
   * @return
   */
  public String solve(InputStream in) {
    Scanner sc = new Scanner(in);
    int n = sc.nextInt();
    Job[] jobs = new Job[n];
    for(int i = 0; i < n; i++){
        jobs[i] = new Job(sc.nextInt(), sc.nextInt());
    }
    sc.close();
    int emp = 0;
    Arrays.sort(jobs);
    for(int i = 1; i < n; i++){
        int cur = 0;
        for(int j = 0; j <= i; j++){
        if(jobs[j].s+ jobs[j].c > jobs[i].s){
            cur++;
        }
        }
        if(cur > emp){
        emp = cur;
        }
    }
    return Integer.toString(emp);
    }
}

class Job implements Comparable<Job>{
    int s,c;
    public Job(int s, int c){
      this.s = s;
      this.c = c;
    }
    
    public int compareTo(Job other){
      int res = Integer.compare(this.s, other.s);
      return res == 0 ? Integer.compare(this.c,other.c) : res;
    }
  }