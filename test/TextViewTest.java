import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * The purpose for this class is to test the TextView. It allows for files to be passed in and
 * evaluated in order to determine the effectiveness of the View.
 */
public class TextViewTest {

  @Test
  public void testTextAnimation() throws FileNotFoundException {

    AnimationBuilderImpl ledger = new AnimationBuilderImpl();
    TextView emptyView = new TextView(ledger);
    assertEquals("" + "\n" + "", emptyView.toString()); // check empty view.
    Readable file = new FileReader("OneShapeMotion.txt");
    cs5004.animator.model.AnimationReader.parseFile(file, ledger);
    TextView testView = new TextView(ledger);
    assertEquals("Name: disk1\n"
                    + "Type: Rectangle\n"
                    + "Min Corner: (190,157), Width: 20, Height: 7, "
                    + "Color: (140.0,117.0,204.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=89\n"
                    + "\n"
                    + "Shape disk1 moves from (190, 157) to (190,50) at t=35\n"
                    + "Shape disk1 moves from (190, 50) to (340,50) at t=46\n"
                    + "Shape disk1 moves from (340, 50) to (340,234) at t=57\n",
            testView.toString()); // Check view for mutation of one shape.
    Readable testFile = new FileReader("smalldemo.txt");
    AnimationBuilderImpl testLedger = new AnimationBuilderImpl();
    cs5004.animator.model.AnimationReader.parseFile(testFile, testLedger);

    TextView multipleMutations = new TextView(testLedger);
    assertEquals("Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min Corner: (200,200), Width: 50, Height: 100, "
                    + "Color: (255.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "Name: C\n"
                    + "Type: Ellipse\n"
                    + "Center: (440,70), Width: 120, Height: 240, Color: (0.0,0.0,255.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200, 200) to (300,300) at t=50\n"
                    + "Shape R scales from Width: 50 Height: 100 to Width: 25 Height: 100 "
                    + "at t=70\n"
                    + "Shape R moves from (300, 300) to (200,200) at t=100\n"
                    + "Shape C moves from (440, 70) to (440,250) at t=50\n"
                    + "Shape C changes color from (0.0,0.0,255.0) to (0.0,170.0,85.0) at t=70\n"
                    + "Shape C moves from (440, 250) to (440,370) at t=70\n"
                    + "Shape C changes color from (0.0,170.0,85.0) to (0.0,255.0,0.0) at t=80\n",
            multipleMutations.toString()); // Test multiple mutations.
  }


  @Test(expected = FileNotFoundException.class) // Check for invalid file reads in view.
  public void testInvalidFileRead() throws FileNotFoundException {
    AnimationBuilderImpl ledger = new AnimationBuilderImpl();
    File file = new File("src/thisFileDoesNotExist");
    BufferedReader read = new BufferedReader(new FileReader(file));
    TextView invalid = new TextView(ledger);
  }
}
