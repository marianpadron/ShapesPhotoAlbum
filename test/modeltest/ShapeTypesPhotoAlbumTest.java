package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import utilities.shapes.I2DShape;
import utilities.shapes.Oval;
import utilities.shapes.Rectangle;
import utilities.shapes.RGB;
import utilities.shapes.Triangle;
import model.ShapesPhotoAlbum;
import utilities.Snapshot;

/**
 * ShapesPhotoAlbum test class.
 */
public class ShapeTypesPhotoAlbumTest {
  private final int DELTA = 0;
  private ShapesPhotoAlbum album;

  /**
   * Create object for testing.
   */
  @Before
  public void setup() {
    this.album = new ShapesPhotoAlbum();
  }

  /**
   * Test bad makeShape() with null type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullTypeMakeShape() {
    this.album.makeShape(null, "shape", 1, 1, 0, 0, 0, 0, 0);
  }

  /**
   * Test bad makeShape() with empty type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyTypeMakeShape() {
    this.album.makeShape("", "shape", 1, 1, 0, 0, 0, 0, 0);
  }

  /**
   * Test makeShape() passes errors from Abstract2DShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMakeShape1() {
    this.album.makeShape("", "shape", 0, 1, 0, 0, 0, 0, 0);
  }

  /**
   * Test makeShape() passes errors from Abstract2DShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMakeShape2() {
    this.album.makeShape("", "shape", 1, 1, -2, 0, 0, 0, 0);
  }

  /**
   * Test makeShape() passes errors from Abstract2DShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMakeShape3() {
    this.album.makeShape("", "shape", 1, 1, 0, 0, 0, -1, 0);
  }

  /**
   * Test makeShape() passes errors from Abstract2DShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMakeShape4() {
    this.album.makeShape("", null, 1, 1, 0, 0, 0, 0, 0);
  }

  /**
   * Test makeShape() with invalid shape type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMakeShape5() {
    this.album.makeShape("rectanglee", "shape", 1, 1, 0, 0, 0,
            0, 0);
  }

  /**
   * Test makeShape().
   */
  @Test
  public void testMakeShape() {
    this.album.makeShape("Oval","oval", 2, 3, 1, 1, 0,
            0, 255);
    assertEquals(1, this.album.getShapeNames().size(), DELTA);

    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    assertEquals(2, this.album.getShapeNames().size(), DELTA);

    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);
    assertEquals(3, this.album.getShapeNames().size(), DELTA);
  }

  /**
   * Test getShapeNames().
   */
  @Test
  public void testGetShapeNames() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Check valid names is list
    List<String> names = this.album.getShapeNames();
    assertEquals("oval", names.get(0));
    assertEquals("rectangle", names.get(1));
    assertEquals("triangle", names.get(2));
  }

  /**
   * Test getShapes().
   */
  @Test
  public void testGetShapes() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Make equivalent shapes
    I2DShape oval = new Oval("oval", 2, 3, 1, 1, 0,
            0, 255);
    I2DShape rectangle = new Rectangle("rectangle", 3, 3, 0, 0,
            0, 0, 0);
    I2DShape triangle = new Triangle("triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Test for correct list of shapes
    assertEquals(oval, this.album.getShapes().get(0));
    assertEquals(rectangle, this.album.getShapes().get(1));
    assertEquals(triangle, this.album.getShapes().get(2));

  }

  /**
   * Test getShapes() returns copy of shapes to avoid modification outside class.
   */
  @Test
  public void testGetShapesUnmodifiable() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);

    // Make equivalent shapes
    I2DShape oval = new Oval("oval", 2, 3, 1, 1, 0,
            0, 255);

    // Get list
    List<I2DShape> list = this.album.getShapes();

    // Make sure changes to list don't affect photo album objects
    list.get(0).move(10, 10);
    assertEquals(album.getShapes().get(0), oval);

    list.remove(0);
    assertEquals(1, list.size());
    assertEquals(2, this.album.getShapes().size());
  }

  /**
   * Test moveShape() if pass invalid name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMoveShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);

    // Try to move nonexistent shape
    this.album.moveShape("shape", 3, 3);
  }

  /**
   * Test moveShape().
   */
  @Test
  public void testMoveShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Get list of shapes for testing
    List<I2DShape> shapes = this.album.getShapes();

    // Check locations before move
    assertEquals(1, shapes.get(0).getX(), DELTA);
    assertEquals(1, shapes.get(0).getY(), DELTA);
    assertEquals(0, shapes.get(1).getX(), DELTA);
    assertEquals(0, shapes.get(1).getY(), DELTA);
    assertEquals(0, shapes.get(2).getX(), DELTA);
    assertEquals(0, shapes.get(2).getY(), DELTA);

    // Move and check
    this.album.moveShape("oval", 3, 2);
    this.album.moveShape("rectangle", 5, 5);

    // Get list of shapes for testing
    List<I2DShape> afterMove = this.album.getShapes();
    assertEquals(3, afterMove.get(0).getX(), DELTA);
    assertEquals(2, afterMove.get(0).getY(), DELTA);
    assertEquals(5, afterMove.get(1).getX(), DELTA);
    assertEquals(5, afterMove.get(1).getY(), DELTA);
    assertEquals(0, shapes.get(2).getX(), DELTA);
    assertEquals(0, shapes.get(2).getY(), DELTA);
  }

  /**
   * Test bad colorShape().
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Pass bad name
    this.album.colorShape("shape", 1, 1, 1);
  }

  /**
   * Test bad colorShape() throws exception from RGB class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadColorShape1() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Pass bad name
    this.album.colorShape("shape", -1, 1, 1);
  }

  /**
   * Test colorShape().
   */
  @Test
  public void testColorShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Recolor shapes
    this.album.colorShape("triangle", 1, 1, 1);
    this.album.colorShape("oval", 3, 3, 3);

    // Create RGB for testing
    RGB triangleRGB = new RGB(1, 1, 1);
    RGB ovalRGB = new RGB(3, 3, 3);
    assertTrue(this.album.getShapes().get(0).getRGB().equals(ovalRGB));
    assertTrue(this.album.getShapes().get(2).getRGB().equals(triangleRGB));
  }

  /**
   * Test bad resizeShape() with bad name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadResizeShape1() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    this.album.resizeShape("shape", 3, 3);
  }

  /**
   * Test bad resizeShape() with bad length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadResizeShape2() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);

    this.album.resizeShape("oval", 0, 3);
  }


  /**
   * Test bad resizeShape() with bad width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadResizeShape3() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);

    this.album.resizeShape("oval", 1, -3);
  }

  /**
   * Test resizeShape().
   */
  @Test
  public void testResizeShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Resize and check
    this.album.resizeShape("oval", 3, 5);
    this.album.resizeShape("rectangle", 10, 11);
    assertEquals(3, this.album.getShapes().get(0).getLength(), DELTA);
    assertEquals(5, this.album.getShapes().get(0).getWidth(), DELTA);
    assertEquals(10, this.album.getShapes().get(1).getLength(), DELTA);
    assertEquals(11, this.album.getShapes().get(1).getWidth(), DELTA);

  }

  /**
   * Test bad deleteShape() with bad name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadDeleteShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    this.album.deleteShape("shape");
  }

  /**
   * Test deleteShape().
   */
  @Test
  public void testDeleteShape() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    // Remove and check
    this.album.deleteShape("oval");
    this.album.deleteShape("triangle");
    assertEquals(1, this.album.getShapes().size(), DELTA);
    assertEquals(1, this.album.getShapeNames().size(), DELTA);
    assertEquals("rectangle", this.album.getShapes().get(0).getName());
  }

  /**
   * Test getCurrentState().
   */
  @Test
  public void testGetCurrentState() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);

    // Check string
    I2DShape oval = new Oval("oval", 2, 3, 1, 1, 0,
            0, 255);
    I2DShape rectangle = new Rectangle("rectangle", 3, 3, 0, 0,
            0, 0, 0);

    assertEquals("Current Shapes:\n" + oval.toString()
            + "\n\n" + rectangle.toString() + "\n\n", this.album.getCurrentState());

    // Check after change
    this.album.colorShape("oval", 255, 255, 255);
    I2DShape oval1 = new Oval("oval", 2, 3, 1, 1, 255,
            255, 255);
    assertEquals("Current Shapes:\n" + oval1.toString()
            + "\n\n" + rectangle.toString() + "\n\n", this.album.getCurrentState());

  }

  /**
   * Test getSnapshots().
   */
  @Test
  public void testGetSnapshots() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    assertEquals(0, this.album.getSnapshots().size(), DELTA);

    this.album.takeSnapshot("test snapshot");
    this.album.takeSnapshot("second snapshot");
    assertEquals(2, this.album.getSnapshots().size(), DELTA);
  }

  /**
   * Test getSnapshots() returns unmodifiable list.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testUnmodifiableSnapshots() {
    // Add shapes and take snapshot
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.takeSnapshot("snapshot");
    // Get list
    List<Snapshot> snaps = this.album.getSnapshots();
    // Try to modify
    snaps.remove(0);
  }

  /**
   * Test editing shapes on canvas does not change shape on snapshots.
   */
  @Test
  public void testShapeChanges() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);

    // Create snapshot
    this.album.takeSnapshot("snap shot test");
    String snapshotBefore = this.album.getSnapshots().get(0).toString();
    System.out.println(snapshotBefore);

    // Edit album
    this.album.resizeShape("oval", 10, 3);
    assertEquals(10, this.album.getShapes().get(0).getLength(), DELTA);

    // Check no changes to shapshot
    String snapshotAfter = this.album.getSnapshots().get(0).toString();
    assertEquals(snapshotAfter, snapshotBefore);

  }

  /**
   * Test getSnapshotIDs().
   */
  @Test
  public void testGetSnapshotIDs() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    assertEquals(0, this.album.getSnapshotIDs().size(), DELTA);

    this.album.takeSnapshot("test snapshot");
    this.album.takeSnapshot("second snapshot");
    assertEquals(2, this.album.getSnapshotIDs().size(), DELTA);
  }

  /**
   * Test getSnapshotIDs() returns unmodifiable list.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testUnmodifiableSnapshotIDs() {
    // Add shapes and take snapshot
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.takeSnapshot("snapshot");
    // Get list
    List<String> snapIDs = this.album.getSnapshotIDs();
    // Try to modify
    snapIDs.remove(0);
  }


  /**
   * Test takeSnapshot().
   */
  @Test
  public void testTakeSnapshot() {
    // Add shapes
    this.album.makeShape("Oval", "oval", 2, 3, 1, 1, 0,
            0, 255);
    this.album.makeShape("recTangle", "rectangle", 3, 3, 0, 0,
            0, 0, 0);
    this.album.makeShape("triangle", "triangle", 2, 1, 0, 0,
            0, 0, 0);

    this.album.takeSnapshot("test snapshot");
    assertEquals(1, this.album.getSnapshotIDs().size(), DELTA);
    assertEquals(1, this.album.getSnapshots().size(), DELTA);
    assertEquals(this.album.getSnapshots().get(0).getDescription(), "test snapshot");

    // Take new snapshot
    this.album.resizeShape("oval", 5, 5);
    this.album.takeSnapshot("second snapshot");
    assertEquals(2, this.album.getSnapshotIDs().size(), DELTA);
    assertEquals(2, this.album.getSnapshots().size(), DELTA);
    assertEquals("second snapshot", this.album.getSnapshots().get(1).getDescription());
  }

}
