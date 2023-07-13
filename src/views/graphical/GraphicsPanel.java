package views.graphical;

import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

import utilities.shapes.I2DShape;
import utilities.shapes.RGB;
import utilities.shapes.ShapeTypes;
import utilities.Snapshot;

/**
 * GraphicsPanel class. JPanel child class responsible for rendering snapshot images as part of the
 * MVC PhotoAlbum program.
 */
public class GraphicsPanel extends JPanel {
  private Snapshot snapshot;
  private int width = 700;
  private int height = 700;

  /**
   * Constructor.
   */
  public GraphicsPanel() {
    super();
    this.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * Method called by controller when rendering shape images. Takes a Snapshot object passed by
   * controller from model and initializes a snapshot attribute that represents the
   * snapshot to be drawn by the paintComponent method.
   * @param snapshot Snapshot object
   */
  public void paintSnapshot(Snapshot snapshot) {
    this.snapshot = snapshot;
    Graphics2D g = (Graphics2D) getGraphics();
    repaint();
  }

  /**
   * Paints shapes listed by the Snapshot object passed by the controller.
   * @param graphics the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
     Graphics2D g = (Graphics2D) graphics;

    // Check if snapshot is null before attempting to use it
    if (this.snapshot == null) {
      return;
    }

    // Get list of shapes within snapshot and draw
      List<I2DShape> shapes = this.snapshot.getShapes();
      for(I2DShape shape : shapes) {
        RGB rgb = shape.getRGB();
        g.setPaint(new Color(rgb.getR(), rgb.getG(), rgb.getB()));

        if (shape.getType() == ShapeTypes.OVAL) {
          g.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getLength());
        }
        if(shape.getType() == ShapeTypes.RECTANGLE) {
          g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getLength());
        }
      }
    }
}
