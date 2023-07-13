package shapestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

import utilities.shapes.I2DShape;
import utilities.shapes.ShapeTypes;
import utilities.shapes.Triangle;

/**
 * Triangle test class.
 */
public class TriangleTest {
  private Triangle triangle;

  /**
   * Create Triangle object for testing.
   */
  @Before
  public void setup() {
    this.triangle = new Triangle("testTriangle", 2, 3,
            0, 0, 255, 0, 0);
  }

  /**
   * Tests default construction.
   */
  @Test
  public void testTriangleDefaultConstructor() {
    Triangle triangle1 = new Triangle();
    assertEquals("Name: DefaultTriangle\nType: Triangle\nCorner: (0, 0)"
            + "\nLength: 1 Height: 1\nColor: (0, 0, 0)", triangle1.toString());
  }

  /**
   * Test bad construction with null name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    Triangle triangle1 = new Triangle(null, 2, 1, 0, 0,
            255, 0, 0);
  }

  /**
   * Test bad construction with empty name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    Triangle triangle1 = new Triangle("", 2, 1, 0, 0,
            255, 0, 0);
  }

  /**
   * Test copy method and copy constructor.
   */
  @Test
  public void testMakeCopy() {
    I2DShape triangle1 = this.triangle.makeCopy();
    assertEquals(this.triangle, triangle1);
    assertNotSame(this.triangle, triangle1);
  }

  /**
   * Test correct illegal argument thrown from RGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadRGB() {
    Triangle triangle1 = new Triangle("triangle1", 2, 1 , 0,
            0, 255, -1, 0);
  }

  /**
   * Test getX()
   */
  @Test
  public void testGetX() {
    assertEquals(0, this.triangle.getX());
  }

  /**
   * Test getY()
   */
  @Test
  public void testGetY() {
    assertEquals(0, this.triangle.getY());
  }

  /**
   * Test move().
   */
  @Test
  public void testMove() {
    // Check initial location
    assertEquals(0, this.triangle.getX());
    assertEquals(0, this.triangle.getY());

    // Move triangle
    this.triangle.move(1, 2);
    assertEquals(1, this.triangle.getX());
    assertEquals(2, this.triangle.getY());
  }

  /**
   * Test setRGB() correct illegal argument thrown from RGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetRGB() {
    this.triangle.setColor(-1, 0, 255);
  }

  /**
   * Test setRGB().
   */
  @Test
  public void testSetRGB() {
    // Check before change
    assertEquals("Name: testTriangle\nType: Triangle\n"
            + "Corner: (0, 0)\nLength: 2 Height: 3\nColor: (255, 0, 0)", triangle.toString());

    // Change RGB
    this.triangle.setColor(255, 255, 255);
    assertEquals("Name: testTriangle\nType: Triangle\n"
                    + "Corner: (0, 0)\nLength: 2 Height: 3\nColor: (255, 255, 255)",
            triangle.toString());
  }

  /**
   * Test invalid setWidth().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetHeight() {
    this.triangle.setWidth(-1);
  }

  /**
   * Test setWidth();() which changes the radius in Triangle class.
   */
  @Test
  public void testSetHeight() {
    // Check before change
    assertEquals("Name: testTriangle\nType: Triangle\n"
            + "Corner: (0, 0)\nLength: 2 Height: 3\nColor: (255, 0, 0)", triangle.toString());

    // Change radius
    this.triangle.setWidth(10);
    assertEquals("Name: testTriangle\nType: Triangle\n"
            + "Corner: (0, 0)\nLength: 2 Height: 10\nColor: (255, 0, 0)", triangle.toString());
  }

  /**
   * Test invalid setLength().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetLength() {
    this.triangle.setLength(-1);
  }

  /**
   * Test setLength().
   */
  @Test
  public void testSetLength() {
    // Check before change
    assertEquals("Name: testTriangle\nType: Triangle\n"
            + "Corner: (0, 0)\nLength: 2 Height: 3\nColor: (255, 0, 0)", triangle.toString());

    // Change radius
    this.triangle.setLength(10);
    assertEquals("Name: testTriangle\nType: Triangle\n"
            + "Corner: (0, 0)\nLength: 10 Height: 3\nColor: (255, 0, 0)", triangle.toString());
  }


  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    Triangle triangle1 = new Triangle("mytriangle", 4,
            4, 1, 1, 0, 0, 0);
    assertEquals("Name: mytriangle\nType: Triangle\n"
            + "Corner: (1, 1)\nLength: 4 Height: 4\nColor: (0, 0, 0)", triangle1.toString());

  }

  /**
   * Test getType().
   */
  @Test
  public void testGetType() {
    assertEquals(triangle.getType(), ShapeTypes.TRIANGLE);
  }
}



