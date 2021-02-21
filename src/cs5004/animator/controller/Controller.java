package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.model.AnimationReader;
import cs5004.animator.model.Shape;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.View;
import cs5004.animator.view.VisualPanel;
import cs5004.animator.view.VisualView;


/**
 * A simple animator class that allows us to create animations using different files. It calls upon
 * the Animation Reader, Builder and the Model to create animations.
 */
public class Controller extends JOptionPane implements IController {
  private AnimationBuilder model;
  private View view;
  String in;
  String views;
  String out;
  int speed;
  String speedCheck;
  String[] inputArray;
  JOptionPane errorHandler;
  boolean includesOutFile;

  /**
   * This class will process user's input and either show a view or create a view output file.
   *
   * @param input from terminal.
   */
  public Controller(String[] input) {
    in = "";
    views = "";
    out = "";
    speed = 1; //tick per second
    inputArray = input;
    this.errorHandler = new JOptionPane();

  }

  @Override
  public void goAnimation() {
    setInitializers();
    setBuilder();
    setView();
    handleOut();
    view.render();
  }

  @Override
  public void setInitializers() {
    // Parsing user input to get the required information.
    try {
      for (int i = 0; i < inputArray.length; i++) {
        if (inputArray[i].equalsIgnoreCase("-view")) {
          views = inputArray[i + 1];
        }
        if (inputArray[i].equalsIgnoreCase("-out")) {
          out = inputArray[i + 1];
        }
        if (inputArray[i].equalsIgnoreCase("-in")) {
          in = inputArray[i + 1];
        }
        if (inputArray[i].equalsIgnoreCase("-speed")) {
          speedCheck = inputArray[i + 1];
          int testSpeed = Integer.parseInt(speedCheck);
          if (testSpeed <= 0) {
            throw new IllegalArgumentException("We don't support negative speed");
          } else {
            speed = Integer.parseInt(inputArray[i + 1]);
          }
        }
      }
    } catch (Exception e) {
      errorHandler.isShowing();
      errorHandler.setVisible(true);
      showMessageDialog(null, "Invalid input detected.",
              "View Error", JOptionPane.WARNING_MESSAGE, icon);
      System.exit(0);
    }
  }


  @Override
  public void setBuilder() {
    try {
      Readable file = new FileReader(in);
      model = new AnimationBuilderImpl(speed);
      model = AnimationReader.parseFile(file, model);
    } catch (Exception e) {
      errorHandler.isShowing();
      errorHandler.setVisible(true);
      showMessageDialog(null, "A file with that name could not be found.",
              "File Error", JOptionPane.WARNING_MESSAGE, icon);
      System.exit(0);
    }

  }

  @Override
  public void handleOut() {
    for (String each : inputArray) {
      if (each.equalsIgnoreCase("-out")) {
        try {
          if (in.equalsIgnoreCase(out)) {
            throw new IllegalArgumentException("Output name can't be input's, not allowed");
          }
          PrintWriter writer = null;
          try {
            writer = new PrintWriter(out, StandardCharsets.UTF_8);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
          if (views.equalsIgnoreCase("SVG")) {
            SVGView svg = new SVGView(model);
            assert writer != null;
            writer.print(svg.toString());
          }
          if (views.equalsIgnoreCase("Text")) {
            TextView textView = new TextView(model);
            assert writer != null;
            writer.print(textView.toString());
          }
          if (views.equalsIgnoreCase("Visual")) {
            throw new FileNotFoundException("Visual can't be printed to file");
          }
          if (views.equalsIgnoreCase("Playback")) {
            throw new FileNotFoundException("Playback can't be printed to file");
          }
          if (out.equalsIgnoreCase("")) {
            throw new FileNotFoundException("Writing a file need a name");
          }
          assert writer != null;
          writer.close();
        } catch (Exception e) {
          errorHandler.isShowing();
          errorHandler.setVisible(true);
          showMessageDialog(null, "Can not write to a file",
                  "Write Error", JOptionPane.WARNING_MESSAGE, icon);
          System.exit(0);
        }
      }
    }
  }

  /**
   * This method will set the type of view based on user input.
   */
  @Override
  public void setView() {
    try {
      // Using the input to create the animation.
      if (views.equalsIgnoreCase("SVG")) {
        view = new SVGView(model);
      } else if (views.equalsIgnoreCase("Text")) {
        view = new TextView(model);
      } else if (views.equalsIgnoreCase("Visual")) {
        view = new VisualView(in.substring(0, in.length() - 4), model);
      } else if (views.equalsIgnoreCase("Playback")) {
        view = new PlaybackView(model, new VisualPanel(model));
      } else {
        throw new IllegalArgumentException("Invalid view");
      }
    } catch (Exception e) {
      errorHandler.isShowing();
      errorHandler.setVisible(true);
      showMessageDialog(null, "A View with that name could not be found.",
              "View Error", JOptionPane.WARNING_MESSAGE, icon);
      System.exit(0);
    }
  }

  @Override
  public View getView() {
    return view;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public AnimationBuilder getModel() {
    return this.model;
  }

  @Override
  public boolean includesOutFile() {
    return this.includesOutFile;
  }

  @Override
  public Shape getCanvas() {
    return this.model.getCanvas();
  }


}