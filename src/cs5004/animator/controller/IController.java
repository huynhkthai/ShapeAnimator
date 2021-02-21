package cs5004.animator.controller;

import java.io.FileNotFoundException;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Shape;
import cs5004.animator.view.View;

/**
 * This represents the Controller interface.
 */
public interface IController {
  /**
   * This method will display the requested view from user input on the command line.
   */
  void goAnimation() throws FileNotFoundException;

  /**
   * This method sets the string values of what is read from the command line.
   */
  void setInitializers() throws FileNotFoundException;

  /**
   * This method transforms the an empty model into an animation if it exits.
   */
  void setBuilder() throws FileNotFoundException;

  /**
   * This method handles the creating of an output file.
   */
  void handleOut() throws FileNotFoundException;

  /**
   * This methos transforms an empty view into the requested view option.
   */
  void setView() throws FileNotFoundException;

  /**
   * This method is a getter for the type of view. Will assist with testing that the controller
   * properly read the input from the command line.
   *
   * @return the type of view
   */
  View getView();

  /**
   * This method is a getter for the speed of the animation as it was read from the command line.
   *
   * @return the speed of the animation
   */
  int getSpeed();

  /**
   * This method is a getter of the model that is created from the command line.
   *
   * @return the model
   */
  AnimationBuilder getModel();

  /**
   * This method will determine is an out file was requested or not.
   *
   * @return true is there is an outfile expected
   */
  boolean includesOutFile();

  /**
   * This method is a getter for the canvas that is used to display the visual graphical views.
   *
   * @return the canvas of the animation
   */
  Shape getCanvas();

}