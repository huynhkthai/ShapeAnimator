package cs5004.animator.view;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Frame;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeName;

/**
 * This generates a SVG view which is browser compatible text if saved in a .svg file. As this
 * stands, it will just be text, one need to manually save it in .svg for it to be functional in a
 * web browser.
 */
public class SVGView extends AbstractView {
  private StringBuilder svgBody;
  //protected Shape canvas;
  //protected ArrayList<FrameImpl> animationBook;
  //protected ArrayList<FrameImpl> nameBook;

  /**
   * Construct a SVG view.
   */
  public SVGView(AnimationBuilder builder) {
    super(builder);
    this.speed = builder.getSpeed();
    //assign canvas
    this.canvas = builder.getCanvas();
    //assign animationBook
    this.animationBook = builder.getAnimationStates();
    //assign nameBook
    this.nameBook = builder.getStartAndFinalState();
    this.svgBody = new StringBuilder();
  }


  @Override
  public String toString() {
    createAnimationCanvas();
    createSVGBody();
    return svgBody.toString();
  }

  @Override
  public String getViewType() {
    return "SVG";
  }

  @Override
  public void render() {
    createAnimationCanvas();
    createSVGBody();
    //return svgBody.toString();
    System.out.println(svgBody);
  }

  /**
   * This method will declare the canvas on which the animation will be played on.
   */
  private void createAnimationCanvas() {
    this.svgBody.append("<svg width=\"").append(canvas.getParameter1())
            .append("\" height=\"").append(canvas.getParameter2()).append("\">\n\n");
  }

  /**
   * This method will create the body of the animation.
   */
  private void createSVGBody() {
    for (Frame eachShape : nameBook) {
      Shape currentShape = eachShape.getShape();
      int calibCurrentX = currentShape.getX() - canvas.getX();
      int calibCurrentY = currentShape.getY() - canvas.getY();
      if (currentShape.getType() == ShapeName.Ellipse) {
        this.svgBody.append("<ellipse id=\"").append(currentShape.getName())
                .append("\" cx=\"").append(calibCurrentX)
                .append("\" cy=\"").append(calibCurrentY)
                .append("\" rx=\"").append(currentShape.getParameter1())
                .append("\" ry=\"").append(currentShape.getParameter2());
      } else if (currentShape.getType() == ShapeName.Rectangle) {
        this.svgBody.append("<rect id=\"").append(currentShape.getName())
                .append("\" x=\"").append(calibCurrentX)
                .append("\" y=\"").append(calibCurrentY)
                .append("\" width=\"").append(currentShape.getParameter1())
                .append("\" height=\"").append(currentShape.getParameter2());
      }
      // add circle here if it is required
      this.svgBody.append("\" begin=\"")
              .append(currentShape.getAppearTime())
              .append("ms\" fill=\"rgb(")
              .append(currentShape.getColorR()).append(",")
              .append(currentShape.getColorG()).append(",")
              .append(currentShape.getColorB()).append(")\">\n");

      for (Frame eachFrame : animationBook) {
        Shape thisFrameShape = eachFrame.getShape();
        int calibFrameX = thisFrameShape.getX() - canvas.getX();
        int calibFrameY = thisFrameShape.getY() - canvas.getY();
        int calibToX = eachFrame.getNewX() - canvas.getX();
        int calibToY = eachFrame.getNewY() - canvas.getY();
        if (eachFrame.getShape().getName().equals(currentShape.getName())) {
          if (calibFrameX != calibToX) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"x\" from=\"").append(calibFrameX)
                    .append("\" to=\"").append(calibToX)
                    .append("\" fill=\"freeze\" />\n");
          }
          if (calibFrameY != calibToY) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"y\" from=\"").append(calibFrameY)
                    .append("\" to=\"").append(calibToY)
                    .append("\" fill=\"freeze\" />\n");
          }
          if ((thisFrameShape.getParameter1() != eachFrame.getNewParameter1())
                  && thisFrameShape.getType() == ShapeName.Ellipse) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"rx\" from=\"")
                    .append(thisFrameShape.getParameter1())
                    .append("\" to=\"").append(eachFrame.getNewParameter1())
                    .append("\" fill=\"freeze\" />\n");
          }
          if ((thisFrameShape.getParameter1() != eachFrame.getNewParameter1())
                  && thisFrameShape.getType() == ShapeName.Rectangle) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"width\" from=\"")
                    .append(thisFrameShape.getParameter1())
                    .append("\" to=\"").append(eachFrame.getNewParameter1())
                    .append("\" fill=\"freeze\" />\n");
          }
          if ((thisFrameShape.getParameter2() != eachFrame.getNewParameter2())
                  && thisFrameShape.getType() == ShapeName.Ellipse) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"ry\" from=\"")
                    .append(thisFrameShape.getParameter2())
                    .append("\" to=\"").append(eachFrame.getNewParameter2())
                    .append("\" fill=\"freeze\" />\n");
          }
          if ((thisFrameShape.getParameter2() != eachFrame.getNewParameter2())
                  && thisFrameShape.getType() == ShapeName.Rectangle) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"height\" from=\"")
                    .append(thisFrameShape.getParameter2())
                    .append("\" to=\"").append(eachFrame.getNewParameter2())
                    .append("\" fill=\"freeze\" />\n");
          }
          if ((thisFrameShape.getColorR() != eachFrame.getNewColorR())
                  || (thisFrameShape.getColorG() != eachFrame.getNewColorG())
                  || (thisFrameShape.getColorB() != eachFrame.getNewColorB())) {
            this.svgBody.append("    <animate attributeType=\"xml\" begin=\"")
                    .append(thisFrameShape.getAppearTime() * this.speed).append("ms\" dur=\"")
                    .append((eachFrame.getTimeTo() - thisFrameShape.getAppearTime()) * this.speed)
                    .append("ms\" attributeName=\"fill\" from=\"rgb(")
                    .append(thisFrameShape.getColorR()).append(",")
                    .append(thisFrameShape.getColorG()).append(",")
                    .append(thisFrameShape.getColorB()).append(")\" to=\"rgb(")
                    .append(eachFrame.getNewColorR()).append(",")
                    .append(eachFrame.getNewColorG()).append(",")
                    .append(eachFrame.getNewColorB()).append(",")
                    .append(")\"/>\n");
          }
        }
      }
      if (currentShape.getType() == ShapeName.Ellipse) {
        this.svgBody.append("\n</ellipse>\n\n");
      } else if (currentShape.getType() == ShapeName.Rectangle) {
        this.svgBody.append("\n</rect>\n\n");
      }
    }
    this.svgBody.append("\n</svg>");
  }

  /**
   * This method will return the speed of the view.
   *
   * @return the speed of the view
   */
  public int getSpeed() {
    return this.speed;
  }


}