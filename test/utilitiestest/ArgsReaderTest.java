package utilitiestest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import utilities.ArgsReader;

/**
 * ArgsReader Junit test class.
 */
public class ArgsReaderTest {

  private String input = "buildings.txt";
  private String output = "out.html";
  private String graphical = "graphical";
  private String web = "web";
  private String xmax = "800";
  private String ymax = "800";

  /**
   * Test less than 2 args passed
   */
  @Test(expected = IllegalArgumentException.class)
  public void test1() throws FileNotFoundException {
    String[] args = {"arg1"};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);

  }
  /**
   * Tests input file not found.
   */
  @Test(expected = FileNotFoundException.class)
  public void test2() throws FileNotFoundException {
    String[] args = {"-in", "test.txt", "-out", output, "-view", web, xmax, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);

  }

  /**
   * Tests args list returns list with correct data.
   */
  @Test
  public void test3() throws FileNotFoundException {
    String[] args = {"-in", input, "-out", output, "-view", web, xmax, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
    List<String> argsList = reader.getArgsList();
    assertEquals(input, argsList.get(0));
    assertEquals(web, argsList.get(1));
    assertEquals(output, argsList.get(2));
    assertEquals(xmax, argsList.get(3));
    assertEquals(ymax, argsList.get(4));
  }

  /**
   * Tests default of xmax and ymax if no values passed.
   */
  @Test
  public void test4() throws FileNotFoundException {
    String[] args = {"-in", input, "-out", output, "-view", web};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
    List<String> argsList = reader.getArgsList();
    assertEquals("1000", argsList.get(3));
    assertEquals("1000", argsList.get(4));
  }

  /**
   * Tests request of -out file if web view passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test5() throws FileNotFoundException {
    String[] args = {"-in", input,"-view", web, xmax, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
  }

  /**
   * Tests for passing of views as -v.
   */
  @Test
  public void test6() throws FileNotFoundException {
    String[] args = {"-in", input, "-out", output, "-v", web, xmax, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
    List<String> argsList = reader.getArgsList();
    assertEquals(input, argsList.get(0));
    assertEquals(web, argsList.get(1));
    assertEquals(output, argsList.get(2));
    assertEquals(xmax, argsList.get(3));
    assertEquals(ymax, argsList.get(4));
  }

  /**
   * Tests correct args list returned if args passed in random order.
   */
  @Test
  public void test7() throws FileNotFoundException {
    String[] args = {"-view", graphical, xmax, "-in", input, "-out", output, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
    List<String> argsList = reader.getArgsList();
    assertEquals(input, argsList.get(0));
    assertEquals(graphical, argsList.get(1));
    assertEquals(output, argsList.get(2));
    assertEquals(xmax, argsList.get(3));
    assertEquals(ymax, argsList.get(4));
  }

  /**
   * Tests no input file.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test8() throws FileNotFoundException {
    String[] args = {"-out", output, "-view", web, xmax, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
  }

  /**
   * Tests no view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test9() throws FileNotFoundException {
    String[] args = {"-out", output, "-in", input, xmax, ymax};

    // Redirect standard input to simulate command line arguments
    ByteArrayInputStream in = new ByteArrayInputStream(String.join(" ", args).getBytes());
    System.setIn(in);

    // Redirect standard output to capture program output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // Run the program
    ArgsReader reader = new ArgsReader(args);
  }
}

