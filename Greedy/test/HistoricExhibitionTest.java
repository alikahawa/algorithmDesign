package Greedy.test;

import java.io.*;
import java.nio.charset.*;
import java.util.Scanner;
import java.util.concurrent.*;

class fpc {

    static class RunError extends RuntimeException {
  
      private static final long serialVersionUID = 42;
    }
  
    static class TimeLimit extends RuntimeException {
  
      private static final long serialVersionUID = 42;
    }
  
    static class WrongAnswer extends RuntimeException {
  
      private static final long serialVersionUID = 42;
    }
  }
  
  public class HistoricExhibitionTest {
  
    public static void runTestWithFile(String fileName) {
      ByteArrayInputStream inStream = new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8));
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      try {
        long start = System.currentTimeMillis();
        Executors.newSingleThreadExecutor().submit(() -> {
          Solution.run(inStream, new PrintStream(outStream));
          try {
            outStream.flush();
          } catch (IOException e) {
            throw new fpc.RunError();
          }
        }).get(timeout, // throws the exception if one occurred during the invocation
        TimeUnit.MILLISECONDS);
        System.out.println(fileName + ": Took " + (System.currentTimeMillis() - start) + " ms");
      } catch (TimeoutException e) {
        System.out.println(fileName + ": Timeout after " + timeout + " ms");
        throw new fpc.TimeLimit();
      } catch (ExecutionException e) {
        System.out.println(fileName + ": " + e.getCause());
        throw new fpc.RunError();
      } catch (Throwable e) {
        System.out.println(fileName + ": " + e);
        throw new fpc.RunError();
      }
      String ans = WebLab.getData(fileName + ".ans").trim();
      String out = outStream.toString().trim();
      if (!verifyAns(new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8)), ans, out)) {
        System.out.println(fileName + ": Expected '" + ans + "', but got '" + out + "'");
        throw new fpc.WrongAnswer();
      }
    }
  
    private static boolean verifyAns(ByteArrayInputStream byteArrayInputStream, String ans, String out) {
      Scanner team = new Scanner(out);
      Scanner ours = new Scanner(ans);
      Scanner input = new Scanner(new BufferedReader(new InputStreamReader(byteArrayInputStream)));
      if (!team.hasNext() && !ours.hasNext()) {
        return true;
      }
      String team_first = team.next();
      if (team_first.equals("impossible")) {
        return ours.next().equals(team_first);
      }
      int n = input.nextInt();
      int k = input.nextInt();
      int[][] sheets = new int[n][2];
      for (int i = 0; i < n; i++) {
        sheets[i][0] = input.nextInt();
        sheets[i][1] = input.nextInt();
      }
      boolean[] used = new boolean[n];
      int s = Integer.parseInt(team_first);
      for (int i = 0; i < k; i++) {
        if (used[s - 1]) {
          return false;
        }
        used[s - 1] = true;
        int wanted_colour = input.nextInt();
        if (sheets[s - 1][0] != wanted_colour && sheets[s - 1][1] != wanted_colour) {
          return false;
        }
        if (i < k - 1) {
          s = team.nextInt();
        }
      }
      if (ours.next().equals("impossible")) {
        return false;
      }
      return true;
    }
  
    private static final int timeout = 1000;
  
    @Test
    public void specTests() {
      runTestWithFile("1000_1000_400");
      runTestWithFile("100_100_20");
      runTestWithFile("100_60_60");
      runTestWithFile("10_7_8");
      runTestWithFile("5_3_5");
      runTestWithFile("all_duplicate_colours2");
      runTestWithFile("all_duplicate_colours");
      runTestWithFile("all-one-color");
      runTestWithFile("all-one-color-not-enough");
      runTestWithFile("insufficient");
      runTestWithFile("minimal-2");
      runTestWithFile("minimal-3");
      runTestWithFile("minimal-4");
      runTestWithFile("minimal-impossible");
      runTestWithFile("minimal");
      runTestWithFile("need-all");
      runTestWithFile("nicky");
      runTestWithFile("one_colour");
      runTestWithFile("peaky");
      runTestWithFile("rand_10");
      runTestWithFile("rand-1");
      runTestWithFile("rand_1");
      runTestWithFile("rand_2");
      runTestWithFile("rand_3");
      runTestWithFile("rand_4");
      runTestWithFile("rand_5");
      runTestWithFile("rand_6");
      runTestWithFile("rand_7");
      runTestWithFile("rand_8");
      runTestWithFile("rand_9");
      runTestWithFile("reverse_nicky");
      runTestWithFile("simple_wave_possible");
      runTestWithFile("simple_wave_reverse_possible");
      runTestWithFile("small_1");
      runTestWithFile("smallest-impossible");
      runTestWithFile("threerow");
    }
  }
  