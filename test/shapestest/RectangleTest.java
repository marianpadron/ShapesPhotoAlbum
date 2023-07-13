package shapestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

import utilities.shapes.I2DShape;
import utilities.shapes.Rectangle;
import utilities.shapes.ShapeTypes;

/**
 * Rectangle test class.
 */
public class RectangleTest {
  private Rectangle rectangle;

  /**
   * Create Rectangle object for testing.
   */
  @Before
  public void setup() {
    this.rectangle = new Rectangle("testRectangle", 2, 3,
            0, 0, 255, 0, 0);
  }

  /**
   * Tests default construction.
   */
  @Test
  public void testRectangleDefaultConstructor() {
    Rectangle rectangle1 = new Rectangle();
    assertEquals("Name: DefaultRectangle\nType: Rectangle\nCorner: (0, 0)"
            + "\nLength: 1 Width: 1\nColor: (0, 0, 0)", rectangle1.toString());
  }

  /**
   * Test bad construction with null name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    Rectangle rectangle1 = new Rectangle(null, 2, 1, 0, 0,
            255, 0, 0);
  }

  /**
   * Test bad construction with empty name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    Rectangle rectangle1 = new Rectangle("", 2, 1, 0, 0,
            255, 0, 0);
  }

  /**
   * Test copy method and copy constructor.
   */
  @Test
  public void testMakeCopy() {
    I2DShape rectangle1 = this.rectangle.makeCopy();
    assertEquals(this.rectangle, rectangle1);
    assertNotSame(this.rectangle, rectangle1);
  }

  /**
   * Test correct illegal argument thrown from RGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadRGB() {
    Rectangle rectangle1 = new Rectangle("rectangle1", 2, 1 , 0,
            0, 255, -1, 0);
  }

  /**
   * Test getX()
   */
  @Test
  public void testGetX() {
    assertEquals(0, this.rectangle.getX());
  }

  /**
   * Test getY()
   */
  @Test
  public void testGetY() {
    assertEquals(0, this.rectangle.getY());
  }

  /**
   * Test move().
   */
  @Test
  public void testMove() {
    // Check initial location
    assertEquals(0, this.rectangle.getX());
    assertEquals(0, this.rectangle.getY());

    // Move rectangle
    this.rectangle.move(1, 2);
    assertEquals(1, this.rectangle.getX());
    assertEquals(2, this.rectangle.getY());
  }

  /**
   * Test setRGB() correct illegal argument thrown from RGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetRGB() {
    this.rectangle.setColor(-1, 0, 255);
  }

  /**
   * Test setRGB().
   */
  @Test
  public void testSetRGB() {
    // Check before change
    assertEquals("Name: testRectangle\nType: Rectangle\n"
            + "Corner: (0, 0)\nLength: 2 Width: 3\nColor: (255, 0, 0)", rectangle.toString());

    // Change RGB
    this.rectangle.setColor(255, 255, 255);
    assertEquals("Name: testRectangle\nType: Rectangle\n"
            + "Corner: (0, 0)\nLength: 2 Width: 3\nColor: (255, 255, 255)",
            rectangle.toString());
  }

  /**
   * Test invalid setWidth().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetWidth() {
    this.rectangle.setWidth(-1);
  }

  /**
   * Test setWidth().
   */
  @Test
  public void testSetWidth() {
    // Check before change
    assertEquals("Name: testRectangle\nType: Rectangle\n"
            + "Corner: (0, 0)\nLength: 2 Width: 3\nColor: (255, 0, 0)", rectangle.toString());

    // Change radius
    this.rectangle.setWidth(10);
    assertEquals("Name: testRectangle\nType: Rectangle\n"
            + "Corner: (0, 0)\nLength: 2 Width: 10\nColor: (255, 0, 0)", rectangle.toString());
  }

  /**
   * Test invalid setLength().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetLength() {
    this.rectangle.setLength(-1);
  }

  /**
   * Test setLength().
   */
  @Test
  public void testSetLength() {
    // Check before change
    assertEquals("Name: testRectangle\nType: Rectangle\n"
            + "Corner: (0, 0)\nLength: 2 Width: 3\nColor: (255, 0, 0)", rectangle.toString());

    // Change radius
    this.rectangle.setLength(10);
    assertEquals("Name: testRectangle\nType: Rectangle\n"
            + "Corner: (0, 0)\nLength: 10 Width: 3\nColor: (255, 0, 0)", rectangle.toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    Rectangle rectangle1 = new Rectangle("myrectangle", 4,
            4, 1, 1, 0, 0, 0);
    assertEquals("Name: myrectangle\nType: Rectangle\n"
            + "Corner: (1, 1)\nLength: 4 Width: 4\nColor: (0, 0, 0)", rectangle1.toString());

  }

  /**
   * Test getType().
   */
  @Test
  public void testGetType() {
    assertEquals(rectangle.getType(), ShapeTypes.RECTANGLE);
  }
}
