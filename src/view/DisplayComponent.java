package view;

import javafx.scene.Node;

/**
 * Purpose: This class is an abstraction for any component of the display that will be added to the
 * high level display. This is meant to help organize every element that will be added to the high
 * level view by categorizing these elements all under the same category. Additionally,
 * getDisplayComponentNode() can just be called on every one of these elements when needed to be
 * added to the main view.
 * Assumptions: DisplayComponents should all be of type node and broken down into relatively simple
 * and well-organized responsibilities.
 * Dependencies: Node
 *
 * Example: When creating a button that has some responsibility in the view, this can be made as a
 * class that extends Display Component. Inside this class the abstract method getDisplayComponentNode()
 * can be overwritten to return the button, which the high level model can then easily get by
 * calling getDisplayComponentNode() on the class instance.
 *
 * @author Evan Kenyon
 */
public abstract class DisplayComponent {

  /**
   * Purpose: This method is meant to be an easy and well-organized way for the high level view to
   * be able to get the DisplayComponent node without being able to alter that node. This leaves
   * the responsibility of altering that specific DisplayComponent subclass to that subclass itself.
   * This helps organize the high level view, because each DisplayComponent element can be gotten
   * using the same method.
   * @return Node representing the specific DisplayComponent.
   */
  public abstract Node getDisplayComponentNode();

}
