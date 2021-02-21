package cs5004.animator.model;

/**
 * The Model.Animator deals primarily with the Appendable, ensuring that the correct information is
 * derived from the Model.Shape classes and organized in a way that allows for effective animation.
 */

public interface Animator {

  /**
   * The create shape method allows for any kind of shape to be created using this one method. It
   * further appends the requisite animation information to the appendable. The appendable is in
   * turn used for animation.
   *
   * @param x x coordinate for the corner/point of the shape. Center point for ovals and circles.
   * @param y y coordinate for the corner/point of shape. Center point for ovals and circles.
   * @return a Model.Shape that will be used for animation.
   */

  Shape createRectangle(String name, int x, int y, int width, int height, int appear,
                        int disappear, int colorR, int colorG, int colorB, ShapeName type);

  Shape createOval(String name, int x, int y, int horizontalRadius, int verticalRadius,
                   int appear, int disappear, int colorR, int colorG,
                   int colorB, ShapeName type);

  Shape createCircle(String name, int x, int y, int radius, int appear,
                     int disappear, int colorR, int colorG, int colorB, ShapeName type);

  /**
   * This method allows us to move the shape to a given position. Appends information to the
   * appendable.
   *
   * @param shape the Model.Shape itself that has to be moved.
   */
  void moveShape(Shape shape, int toX, int toY,
                 int timeFrom, int timeTo);

  /**
   * The purpose of this method is to create a string representation of the animation.
   *
   * @return a String that is a representation of the animation in text.
   */
  String getAnimation();

  /**
   * This method is responsible for changing the color of the shape, and then ensuring that the
   * animation is recorded on the appendable.
   *
   * @param shape the shape who's color has to be changed.
   */
  void changeColor(Shape shape, int newR, int newG, int newB, int timeFrom, int timeTo);

  /**
   * This method allows us to change the size of the Model.Rectangle, or to 'scale' it according to
   * a factor. The information is then animated through the appendable.
   *
   * @param shape    the shape which needs to be resized.
   * @param factor   the factor with which the resize has to take place.
   * @param timeFrom the time where the change is enacted.
   * @param timeTo   the time till which the change must be enacted.
   */
  void scaleRectangle(Rectangle shape, int factor, int timeFrom, int timeTo);

  /**
   * This method allows us to change the size of the Model.Rectangle, or to 'scale' it according to
   * a factor. The information is then animated through the appendable.
   *
   * @param shape    the shape which needs to be resized.
   * @param width    the resized width of the rectangle.
   * @param height   the resized height of the rectangle.
   * @param timeFrom the time where the change is enacted.
   * @param timeTo   the time till which the change must be enacted.
   */
  void scaleRectangle(Rectangle shape, int width, int height, int timeFrom, int timeTo);

  /**
   * This method allows us to change the size of the Model.Oval, or to 'scale' it according to a
   * factor. The information is then animated through the appendable.
   *
   * @param shape    the shape which needs to be resized.
   * @param factor   the factor with which the resize must take place.
   * @param timeFrom the time where the change is enacted.
   * @param timeTo   the time till which the change must be enacted.
   */

  void scaleOval(Oval shape, int factor, int timeFrom, int timeTo);

  /**
   * This method allows us to change the size of the Model.Oval, or to 'scale' it according to a
   * factor. The information is then animated through the appendable.
   *
   * @param shape            the shape which needs to be resized.
   * @param horizontalRadius the resized horizontal radius.
   * @param verticalRadius   the resized vertical radius.
   * @param timeFrom         the time where the change is enacted.
   * @param timeTo           the time till which the change must be enacted.
   */
  void scaleOval(Oval shape, int horizontalRadius, int verticalRadius, int timeFrom,
                 int timeTo);


  /**
   * This method allows us to change the size of the Model.Circle, or to 'scale' it according to a
   * factor. The information is then animated through the appendable.
   *
   * @param shape    the shape which needs to be resized.
   * @param factor   the factor with which the resize must take place.
   * @param timeFrom the time where the change is enacted.
   * @param timeTo   the time till which the change must be enacted.
   */
  void scaleCircle(Circle shape, int factor, int timeFrom, int timeTo);
}
