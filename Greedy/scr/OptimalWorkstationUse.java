package Greedy.scr;


import java.util.*;

/**
 * 
 */
public class OptimalWorkstationUse {
    
/**
 * You are given start times si and duration di for all 1≤i≤n

    Given the following research times and a lock time of 10 minutes:

    2 6
    1 2
    17 7
    3 9
    15 6

    the output should be 3.
 */
 /**
   * @param n number of researchers
   * @param m number of minutes after which workstations lock themselves
   * @param start start times of jobs 1 through n. NB: you should ignore start[0]
   * @param duration duration of jobs 1 through n. NB: you should ignore duration[0]
   * @return the number of unlocks that can be saved.
   */
  public static  int solve(int n, int m, int[] start, int[] duration) {

  ArrayList<Job> jobs = new ArrayList<Job>();
  for(int i = 1; i < start.length; i++){
    jobs.add(new Job(start[i], duration[i]));
  }
  int locks = 0;
  Collections.sort(jobs);
  PriorityQueue<Ws> q = new PriorityQueue<>();
  for(Job j : jobs){
    while(!q.isEmpty()){
      if(q.peek().a > j.s){
        break;
      }
      if(q.poll().l >= j.s){
        locks++;
        break;
      }
    }
    q.add(new Ws(j.s+j.d, j.s+j.d+m));
  }
  return locks;
  }
/**
 * A cloud service offers the opportunity for n researchers to submit jobs from workstations in a nearby office
building. Access to these workstations is controlled by an operator. This operator needs to unlock the
workstations for the researchers who come to run their computations at the cloud service. 
However, this operator is very lazy. 
She can unlock the machines remotely from her desk, but does not feel that this menial
task matches her qualifications. She therefore decides to ignore the security guidelines and to simply ask the
researchers not to lock their workstations when they leave, and then assign new researchers to workstations
that are not used any more but that are still unlocked. That way, she only needs to unlock each workstation
for the first researcher using it. Unfortunately, unused workstations lock themselves automatically if they are
unused for more than m minutes. After a workstation has locked itself, the operator has to unlock it again
for the next researcher using it. Given the exact schedule of arriving and leaving researchers, can you tell the
operator how many unlockings she may save by asking the researchers not to lock their workstations when
they leave and assigning arriving researchers to workstations in an optimal way? You may assume that there
are always enough workstations available.
 */
}

class Job implements Comparable<Job>{
  int s, d;
  public Job(int s, int d){
    this.s = s;
    this.d = d;
  }
  
  public int compareTo(Job other){
    int res = Integer.compare(this.s, other.s);
    return res == 0? Integer.compare(this.d, other.d): res;
  }
}

class Ws implements Comparable<Ws>{
    int a, l;
    
    public Ws(int a, int l){
      this.a = a;
      this.l = l;
    }
    
    public int compareTo(Ws other){
      return Integer.compare(this.a, other.a);
    }
}
