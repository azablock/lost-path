package lp.view_controller.graphics;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

import static lp.view_controller.graphics.GraphicsConstants.WORLD_FIELD_SIZE;

@Component
public class AStarPathViewController extends Group {

  @NotNull
  private final ObjectProperty<LinkedList<Apex>> path;

  @NotNull
  private final LinkedList<Circle> pathView;

  public AStarPathViewController() {

    path = new SimpleObjectProperty<>();
    pathView = new LinkedList<>();

    path.addListener((observable, oldPath, newPath) -> {

      getChildren().clear();
      pathView.clear();

      newPath.forEach(apex -> pathView.addLast(new Circle(apex.getX() * WORLD_FIELD_SIZE + WORLD_FIELD_SIZE / 2,
                                                          apex.getY() * WORLD_FIELD_SIZE + WORLD_FIELD_SIZE / 2,
                                                          WORLD_FIELD_SIZE / 4,
                                                          new Color(0.6,
                                                                    0.25,
                                                                    1.0 - newPath.indexOf(apex) * (1.0 / newPath.size()),
                                                                    0.10 + 0.90 * (newPath.indexOf(apex) * (1.0 / newPath.size()))))));

      getChildren().addAll(pathView);
    });
  }

  @NotNull
  public LinkedList<Circle> getPathView() {

    return pathView;
  }

  @NotNull
  public ObjectProperty<LinkedList<Apex>> pathProperty() {

    return path;
  }
}
