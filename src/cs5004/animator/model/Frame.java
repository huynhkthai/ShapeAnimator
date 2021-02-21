package cs5004.animator.model;

import java.util.ArrayList;

/**
 * The Model.Animator deals primarily with the Appendable, ensuring that the correct information is
 * derived from the Model.Shape classes and organized in a way that allows for effective animation.
 */

public interface Frame {

  /**
   * The create shape method allows for any kind of shape to be created using this one method. It
   * further appends the requisite animation information to the appendable. The appendable is in
   * turn used for animation.
   *
   * @param name   A String that represents the name of the Model.Rectangle.
   * @param x      x coordinate for the corner/point of the shape. Center point for ovals and
   *               circles.
   * @param y      y coordinate for the corner/point of shape. Center point for ovals and circles.
   * @param width  A int that is the width of the rectangle.
   * @param height The height of the rectangle.
   * @param appear The time at which it appears.
   * @param colorR The R color value.
   * @param colorG The G color value.
   * @param colorB The B color value.
   * @param type   The type of the shape as per the enum declared.
   * @return a Model.Shape that will be used for animation.
   */
  Shape createRectangle(String name, int x, int y, int width, int height, int appear,
                        int colorR, int colorG, int colorB, ShapeName type);

  Shape createOval(String name, int x, int y, int horizontalRadius, int verticalRadius,
                   int appear, int colorR, int colorG, int colorB, ShapeName type);

  Shape createCircle(String name, int x, int y, int radius, int radiusAgain,
                     int appear, int colorR, int colorG, int colorB, ShapeName type);

  /**
   * This method allows us to move the shape to a given position. Appends information to the
   * appendable.
   */
  void moveShape(int toX, int toY, int timeTo);


  /**
   * This method is responsible for changing the color of the shape, and then ensuring that the
   * animation is recorded on the appendable.
   */
  void changeColor(int newR, int newG, int newB, int timeTo);

  /**
   * This method allows us to change the size of the Model.Rectangle, or to 'scale' it according to
   * a factor. The information is then animated through the appendable.
   *
   * @param width  the resized width of the rectangle.
   * @param height the resized height of the rectangle.
   * @param timeTo the time till which the change must be enacted.
   */
  void scaleRectangle(int width, int height, int timeTo);

  /**
   * This method allows us to change the size of the Model.Oval, or to 'scale' it according to a
   * factor. The information is then animated through the appendable.
   *
   * @param horizontalRadius the resized horizontal radius.
   * @param verticalRadius   the resized vertical radius.
   * @param timeTo           the time till which the change must be enacted.
   */
  void scaleOval(int horizontalRadius, int verticalRadius, int timeTo);


  /**
   * This method allows us to change the size of the Model.Circle, or to 'scale' it according to a
   * factor. The information is then animated through the appendable.
   *
   * @param radius the resized radius.
   * @param timeTo the time till which the change must be enacted.
   */
  void scaleCircle(int radius, int timeTo);

  /**
   * A getter method for the Shape in the Frame.
   *
   * @return the Shape that is stored in the Frame.
   */
  Shape getShape();

  /**
   * Allows to get the newX value caused by mutating the shape.
   *
   * @return the newX that the shape will have after the mutation.
   */
  int getNewX();

  /**
   * Allows to get the newY value caused by mutating the shape.
   *
   * @return the newY that the shape will have after the mutation.
   */
  int getNewY();

  /**
   * Allows to get the parameter1 value caused by mutating the shape.
   *
   * @return the parameter1 that the shape will have after the mutation.
   */
  int getNewParameter1();

  /**
   * Allows to get the parameter2 value caused by mutating the shape.
   *
   * @return the parameter2 that the shape will have after the mutation.
   */
  int getNewParameter2();

  /**
   * Allows to get the new R Color value caused by mutating the shape.
   *
   * @return the R Color that the shape will have after the mutation.
   */
  int getNewColorR();

  /**
   * Allows to get the new G color value caused by mutating the shape.
   *
   * @return the new G color that the shape will have after the mutation.
   */
  int getNewColorG();

  /**
   * Allows to get the new B color value caused by mutating the shape.
   *
   * @return the new B color that the shape will have after the mutation.
   */
  int getNewColorB();

  /**
   * Allows to get the time to value caused by mutating the shape.
   *
   * @return the time to that the shape will have after the mutation.
   */
  int getTimeTo();

  /**
   * Getter method to determine if the Shape has changed color.
   *
   * @return a boolean that is true if the color is changed.
   */
  boolean getColorStatus();

  /**
   * Getter method to determine if the Shape has been scaled.
   *
   * @return a boolean that is true if the Shape is scaled.
   */
  boolean getScaledStatus();

  /**
   * Getter method that determines if the Shape has moved.
   *
   * @return a boolean that is true of the Shape has moved.
   */
  boolean getMoveStatus();

  /**
   * This method makes a copy of current frame.
   *
   * @return a copied frame
   */
  Frame makeCopy();


  /**
   * Same as the other initializer but compatible with integer values.
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
  void initializer(int a, int b, int c,
                          int d, int e,
                          int f, int g, int h);

  /**
   * Add a shape into frame object.
   *
   * @param someShape shape wanted to add into frame.
   */
  void addShape(Shape someShape);

  /**
   * Save a given frame in a frame list within this frame.
   *
   * @param currentFrame line of motion to be saved as a frame in a frame list.
   */
  void historySetter(Frame currentFrame);

  /**
   * Name getter.
   *
   * @return name of this frame.
   */
  String getFrameName();

  /**
   * History list getter.
   *
   * @return history list from this frame.
   */
  ArrayList<Frame> getHistory();

  /**
   * Check if this shape has anymore animation during this given time.
   *
   * @param currentTime time used to inspect if a shape no longer has animation.
   * @return indicator if the shape no longer animate.
   */
  boolean checkExpire(int currentTime);

  /**
   * Get the state at which the shape stop animating.
   *
   * @param currentTime time used to inspect if a shape has stopped animating.
   * @return the latest shape before it stop animating.
   */
  Shape getLastState(int currentTime);
}