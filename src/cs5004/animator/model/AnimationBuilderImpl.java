package cs5004.animator.model;

import java.util.ArrayList;

/**
 * We use this class as our model class where all the work is done.
 */
public class AnimationBuilderImpl implements AnimationBuilder {
  private ArrayList<Shape> tempShapes;
  private ArrayList<Frame> animationStates;
  private ArrayList<Frame> startAndFinalState;
  private ArrayList<Frame> shapeHistory;
  private Shape canvas;
  private int speed;

  /**
   * Constructs and initializes.
   */
  public AnimationBuilderImpl(int speed) {
    this.startAndFinalState = new ArrayList<>();
    this.shapeHistory = new ArrayList<>();
    this.animationStates = new ArrayList<>();
    this.tempShapes = new ArrayList<>();
    this.speed = speed;
  }

  /**
   * Constructs and initializes.
   */
  public AnimationBuilderImpl() {
    this.startAndFinalState = new ArrayList<>();
    this.shapeHistory = new ArrayList<>();
    this.animationStates = new ArrayList<>();
    this.tempShapes = new ArrayList<>();
    this.speed = 1;
  }


  @Override
  public AnimationBuilderImpl build() {
    return this;
  }

  @Override
  public AnimationBuilder setBounds(int x, int y, int width, int height) {
    this.canvas = new Rectangle("canvas", ShapeName.Rectangle, x, y, width, height,
            0, 0, 0, 0);
    return this;
  }

  @Override
  public AnimationBuilder declareShape(String name, String type)
          throws IllegalArgumentException {
    //name to be declared again in add motion so it's not assigned here
    if (type.equalsIgnoreCase("rectangle")) {
      Rectangle rec = new Rectangle(name, type);
      this.tempShapes.add(rec);
      this.shapeHistory.add(new FrameImpl(name, ShapeName.Rectangle));
    } else if (type.equalsIgnoreCase("ellipse")) {
      Oval oval = new Oval(name, type);
      this.tempShapes.add(oval);
      this.shapeHistory.add(new FrameImpl(name, ShapeName.Ellipse));
    } else if (type.equalsIgnoreCase("circle")) {
      Circle circle = new Circle(name, type);
      this.tempShapes.add(circle);
    } else {
      throw new IllegalArgumentException("Invalid type of the shape");
    }
    return this;
  }

  @Override
  public AnimationBuilder addMotion(String name,
                                    int t1, int x1, int y1,
                                    int w1, int h1, int r1, int g1, int b1,
                                    int t2, int x2, int y2,
                                    int w2, int h2, int r2, int g2, int b2) {
    Frame frame = new FrameImpl();
    Shape temp = null;
    for (Shape shapes : tempShapes) {
      if (shapes.getName().equalsIgnoreCase(name)) {
        if (shapes.getType().getValue().equalsIgnoreCase("Rectangle")) {
          temp = new Rectangle(name, ShapeName.Rectangle, x1, y1, w1, h1, t1, r1, g1, b1);
          frame.addShape(temp);
          //AddEntryToNameBook(name, t2, x2, y2, w2, h2, r2, g2, b2, temp);
          frame.initializer(x1, y1, w1, h1, t1, r1, g1, b1);
          if (w1 != w2 || h1 != h2) {
            frame.scaleRectangle(w2, h2, t2);
          }
        }
        if (shapes.getType().getValue().equalsIgnoreCase("Ellipse")) {
          temp = new Oval(name, ShapeName.Ellipse, x1, y1, w1, h1, t1, r1, g1, b1);
          frame.addShape(temp);
          //AddEntryToNameBook(name, t2, x2, y2, w2, h2, r2, g2, b2, temp);
          frame.initializer(x1, y1, w1, h1, t1, r1, g1, b1);
          if (w1 != w2 || h1 != h2) {
            frame.scaleOval(w2, h2, t2);
          }
        }
        if (shapes.getType().getValue().equalsIgnoreCase("Circle")) {
          temp = new Circle(name, ShapeName.Circle, x1, y1, w1, t1, r1, g1, b1);
          frame.addShape(temp);
          //AddEntryToNameBook(name, t2, x2, y2, w2, h2, r2, g2, b2, temp);
          frame.initializer(x1, y1, w1, h1, t1, r1, g1, b1);
          if (w1 != w2 || h1 != h2) {
            frame.scaleCircle(w2, t2);
          }
        }
      }
    }
    if (x1 != x2 || y1 != y2) {
      frame.moveShape(x2, y2, t2);
    }
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      frame.changeColor(r2, g2, b2, t2);
    }

    this.animationStates.add(new FrameImpl(frame));
    for (Frame each : shapeHistory) {
      if (each.getFrameName().equalsIgnoreCase(name)) {
        each.historySetter(new FrameImpl(frame));
      }
    }
    addEntryToNameBook(name, t2, x2, y2, w2, h2, r2, g2, b2, temp);

    return this;
  }

  @Override
  public ArrayList<Frame> getAnimationStates() {
    return this.animationStates;
  }

  @Override
  public ArrayList<Frame> getStartAndFinalState() {
    return this.startAndFinalState;
  }

  @Override
  public Shape getCanvas() {
    return this.canvas;
  }

  /**
   * In order to access/modify the StartAndFinalState appropriately, this method will be used. It
   * will go through and check for existing frames with the same name as the shape that is to be
   * created. Depending on whether a shape with the same name exists within a Frame or not, this
   * method will do one of two things: 1. Add a new shape to the Frame with the shape and the
   * required information 2. Let the shape remain and update the mutation in the frame. This will
   * allow for the start and final states to be present in this array for all shapes in the
   * animation.
   *
   * @param name  A string that is the name of the shape.
   * @param t2    The time at which the mutation occurs represented as an integer
   * @param x2    The x coordinate of the corner of the Shape.
   * @param y2    The y coordinate of the corner of the Shape.
   * @param w2    The width of the shape given the mutation.
   * @param h2    The Height of the shape given the mutation.
   * @param r2    The red color in the RGB spectrum post the mutation.
   * @param g2    The green color in the RGB spectrum post the mutation.
   * @param b2    The blue color in the RGB spectrum post the mutation.
   * @param shape The Shape that must be added into the Frame.
   */
  private void addEntryToNameBook(String name,
                                  int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2,
                                  Shape shape) {

    int acc = 0;
    for (Frame frames : startAndFinalState) {
      if (frames.getShape().getName().equalsIgnoreCase(name)) {
        acc++;
      }
    }

    if (acc == 0) {
      this.startAndFinalState.add(new FrameImpl(shape));
    }

    for (Frame frames : startAndFinalState) {
      if (frames.getShape().getName().equalsIgnoreCase(name)) {
        frames.initializer(x2, y2, w2, h2, t2, r2, g2, b2);
      }
    }
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public ArrayList<Frame> getShapeEvent() {
    return shapeHistory;
  }

  @Override
  public void removeShape(String shapeName) {
    for (Frame each : animationStates) {
      if (each.getShape().getName().equalsIgnoreCase(shapeName)) {
        animationStates.remove(each);
        startAndFinalState.remove(each);
        tempShapes.remove(each);
      }
    }
  }

  @Override
  public void removeMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1,
                           int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    for (Frame each : animationStates) {
      if (each.getShape().getName().equalsIgnoreCase(name)
              && each.getShape().getAppearTime() == t1
              && each.getShape().getX() == x1
              && each.getShape().getY() == y1
              && each.getShape().getParameter1() == w1
              && each.getShape().getParameter2() == h1
              && each.getShape().getColorR() == r1
              && each.getShape().getColorG() == g1
              && each.getShape().getColorB() == b1
              && each.getShape().getAppearTime() == t2
              && each.getShape().getX() == x2
              && each.getShape().getY() == y2
              && each.getShape().getParameter1() == w2
              && each.getShape().getParameter2() == h2
              && each.getShape().getColorR() == r2
              && each.getShape().getColorG() == g2
              && each.getShape().getColorB() == b2) {
        animationStates.remove(each);
      }
    }
  }

  @Override
  public boolean isMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
                          int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
                          int g2, int b2) {
    for (Frame each : animationStates) {
      if (each.getShape().getName().equalsIgnoreCase(name)
              && each.getShape().getAppearTime() == t1
              && each.getShape().getX() == x1
              && each.getShape().getY() == y1
              && each.getShape().getParameter1() == w1
              && each.getShape().getParameter2() == h1
              && each.getShape().getColorR() == r1
              && each.getShape().getColorG() == g1
              && each.getShape().getColorB() == b1
              && each.getShape().getAppearTime() == t2
              && each.getShape().getX() == x2
              && each.getShape().getY() == y2
              && each.getShape().getParameter1() == w2
              && each.getShape().getParameter2() == h2
              && each.getShape().getColorR() == r2
              && each.getShape().getColorG() == g2
              && each.getShape().getColorB() == b2) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean shapeExist(String name) {

    for (Frame each : animationStates) {
      if (each.getShape().getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }


}
