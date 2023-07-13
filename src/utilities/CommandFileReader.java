package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CommandFileReader class. Reads a file containing commands and creates a filtered
 * list of commands.
 */
public class CommandFileReader {
  private final List<List<String>> commandsList = new ArrayList<>();
  private final String commandFile;

  /**
   * Constructor.
   * @param commandFile takes a file and reads through the commands.
   */
  public CommandFileReader(String commandFile) {
    this.commandFile = commandFile;
    this.readFile();
  }

  /**
   * Reads commandFile and initializes commandList values.
   */
  private void readFile() {
    File file = new File(this.commandFile);

    // Use try-catch block in case file not found
    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      // Read through lines in file
      while ((line = bufferedReader.readLine()) != null) {
        line = line.trim(); // gets rid of extra spaces
        if (line.isEmpty() || line.startsWith("#")) {
          continue; // skip empty or comment lines
        }
        // Make list of words per line to go in commands list
        List<String> wordsList = Arrays.asList(line.split("\\s+"));
        this.commandsList.add(wordsList);
      }
      // Print error to terminal
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Getter method for commandsList.
   * @return List<List<String>>
   */
  public List<List<String>> getCommandsList() {
    return this.commandsList;
  }

}
