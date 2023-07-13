package views.graphical;

import javax.swing.*;

/**
 * HeaderPanel class. JPanel child class used to set the header in the ViewFrame for MVC PhotoAlbum
 * program.
 */
public class HeaderPanel extends JPanel {
  private String text = "";
  private JLabel label;

  /**
   * Constructor.
   */
  public HeaderPanel() {
    this.label = new JLabel(this.text);
    this.add(this.label);
  }

  /**
   * Sets the Jlabel displayed in the panel to the passed text.
   * @param text String
   */
  public void setHeaderText(String text) {
    this.text = text;
    this.label.setText(this.text);
  }

}
