package lp.view_controller.ui;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lp.LpContext;
import lp.model.UserActor;
import lp.model.maze.MazeFactory;
import lp.model.pathfinder.Pathfinder;
import lp.model.position.Apex;
import lp.view_controller.Anim8Service;
import lp.view_controller.graphics.AStarPathViewController;
import lp.view_controller.graphics.MazeViewController;
import lp.view_controller.graphics.UserActorViewController;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lp.LpUIState.MENU;
import static lp.model.DiscreteUtils.pos;
import static lp.view_controller.graphics.GraphicsConstants.WORLD_FIELD_SIZE;

@Component
public class GameWorldPaneController implements NodeController {

  @Autowired
  private MazeFactory mazeFactory;

  @Autowired
  private Pathfinder pathfinder;

  @Autowired
  private AStarPathViewController aStarPathViewController;

  @Autowired
  private Anim8Service anim8Service;

  @Autowired
  private LpContext lpContext;

  @FXML
  private GridPane gameWorldPane;

  @FXML
  private Pane mazePane;

  @FXML
  public void initialize() {

    lpContext.mazeProperty().setValue(mazeFactory.newMaze());

    MazeViewController mazeViewController = new MazeViewController(lpContext.mazeProperty().get());

    UserActorViewController userActorViewController = new UserActorViewController(new UserActor());

    mazePane.getChildren().add(mazeViewController);
    mazePane.getChildren().add(aStarPathViewController);
    mazePane.getChildren().add(userActorViewController);

    mazeViewController.setOnMouseMoved(event -> {
      int discreteX = (int) (event.getX() / WORLD_FIELD_SIZE);
      int discreteY = (int) (event.getY() / WORLD_FIELD_SIZE);
      Apex discretePos = pos(discreteX, discreteY);

      if (lpContext.mazeProperty().get().getBoundingBox().contains(discretePos) &&
          !discretePos.equals(userActorViewController.getUserActor().getApexPosition()))
        aStarPathViewController.pathProperty().setValue(pathfinder.calculatePath(userActorViewController.getUserActor().getApexPosition(),
                                                                                 discretePos,
                                                                                 lpContext.mazeProperty().get()));
    });

    mazeViewController.setOnMouseClicked(event -> anim8Service.pathTransition(userActorViewController, aStarPathViewController.getPathView()).play());

    aStarPathViewController.setOnMouseClicked(event -> {
      if (aStarPathViewController.getPathView().getLast().contains(new Point2D(event.getX(), event.getY())))
        anim8Service.pathTransition(userActorViewController, aStarPathViewController.getPathView()).play();
    });
  }

  @FXML
  public void onBackToMenuClicked() {

    lpContext.lpUIStateProperty().setValue(MENU);
  }

  @NotNull
  @Override
  public Node getNode() {

    return gameWorldPane;
  }
}
