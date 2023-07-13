package controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import controller.PhotoAlbumController;
import model.ShapesPhotoAlbum;
import utilities.ArgsReader;
import utilities.Snapshot;

/**
 * PhotoAlbumControllerTest Junit test class.
 */
public class PhotoAlbumControllerTest {

  private String[] args = {"-in", "test_input.txt", "-view", "graphical", "-out",
          "controller_test_outfile.html", "800", "800"};
  private List<String> argsList;
  private PhotoAlbumController controller;
  private ShapesPhotoAlbum model;
  private String header = "Photo Album Controller Test";
  private int snapshots = 4;
  private String outfile = "controller_test_outfile.html";

  /**
   * Initialize objects for testing.
   */
  @Before
  public void setup() throws FileNotFoundException {
    ArgsReader reader = new ArgsReader(args);
    this.argsList = reader.getArgsList();
    this.model = new ShapesPhotoAlbum();
    this.controller = new PhotoAlbumController(argsList, model);

  }

  /**
   * Run controller, check changes to model were made.
   */
  @Test
  public void test1() {
    // Run controller
    this.controller.run(header);

    // Check changes to model by checking created shapes and snapshots
    List<Snapshot> snapshotList = this.model.getSnapshots();
    assertEquals(snapshots, snapshotList.size());
    assertEquals("After first selfie", snapshotList.get(0).getDescription());
    assertEquals("2nd selfie", snapshotList.get(1).getDescription());
    assertEquals("", snapshotList.get(2).getDescription());
    assertEquals("Selfie after removing the rectangle from the picture",
            snapshotList.get(3).getDescription());
    assertEquals(1, this.model.getShapes().size());
    assertEquals("[myoval]", this.model.getShapeNames().toString());

  }

  /**
   * Test getIds.
   */
  @Test
  public void test2() {
    // Run controller
    this.controller.run(header);

    // Check method
    assertEquals(4, this.controller.getIds().size());
  }

  /**
   * Test getDescriptions.
   */
  @Test
  public void test3() {
    // Run controller
    this.controller.run(header);

    // Check method
    List<String> descriptions = this.controller.getDescriptions();
    assertEquals(4, descriptions.size());
    assertEquals("After first selfie", descriptions.get(0));
    assertEquals("2nd selfie", descriptions.get(1));
    assertEquals("", descriptions.get(2));
    assertEquals("Selfie after removing the rectangle from the picture",
            descriptions.get(3));
  }

  /**
   * Test getaSnapIndex.
   */
  @Test
  public void test4() {
    // Run controller
    this.controller.run(header);

    // Check method
    assertEquals(0, this.controller.getSnapIndex());
  }

  /**
   * Test web view is created.
   */
  @Test
  public void test5() throws IOException {
    // Run controller
    this.controller.run(header);

    // Check html file was created
    File file = new File(outfile);
    assertTrue(file.exists());
    byte[] encodedBytes = Files.readAllBytes(Paths.get(outfile));
    String file_string = new String(encodedBytes, StandardCharsets.UTF_8);
    assertTrue(file_string.contains(header));
  }

}
