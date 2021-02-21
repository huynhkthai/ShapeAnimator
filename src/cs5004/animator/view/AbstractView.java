package cs5004.animator.view;

import java.util.ArrayList;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Frame;
import cs5004.animator.model.Shape;

/**
 * An abstract class that holds the Views in their most abstract form. Contains methods that are
 * common to all views and is used to execute those methods.
 */
public abstract class AbstractView implements View {
  protected int speed;
  protected Shape canvas;
  protected ArrayList<Frame> animationBook;
  protected ArrayList<Frame> nameBook;
  protected AnimationBuilder builderFinishedProducts;
  private boolean visibility;

  /**
   * Constructs and initializes an Abstract View object that allows for further child classes to be
   * formed using this constructor.
   */
  public AbstractView(AnimationBuilder builder) {
    this.builderFinishedProducts = builder;
    this.speed = builder.getSpeed();
    this.canvas = builderFinishedProducts.getCanvas();
    this.animationBook = builderFinishedProducts.getAnimationStates();
    this.nameBook = builderFinishedProducts.getStartAndFinalState();
  }

  /**
   * Default for this method will be to throw an exception because this functionality is not
   * available to a view. If it is available, it will be done in its corresponding concrete class.
   *
   * @return the string description of the view
   */
  @Override
  public String toString() {
    throw new IllegalArgumentException("Unable to get the string representation of the view.");
  }
}