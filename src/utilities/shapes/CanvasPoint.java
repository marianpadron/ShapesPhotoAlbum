package utilities.shapes;

import java.util.Objects;

/**
 * CanvasPoint class. Creates a point with an x and y coordinate in a canvas with
 * minimum height and width.
 */
public class CanvasPoint {
  private static final int MAX_X = Integer.MAX_VALUE;
  private static final int MIN_X = Integer.MIN_VALUE;
  private static final int MAX_Y = Integer.MAX_VALUE;
  private static final int MIN_Y = Integer.MIN_VALUE;
  private int x;
  private int y;

  /**
   * Constructor.
   * @param x x coordinate of point
   * @param y y coordinate of point
   * @throws IllegalArgumentException if x or y coordinate out of bounds
   */
  public CanvasPoint(int x, int y) throws IllegalArgumentException {
    // Check valid coordinates
    this.checkCoordinates(x, y);
    // Initialize
    this.x = x;
    this.y = y;
  }

  /**
   * Helper function, checks if passed x and y coordinates are out of bounds.
   * @param x int x coordinate
   * @param y int y coordinate
   * @throws IllegalArgumentException if x or y coordinate out of bounds
   */
  private void checkCoordinates(int x, int y) throws IllegalArgumentException {
    // Check valid coordinates
    if (x < MIN_X || x > MAX_X) {
      throw new IllegalArgumentException("X coordinates out of bounds.");
    }
    if (y < MIN_Y || y > MAX_Y) {
      throw new IllegalArgumentException("Y coordinates out of bounds.");
    }
  }

  /**
   * Getter method for x coordinate.
   * @return int x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Getter method for y coordinate.
   * @return int y coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Setter method for x coordinate.
   * @param x int new x coordinate
   * @return void
   * @throws IllegalArgumentException if x coordinate out of bounds
   */
  public void setX(int x) throws IllegalArgumentException {
    checkCoordinates(x, this.y); // check valid x coordinate
    this.x = x;
  }

  /**
   * Setter method for y coordinate
   * @param y int new y coordinate
   * @return void
   * @throws IllegalArgumentException if y coordinate out of bounds
   */
  public void setY(int y) throws IllegalArgumentException {
    checkCoordinates(this.x, y); // check valid x coordinate
    this.y = y;
  }

  /**
   * toString method.
   * @return String
   */
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }

  /**
   * hashCode method.
   * @return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
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

    CanvasPoint otherPoint = (CanvasPoint)other;
    if (this.x == otherPoint.x && this.y == otherPoint.y) {
      return true;
    }
    return false;
  }

}
