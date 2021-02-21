package cs5004.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Frame;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeName;

import static cs5004.animator.model.ShapeName.Ellipse;
import static cs5004.animator.model.ShapeName.Rectangle;

/**
 * This represents the VisualPanel class that extends JPanel. We are customizing the panel that will
 * go into the JFrame to display an animation.
 */
public class VisualPanel extends JPanel implements ActionListener {
  ArrayList<Frame> shapeEvents; // all info of a shapes
  Timer t;
  int tempo;
  ArrayList<Shape> tempShapeState;
  int timerTrigger;
  int indicator;
  int currentDelay;


  /**
   * This constructs a panel object. It will set the tempo, update shapeEvents, create and start the
   * timer and calling player to play the animation.
   *
   * @param builder is the model package which holds all the ingredients needed for construction.
   */
  public VisualPanel(AnimationBuilder builder) {
    this.shapeEvents = builder.getShapeEvent();
    this.indicator = 0; //0 - default, 1 - reverse, 2 - pause/start, 3 - Repeat/Loop
    this.tempo = builder.getSpeed();
    this.currentDelay = 1000 / this.tempo;
    t = new Timer(currentDelay, this);
    timerTrigger = firstTime();
    player(timerTrigger);
    t.start();
  }

  /**
   * This method finds time at which the first animation is started.
   *
   * @return time at which the first animation is started.
   */
  public int firstTime() {
    int first = 100000;
    for (Frame each : shapeEvents) {
      if (each.getHistory().get(0).getShape().getAppearTime() < first) {
        first = each.getHistory().get(0).getShape().getAppearTime();
      }
    }
    return first;
  }

  /**
   * This method finds time at which the first animation is started.
   *
   * @return time at which the first animation is started.
   */
  public int lastTime() {
    int last = 0;
    for (Frame each : shapeEvents) {
      if (each.getHistory().get(each.getHistory().size() - 1).getTimeTo() > last) {
        last = each.getHistory().get(each.getHistory().size() - 1).getTimeTo();
      }
    }
    return last;
  }

  /**
   * This method set up the shapes at a given time for paintComponent to paint.
   *
   * @param currentTime time at which user want screen painted.
   */
  public void player(int currentTime) {
    tempShapeState = new ArrayList<>();
    for (Frame eachFrame : shapeEvents) {
      for (Frame innerFrame : eachFrame.getHistory()) {
        if (innerFrame.getShape().getAppearTime() <= currentTime
                && currentTime <= innerFrame.getTimeTo()) {
          Shape tempShape = linearInterpolation(currentTime,
                  innerFrame.getShape().getAppearTime(),
                  innerFrame.getTimeTo(),
                  innerFrame.getShape().getX(),
                  innerFrame.getNewX(),
                  innerFrame.getShape().getY(),
                  innerFrame.getNewY(),
                  innerFrame.getShape().getParameter1(),
                  innerFrame.getNewParameter1(),
                  innerFrame.getShape().getParameter2(),
                  innerFrame.getNewParameter2(),
                  innerFrame.getShape().getColorR(),
                  innerFrame.getNewColorR(),
                  innerFrame.getShape().getColorG(),
                  innerFrame.getNewColorG(),
                  innerFrame.getShape().getColorB(),
                  innerFrame.getNewColorB(),
                  innerFrame.getShape().getType(),
                  innerFrame.getShape().getName());

          tempShapeState.add(tempShape);
        }
      }
    }

    if (tempShapeState.size() < shapeEvents.size()) {
      for (Frame each : shapeEvents) {
        boolean eachEventIndicator = true;
        for (Shape eachShape : tempShapeState) {
          if (each != null && eachShape != null
                  && each.getFrameName().equalsIgnoreCase(eachShape.getName())) {
            eachEventIndicator = false;
          }
        }
        if (eachEventIndicator && each != null) {
          tempShapeState.add(each.getLastState(currentTime));
        }
      }
    }
  }


  /**
   * This create a state of a given object in a given time using linear interpolation.
   *
   * @param currentTime time at which user wish to paint at.
   * @param timeBefore  lower band time of motion.
   * @param timeAfter   higher band time of motion.
   * @param xBefore     lower band x position of motion.
   * @param xAfter      higher band x position of motion.
   * @param yBefore     lower band y position of motion.
   * @param yAfter      higher band y position of motion.
   * @param p1Before    lower band p1 position of motion.
   * @param p1After     higher band p1 position of motion.
   * @param p2Before    lower band p1 position of motion.
   * @param p2After     higher band p1 position of motion.
   * @param rBefore     lower band r position of motion.
   * @param rAfter      higher band r position of motion.
   * @param gBefore     lower band g position of motion.
   * @param gAfter      higher band g position of motion.
   * @param bBefore     lower band b position of motion.
   * @param bAfter      higher band b position of motion.
   * @param type        type of this shape.
   * @param name        name of this shape.
   * @return shape to be painted by paintComponents.
   */
  public Shape linearInterpolation(int currentTime, int timeBefore, int timeAfter,
                                   int xBefore, int xAfter, int yBefore, int yAfter,
                                   int p1Before, int p1After, int p2Before, int p2After,
                                   int rBefore, int rAfter, int gBefore, int gAfter,
                                   int bBefore, int bAfter, ShapeName type, String name) {
    int x;
    int y;
    int p1;
    int p2;
    int r;
    int g;
    int b;
    double timeInterval = timeAfter - timeBefore;
    if (timeInterval > 0) {
      x = (int) (xBefore * ((timeAfter - currentTime) / timeInterval)
              + xAfter * ((currentTime - timeBefore) / timeInterval));

      y = (int) (yBefore * ((timeAfter - currentTime) / timeInterval)
              + yAfter * ((currentTime - timeBefore) / timeInterval));

      p1 = (int) (p1Before * ((timeAfter - currentTime) / timeInterval)
              + p1After * ((currentTime - timeBefore) / timeInterval));

      p2 = (int) (p2Before * ((timeAfter - currentTime) / timeInterval)
              + p2After * ((currentTime - timeBefore) / timeInterval));

      r = (int) (rBefore * ((timeAfter - currentTime) / timeInterval)
              + rAfter * ((currentTime - timeBefore) / timeInterval));

      g = (int) (gBefore * ((timeAfter - currentTime) / timeInterval)
              + gAfter * ((currentTime - timeBefore) / timeInterval));

      b = (int) (bBefore * ((timeAfter - currentTime) / timeInterval)
              + bAfter * ((currentTime - timeBefore) / timeInterval));
    } else {
      x = xBefore;
      y = yBefore;
      p1 = p1Before;
      p2 = p2Before;
      r = rBefore;
      g = gBefore;
      b = bBefore;
    }
    if (type == Rectangle) {
      return new Rectangle(type, x, y, p1, p2, r, g, b, name);
    } else if (type == Ellipse) {
      return new Oval(type, x, y, p1, p2, r, g, b, name);
    } else {
      throw new IllegalArgumentException("We only take Rectangle and Ellipse right now");
    }
  }

  /**
   * This method paints our frames of animations.
   *
   * @param ghaphic is the brush stroke that paint our frames of animations.
   */
  public void paintComponent(Graphics ghaphic) {
    super.paintComponent(ghaphic);
    Graphics2D g2 = (Graphics2D) ghaphic;
    for (Shape each : tempShapeState) {
      if (each != null) {
        g2.setColor(new Color(each.getColorR(), each.getColorG(), each.getColorB()));
        if (each.getType() == Rectangle) {
          Rectangle2D.Double rect = new Rectangle2D.Double(each.getX(), each.getY(),
                  each.getParameter1(), each.getParameter2());
          g2.draw(rect);
          g2.fill(rect);
        } else if (each.getType() == ShapeName.Ellipse) {
          Ellipse2D.Double ellip = new Ellipse2D.Double(each.getX(), each.getY(),
                  each.getParameter1(), each.getParameter2());
          g2.draw(ellip);
          g2.fill(ellip);
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (this.indicator != 1) {
      player(timerTrigger);
      repaint();
      if (this.indicator != 2) {
        timerTrigger++;
      }
    } else if (this.indicator == 1) {
      player(timerTrigger);
      repaint();
      if (this.indicator != 2) {
        timerTrigger--;
      }
    }
    if (timerTrigger == 0 && indicator == 1) {
      indicator = 0;
      repaint();
    }

    if (this.timerTrigger > lastTime() && this.indicator == 3) {
      timerTrigger = 0;
      player(timerTrigger);

    }
  }

  /**
   * This method is used to increase the speed of the animation.
   */
  public void increaseSpeed() {
    this.tempo = this.tempo + 5;
    currentDelay = 1000 / this.tempo;
    t.setDelay(currentDelay);
  }


  /**
   * This method is used to decrease the speed of the animation.
   */
  public void decreaseSpeed() {
    if (this.tempo > 5) {
      this.tempo = this.tempo - 5;
      currentDelay = 1000 / this.tempo;
    }
    t.setDelay(currentDelay);
  }

}
