package cs5004.animator.model;

import cs5004.animator.controller.Controller;


/**
 * A simple animator class that allows us to create animations using different files. It calls upon
 * the Animation Reader, Builder and the Model to create animations.
 */
public class SimpleAnimator {

  /**
   * This class initialize the MVC and give the controller the ability to manage user input.
   *
   * @param args user's input from terminal.
   */
  public static void main(String[] args) {
    Controller controller = new Controller(args);
    controller.goAnimation();
  }
}