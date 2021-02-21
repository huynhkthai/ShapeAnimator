package cs5004.animator.model;

import java.util.ArrayList;


/**
 * This class represents the Model.AnimatorImpl Class that implements the Model.Animator interface.
 */
public class AnimatorImpl implements Animator {

  private StringBuilder shapeRegister;
  private StringBuilder mutationRegister;
  private ArrayList<String> nameList;

  /**
   * Constructs and initializes.
   */
  public AnimatorImpl() {
    this.shapeRegister = new StringBuilder();
    shapeRegister.append("Shapes: \n");
    this.mutationRegister = new StringBuilder();
    this.nameList = new ArrayList<String>();

  }

  @Override
  public Rectangle createRectangle(String name, int x, int y, int width, int height,
                                   int appear, int disappear, int colorR, int colorG,
                                   int colorB, ShapeName type) throws IllegalArgumentException {
    if (checkName(name)) {
      throw new IllegalArgumentException("A shape with this name already exists!");
    }
    try {
      Shape test = new Rectangle(name, type, x, y, width, height, appear, disappear,
              colorR, colorG, colorB);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Model.Rectangle cannot be created due to invalid data.");
    }
    this.shapeRegister.append("Name: ").append(name).append("\n").append("Type: ").append(type)
            .append("\n").append("Min Corner: ").append("(").append(x).append(",").append(y)
            .append(")").append(", ").append("Width: ").append(width).append(", ")
            .append("Height: ")
            .append(height).append(", ").append("Color: ").append("(").append(colorR).append(".0,")
            .append(colorG).append(".0,").append(colorB).append(".0)").append("\n")
            .append("Appears at t=").append(appear).append("\n").append("Disappears at t=")
            .append(disappear).append("\n");
    this.nameList.add(name);
    return new Rectangle(name, type, x, y, width, height, appear, disappear,
            colorR, colorG, colorB);
  }

  @Override
  public Oval createOval(String name, int x, int y, int horizontalRadius,
                         int verticalRadius, int appear, int disappear, int colorR,
                         int colorG, int colorB, ShapeName type)
          throws IllegalArgumentException {
    if (checkName(name)) {
      throw new IllegalArgumentException("A shape with this name already exists!");
    }
    try {
      Shape test = new Oval(name, type, x, y, horizontalRadius, verticalRadius, appear, disappear,
              colorR, colorG, colorB);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Model.Oval cannot be created due to invalid data./");
    }
    this.shapeRegister.append("Name: ").append(name).append("\n").append("Type: ").append(type)
            .append("\n").append("Center: ").append("(").append(x).append(",").append(y)
            .append(")").append(", ").append("Width: ").append(horizontalRadius * 2).append(", ")
            .append("Height: ").append(verticalRadius * 2).append(", ").append("Color: ")
            .append("(").append(colorR).append(".0,").append(colorG).append(".0,").append(colorB)
            .append(".0)\n").append("Appears at t=").append(appear).append("\n")
            .append("Disappears at t=").append(disappear).append("\n");
    return new Oval(name, type, x, y, horizontalRadius, verticalRadius, appear, disappear, colorR,
            colorG, colorB);
  }

  @Override
  public Circle createCircle(String name, int x, int y, int radius, int appear,
                             int disappear, int colorR, int colorG, int colorB,
                             ShapeName type) throws IllegalArgumentException {
    if (checkName(name)) {
      throw new IllegalArgumentException("A shape with this name already exists!");
    }
    try {
      Shape test = new Circle(name, x, y, radius, appear, disappear, colorR, colorG, colorB, type);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Model.Circle cannot be created due to invalid data./");
    }
    this.shapeRegister.append("Name: ").append(name).append("\n").append("Type: ").append(type)
            .append("\n").append("Center: ").append("(").append(x).append(",").append(y)
            .append(")").append(", ").append("Diameter: ").append(radius * 2).append(", ")
            .append("Radius: ").append(radius).append(", ").append("Color : ").append("(")
            .append(colorR).append(".0,").append(colorG).append(".0,").append(colorB)
            .append(".0)\n")
            .append("Appears at t=").append(appear).append("\n").append("Disappears at t=")
            .append(disappear).append("\n");
    return new Circle(name, x, y, radius, appear, disappear, colorR, colorG, colorB, type);
  }

  @Override
  public void moveShape(Shape shape, int toX, int toY, int timeFrom, int timeTo) {
    int initialX = shape.getX();
    int initialY = shape.getY();
    shape.move(toX, toY, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName()).append(" moves from (")
            .append(initialX).append(", ").append(initialY).append(") to (").append(toX)
            .append(",").append(toY).append(") from t=").append(timeFrom).append(" to t=")
            .append(timeTo).append("\n");
  }

  @Override
  public void changeColor(Shape shape, int newR, int newG, int newB, int timeFrom,
                          int timeTo) {
    String initialColor = "(" + shape.getColorR() + ".0" + "," + shape.getColorG() + ".0" + ","
            + shape.getColorB() + ".0" + ")";
    shape.changeColor(newR, newG, newB, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName())
            .append(" changes color from ")
            .append(initialColor).append(" to (").append(newR).append(".0,")
            .append(newG).append(".0,").append(newB).append(".0) from t=").append(timeFrom)
            .append(" to t=").append(timeTo).append("\n");
  }

  @Override
  public void scaleRectangle(Rectangle shape, int width, int height, int timeFrom,
                             int timeTo) {
    int initialHeight = shape.getParameter2();
    int initialWidth = shape.getParameter1();
    shape.resize(width, height, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName())
            .append(" scales from Width: ")
            .append(initialWidth).append(" Height: ").append(initialHeight).append(" to Width: ")
            .append(shape.getParameter1()).append(" Height: ").append(shape.getParameter2())
            .append(" from t=").append(shape.getAppearTime()).append(" to t=")
            .append(shape.getDisappearTime()).append("\n");
  }

  @Override
  public void scaleRectangle(Rectangle shape, int factor, int timeFrom, int timeTo) {
    int initialHeight = shape.getParameter2();
    int initialWidth = shape.getParameter1();
    shape.scale(factor, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName())
            .append(" scales from Width: ")
            .append(initialWidth).append(" Height: ").append(initialHeight).append(" to Width: ")
            .append(shape.getParameter1()).append(" Height: ").append(shape.getParameter2())
            .append(" from t=").append(shape.getAppearTime()).append(" to t=")
            .append(shape.getDisappearTime()).append("\n");
  }

  @Override
  public void scaleCircle(Circle shape, int factor, int timeFrom, int timeTo) {
    int initialDiameter = shape.getRadius() * 2;
    int initialRadius = shape.getRadius();
    shape.resize(factor, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName())
            .append(" scales from Diameter: ")
            .append(initialDiameter).append(" Radius: ").append(initialRadius)
            .append(" to Diameter: ").append(shape.getRadius() * 2).append(" Radius: ")
            .append(shape.getRadius()).append(" from t=").append(shape.getAppearTime())
            .append(" to t=").append(shape.getDisappearTime()).append("\n");
  }

  //append toString for each shape
  @Override
  public void scaleOval(Oval shape, int factor, int timeFrom, int timeTo) {
    int initialVRadius = shape.getVerticalRadius();
    int initialHRadius = shape.getHorizontalRadius();
    shape.scale(factor, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName())
            .append(" scales from Vertical Radius: ").append(initialVRadius)
            .append(" Horizontal Radius: ").append(initialHRadius)
            .append(" to Vertical Radius: ").append(shape.getVerticalRadius())
            .append(" Horizontal Radius: ").append(shape.getHorizontalRadius())
            .append(" from t=").append(shape.getAppearTime()).append(" to t=")
            .append(shape.getDisappearTime()).append("\n");
  }

  @Override
  public void scaleOval(Oval shape, int horizontalRadius, int verticalRadius, int timeFrom,
                        int timeTo) {
    int initialVRadius = shape.getVerticalRadius();
    int initialHRadius = shape.getHorizontalRadius();
    shape.resize(horizontalRadius, verticalRadius, timeFrom, timeTo);
    this.mutationRegister.append("Model.Shape ").append(shape.getName())
            .append(" scales from Vertical Radius: ").append(initialVRadius)
            .append(" Horizontal Radius: ").append(initialHRadius)
            .append(" to Vertical Radius: ").append(shape.getVerticalRadius())
            .append(" Horizontal Radius: ").append(shape.getHorizontalRadius())
            .append(" from t=").append(shape.getAppearTime()).append(" to t=")
            .append(shape.getDisappearTime()).append("\n");
  }

  @Override
  public String getAnimation() {
    return this.shapeRegister + "\n" + this.mutationRegister;
  }

  /**
   * This method checks for name duplication.
   *
   * @param name name of this shape.
   * @return true if there is name duplicate, false otherwise.
   */
  public boolean checkName(String name) {
    boolean def = true;
    for (String s : nameList) {
      if (s.equals(name)) {
        def = false;
        break;
      }
    }
    return !def;
  }
}
