package utilities.shapes;

import java.util.Objects;

/**
 * Triangle shape class. Creates a type of I2DShape that can behave as a triangle.
 */
public class Triangle extends Abstract2DShape {
  /**
   * Default constructor.
   */
  public Triangle() {
    super("DefaultTriangle", 1, 1, 0, 0,
            0, 0, 0);
  }

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
   */
  public Triangle(String name, int length, int width, int x, int y, int r, int g, int b) {
    super(name, length, width, x, y, r, g, b);
  }

  /**
   * Copy constructor.
   * @param other Triangle object
   */
  public Triangle(Triangle other) {
    super(other.getName(), other.getLength(), other.getWidth(), other.getX(), other.getY(),
            other.getRGB().getR(), other.getRGB().getG(), other.getRGB().getB());
  }

  /**
   * Makes a deep copy of the shape.
   *
   * @return Triangle
   */
  @Override
  public Triangle makeCopy() {
    return new Triangle(this);
  }

  /**
   * toString method.
   * @return String
   */
  @Override
  public String toString() {
    return "Name: " + super.getName() + "\n"
            + "Type: Triangle\n"
            + "Corner: " + super.getCoordinates().toString() + "\n"
            + "Length: " + super.getLength() + " Height: " + super.getWidth() + "\n"
            + "Color: " + super.getRGB().toString();
  }

  /**
   * hashCode method.
   * @return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.getName(), this.getClass(), super.getLength(),
            super.getWidth(), super.getCoordinates(), super.getRGB());
  }

  /**
   * equals method.
   * @return boolean
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null || this.getClass() != other.getClass()) {
      return false;
    }

    Triangle otherTriangle = (Triangle)other;
    if (this.getName().equals(otherTriangle.getName())
            && this.getLength() == otherTriangle.getLength()
            && this.getWidth() == otherTriangle.getWidth()
            && this.getCoordinates().equals(otherTriangle.getCoordinates())
            && this.getRGB().equals(otherTriangle.getRGB())) {
      return true;
    }
    return false;
  }

  /**
   * Returns the type of shape.
   * @return enum ShapeType
   */
  @Override
  public ShapeTypes getType() {
    return ShapeTypes.TRIANGLE;
  }
}
