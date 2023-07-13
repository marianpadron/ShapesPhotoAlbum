package utilities.shapes;

import java.util.Objects;

/**
 * Oval shape class. Creates a type of I2DShape that can behave as an oval or a circle depending on
 * the passed dimensions.
 */
public class Oval extends Abstract2DShape {
  /**
   * Default constructor.
   */
  public Oval() {
    super("DefaultOval", 1, 1, 0, 0, 0, 0, 0);
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
  public Oval(String name, int length, int width, int x, int y, int r, int g, int b) {
    super(name, length, width, x, y, r, g, b);
  }

  /**
   * Copy constructor.
   * @param other Oval object
   */
  public Oval(Oval other) {
    super(other.getName(), other.getLength(), other.getWidth(), other.getX(), other.getY(),
            other.getRGB().getR(), other.getRGB().getG(), other.getRGB().getB());
  }

  /**
   * Makes a deep copy of the shape.
   *
   * @return I2DShape
   */
  @Override
  public Oval makeCopy() {
    return new Oval(this);
  }

  /**
   * toString method.
   * @return String
   */
  @Override
  public String toString() {
    return "Name: " + super.getName() + "\n"
            + "Type: Oval\n"
            + "Center: " + super.getCoordinates().toString() + "\n"
            + "X-Radius: " + super.getLength() + " Y-Radius: " + super.getWidth() + "\n"
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

    Oval otherOval = (Oval)other;
    if (this.getName().equals(otherOval.getName())
            && this.getLength() == otherOval.getLength()
            && this.getWidth() == otherOval.getWidth()
            && this.getCoordinates().equals(otherOval.getCoordinates())
            && this.getRGB().equals(otherOval.getRGB())) {
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
    return ShapeTypes.OVAL;
  }
}
