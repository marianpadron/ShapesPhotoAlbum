package utilities;

import java.util.List;

import utilities.shapes.I2DShape;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Snapshot concrete class. Create Snapshot objects that hold I2DShapes on a canvas at a
 * given timestamp.
 */
public class Snapshot {
  private String id;
  private String timestamp;
  private String description;
  private List<I2DShape> shapes;

  /**
   * Constructor.
   * @param description String description of snapshot
   * @param shapes List<I2DShape></I2DShape>
   * @throws IllegalArgumentException if no description provided
   */
  public Snapshot(String description, List<I2DShape> shapes) throws IllegalArgumentException {
//    if (description == null || description.isBlank()) {
//      throw new IllegalArgumentException("A description is needed.");
//    }

    this.shapes = shapes;
    this.description = description;

    // Create ID and timestamp
    Date date = new Date();

    this.id = new Timestamp(date.getTime()).toString() + "-"
            + String.valueOf((new Random()).nextInt(26));
    this.timestamp = this.id.substring(0, 19); // timestamp up to second
  }

  /**
   * Getter method for snapshot id.
   * @return String id
   */
  public String getID() {
    return this.id;
  }

  /**
   * Getter method for snapshot description.
   * @return String description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns a list of the shapes contained within the Snapshot object.
   * @return
   */
  public List<I2DShape> getShapes() {
    return this.shapes;
  }

  /**
   * toString method.
   * @return String
   */
  @Override
  public String toString() {
    String text = "Snapshot ID: " + this.id + "\nTimestamp: "
            + this.timestamp + "\nDescription: " + this.description
            + "\nShape Information:\n\n";
    for (I2DShape shape : this.shapes) {
      text += shape.toString() + "\n\n";
    }
    return text;
  }
}
