package Dynamic_Programming.test;

import java.util.concurrent.Executors;

import Dynamic_Programming.scr.KnapsackPacking;
import Greedy.test.Test;

import java.io.*;
import java.nio.charset.*;
import java.util.concurrent.*;
// import org.junit.*;

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

/**
 * 
 */
public class KnapsackPackingTest {
    public static void runTestWithFile(String fileName) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
          long start = System.currentTimeMillis();
          Executors.newSingleThreadExecutor().submit(() -> {
            KnapsackPacking.run(inStream, new PrintStream(outStream));
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
    
      private static final int timeout = 2000;
    
      @Test(timeout = 16000)
      public void specTests() {
        runTestWithFile("01_test");
        runTestWithFile("02_large");
        runTestWithFile("03_small");
        runTestWithFile("04_large-2");
        runTestWithFile("05_exponential");
        runTestWithFile("06_zero");
        runTestWithFile("07_one");
        runTestWithFile("08_zero_one");
        runTestWithFile("09_zero_one_possible");
        runTestWithFile("10_large_ints");
        runTestWithFile("11_2_large_ints");
        runTestWithFile("12_solution_offset");
        runTestWithFile("14_solution_offset_3");
        runTestWithFile("16_input_prefix");
        runTestWithFile("17_weird_convolution");
        runTestWithFile("18_zero_many_ones");
        runTestWithFile("constant_10_0_fail");
        runTestWithFile("constant_10_0");
        runTestWithFile("constant_18_131039_fail");
        runTestWithFile("constant_18_131039");
        runTestWithFile("constant_18_1_fail");
        runTestWithFile("constant_18_1");
        runTestWithFile("constant_8_1_fail");
        runTestWithFile("constant_8_1");
        runTestWithFile("fixed_exponential_fail");
        runTestWithFile("fixed_exponential");
        runTestWithFile("fixed_exponentialoffset_fail");
        runTestWithFile("fixed_exponentialoffset");
        runTestWithFile("fixed_linear_fail");
        runTestWithFile("fixed_linear");
        runTestWithFile("fixed_maxvalue_15_fail");
        runTestWithFile("fixed_maxvalue_15");
        runTestWithFile("fixed_maxvalue_1_fail");
        runTestWithFile("fixed_maxvalue_1");
        runTestWithFile("fixed_maxvalue_spread_fail");
        runTestWithFile("fixed_maxvalue_spread");
        runTestWithFile("fixed_onetworepeat_fail");
        runTestWithFile("fixed_onetworepeat");
        runTestWithFile("fixed_repeats_fail");
        runTestWithFile("fixed_repeats");
        runTestWithFile("fixed_sublinear2_fail");
        runTestWithFile("fixed_sublinear2");
        runTestWithFile("fixed_sublinear_fail");
        runTestWithFile("fixed_sublinear");
        runTestWithFile("fixed_superlinear2_fail");
        runTestWithFile("fixed_superlinear2");
        runTestWithFile("fixed_superlinear3_fail");
        runTestWithFile("fixed_superlinear3");
        runTestWithFile("fixed_superlinear_fail");
        runTestWithFile("fixed_superlinear");
        runTestWithFile("fixed_zeroone_fail");
        runTestWithFile("fixed_zeroone");
        runTestWithFile("fixed_zeroonerepeat_fail");
        runTestWithFile("fixed_zeroonerepeat");
        runTestWithFile("minimal_1");
        runTestWithFile("minimal_2");
        runTestWithFile("uniform_15_0_5_fail");
        runTestWithFile("uniform_15_0_5");
        runTestWithFile("uniform_18_0_13421700_fail");
        runTestWithFile("uniform_18_0_13421700");
        runTestWithFile("uniform_18_0_13421772_fail");
        runTestWithFile("uniform_18_0_13421772");
        runTestWithFile("uniform_18_0_3_fail");
        runTestWithFile("uniform_18_0_3");
        runTestWithFile("uniform_18_10000_10100_fail");
        runTestWithFile("uniform_18_10000_10100");
        runTestWithFile("uniform_broken");
      }
}
