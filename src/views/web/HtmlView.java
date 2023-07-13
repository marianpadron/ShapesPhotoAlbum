package views.web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import views.IView;

/**
 * HtmlView class, writes shapes into html format and writes file for viewing.
 */
public class HtmlView implements IView {

  private final String widthBuffer = "***width***";
  private final String heightBuffer = "***height***";
  private final String idBuffer = "***id***";
  private final String xBuffer = "***x***";
  private final String yBuffer = "***y***";
  private final String rgbBuffer = "***r,g,b***";
  private final String titleBuffer = "***title***";
  private final String headerBuffer = "***header***";
  private final String[] headerTemplate = {"<!DOCTYPE html>\n" +
          "<html>\n" +
          "<head>\n" +
          "    <style>\n" +
          "    .snapshot {\n" +
          "        border: 3px outset black;\n" +
          "        background-color: white;\n" +
          "    }\n" +
          "</style>\n" +
          "</head>\n" +
          "<body>\n" +
          "<h1>***header***</h1>\n", "</body>\n</html>"};
  private final String[] divTemplate = {"<div class=\"snapshot\">\n", "</div>\n"};
  private final String[] sgvTemplate = {"\t<h2>***title***</h2>\n\t<svg width=\"1000\" height=\"1000\">\n",
          "\t</svg>\n"};
  private final String rectTemplate = """
          \t\t<rect id="***id***" x="***x***" y="***y***" width="***width***" height="***height***"
           fill="rgb(***r,g,b***)">\n\t\t</rect>
          """;
  private final String ellipseTemplate = """
          \t\t<ellipse id="***id***" cx="***x***" cy="***y***" rx="***width***" ry="***height***" 
          fill="rgb(***r,g,b***)">\n\t\t</ellipse>
          """;
  private boolean isClosed = false;
  private String html_string;
  private String out_file;

  /**
   * Constructor. Initializes a string with a html header.
   */
  public HtmlView(String out_file, String header){
    this.out_file = out_file;
    this.setHeader(header);
  }

  /**
   * Sets the header for the html file.
   * @param header String header text
   */
  private void setHeader(String header) {
    this.html_string = this.headerTemplate[0].replace(this.headerBuffer, header);
  }


  /**
   * Opens divisor to represent a page in html text body.
   * @param title String title for page
   */
  public void openPage(String title) {
    this.html_string += divTemplate[0] + sgvTemplate[0].replace(titleBuffer, title);
  }

  /**
   * Closes a divisor in of html file that represents a page.
   */
  public void closePage() {
    this.html_string += sgvTemplate[1] + divTemplate[1];
    this.isClosed = true;
  }


  /**
   * Closes the main header in the html file once done adding to body.
   */
  public void closeHeader() {
    this.html_string += this.headerTemplate[1];
    this.isClosed = true;
  }

  /**
   * Writes SVG rectangle shape into divisor of html file.
   * @param name String name of shape
   * @param length String length size
   * @param width String width size
   * @param xpos String x position
   * @param ypos String y position
   * @param rgb String red, blue, green rbg values
   */
  public void newRect(String name, String length, String width, String xpos, String ypos,
                      String rgb) {
    this.html_string += this.rectTemplate
            .replace(idBuffer, name)
            .replace(heightBuffer, length)
            .replace(widthBuffer, width)
            .replace(xBuffer, xpos)
            .replace(yBuffer, ypos)
            .replace(rgbBuffer, rgb);
  }

  /**
   * Writes SVG rectangle shape into divisor of html file.
   * @param name String name of shape
   * @param length String length size
   * @param width String width size
   * @param xpos String x position
   * @param ypos String y position
   * @param rgb String red, blue, green rbg values
   */
  public void newOval(String name, String length, String width, String xpos, String ypos,
                      String rgb) {
    this.html_string += this.ellipseTemplate
            .replace(idBuffer, name)
            .replace(heightBuffer, length)
            .replace(widthBuffer, width)
            .replace(xBuffer, xpos)
            .replace(yBuffer, ypos)
            .replace(rgbBuffer, rgb);

  }

  /**
   * Writes html String representation into a html file for viewing.
   */
  public void startView() {
    if (!this.isClosed) {
      throw new IllegalStateException("Header of HTML file must be closed before writing to " +
              "outfile.");
    }

    try {
      File file = new File(this.out_file);
      FileWriter writer = new FileWriter(file);
      writer.write(this.html_string);
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred while creating the HTML file.");
      e.printStackTrace();
    }

  }

  /**
   * Getter method for html string, used for testing.
   * @return String
   */
  public String getHtmlString() {
    return this.html_string;
  }

}
