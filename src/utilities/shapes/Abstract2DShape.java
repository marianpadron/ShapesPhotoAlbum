package utilities.shapes;

/**
 * Abstract2DShape class. Creates abstract 2 dimensional shapes with length and width, a given name,
 * coordinates on a canvas, color on RGB color scale.
 */
public abstract class Abstract2DShape implements I2DShape {
  private String name;
  private CanvasPoint coordinates;
  private RGB rgb;
  private int length;
  private int width;

  /**
   * Constructor.
   * @param name String shape name
   * @param length int shape length
   * @param width int shaped width
   * @param x int x coordinate
   * @param y int y coordiante
   * @param r int r RBG value
   * @param g int g RGB value
   * @param b int b RGB value
   * @throws IllegalArgumentException if invalid name or dimensions
   */
  public Abstract2DShape(String name, int length, int width, int x, int y, int r,
                         int g, int b) throws IllegalArgumentException{
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name must be provided.");
    }
    if (length <= 0 || width <= 0) {
      throw new IllegalArgumentException("Invalid length or width.");
    }
    this.name = name;
    this.length = length;
    this.width = width;
    this.coordinates = new CanvasPoint(x, y);
    this.rgb = new RGB(r, g, b);
  }

  /**
   * Moves the x and y coordinates of a shape.
   *
   * @param x int new x coordinate
   * @param y int new y coordinate
   */
  @Override
  public void move(int x, int y) {
    this.coordinates.setX(x);
    this.coordinates.setY(y);
  }

  /**
   * Getter method for x coordinate of shape.
   *
   * @return int x coordinate
   */
  @Override
  public int getX() {
    return this.coordinates.getX();
  }

  /**
   * Getter method for y coordinate of shape.
   *
   * @return int y coordinate
   */
  @Override
  public int getY() {
    return this.coordinates.getY();
  }

  /**
   * Getter method for shape name.
   *
   * @return String shape name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Getter method for shape RGB color.
   *
   * @return RGB object
   */
  @Override
  public RGB getRGB() {
    return this.rgb;
  }

  /**
   * Getter method for shape coordinates.
   *
   * @return CanvasPoint object.
   */
  @Override
  public CanvasPoint getCoordinates() {
    return this.coordinates;
  }

  /**
   * Getter method for length.
   *
   * @return int length
   */
  @Override
  public int getLength() {
    return this.length;
  }

  /**
   * Getter method for width.
   *
   * @return int width
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Sets a new length size for the shape.
   *
   * @param length int new length
   */
  @Override
  public void setLength(int length) throws IllegalArgumentException {
    if (length <= 0) {
      throw new IllegalArgumentException("Invalid length");
    }
    this.length = length;
  }

  /**
   * Sets a new width size for the shape.
   *
   * @param width int new width
   */
  @Override
  public void setWidth(int width) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Invalid width");
    }
    this.width = width;
  }

  /**
   * Sets a new color for the shape.
   *
   * @param r int r RGB value
   * @param g int g RGB value
   * @param b int b RGB value
   */
  @Override
  public void setColor(int r, int g, int b) {
    this.rgb.setRGB(r, g, b);
  }
}
