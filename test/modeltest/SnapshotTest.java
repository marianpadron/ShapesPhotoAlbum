package modeltest;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import utilities.shapes.I2DShape;
import utilities.shapes.Oval;
import utilities.shapes.Rectangle;
import utilities.shapes.ShapeTypes;
import utilities.shapes.Triangle;
import utilities.Snapshot;

/**
 * Snapshot test class.
 */
public class SnapshotTest {
  private List<I2DShape> shapes = new ArrayList<>();
  I2DShape circle;
  I2DShape rectangle;
  I2DShape square;
  I2DShape triangle;

  /**
   * Create object for testing.
   */
  @Before
  public void setup() {
    this.circle = new Oval("circle", 2, 2, 1, 1, 0, 0,0);
    this.rectangle = new Rectangle("rectangle", 2, 4, 3, 5,
            255, 0, 0);
    this.square = new Rectangle("square", 1, 1, 5, 6, 0,
            255, 0);
    this.triangle = new Triangle("triangle", 1, 2, 7, 7, 0, 0, 255);
    this.shapes.add(circle);
    this.shapes.add(rectangle);
    this.shapes.add(square);
    this.shapes.add(triangle);
  }

  /**
   * Test getID().
   */
  @Test
  public void testGetID() {
    // Make snapshot
    Snapshot snap = new Snapshot("testsnap", this.shapes);

    // Create id with equal timestamp
    Date date = new Date();
    String id = new Timestamp(date.getTime()).toString();

    // Check rounded to miliseconds for ID timestamp
    int idSec = Integer.parseInt(id.substring(20, 21));
    int snapSec = Integer.parseInt(snap.toString().substring(33, 34));
    assertEquals(idSec, snapSec, 1);

    // Check rest of id
   assertEquals("Snapshot ID: " + id.substring(0, 19),snap.toString().substring(0, 32));

  }

  /**
   * Test getDescription().
   */
  @Test
  public void testGetDescription() {
    // Make snapshot
    Snapshot snap = new Snapshot("testsnap", this.shapes);
    assertEquals("testsnap", snap.getDescription());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    // Make snapshot
    Snapshot snap = new Snapshot("testsnap", this.shapes);

    // Make string comparator
    String text = "Shape Information:\n\n";
    text += this.circle.toString() + "\n\n";
    text += this.rectangle.toString() + "\n\n";
    text += this.square.toString() + "\n\n";
    text += this.triangle.toString() + "\n\n";

    // Compare rest of string
    assertEquals(text, snap.toString().substring(93));
  }

  /**
   * Test getShapes().
   */
  @Test
  public void testGetShapes() {
    // Make snapshot
    Snapshot snap = new Snapshot("testsnap", this.shapes);
    assertEquals(4, snap.getShapes().size());
    List<I2DShape> shapes = snap.getShapes();
    assertEquals(shapes.get(0).getType(), ShapeTypes.OVAL);
    assertEquals(shapes.get(1).getType(), ShapeTypes.RECTANGLE);
    assertEquals(shapes.get(2).getType(), ShapeTypes.RECTANGLE);
    assertEquals(shapes.get(3).getType(), ShapeTypes.TRIANGLE);
  }


}
