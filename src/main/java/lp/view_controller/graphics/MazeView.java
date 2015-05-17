package lp.view_controller.graphics;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import lp.model.maze.Maze;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static lp.view_controller.graphics.GraphicsConstants.WORLD_FIELD_SIZE;

public class MazeView extends Group {

  public MazeView(@NotNull final Maze maze) {

    Rectangle mazeBoundingBoxRectangle = new Rectangle(maze.getBoundingBox().getWidth() * WORLD_FIELD_SIZE,
                                                       maze.getBoundingBox().getHeight() * WORLD_FIELD_SIZE, new Color(0.65, 0.65, 0.65, 1.0));

    mazeBoundingBoxRectangle.setStroke(new Color(0.2, 0.2, 0.4, 1.0));
    mazeBoundingBoxRectangle.setStrokeWidth(WORLD_FIELD_SIZE / 10.0);

    this.getChildren().add(mazeBoundingBoxRectangle);

    maze.getWallPositions().forEach(edge -> {

      Apex first = edge.getFirst();
      Apex second = edge.getSecond();

      Line wallLine = new Line(WORLD_FIELD_SIZE * (max(first.getX(), second.getX())),
                               WORLD_FIELD_SIZE * (max(first.getY(), second.getY())),
                               WORLD_FIELD_SIZE * (max(first.getX(), second.getX()) + abs(first.getY() - second.getY())),
                               WORLD_FIELD_SIZE * (max(first.getY(), second.getY()) + abs(first.getX() - second.getX())));

      wallLine.setStroke(new Color(0.2, 0.2, 0.4, 1.0));
      wallLine.setStrokeWidth(WORLD_FIELD_SIZE / 10.0);
      this.getChildren().add(wallLine);
    });
  }
}
