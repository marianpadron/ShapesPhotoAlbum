package viewstest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import views.web.HtmlView;

/**
 * HtmlViewTest Junit class.
 */
public class HtmlViewTest {

  private HtmlView writer;
  private String outfile = "html_view_test_out.html";
  private String header = "HtmlViewTest";

  /**
   * Initialize object for testing.
   */
  @Before
  public void setup() {
    this.writer = new HtmlView(outfile, header);
  }

  /**
   * Test open new div page for svg.
   */
  @Test
  public void test1() {
    String comparator = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                .snapshot {
                    border: 3px outset black;
                    background-color: white;
                }
            </style>
            </head>
            <body>
            <h1>HtmlViewTest</h1>
            <div class="snapshot">
            	<h2>test1</h2>
            	<svg width="1000" height="1000">
            	""";
    writer.openPage("test1");
    assertEquals(comparator, writer.getHtmlString());
  }

  /**
   * Test close div page for svg.
   */
  @Test
  public void test2() {
    String comparator = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                .snapshot {
                    border: 3px outset black;
                    background-color: white;
                }
            </style>
            </head>
            <body>
            <h1>HtmlViewTest</h1>
            <div class="snapshot">
            	<h2>test1</h2>
            	<svg width="1000" height="1000">
            	</svg>
            </div>
            """;
    writer.openPage("test1");
    writer.closePage();
    assertEquals(comparator, writer.getHtmlString());
  }

  /**
   * Test add rectangle.
   */
  @Test
  public void test3() {
    String comparator = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                .snapshot {
                    border: 3px outset black;
                    background-color: white;
                }
            </style>
            </head>
            <body>
            <h1>HtmlViewTest</h1>
            <div class="snapshot">
            	<h2>test1</h2>
            	<svg width="1000" height="1000">
            		<rect id="B1" x="260" y="365" width="100" height="385"
             fill="rgb(255,0,0)">
            		</rect>
            		""";
    writer.openPage("test1");
    writer.newRect("B1", "385", "100", "260", "365", "255,0,0");
    assertEquals(comparator, writer.getHtmlString());
  }

  /**
   * Test add oval.
   */
  @Test
  public void test4() {
    String comparator = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                .snapshot {
                    border: 3px outset black;
                    background-color: white;
                }
            </style>
            </head>
            <body>
            <h1>HtmlViewTest</h1>
            <div class="snapshot">
            	<h2>test1</h2>
            	<svg width="1000" height="1000">
            		<ellipse id="B1" cx="260" cy="365" rx="100" ry="385"
            fill="rgb(255,0,0)">
            		</ellipse>
            		""";
    writer.openPage("test1");
    writer.newOval("B1", "385", "100", "260", "365", "255,0,0");
    assertEquals(comparator, writer.getHtmlString());
  }

  /**
   * Test close header.
   */
  @Test
  public void test5() {
    String comparator = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                .snapshot {
                    border: 3px outset black;
                    background-color: white;
                }
            </style>
            </head>
            <body>
            <h1>HtmlViewTest</h1>
            </body>
            </html>""";
    writer.closeHeader();
    assertEquals(comparator, writer.getHtmlString());
  }

  /**
   * Test write to html without closing the header.
   */
  @Test(expected = IllegalStateException.class)
  public void test6() {
    writer.startView();
  }

  /**
   * Tests writing to html file.
   */
  @Test
  public void test7() throws IOException {
    String comparator = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                .snapshot {
                    border: 3px outset black;
                    background-color: white;
                }
            </style>
            </head>
            <body>
            <h1>HtmlViewTest</h1>
            <div class="snapshot">
            	<h2>test1</h2>
            	<svg width="1000" height="1000">
            		<ellipse id="B1" cx="260" cy="365" rx="100" ry="385"
            fill="rgb(255,0,0)">
            		</ellipse>
            	</svg>
            </div>
            </body>
            </html>""";
    writer.openPage("test1");
    writer.newOval("B1", "385", "100", "260", "365", "255,0,0");
    writer.closePage();
    writer.closeHeader();
    writer.startView();
    byte[] encodedBytes = Files.readAllBytes(Paths.get(outfile));
    String file_string = new String(encodedBytes, StandardCharsets.UTF_8);
    assertEquals(comparator, file_string);
  }
}
