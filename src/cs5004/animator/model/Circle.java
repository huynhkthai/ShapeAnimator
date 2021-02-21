package cs5004.animator.model;

/**
 * This class creates an Model.Circle with all its properties and mutations.
 */
public class Circle extends AbstractShape {

  /**
   * Constructs and initialized the circle shape.
   *
   * @param name      name of this circle.
   * @param type      type of this circle.
   * @param x         of this circle.
   * @param y         of this circle.
   * @param radius    horizontal radius of this circle.
   * @param appear    time of this circle.
   * @param disappear time of this circle.
   * @param colorR    Red factor color of this circle.
   * @param colorG    Green factor color of this circle.
   * @param colorB    Blue factor color of this circle.
   * @throws IllegalArgumentException when appropriate.
   */
  public Circle(String name, int x, int y, int radius, int appear,
                int disappear, int colorR, int colorG, int colorB, ShapeName type)
          throws IllegalArgumentException {                           //change type to last
    super(name, x, y, radius, appear, disappear, colorR, colorG, colorB, type);
    if (type != ShapeName.Circle) {
      throw new IllegalArgumentException("Not a circle.");
    }
  }

  /**
   * Constructs and initialized the circle shape.
   *
   * @param name   name of this circle.
   * @param type   type of this circle.
   * @param x      of this circle.
   * @param y      of this circle.
   * @param radius horizontal radius of this circle.
   * @param appear time of this circle.
   * @param colorR Red factor color of this circle.
   * @param colorG Green factor color of this circle.
   * @param colorB Blue factor color of this circle.
   * @throws IllegalArgumentException when appropriate.
   */
  public Circle(String name, ShapeName type, int x, int y, int radius, int appear,
                int colorR, int colorG, int colorB)
          throws IllegalArgumentException {
    super(name, type, x, y, radius, appear, colorR, colorG, colorB);
    if (type != ShapeName.Circle) {
      throw new IllegalArgumentException("Not a circle.");
    }
  }

  /**
   * Constructs and initialized the circle shape.
   *
   * @param name name of this circle.
   * @param type type of this circle.
   */
  public Circle(String name, String type) {
    super(name, type);
  }

  @Override
  public void scale(int factor, int timeFrom, int timeTo) {
    super.scale(factor, timeFrom, timeTo);
    this.parameter1 = parameter1 * factor;
  }

  /**
   * Resizes the circle by a provided radius.
   *
   * @param radius   desired radius
   * @param timeFrom start time of radius change
   * @param timeTo   end time of radius change
   */
  public void resize(int radius, int timeFrom, int timeTo) {
    super.resizeP1(radius, timeFrom, timeTo);
  }

  /**
   * Resize the circle by a provided radius.
   *
   * @param radius desired radius
   * @param timeTo end time of radius change
   */
  public void resize(int radius, int timeTo) {
    super.resizeP1(radius, timeFrom, timeTo);
  }

  @Override
  public void resize(int width, int height, int timeFrom, int timeTo) {
    throw new IllegalArgumentException("A circle does not have a width and height");
  }


  /**
   * Returns the width field, which is the radius of the circle.
   *
   * @return radius of circle
   */
  public int getRadius() {
    return this.parameter1;
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Name: ").append(name).append("\n").append("Type: ").append(type)
            .append("\n").append("Center: ").append("(").append(this.position.getX()).append(",")
            .append(this.position.getY())
            .append(")").append(", ").append("Diameter: ").append(parameter1 * 2).append(", ")
            .append("Radius: ").append(parameter1).append(", ").append("Color : ").append("(")
            .append(colorR).append(",").append(colorG).append(",").append(colorB).append(")\n")
            .append("Appears at t=").append(appear).append("\n").append("Disappears at t=")
            .append(disappear).append("\n");
    return description.toString();
  }
}
