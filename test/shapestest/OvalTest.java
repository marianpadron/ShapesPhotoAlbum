package shapestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

import utilities.shapes.I2DShape;
import utilities.shapes.Oval;
import utilities.shapes.ShapeTypes;

/**
 * Oval test class.
 */
public class OvalTest {
  private Oval oval;

  /**
   * Create Oval object for testing.
   */
  @Before
  public void setup() {
    this.oval = new Oval("testOval", 2, 1,
            0, 0, 255, 0, 0);
  }

  /**
   * Tests default construction.
   */
  @Test
  public void testOvalDefaultConstructor() {
    Oval oval1 = new Oval();
    assertEquals("Name: DefaultOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 1 Y-Radius: 1\nColor: (0, 0, 0)", oval1.toString());
  }

  /**
   * Test bad construction with null name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    Oval oval1 = new Oval(null, 2, 2, 0, 0,
            255, 0, 0);
  }

  /**
   * Test bad construction with empty name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    Oval oval1 = new Oval("", 2, 2, 0, 0,
            255, 0, 0);
  }

  /**
   * Test copy method and copy constructor.
   */
  @Test
  public void testMakeCopy() {
    I2DShape oval1 = this.oval.makeCopy();
    assertEquals(this.oval, oval1);
    assertNotSame(this.oval, oval1);
  }

  /**
   * Test correct illegal argument thrown from RGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadRGB() {
    Oval oval1 = new Oval("oval1",2,  2, 0,
            0, 255, -1, 0);
  }

  /**
   * Test getX()
   */
  @Test
  public void testGetX() {
    assertEquals(0, this.oval.getX());
  }

  /**
   * Test getY()
   */
  @Test
  public void testGetY() {
    assertEquals(0, this.oval.getY());
  }

  /**
   * Test move().
   */
  @Test
  public void testMove() {
    // Check initial location
    assertEquals(0, this.oval.getX());
    assertEquals(0, this.oval.getY());

    // Move oval

    this.oval.move(1, 2);
    assertEquals(1, this.oval.getX());
    assertEquals(2, this.oval.getY());
  }

  /**
   * Test setRGB() correct illegal argument thrown from RGB.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetRGB() {
    this.oval.setColor(-1, 0, 255);
  }

  /**
   * Test setRGB().
   */
  @Test
  public void testSetRGB() {
    // Check before change
    assertEquals("Name: testOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 2 Y-Radius: 1\nColor: (255, 0, 0)", oval.toString());

    // Change RGB
    this.oval.setColor(255, 255, 255);
    assertEquals("Name: testOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 2 Y-Radius: 1\nColor: (255, 255, 255)", oval.toString());
  }

  /**
   * Test invalid setWidth().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetWidth() {
    this.oval.setWidth(-1);
  }

  /**
   * Test setWidth()..
   */
  @Test
  public void testSetWidth() {
    // Check before change
    assertEquals("Name: testOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 2 Y-Radius: 1\nColor: (255, 0, 0)", oval.toString());

    // Change radius
    this.oval.setWidth(10);
    assertEquals("Name: testOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 2 Y-Radius: 10\nColor: (255, 0, 0)", oval.toString());
  }

  /**
   * Test invalid setLength().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetLength() {
    this.oval.setLength(-1);
  }

  /**
   * Test setLength().
   */
  @Test
  public void testSetLength() {
    // Check before change
    assertEquals("Name: testOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 2 Y-Radius: 1\nColor: (255, 0, 0)", oval.toString());

    // Change radius
    this.oval.setLength(10);
    assertEquals("Name: testOval\nType: Oval\n"
            + "Center: (0, 0)\nX-Radius: 10 Y-Radius: 1\nColor: (255, 0, 0)", oval.toString());
  }

    /**
     * Test toString.
     */
  @Test
  public void testToString() {
    Oval oval1 = new Oval("myoval", 4, 5, 1, 1, 0, 0, 0);
    assertEquals("Name: myoval" +
            "\nType: Oval\n"
            + "Center: (1, 1)\nX-Radius: 4 Y-Radius: 5\nColor: (0, 0, 0)", oval1.toString());
  }

  /**
   * Test getType().
   */
  @Test
  public void testGetType() {
    assertEquals(oval.getType(), ShapeTypes.OVAL);
  }

}
