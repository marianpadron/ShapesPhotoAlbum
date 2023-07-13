package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utilities.shapes.I2DShape;
import utilities.shapes.ShapeTypes;
import model.ShapesPhotoAlbum;
import utilities.Snapshot;
import utilities.CommandFileReader;
import views.graphical.ViewFrame;
import views.web.HtmlView;

/**
 * Controller for PhotoAlbum MVC program.
 */
public class PhotoAlbumController implements IPhotoAlbumController {
  private enum COMMANDS {SHAPE, MOVE, RESIZE, COLOR, REMOVE, SNAPSHOT;}
  private ShapesPhotoAlbum model;
  private List<List<String>> commandsList = null;
  private ViewFrame viewFrame;
  private int snapshotIndex = 0;
  private List<String> args;
  private HtmlView htmlView;

  /**
   * Constructor. Takes list of command line arguments and ShapesPhotoAlbum model.
   * @param args List<String> args
   * @param model ShapesPhotoAlbum
   */
  public PhotoAlbumController(List<String> args, ShapesPhotoAlbum model) {
    // Initialize args and model object
    this.args = args;
    this.model = model;

    // Get filtered shapes command list
    this.commandsList = new CommandFileReader(args.get(0)).getCommandsList();
  }

  /**
   * Begins MVC program with graphical view.
   * @param view ViewFrame object for rendering graphics
   */
  @Override
  public void run(ViewFrame view) {
    // Parse through file commands and pass creation commands to model
    this.executeCommands();

    this.viewFrame = view; // initialize views object
    this.passGraphics(); // pass snapshots to views
    this.viewFrame.startView(); // start views rendering

  }

  /**
   * Begins MVC program with web view.
   * @param header String header for HTML rendering
   */
  @Override
  public void run(String header) {
    // Parse through file commands and pass shape creation commands to model
    this.executeCommands();

    // Initialize view object
    this.htmlView = new HtmlView(this.args.get(2), header); // pass outfile and name
    this.snapshotsToWriter(); // pass snapshots to views
    this.htmlView.closeHeader(); // finish html file
    this.htmlView.startView(); // start views rendering
  }

  /**
   * Goes through list of commands and passes them to model to create shapes and snapshots.
   */
  private void executeCommands() {
    // Get list of validCommands from COMMANDS enum
    List<String> validCommands = Stream.of(COMMANDS.values()).
            map(COMMANDS::name).toList();

    // Iterate through commandsList
    for(int i = 0; i < this.commandsList.size(); i++) {

      // Check that line contains a valid command
      if(validCommands.contains(this.commandsList.get(i).get(0).toUpperCase())) {

        // Get line
        List<String> line = this.commandsList.get(i);
        // Get command
        String command = line.get(0).toUpperCase();

        // Check what the command is
        if (command.equals(COMMANDS.SHAPE.toString())) {
          this.createShape(line);

        } else if (command.equals(COMMANDS.MOVE.toString())) {
          this.moveShape(line);

        } else if (command.equals(COMMANDS.RESIZE.toString())) {
          this.resizeShape(line);

        } else if (command.equals(COMMANDS.COLOR.toString())) {
          this.colorShape(line);

        } else if (command.equals(COMMANDS.REMOVE.toString())) {
          this.removeShape(line);

        } else if (command.equals(COMMANDS.SNAPSHOT.toString())) {
          this.takeSnapshot(line);
        }
      }
      // keep iterating until finds a valid command
    }
  }

  /**
   * Gets a snapshot with the current snapshotIndex from the model and passes it to views for
   * rendering.
   */
  private void passGraphics() {
    this.viewFrame.receiveGraphics(this.model.getSnapshots().get(this.snapshotIndex));
  }

  /**
   * Goes through snapshots and tells HTML renderer what shapes to write to file.
   */
  private void snapshotsToWriter() {
    if(this.htmlView == null || this.htmlView.getClass() != HtmlView.class) {
      System.err.println("Error: Invalid HTML writer object.");
      System.exit(1);
    }

    // Get list of snapshots from model
    List<Snapshot> snapshots = this.model.getSnapshots();

    // For each snapshot tell htmlWriter to write to file
    for(Snapshot snapshot : snapshots) {
      String title = "ID: " + snapshot.getID() + "  " + snapshot.getDescription();

      // Open new snapshot page with snapshot description
      this.htmlView.openPage(title);

      // Add shapes to page
      List<I2DShape> shapes = snapshot.getShapes();
      for (I2DShape shape : shapes) {
        String length = String.valueOf(shape.getLength());
        String width = String.valueOf(shape.getWidth());
        String xpos = String.valueOf(shape.getX());
        String ypos = String.valueOf(shape.getY());
        String rgb = shape.getRGB().getR() + "," + shape.getRGB().getG() + ","
                + shape.getRGB().getB();

        // Add rectangle shape
        if (shape.getType() == ShapeTypes.RECTANGLE) {
          this.htmlView.newRect(shape.getName(), length, width, xpos, ypos, rgb);

        // Add oval shape
        } else if (shape.getType() == ShapeTypes.OVAL) {
          this.htmlView.newOval(shape.getName(), length, width, xpos, ypos, rgb);
        }
      }
      // Close snapshot page
      this.htmlView.closePage();
    }
  }

  /**
   * Tells models to create a shape given the passed list of commands.
   * @param line List<String>
   */
  private void createShape(List<String> line) {
    String name = line.get(1);
    String type = line.get(2);
    int xpos = Integer.parseInt(line.get(3));
    int ypos = Integer.parseInt(line.get(4));
    int width = Integer.parseInt(line.get(5));
    int length = Integer.parseInt(line.get(6));
    int red = Integer.parseInt(line.get(7));
    int green = Integer.parseInt(line.get(8));
    int blue = Integer.parseInt(line.get(9));

    this.model.makeShape(type, name, length, width, xpos, ypos, red, green, blue);
  }

  /**
   * Tells model to move a shape given the passed list of commands.
   * @param line List<String>
   */
  private void moveShape(List<String> line) {
    String name = line.get(1);
    int xpos = Integer.parseInt(line.get(2));
    int ypos = Integer.parseInt(line.get(3));

    this.model.moveShape(name, xpos, ypos);
  }

  /**
   * Tells models to resize a shape given the passed list of commands.
   * @param line List<String>
   */
  private void resizeShape(List<String> line) {
    String name = line.get(1);
    int width = Integer.parseInt(line.get(2));
    int length = Integer.parseInt(line.get(3));

    this.model.resizeShape(name, length, width);
  }

  /**
   * Tells model to color a shape given the passed list of commands.
   * @param line List<String>
   */
  private void colorShape(List<String> line) {
    String name = line.get(1);
    int red = Integer.parseInt(line.get(2));
    int green = Integer.parseInt(line.get(3));
    int blue = Integer.parseInt(line.get(4));

    this.model.colorShape(name, red, green, blue);
  }

  /**
   * Tells model to remove a shape given the passed list of commands.
   * @param line List<String>
   */
  private void removeShape(List<String> line) {
    String name = line.get(1);
    this.model.deleteShape(name);
  }

  /**
   * Tells model to take a snapshot with provided description (optional).
   * @param line List<String>
   */
  private void takeSnapshot(List<String> line) {
    String description = "";
    if (line.size() > 1) {
      for (int i = 1; i < line.size(); i++) {
        if (i == line.size() - 1) {
          description += line.get(i);
        } else {
          description += line.get(i) + " ";
        }
      }
    }

    this.model.takeSnapshot(description);
  }

  /**
   * Checks request from graphical view for next snapshot, passes snapshot to view if end of
   * snapshot list has not been reached. If no more snapshots, tells view to show message to user.
   */
  @Override
  public void getNext() {
    // Check the next snapshot index is not out of bounds
    if (this.snapshotIndex + 1 <= this.model.getSnapshots().size() - 1) {
      this.snapshotIndex += 1;
      this.passGraphics();
    } else {
      this.viewFrame.showMessage();
    }
  }

  /**
   * Checks request from graphical view for previous snapshot, passes snapshot to view if end of
   * snapshot list has not been reached. If no more snapshots, tells view to show message to user.
   */
  @Override
  public void getPrevious() {
    if (this.snapshotIndex - 1 > -1) {
      this.snapshotIndex -= 1;
      this.passGraphics();
    } else {
      this.viewFrame.showMessage();
    }
  }

  /**
   * Displays snapshot with specific ID when requested by the graphical view.
   * @param id String ID of snapshot to be rendered.
   */
  @Override
  public void showSnapshot(String id) {
    // Get snapshot index from model
    int index = this.model.getSnapshotIDs().indexOf(id);
    this.snapshotIndex = index;
    this.passGraphics();
  }

  /**
   * Gets snapshot ids from model to pass to view.
   * @return List<String>
   */
  public List<String> getIds () {
    return this.model.getSnapshotIDs();
  }

  /**
   * Gets snapshot descriptions from model to pass to views.
   * @return List<String>
   */
  public List<String> getDescriptions() {
    return this.model.getSnapshots().stream()
            .map(s -> s.getDescription()).collect(Collectors.toList());
  }

  /**
   * Getter for index of snapshot currently displayed.
   * @return int index
   */
  public int getSnapIndex() {
    return this.snapshotIndex;
  }

}
