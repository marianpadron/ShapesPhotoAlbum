package utilities.shapes;

/**
 * I2DShape interface for creating 2 dimensional shape objects.
 */
public interface I2DShape {

  /**
   * Getter method for x coordinate of shape.
   * @return int x coordinate
   */
  int getX();

  /**
   * Getter method for y coordinate of shape.
   * @return int y coordinate
   */
  int getY();

  /**
   * Getter method for shape name.
   * @return String shape name
   */
  String getName();

  /**
   * Getter method for shape RGB color.
   * @return RGB object
   */
  RGB getRGB();

  /**
   * Getter method for length.
   * @return int length
   */
  int getLength();

  /**
   * Getter method for width.
   * @return int width
   */
  int getWidth();

  /**
   * Getter method for shape coordinates.
   * @return CanvasPoint object.
   */
  CanvasPoint getCoordinates();
  /**
   * Moves the x and y coordinates of a shape.
   * @param x int new x coordinate
   * @param y int new y coordinate
   */
  void move(int x, int y);

  /**
   * Sets a new color for the shape.
   * @param r int r RGB value
   * @param g int g RGB value
   * @param b int b RGB value
   */
  void setColor(int r, int g, int b);

  /**
   * Sets a new length size for the shape.
   * @param length int new length
   */
  void setLength(int length);

  /**
   * Sets a new width size for the shape.
   * @param width int new width
   */
  void setWidth(int width);

  /**
   * Makes a deep copy of the shape.
   * @return <T>
   */
  <T> T makeCopy();

  /**
   * Returns the type of shape.
   * @return enum ShapeType
   */
  ShapeTypes getType();

}
