import org.junit.Test;

import cs5004.animator.model.AnimatorImpl;
import cs5004.animator.model.Circle;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeName;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class that test the functionality of Model.AnimatorImpl.
 */
public class AnimatorImplTest {

  @Test
  public void testAnimation() {
    AnimatorImpl test = new AnimatorImpl();

    // Testing empty registers.
    assertEquals("Shapes: " + "\n" + "\n", test.getAnimation()); // Empty registers.

    // Test creating a Model.Rectangle.
    Rectangle check = test.createRectangle("R", 3, 3, 4, 6, 15,
            25, 0, 0, 1, ShapeName.Rectangle);

    //Testing register after shape is created.
    assertEquals("Shapes: \n" + "Name: R" + "\n" + "Type: Rectangle" + "\n"
            + "Min Corner: (3,3), Width: 4, Height: 6, Color: (0.0,0.0,1.0)" + "\n"
            + "Appears at t=15\n" + "Disappears at t=25\n" + "\n", test.getAnimation());

    // Testing mutation, changed color.
    test.changeColor(check, 1, 1, 1, 17, 19);
    assertEquals("Shapes: \n" + "Name: R" + "\n" + "Type: Rectangle" + "\n"
                    + "Min Corner: (3,3), Width: 4, Height: 6, Color: (0.0,0.0,1.0)" + "\n"
                    + "Appears at t=15\n" + "Disappears at t=25\n" + "\n"
                    + "Model.Shape R changes color from (0.0,0.0,1.0)"
                    + " to (1.0,1.0,1.0) from t=17 to t=19\n",
            test.getAnimation());

    // Testing creating an Model.Oval.
    Oval oval = test.createOval("O", 2, 2, 3, 5, 5,
            15, 1, 1, 1, ShapeName.Ellipse);
    assertEquals("Shapes: \n" + "Name: R" + "\n" + "Type: Rectangle" + "\n"
                    + "Min Corner: (3,3), Width: 4, Height: 6, Color: (0.0,0.0,1.0)" + "\n"
                    + "Appears at t=15\n" + "Disappears at t=25\n"
                    + "Name: O" + "\n" + "Type: Ellipse\n"
                    + "Center: (2,2), Width: 6, Height: 10,"
                    + " Color: (1.0,1.0,1.0)" + "\n"
                    + "Appears at t=5\n" + "Disappears at t=15\n" + "\n"
                    + "Model.Shape R changes color from (0.0,0.0,1.0)"
                    + " to (1.0,1.0,1.0) from t=17 to t=19\n",
            test.getAnimation());

    //Test mutation on Model.Oval.
    test.scaleOval(oval, 3, 7, 10);
    assertEquals("Shapes: \n" + "Name: R" + "\n" + "Type: Rectangle" + "\n"
                    + "Min Corner: (3,3), Width: 4, Height: 6, Color: (0.0,0.0,1.0)" + "\n"
                    + "Appears at t=15\n" + "Disappears at t=25\n"
                    + "Name: O" + "\n" + "Type: Ellipse\n" + "Center: (2,2), Width: 6, "
                    + "Height: 10,"
                    + " Color: (1.0,1.0,1.0)" + "\n"
                    + "Appears at t=5\n" + "Disappears at t=15\n" + "\n"
                    + "Model.Shape R changes color from (0.0,0.0,1.0) to (1.0,1.0,1.0) "
                    + "from t=17 to t=19\n"
                    + "Model.Shape O scales from Vertical Radius: 5 Horizontal Radius: 3 to "
                    + "Vertical Radius: 15 "
                    + "Horizontal Radius: 9 from t=5 to t=15" + "\n",
            test.getAnimation());

  }

  @Test(expected = IllegalArgumentException.class)
  public void shapeWithSameName() {
    AnimatorImpl test = new AnimatorImpl();
    Shape check = test.createRectangle("R", 3, 3, 4, 6, 15,
            25, 0, 0, 1, ShapeName.Rectangle);

    // Same type Same Name
    Shape check2 = test.createRectangle("R", 3, 3, 4, 6, 15,
            25, 0, 0, 1, ShapeName.Rectangle);

    // Different Type Same Name
    Shape check3 = test.createOval("R", 3, 3, 4, 6,
            15, 25, 0, 0, 1, ShapeName.Ellipse);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shapeInvalidConstruction() {
    AnimatorImpl test = new AnimatorImpl();
    Rectangle check = test.createRectangle("R", 3, 3, 4, 6, 75,
            25, 257, 0, 1, ShapeName.Rectangle);
    Circle testC = test.createCircle("C", -13, 3, 4, 6, 75,
            25, 256, 0, ShapeName.Circle);
    Oval check3 = test.createOval("R", 3, 3, 0, -1,
            75, 25, 257, 0, 1, ShapeName.Ellipse);
  }
}