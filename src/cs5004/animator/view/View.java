package cs5004.animator.view;

/**
 * The View.View will take in an animationBook object, utilize an iterator to play the animationBook
 * frame by frame and pass each frame information to the view mode and decode it there.
 */

public interface View {
  //This used be to useful, we cut all of it out for now.

  /**
   * This method will display the desired view. Will be easier to handle in the controller.
   */
  void render();

  /**
   * This method creates the string representation of the view, that will be used to write the
   * file.
   *
   * @return the string representation of the view
   */
  String toString();


  /**
   * This method will get the type of view as a string representation.
   *
   * @return
   */
  String getViewType();


}