package shapestest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import utilities.shapes.RGB;

/**
 * RGB test class.
 */
public class RGBTest {
  private RGB rgb;

  /**
   * Creates RGB object for testing.
   */
  @Before
  public void setup() {
    this.rgb = new RGB(255, 0, 0);
  }

  /**
   * Tests bad construction with RGB R value below range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRBelow() {
    RGB rgb1 = new RGB(-1, 0, 0);
  }

  /**
   * Tests bad construction with RGB R value above range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRAbove() {
    RGB rgb1 = new RGB(300, 0, 0);
  }

  /**
   * Tests bad construction with RGB G value below range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGBelow() {
    RGB rgb1 = new RGB(0, -1, 0);
  }

  /**
   * Tests bad construction with RGB G value above range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGAbove() {
    RGB rgb1 = new RGB(0, 300, 0);
  }

  /**
   * Tests bad construction with RGB B value below range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBBelow() {
    RGB rgb1 = new RGB(0, 0, -1);
  }

  /**
   * Tests bad construction with RGB B value above range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBAbove() {
    RGB rgb1 = new RGB(0, 0, 300);
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    assertEquals("(255, 0, 0)", this.rgb.toString());
  }

  /**
   * Test bad setRGB() with invalid R value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadRSetRGB() {
    this.rgb.setRGB(-1, 0, 0);
  }

  /**
   * Test bad setRGB() with invalid G value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadGSetRGB() {
    this.rgb.setRGB(0, 1000, 0);
  }

  /**
   * Test bad setRGB() with invalid B value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadBSetRGB() {
    this.rgb.setRGB(0, 0, -2);
  }

  /**
   * Test getters.
   */
  @Test
  public void testGetters() {
    assertEquals(255, this.rgb.getR());
    assertEquals(0, this.rgb.getG());
    assertEquals(0, this.rgb.getB());
  }

  /**
   * Test setRGB().
   */
  @Test
  public void testSetRGB() {
    // Check before change
    assertEquals("(255, 0, 0)", this.rgb.toString());

    // Change RGB
    this.rgb.setRGB(0, 255, 255);
    assertEquals("(0, 255, 255)", this.rgb.toString());
  }

}
