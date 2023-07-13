package views.graphical;

import java.awt.*;

import javax.swing.*;

import controller.PhotoAlbumController;
import utilities.Snapshot;
import views.IView;

/**
 * ViewFrame class. View object for PhotoAlbum MVC program. Communicates with controller and passes
 * requests to graphicsPanel, navigationPanel, and headerPanel which render the complete views to
 * the user.
 */
public class ViewFrame extends JFrame implements IView {

  private int width = 1000;
  public int height = 1000;
  private String title;
  private GraphicsPanel graphicsPanel;
  private NavigationPanel navigationPanel;
  private HeaderPanel headerPanel;
  private PhotoAlbumController controller;

  /**
   * Constructor. Takes a header for the JFrame view, dimensions, and reference to program
   * controller for communication.
   * @param title String header
   * @param width int
   * @param height int
   * @param controller PhotoAlbumController
   */
  public ViewFrame(String title, int width, int height, PhotoAlbumController controller) {
    super();
    this.controller = controller;
    this.title = title;
    this.width = width;
    this.height = height;
    this.graphicsPanel = new GraphicsPanel();
    this.navigationPanel = new NavigationPanel(this);
    this.headerPanel = new HeaderPanel();
  }

  /**
   * Displays views to the user.
   */
  @Override
  public void startView() {
    this.setupView(); // add panels and graphics to frame
    this.navigationPanel.idsToButton(this.controller.getIds()); // pass data to navigation buttons
    this.setVisible(true);
  }

  /**
   * Receives graphics data from controller and passes to graphics panel for rendering.
   * @param snapshot Snapshot object
   */
  public void receiveGraphics(Snapshot snapshot) {
    this.graphicsPanel.paintSnapshot(snapshot);
  }

  /**
   * Sets up frame and panels for view.
   */
  private void setupView() {
    this.setTitle(this.title);
    this.setDefaultCloseOperation(3); // ends program when exit out
    this.setLayout(new BorderLayout());

    this.add(this.graphicsPanel);
    this.add(this.navigationPanel, BorderLayout.SOUTH);
    this.add(this.headerPanel, BorderLayout.NORTH);

    this.pack();
    this.setSize(this.width, this.height);
    this.setLocationRelativeTo(null);
  }

  /**
   * Updates header panel with ID and description of snapshot currently being displayed.
   */
  public void updateHeader() {
    int index = this.controller.getSnapIndex();
    String id = this.controller.getIds().get(index);
    String description = this.controller.getDescriptions().get(index);
    this.headerPanel.setHeaderText("ID: " + id + "  " + description);
  }

  /**
   * Passes request from navigation panel to controller to get next snapshot.
   */
  public void requestNext() {
    this.controller.getNext();
  }

  /**
   * Passes request from navigation panel to controller to get previous snapshot.
   */
  public void requestPrevious() {
    this.controller.getPrevious();
  }

  /**
   * Passes request from navigation panel to controller to display a specific snapshot.
   * @param id String id of snapshot to be displayed
   */
  public void requestSnapshot(String id) {
    this.controller.showSnapshot(id);
  }

  /**
   * Shows JOptionPane message when user has clicked through all album pictures.
   */
  public void showMessage() {
    JOptionPane.showMessageDialog(this,
            "You've reached the end of your album.",
            "Shapes Photo Album Message",
            JOptionPane.PLAIN_MESSAGE);
  }

}
