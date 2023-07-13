package model;

import java.util.List;

import utilities.Snapshot;
import utilities.shapes.I2DShape;

/**
 * IShapesPhotoAlbum interface.
 */
public interface IShapesPhotoAlbum {
  /**
   * Makes a shape within and adds it to the photo album canvas.
   * @param type String type of shape
   * @param name String name of shape
   * @param length int length
   * @param width int width
   * @param x int x coordinate
   * @param y int y coordiante
   * @param r int r RGB value
   * @param g int g RGB value
   * @param b int b RGB value
   */
  void makeShape(String type, String name, int length, int width, int x, int y,
                        int r, int g, int b);
  /**
   * Returns an unmodifiable List of all shape names.
   * @return List<String></String>
   */
  List<String> getShapeNames();

  /**
   * Returns a list with copies of all shapes.
   * @return List<I2DShape></I2DShape>
   */
  List<I2DShape> getShapes();

  /**
   * Changes a shape's x and y coordinates given its name on the album.
   * @param name String shape name
   * @param x int x coordinate
   * @param y int y coordinate
   */
  void moveShape(String name, int x, int y);

  /**
   * Changes the color of a shape given its name on the album.
   * @param name String shape name
   * @param r int r RGB value
   * @param g int g RGB value
   * @param b int b RGB value
   */
  void colorShape(String name, int r, int g, int b);

  /**
   * Resizes a shape with a new length and width given its name on the album.
   * @param name String shape name
   * @param length int new length
   * @param width int new width
   */
  void resizeShape(String name, int length, int width);

  /**
   * Deletes a shape from the photo album by passing its name.
   * @param name String shape name
   */
  void deleteShape(String name);

  /**
   * Takes a snapshot of the current shapes.
   * @param description String description of snapshot
   */
  void takeSnapshot(String description);

  /**
   * Returns a list of snapshots in album.
   * @return List<Snapshot></Snapshot>
   */
  List<Snapshot> getSnapshots();

  /**
   * Returns a list of IDs of the snapshots taken.
   * @return List<String></String>
   */
  List<String> getSnapshotIDs();
}
