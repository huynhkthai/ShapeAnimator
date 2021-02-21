package cs5004.animator.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This abstract class that represent the possible shapes.
 */
public abstract class AbstractShape implements Shape {
  protected String name;
  protected Point position;
  protected int parameter1;
  protected int parameter2;
  protected int appear;
  protected int disappear;
  protected ShapeName type;
  protected int colorR;
  protected int colorG;
  protected int colorB;
  protected Color color;
  protected int timeFrom;
  protected int timeTo;
  protected ArrayList<Shape> history;

  /**
   * This constructor creates a shape with one parameter, namely circle.
   */
  public AbstractShape(String name, ShapeName type, int x, int y,
                       int parameter1,
                       int appear, int colorR, int colorG, int colorB)
          throws IllegalArgumentException {
    if (parameter1 < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }
    if (appear >= disappear) {
      throw new IllegalArgumentException("Appear time cannot be greater than or equal to "
              + "disappear time.");
    }

    if (appear < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (colorR < 0 || colorG < 0 || colorB < 0
            || colorR >= 256 || colorG >= 256 || colorB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    this.name = name;
    this.type = type;
    this.position = new Point(x, y);
    this.appear = appear;
    this.parameter1 = parameter1;
    this.parameter2 = parameter1;
    this.colorR = colorR;
    this.colorG = colorG;
    this.colorB = colorB;
    this.color = new Color(colorR, colorG, colorB);
  }

  /**
   * This constructor creates a shape with one parameter, namely circle.
   */
  public AbstractShape(String name, int x, int y,
                       int parameter1, int appear, int disappear,
                       int colorR, int colorG, int colorB, ShapeName type)
          throws IllegalArgumentException {
    if (parameter1 < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }
    if (appear >= disappear) {
      throw new IllegalArgumentException("Appear time cannot be greater than or equal to "
              + "disappear time.");
    }

    if (appear < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (colorR < 0 || colorG < 0 || colorB < 0
            || colorR >= 256 || colorG >= 256 || colorB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    this.name = name;
    this.type = type;
    this.position = new Point(x, y);
    this.appear = appear;
    this.parameter1 = parameter1;
    this.parameter2 = parameter1;
    this.colorR = colorR;
    this.colorG = colorG;
    this.colorB = colorB;
    this.color = new Color(colorR, colorG, colorB);
  }


  /**
   * This constructor creates a shape with two parameter, namely rectangle or oval.
   */
  public AbstractShape(String name, ShapeName type, int x, int y,
                       int parameter1, int parameter2,
                       int appear, int disappear, int colorR, int colorG, int colorB)
          throws IllegalArgumentException {
    if (parameter1 < 0 || parameter2 < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }
    if (appear >= disappear) {
      throw new IllegalArgumentException("Appear time cannot be greater than or equal to "
              + "disappear time.");
    }

    if (appear < 0 || disappear < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (colorR < 0 || colorG < 0 || colorB < 0
            || colorR >= 256 || colorG >= 256 || colorB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    this.name = name;
    this.type = type;
    this.position = new Point(x, y);
    this.appear = appear;
    this.disappear = disappear;
    this.parameter1 = parameter1;
    this.parameter2 = parameter2;
    this.colorR = colorR;
    this.colorG = colorG;
    this.colorB = colorB;
    this.color = new Color(colorR, colorG, colorB);
  }

  /**
   * This constructor creates a shape with two parameter, namely rectangle or oval.
   */
  public AbstractShape(String name, ShapeName type, int x, int y,
                       int parameter1, int parameter2,
                       int appear, int colorR, int colorG, int colorB)
          throws IllegalArgumentException {
    if (parameter1 < 0 || parameter2 < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }


    if (appear < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (colorR < 0 || colorG < 0 || colorB < 0
            || colorR >= 256 || colorG >= 256 || colorB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    this.name = name;
    this.type = type;
    this.position = new Point(x, y);
    this.appear = appear;
    this.parameter1 = parameter1;
    this.parameter2 = parameter2;
    this.colorR = colorR;
    this.colorG = colorG;
    this.colorB = colorB;
    this.color = new Color(colorR, colorG, colorB);
  }


  /**
   * This constructor deals with creating a shape without design parameters. Used in the Animation
   * Builder class to create shapes in the most abstract way possible.
   *
   * @param name A string that represents the name of the Shape object.
   * @param type A String that represents the type of the Shape. Used with the ShapeType enum.
   */
  public AbstractShape(String name, String type) {
    this.name = name;
    if (type.equalsIgnoreCase("Rectangle")) {
      this.type = ShapeName.Rectangle;
    }
    if (type.equalsIgnoreCase("Ellipse")) {
      this.type = ShapeName.Ellipse;
    }
    if (type.equalsIgnoreCase("Circle")) {
      this.type = ShapeName.Circle;
    }
  }


  /**
   *This is another constructor of a shape. It takes in a type of shape by its enum, instead of a
   * string.
   * @param type the type of shape
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param parameter1 the width of the shape
   * @param parameter2 the height of the shape
   * @param colorR the R value of the shape's color
   * @param colorG the G value of the shape's color
   * @param colorB the B value of the shape's color
   * @param name the name of the shape
   * @throws IllegalArgumentException validates for valid input
   */
  public AbstractShape(ShapeName type, int x, int y,
                       int parameter1, int parameter2,
                       int colorR, int colorG, int colorB, String name)
          throws IllegalArgumentException {
    if (parameter1 < 0 || parameter2 < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }
    if (colorR < 0 || colorG < 0 || colorB < 0
            || colorR >= 256 || colorG >= 256 || colorB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    this.name = name;
    this.type = type;
    this.position = new Point(x, y);
    this.parameter1 = parameter1;
    this.parameter2 = parameter2;
    this.colorR = colorR;
    this.colorG = colorG;
    this.colorB = colorB;
    this.color = new Color(colorR, colorG, colorB);
  }

  @Override
  public int getX() {
    return position.getX();
  }

  @Override
  public int getY() {
    return position.getY();
  }

  @Override
  public int getAppearTime() {
    return appear;
  }

  @Override
  public int getDisappearTime() {
    return disappear;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void changeColor(int newR, int newG, int newB,
                          int timeFrom, int timeTo) throws IllegalArgumentException {
    if (newR < 0 || newG < 0 || newB < 0 || newR >= 256 || newG >= 256 || newB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Time again");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Color Changes need done within when the shape exists");
    }
    Color col = new Color(newR, newG, newB);
    this.colorR = newR;
    this.colorG = newG;
    this.colorB = newB;
    this.color = col;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }

  @Override
  public void changeColor(int newR, int newG, int newB,
                          int timeFrom) throws IllegalArgumentException {
    if (newR < 0 || newG < 0 || newB < 0 || newR >= 256 || newG >= 256 || newB >= 256) {
      throw new IllegalArgumentException("Cannot get a color with these values.");
    }
    if (timeFrom < 0) {
      throw new IllegalArgumentException("Time again");
    }
    if (timeFrom >= disappear) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear) {
      throw new IllegalArgumentException(" Color Changes need done within when the shape exists");
    }
    Color col = new Color(newR, newG, newB);
    this.colorR = newR;
    this.colorG = newG;
    this.colorB = newB;
    this.color = col;
    this.timeFrom = timeTo;//should this be timeFrom since its the left side??
  }

  @Override
  public void changeAppearTime(int newAppearTime) throws IllegalArgumentException {
    if (newAppearTime < 0) {
      throw new IllegalArgumentException("New appear time cannot be negative.");
    }
    if (newAppearTime >= this.disappear) {
      throw new IllegalArgumentException("Appear time cannot be before Disappear time");
    }
    if (timeFrom < appear || timeFrom > disappear) {
      throw new IllegalArgumentException(" Appear Time Changes need done"
              + "within when the shape exists");
    }
    this.appear = newAppearTime;
  }

  @Override
  public void changeDisappearTime(int newDisappearTime) throws IllegalArgumentException {
    if (newDisappearTime < 0) {
      throw new IllegalArgumentException("New disappear time cannot be negative");
    }
    if (newDisappearTime <= this.appear) {
      throw new IllegalArgumentException("NewDisappear time cannot be less than appear time");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException("Disappear time Changes need done"
              + "within when the shape exists");
    }
    this.disappear = newDisappearTime;
  }

  @Override
  public void move(int toX, int toY,
                   int timeFrom, int timeTo) throws IllegalArgumentException {
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Time cannot be negative!");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Move Changes need done within when the shape exists");
    }
    this.position = new Point(toX, toY);
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }

  @Override
  public void move(int toX, int toY,
                   int timeTo) throws IllegalArgumentException {
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Time cannot be negative!");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Move Changes need done within when the shape exists");
    }
    this.position = new Point(toX, toY);
    this.timeTo = timeTo;
  }

  @Override
  public double distanceFromOrigin() {
    return Math.sqrt(position.getX() * position.getX() + position.getY() * position.getY());
  }

  @Override
  public void scale(int factor, int timeFrom, int timeTo) throws IllegalArgumentException {
    if (factor < 0) {
      throw new IllegalArgumentException("Cannot be scaled by a negative number");
    }
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Not possible to have negative time");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Scale Changes need done within when the shape exists");
    }
    this.parameter1 = this.parameter1 * factor;
    this.parameter2 = this.parameter2 * factor;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }

  @Override
  public void scale(int factor, int timeTo) throws IllegalArgumentException {
    if (factor < 0) {
      throw new IllegalArgumentException("Cannot be scaled by a negative number");
    }
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Not possible to have negative time");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Scale Changes need done within when the shape exists");
    }
    this.parameter1 = this.parameter1 * factor;
    this.parameter2 = this.parameter2 * factor;
    this.timeTo = timeTo;
  }

  @Override
  public void resizeP1(int width, int timeFrom, int timeTo) {
    if (width < 0) {
      throw new IllegalArgumentException("Cannot resize by negative width");
    }
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Resize Changes need done within when the shape exists");
    }
    this.parameter1 = width;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }

  @Override
  public void resizeP2(int height, int timeFrom, int timeTo) {
    if (height < 0) {
      throw new IllegalArgumentException("Cannot resize by negative height");
    }
    if (timeFrom < 0 || timeTo < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (timeFrom >= timeTo) {
      throw new IllegalArgumentException("TimeFrom cannot be greater than or equal to TimeTo.");
    }
    if (timeFrom < appear || timeFrom > disappear || timeTo < appear || timeTo > disappear) {
      throw new IllegalArgumentException(" Resize P1 Changes need done"
              + "within when the shape exists");
    }
    this.parameter2 = height;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }


  @Override
  public void resize(int width, int height, int timeFrom, int timeTo) {
    this.resizeP1(width, timeFrom, timeTo);
    this.resizeP2(height, timeFrom, timeTo);
  }

  @Override
  public void resize(int width, int height, int timeTo) {
    this.resizeP1(width, timeFrom, timeTo);
    this.resizeP2(height, timeFrom, timeTo);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeName getType() {
    return this.type;
  }

  @Override
  public int getColorR() {
    return this.colorR;
  }

  @Override
  public int getColorG() {
    return this.colorG;
  }

  @Override
  public int getColorB() {
    return this.colorB;
  }

  @Override
  public int getParameter1() {
    return this.parameter1;
  }

  @Override
  public int getParameter2() {
    return this.parameter2;
  }

  @Override
  public void historySetter(Shape currentState) {
    this.history.add(currentState);
  }


}
