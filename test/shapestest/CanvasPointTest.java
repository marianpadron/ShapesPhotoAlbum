package shapestest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import utilities.shapes.CanvasPoint;

/**
 * CanvasPoint test class.
 */
public class CanvasPointTest {
  private static final int MAX_X = 500;
  private static final int MIN_X = 0;
  private static final int MAX_Y = 500;
  private static final int MIN_Y = 0;
  private CanvasPoint point1;
  private CanvasPoint point2;

  /**
   * Create points for testing.
   */
  @Before
  public void setup() {
    this.point1 = new CanvasPoint(200, 400);
    this.point2 = new CanvasPoint(100, 500);
  }

  /**
   * Test getX().
   */
  @Test
  public void testGetX() {
    assertEquals(200, this.point1.getX());
    assertEquals(100, this.point2.getX());
  }

  /**
   * Test getY().
   */
  @Test
  public void testGetY() {
    assertEquals(400, this.point1.getY());
    assertEquals(500, this.point2.getY());
  }

}
