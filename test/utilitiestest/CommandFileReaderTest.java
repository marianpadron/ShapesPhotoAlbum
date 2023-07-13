package utilitiestest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import utilities.CommandFileReader;

/**
 * CommandFileReaderTest Junit test class.
 */
public class CommandFileReaderTest {

  private String test_file = "test_input.txt";
  private int lines = 10;
  private String expected = "[[shape, myoval, oval, 500, 100, 60, 30, 0, 255, 1], "
          + "[shape, myrect, rectangle, 200, 200, 50, 100, 255, 0, 0], "
          + "[snapShot, After, first, selfie], [resize, myrect, 25, 100], [snapShot, 2nd, selfie], "
          + "[color, myrect, 0, 0, 255], [move, myoval, 500, 400], [snapShot], [remove, myrect], "
          + "[snapshot, Selfie, after, removing, the, rectangle, from, the, picture]]";
  private CommandFileReader reader;

  /**
   * Initialize object and read test file.
   */
  @Before
  public void readFile() {
    CommandFileReader reader = new CommandFileReader(this.test_file);
    this.reader = reader;
  }

  /**
   * Test file was read and returned list is correct.
   */
  @Test
  public void test1(){
    List<List<String>> list = this.reader.getCommandsList();
    assertEquals(lines, list.size());
    assertEquals(expected, list.toString());
  }
}
