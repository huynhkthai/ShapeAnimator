package cs5004.animator.model;

/**
 * This represent the Model.Point class that will consist of an x and y coordinate. It will be used
 * in moving shapes and in implementing the "tween" function in the visual animation.
 */
public class Point {
  protected int x;
  private int y;

  /**
   * Constructs and initializes a Model.Point with an x and y coordinate.
   *
   * @param x the desired x-coordinate
   * @param y the desired y-coordinate
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }


  /**
   * Returns the x-coordinate of the point.
   *
   * @return the x-coordinate of the point
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y-coordinate of the point.
   *
   * @return the y-coordinate of point
   */
  public int getY() {
    return this.y;
  }
}
