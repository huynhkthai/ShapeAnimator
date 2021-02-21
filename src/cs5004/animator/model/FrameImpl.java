package cs5004.animator.model;
//import java.awt.*;

import java.util.ArrayList;

/**
 * This class represents the Model.AnimatorImpl Class that implements the Model.Animator interface.
 */
public class FrameImpl implements cs5004.animator.model.Frame {
  protected String fName;
  protected ShapeName type;
  protected int newX;
  protected int newY;
  protected int newParameter1; // horizontal
  protected int newParameter2; // vertical
  protected int newColorR;
  protected int newColorG;
  protected int newColorB;
  protected int timeTo;
  protected Shape thisShape;
  private boolean isColorChanged;
  private boolean isScaled;
  private boolean isMoved;
  protected ArrayList<Frame> history;
  protected boolean isExpired;
  protected int currentTime;

  /**
   * Initialize a frame.
   */
  public FrameImpl() {
    //This will create a frameImpl as a holder.
  }

  /**
   * Initialize a frame.
   *
   * @param newFrame a frame with all properties needed to update this frame.
   */
  public FrameImpl(Frame newFrame) {
    newX = newFrame.getNewX();
    newY = newFrame.getNewY();
    newParameter1 = newFrame.getNewParameter1();
    newParameter2 = newFrame.getNewParameter2();
    newColorR = newFrame.getNewColorR();
    newColorG = newFrame.getNewColorG();
    newColorB = newFrame.getNewColorB();
    timeTo = newFrame.getTimeTo();
    thisShape = newFrame.getShape();
    isColorChanged = newFrame.getColorStatus();
    isScaled = newFrame.getScaledStatus();
    isMoved = newFrame.getMoveStatus();
  }

  /**
   * Initialize a frame.
   *
   * @param frameName of the frame.
   * @param frameType shape type of the frame.
   */
  public FrameImpl(String frameName, ShapeName frameType) {
    fName = frameName;
    type = frameType;
    history = new ArrayList<>();
  }

  /**
   * Initialize a frame.
   *
   * @param newX          new coordinate for the corner/point of the shape.
   * @param newY          new coordinate for the corner/point of the shape.
   * @param newParameter1 new width of the rectangle.
   * @param newParameter2 new height of the rectangle.
   * @param newColorR     new R color value.
   * @param newColorG     new G color value.
   * @param newColorB     new B color value.
   * @param timeTo        time at which this frame ends.
   * @param thisShape     with information for the beginning of the frame.
   */
  public FrameImpl(int newX, int newY, int newParameter1, int newParameter2,
                   int newColorR, int newColorG, int newColorB, int timeTo, Shape thisShape) {
    this.newX = newX;
    this.newY = newY;
    this.newParameter1 = newParameter1;
    this.newParameter2 = newParameter2;
    this.newColorR = newColorR;
    this.newColorG = newColorG;
    this.newColorB = newColorB;
    this.timeTo = timeTo;
    this.thisShape = thisShape;
    this.isColorChanged = false;
    this.isScaled = false;
    this.isMoved = false;
  }

  /**
   * * Initialize a frame.
   *
   * @param shape with information for the beginning of the frame.
   */
  public FrameImpl(Shape shape) {
    this.thisShape = shape;
    initializer(shape.getX(), shape.getY(), shape.getParameter1(), shape.getParameter2(),
            shape.getDisappearTime(), shape.getColorR(), shape.getColorG(), shape.getColorB());
    this.isColorChanged = false;
    this.isScaled = false;
    this.isMoved = false;
  }

  // WILL NOT NEED THE CREATE METHODS FOR THIS IMPLEMENTATION AS THINGS STAND. IGNORE FOR NOW.
  @Override
  public Shape createRectangle(String name, int x, int y, int width, int height,
                               int appear, int colorR, int colorG, int colorB, ShapeName type)
          throws IllegalArgumentException {
    try {
      new cs5004.animator.model.Rectangle(name, type, x, y, width, height, appear, colorR, colorG,
              colorB);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Model.Rectangle cannot be created due to invalid data.");
    }
    thisShape = new Rectangle(name, type, x, y, width, height, appear, colorR, colorG, colorB);
    initializer(x, y, width, height, appear, colorR, colorG, colorB);
    return this.thisShape;
  }

  @Override
  public Shape createOval(String name, int x, int y, int horizontalRadius,
                          int verticalRadius, int appear,
                          int colorR, int colorG, int colorB, ShapeName type)
          throws IllegalArgumentException {
    try {
      new Oval(name, type, x, y, horizontalRadius, verticalRadius, appear,
              colorR, colorG, colorB);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Eclipse cannot be created due to invalid data./");
    }
    thisShape = new Oval(name, type, x, y, horizontalRadius, verticalRadius, appear, colorR,
            colorG, colorB);
    initializer(x, y, horizontalRadius, verticalRadius, appear, colorR,
            colorG, colorB); // CHECK FOR POSSIBLE PLACEMENT ERRORS IN PARAMS.
    return this.thisShape;
  }

  @Override
  public Shape createCircle(String name, int x, int y, int radius, int radiusAgain,
                            int appear, int colorR, int colorG, int colorB, ShapeName type)
          throws IllegalArgumentException {
    try {
      new Circle(name, type, x, y, radius, appear, colorR, colorG, colorB);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Model.Circle cannot be created due to invalid data./");
    }
    thisShape = new Circle(name, type, x, y, radius, appear, colorR,
            colorG, colorB);
    initializer(x, y, radius, radiusAgain, appear, colorR,
            colorG, colorB); // CHECK FOR POSSIBLE PLACEMENT ERRORS IN PARAMS.
    return this.thisShape;
  }

  @Override
  public void moveShape(int toX, int toY, int timeTo) {
    this.newX = toX;
    this.newY = toY;
    this.timeTo = timeTo;
    this.isMoved = true;
  }

  @Override
  public void changeColor(int newR, int newG, int newB, int timeTo) {
    this.newColorR = newR;
    this.newColorB = newB;
    this.newColorG = newG;
    this.timeTo = timeTo;
    this.isColorChanged = true;
  }

  @Override
  public void scaleRectangle(int width, int height, int timeTo) {
    this.newParameter1 = width;
    this.newParameter2 = height;
    this.timeTo = timeTo;
    this.isScaled = true;
  }

  @Override
  public void scaleOval(int horizontalRadius, int verticalRadius, int timeTo) {
    this.newParameter1 = horizontalRadius;
    this.newParameter2 = verticalRadius;
    this.timeTo = timeTo;
    this.isScaled = true;
  }

  @Override
  public void scaleCircle(int radius, int timeTo) {
    this.newParameter1 = radius;
    this.newParameter2 = radius;
    this.timeTo = timeTo;
    this.isScaled = true;
  }

  @Override
  public boolean getColorStatus() {
    return this.isColorChanged;
  }

  @Override
  public boolean getScaledStatus() {
    return this.isScaled;
  }

  @Override
  public boolean getMoveStatus() {
    return this.isMoved;
  }

  @Override
  public Shape getShape() {
    return this.thisShape;
  }

  @Override
  public int getNewX() {
    return this.newX;
  }

  @Override
  public int getNewY() {
    return this.newY;
  }

  @Override
  public int getNewParameter1() {
    return this.newParameter1;
  }

  @Override
  public int getNewParameter2() {
    return this.newParameter2;
  }

  @Override
  public int getNewColorR() {
    return this.newColorR;
  }

  @Override
  public int getNewColorG() {
    return this.newColorG;
  }

  @Override
  public int getNewColorB() {
    return this.newColorB;
  }


  @Override
  public int getTimeTo() {
    return this.timeTo;
  }


  /**
   * This is an initializer function that sets the attributes of the Model.Frame class. It sets them
   * to the required values for all input passed in.
   *
   * @param a The X coordinate of the shape.
   * @param b The Y coordinate of the shape.
   * @param c The Horizontal size of the shape.
   * @param d The Vertical size of the shape.
   * @param e The Time at which the shape disappears.
   * @param f The R value of the RGB spectrum that the shape has.
   * @param g The G value of the RGB spectrum that the shape has.
   * @param h The B value of the RGB spectrum that the shape has.
   */
  public void initializer(int a, int b, int c,
                          int d, int e,
                          int f, int g, int h) {
    this.newX = a;
    this.newY = b;
    this.newParameter1 = c;
    this.newParameter2 = d;
    this.newColorR = f;
    this.newColorG = g;
    this.newColorB = h;
    this.timeTo = e;
  }

  /*
  @Override
  public void initializer(int a, int b, int c,
                          int d, int e,
                          int f, int g, int h) {
    this.newX = a;
    this.newY = b;
    this.newParameter1 = c;
    this.newParameter2 = d;
    this.newColorR = f;
    this.newColorG = g;
    this.newColorB = h;
    this.timeTo = e;
  }

   */

  /**
   * Getter method that allows the program to access the X coordinate of the starting condition of
   * the shape.
   *
   * @return A int representing the X coordinate of the corner of the shape.
   */
  public int startX() {
    return this.thisShape.getX();
  }

  /**
   * Getter method that allows the program to access the Y coordinate of the starting condition of
   * the shape.
   *
   * @return A int representing the y coordinate of the corner of the shape.
   */
  public int startY() {
    return this.thisShape.getY();
  }


  /**
   * The start time at which the shape is created in the Animation.
   *
   * @return A int representing the start time of the shape animation.
   */
  public int startTime() {
    return this.thisShape.getAppearTime();
  }

  /**
   * The end time at which the shape is no longer in the Animation.
   *
   * @return A int representing the end time of the shape animation.
   */
  public int endTime() {
    return this.timeTo;
  }

  /**
   * Allows access to the name of the Model.Shape from different parts of the Animation program.
   *
   * @return A string that is the name of the Model.Shape.
   */
  public String getName() {
    return this.thisShape.getName();
  }

  /**
   * The type of the Model.Shape to be used during the Animation.
   *
   * @return A Model.ShapeName type that represents the type of the Model.Shape.
   */
  public ShapeName getType() {
    return this.thisShape.getType();
  }

  /**
   * Getter methods specific to the Visual animation for the delta of the X coordinate of the corner
   * of the shape.
   *
   * @return A delta of the X coordinate before and after the mutation.
   */
  public int deltaX() {
    return this.thisShape.getX() - newX;
  }

  /**
   * Getter methods specific to the Visual animation for the delta of the Y coordinate of the corner
   * of the shape.
   *
   * @return A delta of the Y coordinate before and after the mutation.
   */
  public int deltaY() {
    return this.thisShape.getY() - newY;
  }

  @Override
  public Frame makeCopy() {
    return this;
  }

  @Override
  public void addShape(Shape someShape) {
    this.thisShape = someShape;
  }

  @Override
  public void historySetter(Frame currentFrame) {
    this.history.add(currentFrame);
  }

  @Override
  public String getFrameName() {
    return this.fName;
  }

  @Override
  public ArrayList<Frame> getHistory() {
    return history;
  }

  @Override
  public boolean checkExpire(int currentTime) {
    boolean indicator = true;
    for (Frame each : history) {
      if (each.getShape().getAppearTime() <= currentTime && currentTime <= each.getTimeTo()) {
        indicator = false;
      }
    }
    return indicator;
  }

  @Override
  public Shape getLastState(int currentTime) {
    //for (Frame each : history) {
    //for (int currentIndex = 0;
    // currentIndex < history.get(history.size()-1).getTimeTo(); currentIndex++) {
    for (int currentIndex = 0; currentIndex < history.size(); currentIndex++) {
      if (currentIndex < history.size() - 1) {
        if (history.get(currentIndex).getTimeTo() < currentTime
                && currentTime < history.get(currentIndex + 1).getShape().getAppearTime()) {
          Frame tempFrame = history.get(currentIndex);
          return tempFrameCreation(tempFrame);
        }
      } else if (currentIndex == history.size() - 1) {
        if (history.get(currentIndex).getTimeTo() < currentTime) {
          Frame tempFrame = history.get(history.size() - 1);
          return tempFrameCreation(tempFrame);
        }
      }
    }
    return null;
  }


  /**
   * This is a helper method for the getLastState method to help avoid DRY.
   *
   * @param tempFrame input frame used to create desired shape.
   * @return the desired shape from tempFrame.
   */
  private Shape tempFrameCreation(Frame tempFrame) {
    if (tempFrame.getShape().getType() == ShapeName.Rectangle) {
      return new Rectangle(tempFrame.getShape().getType(),
              tempFrame.getNewX(), tempFrame.getNewY(),
              tempFrame.getNewParameter1(), tempFrame.getNewParameter2(),
              tempFrame.getNewColorR(), tempFrame.getNewColorG(),
              tempFrame.getNewColorB(), tempFrame.getFrameName());
    } else {
      return new Oval(tempFrame.getShape().getType(),
              tempFrame.getNewX(), tempFrame.getNewY(),
              tempFrame.getNewParameter1(), tempFrame.getNewParameter2(),
              tempFrame.getNewColorR(), tempFrame.getNewColorG(),
              tempFrame.getNewColorB(), tempFrame.getFrameName());
    }
  }
}