package cs5004.animator.view;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.Frame;
import cs5004.animator.model.ShapeName;

/**
 * This generates a text view.
 */
public class TextView extends AbstractView {
  private StringBuilder shapeRegister;
  private StringBuilder mutationRegister;

  /**
   * Construct a text view.
   */
  public TextView(AnimationBuilder builder) {
    super(builder);
    shapeRegister = new StringBuilder();
    mutationRegister = new StringBuilder();

  }

  @Override
  public void render() {
    getShapeView();
    getAnimationText();
    System.out.println(this.shapeRegister + "\n" + this.mutationRegister);
  }

  @Override
  public String toString() {
    getShapeView();
    getAnimationText();
    return this.shapeRegister + "\n" + this.mutationRegister;
  }

  @Override
  public String getViewType() {
    return "Text";
  }


  /**
   * This method will go over the StartAndEndState and create all shapes in text.
   */
  private void getShapeView() {
    for (Frame frames : nameBook) {
      if (frames.getShape().getType() == ShapeName.Rectangle) {
        this.shapeRegister.append("Name: ").append(frames.getShape().getName()).append("\n")
                .append("Type: ").append(frames.getShape().getType())
                .append("\n").append("Min Corner: ").append("(").append(frames.getShape().getX())
                .append(",").append(frames.getShape().getY())
                .append(")").append(", ").append("Width: ").append(frames.getShape()
                .getParameter1()).append(", ")
                .append("Height: ")
                .append(frames.getShape().getParameter2()).append(", ").append("Color: ")
                .append("(").append(frames.getShape().getColorR()).append(".0,")
                .append(frames.getShape().getColorG()).append(".0,")
                .append(frames.getShape().getColorB()).append(".0)").append("\n")
                .append("Appears at t=").append(frames.getShape().getAppearTime())
                .append("\n").append("Disappears at t=")
                .append(frames.getTimeTo()).append("\n");
      }
      if (frames.getShape().getType() == ShapeName.Ellipse) {
        this.shapeRegister.append("Name: ").append(frames.getShape().getName()).append("\n")
                .append("Type: ").append(frames.getShape().getType())
                .append("\n").append("Center: ").append("(").append(frames.getShape().getX())
                .append(",").append(frames.getShape().getY())
                .append(")").append(", ").append("Width: ").append(frames.getShape()
                .getParameter2() * 2).append(", ")
                .append("Height: ").append(frames.getShape().getParameter1() * 2).append(", ")
                .append("Color: ")
                .append("(").append(frames.getShape().getColorR()).append(".0,")
                .append(frames.getShape().getColorG()).append(".0,")
                .append(frames.getShape().getColorB())
                .append(".0)\n").append("Appears at t=").append(frames.getShape().getAppearTime())
                .append("\n")
                .append("Disappears at t=").append(frames.getTimeTo())
                .append("\n");
      }
      if (frames.getShape().getType() == ShapeName.Circle) {
        this.shapeRegister.append("Name: ").append(frames.getShape().getName()).append("\n")
                .append("Type: ").append(frames.getShape().getType())
                .append("\n").append("Center: ").append("(").append(frames.getShape().getX())
                .append(",").append(frames.getShape().getY())
                .append(")").append(", ").append("Diameter: ")
                .append(frames.getShape().getParameter1() * 2).append(", ")
                .append("Radius: ").append(frames.getShape().getParameter1()).append(", ")
                .append("Color : ").append("(")
                .append(frames.getShape().getColorR()).append(".0,")
                .append(frames.getShape().getColorG()).append(".0,")
                .append(frames.getShape().getColorB()).append(".0)\n")
                .append("Appears at t=").append(frames.getShape().getAppearTime())
                .append("\n").append("Disappears at t=")
                .append(frames.getTimeTo()).append("\n");

      }
    }
  }

  /**
   * This method will go over the AnimationStates and create all animations in text.
   */
  private void getAnimationText() {
    for (Frame frames : animationBook) {
      if (frames.getColorStatus()) {
        String initialColor = "(" + frames.getShape().getColorR() + ".0" + ","
                + frames.getShape().getColorG() + ".0" + ","
                + frames.getShape().getColorB() + ".0" + ")";
        this.mutationRegister.append("Shape ").append(frames.getShape().getName())
                .append(" changes color from ")
                .append(initialColor).append(" to (").append(frames.getNewColorR()).append(".0,")
                .append(frames.getNewColorG()).append(".0,")
                .append(frames.getNewColorB()).append(".0)")
                .append(" at t=").append(frames.getTimeTo()).append("\n");
      }
      if (frames.getMoveStatus()) {
        this.mutationRegister.append("Shape ").append(frames.getShape().getName())
                .append(" moves from (")
                .append(frames.getShape().getX()).append(", ")
                .append(frames.getShape().getY()).append(") to (").append(frames.getNewX())
                .append(",").append(frames.getNewY()).append(")")
                .append(" at t=")
                .append(frames.getTimeTo()).append("\n");
      }
      if (frames.getShape().getType() == ShapeName.Rectangle) {
        if (frames.getScaledStatus()) {
          this.mutationRegister.append("Shape ").append(frames.getShape().getName())
                  .append(" scales from Width: ")
                  .append(frames.getShape().getParameter1()).append(" Height: ")
                  .append(frames.getShape().getParameter2()).append(" to Width: ")
                  .append(frames.getNewParameter1()).append(" Height: ")
                  .append(frames.getNewParameter2())
                  .append(" at t=")
                  .append(frames.getTimeTo()).append("\n");
        }
      }
      if (frames.getShape().getType() == ShapeName.Ellipse) {
        if (frames.getScaledStatus()) {
          this.mutationRegister.append("Shape ").append(frames.getShape().getName())
                  .append(" scales from Vertical Radius: ")
                  .append(frames.getShape().getParameter1())
                  .append(" Horizontal Radius: ").append(frames.getShape().getParameter2())
                  .append(" to Vertical Radius: ").append(frames.getNewParameter1())
                  .append(" Horizontal Radius: ").append(frames.getNewParameter2())
                  .append(" at t=")
                  .append(frames.getTimeTo()).append("\n");
        }
      }
    }
  }
}