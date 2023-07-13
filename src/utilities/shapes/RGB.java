package utilities.shapes;

import java.util.Objects;

/**
 * RGB class, holds values on RGB color scale to represent colors.
 */
public class RGB {
  private static final int MAX = 255;
  private static final int MIN = 0;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor.
   * @param r int red value
   * @param g int green value
   * @param b int blue value
   * @throws IllegalArgumentException if RGB values out of bounds
   */
  public RGB(int r, int g, int b) throws IllegalArgumentException {
    this.checkRGB(r, g, b); // check valid values
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Helper method to check for correct RGB values.
   * @param r int red value
   * @param g int green value
   * @param b int blue value
   * @throws IllegalArgumentException if RGB values out of range
   */
  private void checkRGB(int r, int g, int b)  throws IllegalArgumentException {
    if (r < MIN || r > MAX) {
      throw new IllegalArgumentException("Not valid RGB number, range 0 to 255.");
    }
    if (g < MIN || g > MAX) {
      throw new IllegalArgumentException("Not valid RGB number, range 0 to 255.");
    }
    if (b < MIN || b > MAX) {
      throw new IllegalArgumentException("Not valid RGB number, range 0 to 255.");
    }
  }

  /**
   * Getter for r value.
   * @return int
   */
  public int getR() {
    return this.r;
  }

  /**
   * Getter method for g value.
   * @return int
   */
  public int getG() {
    return this.g;
  }

  /**
   * Getter method for b value.
   * @return int
   */
  public int getB() {
    return this.b;
  }
  /**
   * Setter method for RBG values.
   * @param r int red value
   * @param g int green value
   * @param b int blue value
   * @throws IllegalArgumentException if RGB values out of range
   */
  public void setRGB(int r, int g, int b) throws IllegalArgumentException {
    this.checkRGB(r, g, b); // check valid RGB values
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * toString method.
   * @return String
   */
  @Override
  public String toString() {
    return "(" + this.r + ", " + this.g + ", " + this.b + ")";
  }

  /**
   * hashCode method.
   * @return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
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

    RGB otherRGB = (RGB)other;
    if (this.r == otherRGB.r && this.g == otherRGB.g && this.b == otherRGB.b) {
      return true;
    }
    return false;
  }

}

