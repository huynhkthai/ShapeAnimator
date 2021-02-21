package cs5004.animator.model;

/**
 * The enum represents the number of shapes that the program is capable of building. The string
 * associated with each kind of enum allows for us to distinguish between shapes.
 */

public enum ShapeName {
  Rectangle("Rectangle"),
  Circle("Circle"),
  Triangle("Triangle"),
  Ellipse("Ellipse");

  private final String value;

  ShapeName(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
