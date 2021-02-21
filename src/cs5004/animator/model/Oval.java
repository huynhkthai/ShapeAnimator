package cs5004.animator.model;

/**
 * This class creates an Model.Oval with all its properties and mutations.
 */
public class Oval extends AbstractShape {

  /**
   * Constructs and initialized the Oval shape.
   *
   * @param name             name of this oval.
   * @param type             type of this oval.
   * @param x                of this oval.
   * @param y                of this oval.
   * @param horizontalRadius horizontal radius of this oval.
   * @param verticalRadius   vertical radius of this oval.
   * @param appear           time of this oval.
   * @param disappear        time of this oval.
   * @param colorR           Red factor color of this oval.
   * @param colorG           Green factor color of this oval.
   * @param colorB           Blue factor color of this oval.
   * @throws IllegalArgumentException when appropriate.
   */
  public Oval(String name, ShapeName type, int x, int y, int horizontalRadius,
              int verticalRadius, int appear, int disappear,
              int colorR, int colorG, int colorB)
          throws IllegalArgumentException {
    super(name, type, x, y, horizontalRadius, verticalRadius, appear, disappear, colorR,
            colorG, colorB);
    if (type != ShapeName.Ellipse) {
      throw new IllegalArgumentException("Model.Shape is not an oval");
    }
  }

  /**
   * Constructs and initialized the Oval shape.
   *
   * @param name             name of this oval.
   * @param type             type of this oval.
   * @param x                of this oval.
   * @param y                of this oval.
   * @param horizontalRadius horizontal radius of this oval.
   * @param verticalRadius   vertical radius of this oval.
   * @param appear           time of this oval.
   * @param colorR           Red factor color of this oval.
   * @param colorG           Green factor color of this oval.
   * @param colorB           Blue factor color of this oval.
   * @throws IllegalArgumentException when appropriate.
   */
  public Oval(String name, ShapeName type, int x, int y, int horizontalRadius,
              int verticalRadius, int appear,
              int colorR, int colorG, int colorB)
          throws IllegalArgumentException {
    super(name, type, x, y, horizontalRadius, verticalRadius, appear, colorR,
            colorG, colorB);
    if (type != ShapeName.Ellipse) {
      throw new IllegalArgumentException("Model.Shape is not an oval");
    }
  }

  /**
   * Constructs and initialized the Oval shape.
   *
   * @param name             name of this oval.
   * @param type             type of this oval.
   * @param x                of this oval.
   * @param y                of this oval.
   * @param horizontalRadius horizontal radius of this oval.
   * @param verticalRadius   vertical radius of this oval.
   * @param colorR           Red factor color of this oval.
   * @param colorG           Green factor color of this oval.
   * @param colorB           Blue factor color of this oval.
   * @throws IllegalArgumentException when appropriate.
   */
  public Oval(ShapeName type, int x, int y, int horizontalRadius,
              int verticalRadius,
              int colorR, int colorG, int colorB, String name)
          throws IllegalArgumentException {
    super(type, x, y, horizontalRadius, verticalRadius, colorR,
            colorG, colorB, name);
    if (type != ShapeName.Ellipse) {
      throw new IllegalArgumentException("Model.Shape is not an oval");
    }
  }

  /**
   * Constructs and initialized the Oval shape.
   */
  public Oval(String name, String type) {
    super(name, type);
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Name: ").append(name).append("\n").append("Type: ").append(type)
            .append("\n").append("Center: ").append("(").append(this.position.getX()).append(",")
            .append(this.position.getY())
            .append(")").append(", ").append("Width: ").append(parameter1 * 2).append(", ")
            .append("Height: ").append(parameter2 * 2).append(", ").append("Color : ")
            .append("(").append(colorR).append(",").append(colorG).append(",").append(colorB)
            .append("\n").append("Appears at t=").append(appear).append("\n")
            .append("Disappears at t=").append(disappear).append("\n");
    return description.toString();
  }

  /**
   * This is  a getter method for parameter 2 of this shape.
   *
   * @return parameter 2 of this shape.
   */
  public int getVerticalRadius() {
    return this.parameter2;
  }

  /**
   * This is  a getter method for parameter 1 of this shape.
   *
   * @return parameter 1 of this shape.
   */
  public int getHorizontalRadius() {
    return this.parameter1;
  }
}
