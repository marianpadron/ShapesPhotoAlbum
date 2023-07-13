package controller;

import java.util.List;

import views.graphical.ViewFrame;

/**
 * IPhotoAlbumController interface.
 */
public interface IPhotoAlbumController {
  /**
   * Begins MVC program with graphical view.
   * @param view ViewFrame object for rendering graphics
   */
  public void run(ViewFrame view);

  /**
   * Begins MVC program with web view.
   * @param header String header for HTML rendering
   */
  public void run(String header);

  /**
   * Takes request from graphical view for next snapshot.
   */
  public void getNext();

  /**
   * Takes request from graphical view for previous snapshot.
   */
  public void getPrevious();

  /**
   * Displays snapshot with specific ID when requested by the graphical view.
   * @param id String ID of snapshot to be rendered.
   */
  public void showSnapshot(String id);

}
