package cs5004.animator.view;



import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Shape;

/**
 * This represents the PlayBack view.
 */
public class PlaybackView extends JFrame implements View {
  VisualPanel panel;
  AnimationBuilder builder;
  Shape canvas;

  /**
   * Constructs and initializes an Playback View object that allows to use the Visual view with
   * added functionality.
   *
   * @param builder an animation builder object that has two arrays containing animation data and a
   *                canvas.
   * @param panel   A visual panel object that will be used to perform actions.
   */
  public PlaybackView(AnimationBuilder builder, VisualPanel panel) {
    super("Playback View");
    this.builder = builder;
    this.panel = panel;
    this.canvas = builder.getCanvas();

    setSize(canvas.getParameter1(), canvas.getParameter2());
    setLocation(canvas.getX(), canvas.getY());


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JButton start = new JButton("Start");
    JButton pause = new JButton("Pause");
    JButton loop = new JButton("Loop");
    JButton increaseSpeed = new JButton("Increase speed");
    JButton decreaseSpeed = new JButton("Decrease speed");
    JButton restart = new JButton("Restart");

    ButtonHandler handler = new ButtonHandler();
    start.addActionListener(handler);
    pause.addActionListener(handler);
    loop.addActionListener(handler);
    increaseSpeed.addActionListener(handler);
    decreaseSpeed.addActionListener(handler);
    restart.addActionListener(handler);

    this.panel.add(start);
    this.panel.add(pause);
    this.panel.add(loop);
    this.panel.add(increaseSpeed);
    this.panel.add(decreaseSpeed);
    this.panel.add(restart);
    add(this.panel);

    this.panel.setPreferredSize(new Dimension(canvas.getParameter1(), canvas.getParameter2()));
    add(this.panel, BorderLayout.CENTER);
    JScrollPane viewScrollBar = new JScrollPane(this.panel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    viewScrollBar.setPreferredSize(new Dimension(canvas.getParameter1(), canvas.getParameter2()));

    add(viewScrollBar, BorderLayout.CENTER);
  }

  /**
   * The start method allows for the user to click on the Start button and effectively begin the
   * animation from a paused state. Pressing the start button will have no effect as the panel will
   * already be in the correct state.
   *
   * <p>It does so by utilizing an indicator present within the Panel object. Once that indicator is
   * set to 0 (which is also the default indicator) the animation begins running.
   */
  public void start() {
    panel.indicator = 0;
  }

  /**
   * Permits the user to restart the animation. It achieves this functionality by employing an
   * indicator that exists within the Panel object. Once the indicator is set to 1, that triggers
   * the animation to rewind and go back to it's initial state. Once there, the animation begins to
   * run as it would from its initial state.
   *
   * <p>Using the restart button several times shall have no effect as the animation shall already
   * be rewinding. However, pressing the restart button multiple times if the animation is beginning
   * anew will restart it to its initial state as the rewind portion shall be reached near
   * spontaneously.
   */
  public void restart() {
    panel.indicator = 1;
  }

  /**
   * Allows the user to press the pause button and halt the animation where it stands. Achieves this
   * functionality by employing an indicator in the Panel class that allows for the program to
   * detect the user input in it's translated form.
   *
   * <p>Prevents the animation from continuing and preserves the screen state as it is. Once used in
   * conjunction with the Start button, can be used to tailor the animation experience to the users
   * preference.
   */
  public void pause() {
    panel.indicator = 2;
  }

  /**
   * Used to replay the animation once it reaches it's end. Achieves this functionality by employing
   * an indicator in the Panel object which can be used to indicate that the user has triggered the
   * loop command.
   *
   * <p>Employs a helper method in the Panel object to set the conditions for the looping mechanism.
   */
  public void loop() {
    panel.indicator = 3;
  }

  /**
   * Permits the user to click on the increase speed button and effectively increase the speed of
   * the animation. Does not employ the indicator as with the methods above, much rather, makes use
   * a helper method inside the Panel object to make the necessary changes.
   *
   * <p>Increases the speed by a factor of 2 each time the user chooses to click on this
   * button. Takes effect spontaneously, and from the moment the button is clicked. Does not
   * change the animation from its current state, and only just increases the speed for the
   * remaining animation.
   *
   * <p>Supports multiple clicks, increasing the speed by a factor of 2 each time.
   */
  public void increaseSpeed() {
    panel.increaseSpeed();
  }

  /**
   * Allows the user to click on the decrease speed button and reduce the speed of the animation.
   * Uses a helper method in the Panel object that allows for the changes in the values to be made.
   * The effect of this method is spontaneous and decreases the speed for the animation when it is
   * pressed.
   *
   * <p>Does not reset or alter the animation states that have passed. Only decreases the speed
   * for the current animation state to its end.
   *
   * <p>Reduces the speed by a factor of 2, effectively reducing it to half its current speed.
   */
  public void decreaseSpeed() {
    panel.decreaseSpeed();
  }


  @Override
  public void render() {
    setVisible(true);
  }

  @Override
  public String getViewType() {
    return "Playback";
  }

  /**
   * This is a nested class called ButtonHandler. It implements the ActionListener interface and is
   * used in conjunction with the JButtons to grant user interactive features to this program.
   *
   * <p>It is responsible for interpreting the user input and calling the appropriate function based
   * upon the clicks on the specific buttons.
   *
   * <p>Implemented as a nested class due to the fact that information as well as method calls can
   * be made in a much simpler and readable fashion. As the entire purpose of this class is to
   * call the requisite methods and use the data from the current class, the design choice to
   * build it here was made.
   */
  public class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      if (actionEvent.getActionCommand().equalsIgnoreCase("loop")) {
        loop();
      }
      if (actionEvent.getActionCommand().equalsIgnoreCase("restart")) {
        restart();
      }
      if (actionEvent.getActionCommand().equalsIgnoreCase("Increase Speed")) {
        increaseSpeed();
      }
      if (actionEvent.getActionCommand().equalsIgnoreCase("Decrease Speed")) {
        decreaseSpeed();
      }
      if (actionEvent.getActionCommand().equalsIgnoreCase("Start")) {
        start();
      }
      if (actionEvent.getActionCommand().equalsIgnoreCase("Pause")) {
        pause();
      }
    }
  }


}