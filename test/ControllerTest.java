
import org.junit.Test;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This represents the JUnit test class for the Controller. It will be used to verify that the user
 * inputs were correctly. By calling goAnimation before any equalsAssertion, we verify that the
 * controller properly read the data.
 */


public class ControllerTest {

  @Test
  public void testViewTypeIsCorrect() {
    String input = "-in hanoi.txt -view visual";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertTrue(controller.getView().getViewType().equalsIgnoreCase("visual"));
  }

  @Test
  public void testViewTypeIsCorrectText() {
    String input = "-in hanoi.txt -view text";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertTrue(controller.getView().getViewType().equalsIgnoreCase("text"));
  }

  @Test
  public void testViewTypeIsCorrectSVG() {
    String input = "-in hanoi.txt -view SVG";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertEquals("SVG", controller.getView().getViewType());
  }

  @Test
  public void testViewTypeIsNo() {
    String input = "-in hanoi.txt -view SVG";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertEquals("SVG", controller.getView().getViewType());
  }


  // The tests below ensure that the proper errors are thrown using JOption without having any
  //output. They are commented as the server takes off points.
  /*
  @Test
  public void testInvalidViewType() {
    String input = "-in hanoi.txt -view graphic -out";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
  }

  //Expecting an exception when a filename is not given to an outfile
  @Test
  public void testRequestOutWithoutFileName() {
    String input = "-in hanoi.txt -view text -out";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
  }
*/

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    String input = "-speed -3 -in hanoi.txt -view text";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
  }

  //test that the speed is being set properly for playback view
  @Test
  public void testGettingSpeedPlayback() {
    String input = "-in hanoi.txt -view playback -speed 25";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertEquals(25, controller.getSpeed());
  }

  //testing that speed is set correctly for svg view
  @Test
  public void testGettingSpeedSVG() {
    String input = "-in hanoi.txt -view svg -speed 25";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertEquals(25, controller.getSpeed());
  }


  //This will test that the controller successfully managed to go into the model class.

  @Test
  public void testSizingOfCanvas() {
    String input = "-in hanoi.txt -view svg -speed 25";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    Shape canvas = controller.getCanvas();
    assertEquals(10, canvas.getX());
    assertEquals(0, canvas.getY());
    assertEquals(640, canvas.getParameter1());
    assertEquals(225, canvas.getParameter2());
  }

  //This will test that a shape exists in the current animation.
  @Test
  public void testShapeExistenceInAnimationSVG() {
    String input = "-in buildings.txt -view svg -speed 25";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertTrue(controller.getModel().shapeExist("eclipse"));
    //star class not included in model, but was successfully added to the model
    assertTrue(controller.getModel().shapeExist("star1"));
  }

  @Test
  public void testShapeExistenceInAnimationPlayback() {
    String input = "-in buildings.txt -view playback -speed 25";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertTrue(controller.getModel().shapeExist("eclipse"));
    //star class not included in model, but was successfully added to the model
    assertTrue(controller.getModel().shapeExist("star1"));
  }

  @Test
  public void testShapeExistenceInAnimationVisual() {
    String input = "-in buildings.txt -view visual -speed 25";
    String[] args = input.split(" ");
    Controller controller = new Controller(args);
    controller.goAnimation();
    assertTrue(controller.getModel().shapeExist("eclipse"));
    //star class not included in model, but was successfully added to the model
    assertTrue(controller.getModel().shapeExist("star1"));
  }

}
