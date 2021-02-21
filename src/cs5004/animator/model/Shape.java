package cs5004.animator.model;

import java.awt.Color;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface Shape {

  /**
   * This is  a getter method to get shape origin, bottom left corner if rectangular or triangular.
   * Middle if it's a circle or oval.
   *
   * @return X coordinate of origin
   */
  int getX();

  /**
   * This is  a getter method to get shape origin, bottom left corner if rectangular or triangular.
   * Middle if it's a circle or oval.
   *
   * @return Y coordinate of origin
   */
  int getY();

  /**
   * This is  a getter method for the time which the shape appear.
   *
   * @return the time which the shape appear.
   */
  int getAppearTime();

  /**
   * This is  a getter method for the time which the shape disappear.
   *
   * @return the time which the shape disappear.
   */
  int getDisappearTime();

  /**
   * This is  a getter method for the color of this shape.
   *
   * @return the color of this shape.
   */
  Color getColor();


  /**
   * This is  a getter method for the name of this shape.
   *
   * @return the name of this shape.
   */
  String getName();

  /**
   * This is  a getter method for the type of this shape.
   *
   * @return the type of this shape.
   */
  ShapeName getType();

  /**
   * This is  a getter method for R factor of the color of this shape.
   *
   * @return the R factor of this shape's color.
   */
  int getColorR();

  /**
   * This is  a getter method for G factor of the color of this shape.
   *
   * @return the G factor of this shape's color.
   */
  int getColorG();

  /**
   * This is  a getter method for B factor of the color of this shape.
   *
   * @return the B factor of this shape's color.
   */
  int getColorB();

  /**
   * This method change the color of the shape.
   *
   * @param newR     R value for the new color.
   * @param newG     G value for the new color.
   * @param newB     B value for the new color.
   * @param timeFrom time when color start changing.
   * @param timeTo   time when color finish changing.
   * @throws IllegalArgumentException if the change is not possible.
   */
  void changeColor(int newR, int newG, int newB, int timeFrom, int timeTo) throws
          IllegalArgumentException;

  /**
   * This method change the color of the shape.
   *
   * @param newR   R value for the new color.
   * @param newG   G value for the new color.
   * @param newB   B value for the new color.
   * @param timeTo time when color finish changing.
   * @throws IllegalArgumentException if the change is not possible.
   */
  void changeColor(int newR, int newG, int newB, int timeTo) throws
          IllegalArgumentException;


  /**
   * This method changes the time which the shape appears.
   *
   * @param newAppearTime new time at which the shape will appear.
   * @throws IllegalArgumentException if the change is not possible.
   */
  void changeAppearTime(int newAppearTime) throws IllegalArgumentException;

  /**
   * This method changes the time which the shape disappears.
   *
   * @param newDisappearTime new time at which the shape will disappear.
   * @throws IllegalArgumentException if the change is not possible.
   */
  void changeDisappearTime(int newDisappearTime) throws IllegalArgumentException;


  /**
   * This method will move the shape to a new location.
   *
   * @param toX      new location of the origin in X coordinate.
   * @param toY      new location of the origin in Y coordinate.
   * @param timeFrom time when shape start moving.
   * @param timeTo   time when shape finish moving.
   * @throws IllegalArgumentException if the move is not possible.
   */
  void move(int toX, int toY, int timeFrom, int timeTo) throws IllegalArgumentException;

  /**
   * This method will move the shape to a new location.
   *
   * @param toX    new location of the origin in X coordinate.
   * @param toY    new location of the origin in Y coordinate.
   * @param timeTo time when shape finish moving.
   * @throws IllegalArgumentException if the move is not possible.
   */
  void move(int toX, int toY, int timeTo) throws IllegalArgumentException;

  /**
   * Returns the distance of this shape from the origin. The distance is measured from whatever
   * reference position a shape is (e.g. a center for a circle).
   *
   * @return the distance from the origin.
   */
  double distanceFromOrigin();


  /**
   * Scales the area of a shape by the provided factor.
   *
   * @param factor   factor of resizing.
   * @param timeFrom time when shape start resizing.
   * @param timeTo   time when shape finish resizing.
   * @throws IllegalArgumentException if the resizing is not possible.
   */
  void scale(int factor, int timeFrom, int timeTo) throws IllegalArgumentException;

  /**
   * Scales the area of a shape by the provided factor.
   *
   * @param factor factor of resizing.
   * @param timeTo time when shape finish resizing.
   * @throws IllegalArgumentException if the resizing is not possible.
   */
  void scale(int factor, int timeTo) throws IllegalArgumentException;

  /**
   * Changes the width of the of a shape.
   *
   * @param parameter1 new width
   * @param timeFrom   time when shape changes width
   * @param timeTo     time when shape changes height
   */
  void resizeP1(int parameter1, int timeFrom, int timeTo);

  /**
   * Changes the width of the of a shape.
   *
   * @param parameter2 new width
   * @param timeFrom   time when shape changes width
   * @param timeTo     time when shape changes height
   */
  void resizeP2(int parameter2, int timeFrom, int timeTo);


  /**
   * Changes width and height of shape.
   *
   * @param parameter1 new width
   * @param parameter2 new height
   * @param timeFrom   start time when shape resizing
   * @param timeTo     end time when shape resizing
   */
  void resize(int parameter1, int parameter2, int timeFrom, int timeTo);

  /**
   * Changes width and height of shape.
   *
   * @param parameter1 new width
   * @param parameter2 new height
   * @param timeTo     end time when shape resizing
   */
  void resize(int parameter1, int parameter2, int timeTo);

  /**
   * This is  a getter method for parameter 1 of this shape.
   *
   * @return parameter 1 of this shape.
   */
  int getParameter1();

  /**
   * This is  a getter method for parameter 2 of this shape.
   *
   * @return parameter 2 of this shape.
   */
  int getParameter2();


  void historySetter(Shape currentState);
}