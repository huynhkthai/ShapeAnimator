package cs5004.animator.model;

/**
 * This represents the Model.Rectangle shape class which extends the Model.AbstractShape class.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs and initialized the Model.Rectangle shape.
   *
   * @param name      name of this Model.Rectangle.
   * @param type      type of this Model.Rectangle.
   * @param x         of this Model.Rectangle.
   * @param y         of this Model.Rectangle.
   * @param width     of this Model.Rectangle.
   * @param height    of this Model.Rectangle.
   * @param appear    time of this Model.Rectangle.
   * @param disappear time of this Model.Rectangle.
   * @param colorR    Red factor color of this Model.Rectangle.
   * @param colorG    Green factor color of this Model.Rectangle.
   * @param colorB    Blue factor color of this Model.Rectangle.
   * @throws IllegalArgumentException when appropriate.
   */
  public Rectangle(String name, ShapeName type, int x, int y, int width, int height,
                   int appear, int disappear, int colorR, int colorG, int colorB)
          throws IllegalArgumentException {

    super(name, type, x, y, width, height, appear, disappear, colorR, colorG, colorB);
    if (type != ShapeName.Rectangle) {
      throw new IllegalArgumentException("Not correct shape");
    }
  }

  /**
   * Constructs and initialized the Model.Rectangle shape.
   *
   * @param name   name of this Model.Rectangle.
   * @param type   type of this Model.Rectangle.
   * @param x      of this Model.Rectangle.
   * @param y      of this Model.Rectangle.
   * @param width  of this Model.Rectangle.
   * @param height of this Model.Rectangle.
   * @param appear time of this Model.Rectangle.
   * @param colorR Red factor color of this Model.Rectangle.
   * @param colorG Green factor color of this Model.Rectangle.
   * @param colorB Blue factor color of this Model.Rectangle.
   * @throws IllegalArgumentException when appropriate.
   */
  public Rectangle(String name, ShapeName type, int x, int y, int width, int height,
                   int appear, int colorR, int colorG, int colorB)
          throws IllegalArgumentException {

    super(name, type, x, y, width, height, appear, colorR, colorG, colorB);
    if (type != ShapeName.Rectangle) {
      throw new IllegalArgumentException("Not correct shape");
    }
  }

  /**
   * Constructs and initialized the Model.Rectangle shape.
   *
   * @param type   type of this Model.Rectangle.
   * @param x      of this Model.Rectangle.
   * @param y      of this Model.Rectangle.
   * @param width  of this Model.Rectangle.
   * @param height of this Model.Rectangle.
   * @param colorR Red factor color of this Model.Rectangle.
   * @param colorG Green factor color of this Model.Rectangle.
   * @param colorB Blue factor color of this Model.Rectangle.
   * @throws IllegalArgumentException when appropriate.
   */
  public Rectangle(ShapeName type, int x, int y, int width, int height,
                   int colorR, int colorG, int colorB, String name)
          throws IllegalArgumentException {

    super(type, x, y, width, height, colorR, colorG, colorB, name);
    if (type != ShapeName.Rectangle) {
      throw new IllegalArgumentException("Not correct shape");
    }
  }

  /**
   * Constructs and initialized the Model.Rectangle shape.
   *
   * @param name name of this Model.Rectangle.
   * @param type type of this Model.Rectangle.
   */
  public Rectangle(String name, String type) {
    super(name, type);
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Name: ").append(name).append("\n").append("Type: ").append(type)
            .append("\n").append("Min Corner: ").append("(").append(this.position.getX())
            .append(",")
            .append(this.position.getY())
            .append(")").append(", ").append("Width: ").append(parameter1).append(", ")
            .append("Height: ").append(parameter2).append(", ").append("Color: ")
            .append("(").append(colorR).append(",")
            .append(colorG).append(",").append(colorB).append(")").append("\n")
            .append("Appears at t=").append(appear).append("\n").append("Disappears at t=")
            .append(disappear).append("\n");
    return description.toString();
  }

  /**
   * Returns the height of the rectangle which is represented by parameter2 field.
   *
   * @return parameter2
   */
  public int getHeight() {
    return this.parameter2;
  }

  /**
   * Returns the width of the rectangle which is represented by paramter1 field.
   *
   * @return paramter1
   */
  public int getWidth() {
    return this.parameter1;
  }

}




