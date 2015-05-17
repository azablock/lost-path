package lp.view_controller.graphics;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lp.model.UserActor;
import org.jetbrains.annotations.NotNull;

import static lp.model.DiscreteUtils.pos;
import static lp.view_controller.graphics.GraphicsConstants.WORLD_FIELD_SIZE;

public class UserActorViewController extends Group {

  @NotNull
  private final UserActor userActor;

  public UserActorViewController(@NotNull final UserActor userActor) {

    this.userActor = userActor;

    getChildren().add(new Circle(userActor.getApexPosition().getX() * WORLD_FIELD_SIZE + WORLD_FIELD_SIZE / 2,
                                 userActor.getApexPosition().getY() * WORLD_FIELD_SIZE + WORLD_FIELD_SIZE / 2,
                                 WORLD_FIELD_SIZE / 2,
                                 Color.BLACK));

    translateXProperty().addListener((observable, oldTranslateX, newTranslateX) -> {
      userActor.apexPositionProperty().setValue(pos((int) ((double) newTranslateX / WORLD_FIELD_SIZE), userActor.getY()));
    });

    translateYProperty().addListener((observable, oldTranslateY, newTranslateY) -> {
      userActor.apexPositionProperty().setValue(pos(userActor.getX(), (int) ((double) newTranslateY / WORLD_FIELD_SIZE)));
    });
  }

  @NotNull
  public UserActor getUserActor() {

    return userActor;
  }

  @Override
  public String toString() {

    return String.format("UserActorViewController{userActor=%s}", userActor);
  }
}
