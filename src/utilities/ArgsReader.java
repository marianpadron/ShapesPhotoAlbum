package utilities;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ArgsReader class. Reads args passed through the command line, checks that required files for the
 * controller are passed, and returns an ordered list of the commands.
 */
public class ArgsReader {
  private String inputFile = null;
  private String output = null;
  private String viewType = null;
  private int xmax = 1000; // default value
  private int ymax = 1000; // default value
  private List<String> argsList = new ArrayList<>();

  /**
   * Constructor. Parses through args and initializes attributes.
   * @param args String [] args
   */
  public ArgsReader(String args[]) throws FileNotFoundException {
    // Check correct amount of args passed
    if (args.length < 2) {
      throw new IllegalArgumentException("At least two arguments please.");
    }

    // Get rid of potential brackets
    for (int i = 0; i < args.length; i++) {
      args[i] = args[i].replace("[", "").replace("]", "");
    }

    // Iterate through args
    for (int i = 0; i < args.length; i ++) {

      // Check -in file
      if (args[i].equalsIgnoreCase("-in")) {
        this.inputFile = args[i + 1];

        // Check -view file
      } else if (args[i].equalsIgnoreCase("-view")
              || args[i].equalsIgnoreCase("-v")) {
        this.viewType = args[i + 1].toLowerCase();

        // Check -out file
      } else if (args[i].equalsIgnoreCase("-out")) {
        this.output = args[i + 1];

        // Check for xmax and ymax
      } else {
        try {
          if (xmax == 1000) {
            this.xmax = Integer.parseInt(args[i]);
          } else {
            this.ymax = Integer.parseInt(args[i]);
          }
        } catch (NumberFormatException e) {
          // ignore if args are not integers
        }
      }
    }

    // Check valid args
    this.checkArgs();
    // Add args to String List
    this.makeArgsList();

  }

  /**
   * Checks that initialized arg variables are valid.
   */
  private void checkArgs() throws FileNotFoundException {
    // Check input and view passed
    if (inputFile == null || viewType == null) {
      throw new IllegalArgumentException("Input file and view file are required.");
    }
    // Check valid file path
    if (!Files.exists(Paths.get(this.inputFile))) {
      throw new FileNotFoundException("File could not be found.");
    }

    // Check valid view type
    if (!viewType.equals("web") && !viewType.equals("graphical")) {
      throw new IllegalArgumentException("Invalid view type.");
    }

    // Check output file provided if web view
    if (viewType.equals("web") && output == null) {
      throw new IllegalArgumentException("No output provided for web view.");
    }
  }

  /**
   * Adds arg variables to argsList in an ordered manner to be easily accessed by index.
   * Index 0:  -in file
   * Index 1: -view type
   * Index 2: -out file
   * Index 3: xmax
   * Index 4: ymax
   */
  private void makeArgsList() {
    this.argsList.add(this.inputFile);
    this.argsList.add(this.viewType);
    this.argsList.add(this.output);
    this.argsList.add(String.valueOf(this.xmax));
    this.argsList.add(String.valueOf(this.ymax));
  }

  /**
   * Returns the argsList.
   * @return List<String>
   */
  public List<String> getArgsList() {
    return this.argsList;
  }

}
