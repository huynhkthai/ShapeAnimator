package cs5004.animator.model;

/**
 * The enum represents the number of animation views that the program is capable of building. The
 * string associated with each kind of enum allows for us to distinguish between views.
 */
public enum MutationType {
  changeX("x"),
  changeY("y"),
  changeP1("p1"),
  changeP2("p2"),
  changeR("r"),
  changeG("g"),
  changeB("b");

  //TextAnimation("text"),
  //VisualAnimation("visual"),
  //SVGAnimation("svg");

  private final String value;

  /**
   * This method is a enum text setter.
   *
   * @param value the string value of the mutation type.
   */
  MutationType(String value) {
    this.value = value;
  }


  /**
   * This method is a enum text getter.
   *
   * @return value the string value of the mutation type.
   */
  String getValue() {
    return this.value;
  }
}

