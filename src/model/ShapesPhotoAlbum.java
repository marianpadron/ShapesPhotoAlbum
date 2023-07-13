package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utilities.Snapshot;
import utilities.shapes.Oval;
import utilities.shapes.I2DShape;
import utilities.shapes.Rectangle;
import utilities.shapes.ShapeTypes;
import utilities.shapes.Triangle;

/**
 * ShapesPhotoAlbum class. Creates a photo album of I2DShapes on a canvas. Can edit shapes and
 * create snapshots of their states at a given timestamp.
 */
public class ShapesPhotoAlbum implements IShapesPhotoAlbum {
  private Map<String, I2DShape> shapes = new LinkedHashMap<>();
  private List<Snapshot> snapshots = new ArrayList<>();

  /**
   * Constructor.
   */
  public ShapesPhotoAlbum(){
  }

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
   * @throws IllegalArgumentException if invalid type or name passed
   */
  @Override
  public void makeShape(String type, String name, int length, int width, int x, int y,
                        int r, int g, int b) throws IllegalArgumentException {
    // Check valid shape type and name
    this.checkValidShapeTypeName(type, name);

    // Create shapes
    type = type.toUpperCase();
    I2DShape newShape = null;

    // If rectangle or square
    if (type.equals(ShapeTypes.RECTANGLE.name())) {
      newShape = new Rectangle(name, length, width, x, y, r, g, b);

    // If circle or oval
    } else if (type.equals(ShapeTypes.OVAL.name())) {
      newShape = new Oval(name, length,width, x, y, r, g, b);

    // If triangle
    } else if (type.equals(ShapeTypes.TRIANGLE.name())) {
      newShape = new Triangle(name, length, width, x, y, r, g, b);
    }

    // Add to shapes
    this.shapes.put(name, newShape);
  }

  /**
   * Helper method to check if a valid shape type and name are passed for creating a new shape
   * in the photo album.
   * @param type String shape type
   * @param name Sting name of shape
   * @throws IllegalArgumentException if invalid type or name already exists
   */
  private void checkValidShapeTypeName(String type, String name) throws IllegalArgumentException {
    if (type == null || type.isBlank()) {
      throw new IllegalArgumentException("Shape type required.");
    }
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Shape name required.");
    }
    // Check for valid shape in enum
    List<String> shapeTypes = Stream.of(ShapeTypes.values()).
            map(ShapeTypes::name).collect(Collectors.toList());
    if (!shapeTypes.contains(type.toUpperCase())) {
      throw new IllegalArgumentException("Invalid shape type");
    }
    // Check if name already exists
    if (this.getShapeNames().contains(name)) {
      throw new IllegalArgumentException("A shape with that name already exists");
    }
  }

  /**
   * Returns an unmodifiable List of all shape names.
   * @return List<String></String>
   */
  @Override
  public List<String> getShapeNames() {
    List<String> names = Collections.unmodifiableList(this.shapes.keySet()
            .stream().collect(Collectors.toList()));
    return names;
  }

  /**
   * Returns a list with copies of all shapes.
   * @return List<I2DShape>
   */
  @Override
  public List<I2DShape> getShapes() {
    List<I2DShape> list = this.shapes.values().stream().collect(Collectors.toList());
    List<I2DShape> copies = new ArrayList<>();
    for (I2DShape shape : list) {
      I2DShape copy = shape.makeCopy();
      copies.add(copy);
    }
    return copies;
  }

  /**
   * Helper method for checking if shape name exists.
   * @param name String name of shape name
   * @throws IllegalArgumentException if invalid name or nonexistent shape
   */
  private void checkValidShapeName(String name) throws IllegalArgumentException {
    // Check valid name
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name of shape required.");
    }
    // Check shape exists
    if (!this.getShapeNames().contains(name)) {
      throw new IllegalArgumentException("There is no shape with that name.");
    }
  }

  /**
   * Changes a shape's x and y coordinates given its name on the album.
   * @param name String shape name
   * @param x int x coordinate
   * @param y int y coordinate
   * @throws IllegalArgumentException if invalid shape name
   */
  @Override
  public void moveShape(String name, int x, int y) throws IllegalArgumentException {
    // Check valid name
    this.checkValidShapeName(name);

    // Grab shape and move it
    this.shapes.get(name).move(x, y);
  }

  /**
   * Changes the color of a shape given its name on the album.
   * @param name String shape name
   * @param r int r RGB value
   * @param g int g RGB value
   * @param b int b RGB value
   * @throws IllegalArgumentException if invalid shape name
   */
  @Override
  public void colorShape(String name, int r, int g, int b) throws IllegalArgumentException {
    // Check valid name
    this.checkValidShapeName(name);

    // Grab shape and recolor
    this.shapes.get(name).setColor(r, g, b);
  }

  /**
   * Resizes a shape with a new length and width given its name on the album.
   * @param name String shape name
   * @param length int new length
   * @param width int new width
   */
  @Override
  public void resizeShape(String name, int length, int width) {
    // Check valid name
    this.checkValidShapeName(name);

    // Check valid length and width
    if (length <= 0|| width <= 0) {
      throw new IllegalArgumentException("Invalid length or width");
    }

    // Grab shape and resize
    I2DShape shape = this.shapes.get(name);
    shape.setLength(length);
    shape.setWidth(width);
  }

  /**
   * Deletes a shape from the photo album by passing its name.
   * @param name String shape name
   */
  @Override
  public void deleteShape(String name) {
    // Check valid name
    this.checkValidShapeName(name);

    this.shapes.remove(name);
  }

  /**
   * Returns a string representation of the shapes currently on the album canvas.
   * @return String
   */
  public String getCurrentState() {
    String text = "Current Shapes:\n";
    for (I2DShape shape : this.shapes.values()) {
      text += shape.toString() + "\n\n";
    }
    return text;
  }

  /**
   * Takes a snapshot of the current shapes.
   * @param description String description of snapshot
   */
  @Override
  public void takeSnapshot(String description) {
    // Get list of current shapes
    List<I2DShape> shapes = this.shapes.values().stream().toList();

    // Make copy of shapes
    List<I2DShape> copies = new ArrayList<>();
    for (I2DShape shape : shapes) {
      I2DShape copy = (I2DShape) shape.makeCopy();
      copies.add(copy);
    }

    // create Snapshot of shapes
    this.snapshots.add(new Snapshot(description, copies));
  }

  /**
   * Returns a list of snapshots in album.
   * @return List<Snapshot></Snapshot>
   */
  @Override
  public List<Snapshot> getSnapshots() {
    return Collections.unmodifiableList(snapshots);
  }

  /**
   * Returns a list of IDs of the snapshots taken.
   * @return List<String></String>
   */
  @Override
  public List<String> getSnapshotIDs() {
    return Collections.unmodifiableList(snapshots.stream().map(s -> s.getID())
            .collect(Collectors.toList()));
  }

}
