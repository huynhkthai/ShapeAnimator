import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

/**
 * The class is used for testing the SVG view. It gets input from different files and checks if the
 * data that is output meets the requirements for a functioning view/animation.
 */
public class SVGViewTest {

  @Test
  public void testSVGView() throws FileNotFoundException {
    AnimationBuilderImpl ledger = new AnimationBuilderImpl(1);
    Readable file = new FileReader("OneShapeMotion.txt");
    cs5004.animator.model.AnimationReader.parseFile(file, ledger);
    SVGView testView = new SVGView(ledger);
    assertEquals("<svg width=\"410\" height=\"191\">\n" + "\n"
            + "<rect id=\"disk1\" x=\"45\" y=\"107\" width=\"20\" height=\"7\" "
            + "begin=\"1ms\" fill=\"rgb(140,117,204)\">\n"
            + "    <animate attributeType=\"xml\" begin=\"25ms\" "
            + "dur=\"10ms\" attributeName=\"y\" from=\"107\" "
            + "to=\"0\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"36ms\""
            + " dur=\"10ms\" attributeName=\"x\" from=\"45\" to=\"195\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"47ms\" "
            + "dur=\"10ms\" attributeName=\"y\" from=\"0\" to=\"184\" "
            + "fill=\"freeze\" />\n"
            + "\n" + "</rect>\n" + "\n" + "\n" + "</svg>", testView.toString()); // ONE MUTATION

    AnimationBuilderImpl builder = new AnimationBuilderImpl(1);
    Readable testFile = new FileReader("smalldemo.txt");
    cs5004.animator.model.AnimationReader.parseFile(testFile, builder);
    SVGView testView2 = new SVGView(builder);
    //Multiple mutations with multiple shapes.
    assertEquals("<svg width=\"360\" height=\"360\">\n" + "\n"
            + "<rect id=\"R\" x=\"0\" y=\"130\" width=\"50\" "
            + "height=\"100\" begin=\"1ms\" "
            + "fill=\"rgb(255,0,0)\">\n"
            + "    <animate attributeType=\"xml\" begin=\"10ms\" dur=\"40ms\" "
            + "attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10ms\" dur=\"40ms\" "
            + "attributeName=\"y\" from=\"130\" to=\"230\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"51ms\" dur=\"19ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70ms\" dur=\"30ms\" "
            + "attributeName=\"x\" from=\"100\" to=\"0\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70ms\" dur=\"30ms\" "
            + "attributeName=\"y\" from=\"230\" to=\"130\" fill=\"freeze\" />\n"
            + "\n" + "</rect>\n" + "\n"
            + "<ellipse id=\"C\" cx=\"240\" cy=\"0\" rx=\"120\" ry=\"60\" begin=\"6ms\" "
            + "fill=\"rgb(0,0,255)\">\n"
            + "    <animate attributeType=\"xml\" begin=\"20ms\" dur=\"30ms\" "
            + "attributeName=\"y\"" + " from=\"0\" to=\"180\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50ms\" dur=\"20ms\" "
            + "attributeName=\"y\" "
            + "from=\"180\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50ms\" dur=\"20ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85,)\"/>\n"
            + "    <animate attributeType=\"xml\" begin=\"70ms\" dur=\"10ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0,)\"/>\n"
            + "\n" + "</ellipse>\n" + "\n" + "\n" + "</svg>", testView2.toString());

  }


  @Test(expected = FileNotFoundException.class) // Check for invalid file reads in view.
  public void testInvalidFileRead() throws FileNotFoundException {
    AnimationBuilderImpl testledger = new AnimationBuilderImpl();
    File testfile = new File("src/thisFileDoesNotExist");
    BufferedReader tempread = new BufferedReader(new FileReader(testfile));
    SVGView invalid = new SVGView(testledger);
  }

}