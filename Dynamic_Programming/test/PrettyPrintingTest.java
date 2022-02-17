package Dynamic_Programming.test;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import Dynamic_Programming.scr.PrettyPrinting;

import org.junit.*;

public class PrettyPrintingTest {
    private long time = 0;

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
    time = System.currentTimeMillis();
  }

  @After
  public void tearDown() {
    System.out.println("Test '" + name.getMethodName() + "' took " + (System.currentTimeMillis() - time) + "ms");
  }

  private static class ProblemInstance {

    int n;

    int wordCount;

    String[] words;

    public ProblemInstance(int n, int wordCount, String[] words) {
      this.n = n;
      this.wordCount = wordCount;
      this.words = words;
    }
  }

  private static List<String> parseExpectedData(String data) {
    String[] lines = data.split("\\r?\\n");
    return Arrays.asList(lines);
  }

  private static void runTestWithFile(String fileName) {
    InputStream in = new ByteArrayInputStream(WebLab.getData(fileName + ".in").getBytes());
    List<String> expected = parseExpectedData(WebLab.getData(fileName + ".out"));
    assertEquals(expected, PrettyPrinting.solve(in));
  }

  private static ProblemInstance parseInput(String fileName) {
    Scanner sc = new Scanner(WebLab.getData(fileName + ".in"));
    int totalLength = sc.nextInt();
    int wordCount = sc.nextInt();
    String[] words = new String[wordCount];
    for (int i = 0; i < wordCount; i++) {
      words[i] = sc.next();
    }
    sc.close();
    return new ProblemInstance(totalLength, wordCount, words);
  }

  @Test(timeout = 100)
  public void example() {
    InputStream in = new ByteArrayInputStream(("42\n14\nThe Answer to the Great Question of Life, " + "the Universe and Everything is Forty-two.").getBytes());
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("The Answer to the Great Question of Life,");
    expected.add("the Universe and Everything is Forty-two.");
    assertEquals(expected, PrettyPrinting.solve(in));
  }

  @Test(timeout = 100)
  public void exerciseExample() {
    runTestWithFile("example");
  }

  @Test(timeout = 100)
  public void smallNoSlack() {
    runTestWithFile("smallNoSlack");
  }

  @Test(timeout = 100)
  public void smallSlack() {
    runTestWithFile("smallSlack");
  }

  @Test(timeout = 100)
  public void words100() {
    runTestWithFile("100words");
  }

  @Test(timeout = 800)
  public void words1000() {
    runTestWithFile("1000words");
  }
}
