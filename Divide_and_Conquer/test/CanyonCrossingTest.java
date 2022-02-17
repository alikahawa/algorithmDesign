package Divide_and_Conquer.test;

import java.io.*;
import java.nio.charset.*;
import java.util.concurrent.*;
// import org.junit.*;

import Divide_and_Conquer.scr.CanyonCrossing;

public class CanyonCrossingTest {
    public static void runTestWithFile(String fileName) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
          long start = System.currentTimeMillis();
          Executors.newSingleThreadExecutor().submit(() -> {
            CanyonCrossing.run(inStream, new PrintStream(outStream));
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
        if (!ans.equals(out)) {
          System.out.println(fileName + ": Expected '" + ans + "', but got '" + out + "'");
          throw new fpc.WrongAnswer();
        }
      }
    
      private static final int timeout = 8000;
    
      @Test
      public void specTests() {
        runTestWithFile("1_1");
        runTestWithFile("1_2");
        runTestWithFile("1_col_0");
        runTestWithFile("1_col_500");
        runTestWithFile("1_col_999");
        runTestWithFile("1_row");
        runTestWithFile("2_1_bridge");
        runTestWithFile("2_1");
        runTestWithFile("bridge_bottom");
        runTestWithFile("bridge_corner_A");
        runTestWithFile("bridge_corner_B");
        runTestWithFile("bridge_corner_C");
        runTestWithFile("bridge_corner_D");
        runTestWithFile("bridge_diag_A");
        runTestWithFile("bridge_diag_B");
        runTestWithFile("bridge_diag_C");
        runTestWithFile("bridge_diag_D");
        runTestWithFile("bridge_down");
        runTestWithFile("bridge_left");
        runTestWithFile("bridge_right");
        runTestWithFile("bridge_top");
        runTestWithFile("bridge_up");
        runTestWithFile("continuous_bridge");
        runTestWithFile("directiontest");
        // runTestWithFile("fingers1000x1000");
        runTestWithFile("gap_bottom");
        runTestWithFile("gap_diag_A");
        runTestWithFile("gap_diag_B");
        runTestWithFile("gap_diag_C");
        runTestWithFile("gap_diag_D");
        runTestWithFile("gap_down");
        runTestWithFile("gap_left");
        runTestWithFile("gap_right");
        runTestWithFile("gap_top");
        runTestWithFile("gap_up");
        // runTestWithFile("grate1000x1000");
        runTestWithFile("plateau1000x1000");
        runTestWithFile("plateau1000x1000K1");
        runTestWithFile("plateau1000x1000K500");
        // runTestWithFile("plateau1000x1000K999");
        runTestWithFile("plateau1000x100");
        runTestWithFile("plateau1000x100K1");
        runTestWithFile("plateau1000x100K500");
        //runTestWithFile("plateau1000x100K999");
        runTestWithFile("plateau100x100");
        runTestWithFile("plateau100x100K1");
        runTestWithFile("plateau100x100K50");
        runTestWithFile("plateau100x100K99");
        // runTestWithFile("plateauhigh1000x1000");
        runTestWithFile("separated_bridges");
        runTestWithFile("separated_edge_bridges");
        runTestWithFile("snake1000x97");
        runTestWithFile("snake1000x997");
        runTestWithFile("snake100x97");
        runTestWithFile("spiral1");
        runTestWithFile("spiral2");
        runTestWithFile("staircase1000x1000");
        runTestWithFile("staircase1000x1000K1");
        //runTestWithFile("staircase1000x1000K500");
        //runTestWithFile("staircase1000x1000K999");
        runTestWithFile("staircase1000x100");
        runTestWithFile("staircase1000x100K1");
        //runTestWithFile("staircase1000x100K500");
        // runTestWithFile("staircase1000x100K999");
        runTestWithFile("staircase100x100");
        runTestWithFile("staircase100x100K1");
        runTestWithFile("staircase100x100K50");
        runTestWithFile("staircase100x100K99");
      }
    }
    
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
    
