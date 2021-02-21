
import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeName;

import static org.junit.Assert.assertEquals;


/**
 * This is the Junit test class for the rectangle test class.
 */
public class RectangleTest {
  private Shape tester;

  @Before
  public void setUp() {
    tester = new Rectangle("Tester", ShapeName.Rectangle, 0, 0, 10, 50,
            0, 100, 100, 200, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidShapeType() {
    Shape test = new Rectangle("Test", ShapeName.Triangle, 2, 3, 3, 10,
            23, 44, 150, 23, 167);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegWidth() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 3, 4, -10, 20,
            1, 10, 100, 30, 150);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegHeight() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 3, 4, 10, -20,
            10, 100, 100, 30, 150);
  }

  //Test RGB value too large
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidColor() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 5, -12, 50, 23,
            1, 55, 300, 23, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidColorNeg() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 2, 34, 29, 89,
            1, 100, -23, 24, 200);
  }

  //Test constructing a rectangle with appear time greater than disappear time
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAppear() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 3, 4, 10, 20,
            10, 1, 100, 30, 150);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegTime() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 3, 4, 10, 20,
            -29, 22, 0, 0, 150);
  }

  @Test
  public void testColorChange() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 0, 1, 10, 20,
            0, 100, 200, 100, 150);
    assertEquals(200, test.getColorR(), 1e-9);
    assertEquals(100, test.getColorG(), 1e-9);
    assertEquals(150, test.getColorB(), 1e-9);
    test.changeColor(24, 56, 100, 55, 100);
    assertEquals("Name: Test\n"
            + "Type: Rectangle\n"
            + "Min Corner: (0,1), Width: 10, Height: 20, Color: (24,56,100)\n"
            + "Appears at t=0\n"
            + "Disappears at t=100\n", test.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorChangeInvalidTooLarge() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 0, 1, 10, 20,
            0, 100, 200, 100, 150);
    test.changeColor(300, 200, 150, 10, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorChangeInvalidNegative() {
    Shape test = new Rectangle("Test", ShapeName.Rectangle, 0, 1, 10, 20,
            0, 100, 200, 100, 150);
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

  @Test
  public void testSuccessfulResize() {
    assertEquals(10, tester.getParameter1(), 1e-9);
    assertEquals(50, tester.getParameter2(), 1e-9);
    tester.resize(25, 75, 10, 20);
    assertEquals(25, tester.getParameter1(), 1e-9);
    assertEquals(75, tester.getParameter2(), 1e-9);
  }

  @Test
  public void testSuccessfulScale() {
    assertEquals(10, tester.getParameter1(), 1e-9);
    assertEquals(50, tester.getParameter2(), 1e-9);
    tester.resize(25, 75, 10, 20);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorSameToFromTime() {
    tester.changeColor(50, 150, 250, 10, 10);
  }

  @Test
  public void testMoveMultipleTimes() {
    assertEquals(0, tester.getX(), 1e-9);
    assertEquals(0, tester.getY(), 1e-9);
    tester.move(20, 45, 15, 30);
    tester.move(1, 1, 35, 80);
    tester.move(-12, -99, 20, 50);
    assertEquals(-12, tester.getX(), 1e-9);
    assertEquals(-99, tester.getY(), 1e-9);
  }

}