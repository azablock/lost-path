package lp.view_controller.graphics;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lp.model.maze.Maze;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;

import static lp.view_controller.graphics.GraphicsConstants.WORLD_FIELD_SIZE;

public class MazeViewController extends Group {

  @NotNull
  private final Rectangle mouseMoveRectangle;

  @NotNull
  private final Rectangle mazeBoundingBoxRectangle;

  public MazeViewController(@NotNull final Maze maze) {

    mouseMoveRectangle = new Rectangle(WORLD_FIELD_SIZE, WORLD_FIELD_SIZE, Color.RED);

    Apex topLeft = maze.getBoundingBox().getTopLeft();
    mazeBoundingBoxRectangle = new Rectangle(topLeft.getX() * WORLD_FIELD_SIZE,
                                             topLeft.getY() * WORLD_FIELD_SIZE,
                                             maze.getBoundingBox().getWidth() * WORLD_FIELD_SIZE,
                                             maze.getBoundingBox().getHeight() * WORLD_FIELD_SIZE);

    this.getChildren().add(mazeBoundingBoxRectangle);
    this.getChildren().add(mouseMoveRectangle);

    this.setOnMouseMoved(event -> {

      double quantizedX = ((int) (event.getX() / WORLD_FIELD_SIZE)) * WORLD_FIELD_SIZE;
      double quantizedY = ((int) (event.getY() / WORLD_FIELD_SIZE)) * WORLD_FIELD_SIZE;

      if (mazeBoundingBoxRectangle.contains(event.getX(), event.getY()))
        mouseMoveRectangle.relocate(quantizedX, quantizedY);
    });
  }
}
