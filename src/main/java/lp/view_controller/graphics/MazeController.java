package lp.view_controller.graphics;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import lp.LpContext;
import lp.model.UserActor;
import lp.model.maze.MazeFactory;
import lp.model.pathfinder.Pathfinder;
import lp.model.position.Apex;
import lp.view_controller.Anim8Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_MOVED;
import static lp.model.DiscreteUtils.pos;
import static lp.view_controller.graphics.GraphicsConstants.WORLD_FIELD_SIZE;

@Component
public class MazeController extends Group implements EventHandler<MouseEvent> {

  @Autowired
  private AStarPathViewController aStarPathViewController;

  @Autowired
  private Anim8Service anim8Service;

  @Autowired
  private LpContext lpContext;

  @Autowired
  private Pathfinder pathfinder;

  @Autowired
  private MazeFactory mazeFactory;

  @NotNull
  private MazeView mazeView;

  @NotNull
  private UserActorViewController userActorViewController;

  @PostConstruct
  private void init() {

    lpContext.mazeProperty().setValue(mazeFactory.newMaze());
    mazeView = new MazeView(lpContext.mazeProperty().get());
    getChildren().add(mazeView);
    userActorViewController = new UserActorViewController(new UserActor());
  }

  @Override
  public void handle(MouseEvent event) {

    if (event.getEventType().equals(MOUSE_MOVED)) {

      int discreteX = (int) (event.getX() / WORLD_FIELD_SIZE);
      int discreteY = (int) (event.getY() / WORLD_FIELD_SIZE);
      Apex discretePos = pos(discreteX, discreteY);
      Apex apexPosition = userActorViewController.getUserActor().getApexPosition();

      if (lpContext.mazeProperty().get().getBoundingBox().contains(discretePos) &&
          !discretePos.equals(apexPosition))
        aStarPathViewController.pathProperty().setValue(pathfinder.calculatePath(apexPosition,
                                                                                 discretePos,
                                                                                 lpContext.mazeProperty().get()));
    } else if (event.getEventType().equals(MOUSE_CLICKED))
      anim8Service.pathTransition(userActorViewController, aStarPathViewController.getPathView()).play();
  }

  @NotNull
  public UserActorViewController getUserActorViewController() {

    return userActorViewController;
  }
}
