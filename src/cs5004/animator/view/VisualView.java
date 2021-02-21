package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Shape;


/**
 * This Visual view is a type of view, like other view classes. This mostly set up the frame using
 * JFrame then call on a JPanel related class to create a panel. A scroll bar is also created here
 * so the user can scroll if panel isn't showing the whole animation.
 */
public class VisualView extends JFrame implements View {
  Shape canvas;

  /**
   * This constructs a Visual view object.
   *
   * @param animationName is the title for this frame.
   * @param builder       is the model package which holds all the ingredients needed for
   *                      construction.
   */
  public VisualView(String animationName, AnimationBuilder builder) {
    super("Visual View of " + animationName);

    this.canvas = builder.getCanvas();
    setSize(canvas.getParameter1(), canvas.getParameter2());
    setLocation(canvas.getX(), canvas.getY());
    //setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    VisualPanel animationAction = new VisualPanel(builder);
    animationAction.setPreferredSize(new Dimension(canvas.getParameter1(), canvas.getParameter2()));
    add(animationAction, BorderLayout.CENTER);
    JScrollPane viewScrollBar = new JScrollPane(animationAction,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    viewScrollBar.setPreferredSize(new Dimension(canvas.getParameter1(), canvas.getParameter2()));

    add(viewScrollBar, BorderLayout.CENTER);
  }

  @Override
  public void render() {
    setVisible(true);
  }

  @Override
  public String getViewType() {
    return "Visual";
  }

}
