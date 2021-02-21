import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Circle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeName;

/**
 * This is the Junit test class for the circle test class.
 */
public class CircleTest {

  private Shape tester;

  @Before
  public void setUp() {
    tester = new Circle("Tester", 0, 0, 10,
            0, 100, 100, 200, 200, ShapeName.Circle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidShapeType() {
    Shape test = new Circle("Test", 2, 3, 10,
            23, 44, 150, 23, 167, ShapeName.Ellipse);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegRadius() {
    Shape test = new Circle("Test", 3, 4, -10,
            1, 10, 100, 30, 150, ShapeName.Circle);
  }


  //Test RGB value too large
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidColor() {
    Shape test = new Circle("Test", 5, -12, 50, 23,
            1, 55, 300, 23, ShapeName.Circle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidColorNeg() {
    Shape test = new Circle("Test", 2, 34, 29, 89,
            1, 100, -23, 240, ShapeName.Circle);
  }

  //Test constructing a rectangle with appear time greater than disappear time
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAppear() {
    Shape test = new Circle("Test", 3, 4, 10, 20,
            10, 1, 100, 30, ShapeName.Circle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegTime() {
    Shape test = new Circle("Test", 3, 4, 10,
            -29, 22, 0, 0, 150, ShapeName.Circle);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorChangeInvalidTooLarge() {
    Shape test = new Circle("Test", 0, 1, 10,
            0, 100, 200, 100, 150, ShapeName.Circle);
    test.changeColor(300, 200, 150, 10, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorChangeInvalidNegative() {
    Shape test = new Circle("Test", 0, 1, 10,
            0, 100, 200, 100, 150, ShapeName.Circle);
    test.changeColor(-150, 200, 150, 10, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidScale() {
    //try scaling with negative factor
    tester.scale(-1, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidScaleTime() {
    //try to scale at invalid time
    tester.scale(10, 50, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidScaleNegTime() {
    //try to scale at a neg time
    tester.scale(10, 25, -90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeParam1Invalid() {
    //try resizing param1 by negative value
    tester.resizeP1(-10, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeParam2() {
    tester.resizeP2(-10, 10, 20);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorSameToFromTime() {
    tester.changeColor(50, 150, 250,
            10, 10);
  }

}