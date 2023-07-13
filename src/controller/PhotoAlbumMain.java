package controller;

import java.io.FileNotFoundException;
import java.util.List;

import model.ShapesPhotoAlbum;
import utilities.ArgsReader;
import views.graphical.ViewFrame;

/**
 * PhotoAlbumMain class. Entry point for PhotoAlbum MVC program.
 */
public class PhotoAlbumMain {

  /**
   * Main method.
   * @param args -in "name-of-command-file" -view "type-of-view"
   * [-out "where-output-should-go"] [xmax] [ymax]
   */
  public static void main(String[] args) throws FileNotFoundException {

    // Get ordered list of args
    List<String> argsList = new ArgsReader(args).getArgsList();

    // Initialize controller with model and args
    PhotoAlbumController controller = new PhotoAlbumController(argsList, new ShapesPhotoAlbum());

    // Initialize view depending on command line args
    if (argsList.get(1).equalsIgnoreCase("graphical")) {
      ViewFrame view = new ViewFrame("Shapes Photo Album",
              Integer.parseInt(argsList.get(3)),Integer.parseInt(argsList.get(4)), controller);

      // Pass view to controller and begin program
      controller.run(view);
    } else {
      controller.run("Shapes Photo Album");
    }
  }
}
