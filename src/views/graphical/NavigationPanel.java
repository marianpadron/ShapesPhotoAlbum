package views.graphical;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import controller.PhotoAlbumController;

/**
 * NavigationPanel class. JPanel child class that holds buttons used by the view of the PhotoAlbum
 * MVC program. It helps communicate commands from user to main view class.
 */
public class NavigationPanel extends JPanel implements ActionListener {
  private int width = 100;
  private int height = 90;
  private JButton nextButton;
  private JButton prevButton;
  private JComboBox albumButton;
  private JButton quitButton;
  private ViewFrame view;

  /**
   * Constructor. Takes MVC program controller and view for communication.
   * @param view ViewFrame
   */
  public NavigationPanel(ViewFrame view) {
    super();
    this.view = view;
    this.setPreferredSize(new Dimension(this.width, this.height));
    this.nextButton = new JButton("next");
    this.prevButton = new JButton("previous");
    this.albumButton = new JComboBox<String>();
    this.quitButton = new JButton("quit");

    // Set up navigation buttons
    this.setupButtons();
  }

  /**
   * Sets up navigation buttons within JPanel.
   */
  private void setupButtons() {
    Border roundedBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true);
    this.nextButton.setBorder(roundedBorder);
    this.prevButton.setBorder(roundedBorder);
    this.albumButton.setBorder(roundedBorder);
    this.quitButton.setBorder(roundedBorder);

    this.nextButton.setPreferredSize(new Dimension(this.width - 10, this.height / 3));
    this.prevButton.setPreferredSize(new Dimension(this.width - 10, this.height / 3));
    this.quitButton.setPreferredSize(new Dimension(this.width - 10, this.height / 3));

    this.nextButton.setBackground(Color.lightGray);
    this.prevButton.setBackground(Color.lightGray);
    this.albumButton.setBackground(Color.lightGray);
    this.quitButton.setBackground(Color.lightGray);

    this.nextButton.addActionListener(this);
    this.prevButton.addActionListener(this);
    this.quitButton.addActionListener(this);
    this.albumButton.addActionListener(this);

    this.add(this.prevButton);
    this.add(this.nextButton);
    this.add(this.albumButton);
    this.add(this.quitButton);

  }

  /**
   * Passes list of snapshot IDs once initialized to the navigation buttons.
   * @param ids List<String>
   */
  public void idsToButton(List<String> ids) {
    for (String id : ids) {
      this.albumButton.addItem(id);
    }
  }


  /**
   * Invoked when an action occurs. Action listener for navigation buttons. Communicate with views
   * and controller the action requested by user.
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == this.nextButton) {
      this.view.requestNext();
      this.view.updateHeader();

    } else if (e.getSource() == this.prevButton) {
      this.view.requestPrevious();
      this.view.updateHeader();

    } else if (e.getSource() == this.quitButton) {
      System.exit(0);

    } else if (e.getSource() == this.albumButton) {
      String selected = (String) this.albumButton.getSelectedItem();
      this.view.requestSnapshot(selected);
      this.view.updateHeader();
    }
  }

}
