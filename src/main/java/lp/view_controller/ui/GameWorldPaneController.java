package lp.view_controller.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import lp.LpContext;
import lp.model.maze.MazeFactory;
import lp.model.pathfinder.Pathfinder;
import lp.view_controller.graphics.AStarPathViewController;
import lp.view_controller.graphics.MazeViewController;
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
  private LpContext lpContext;

  @FXML
  private StackPane gameWorldPane;

  @FXML
  private Pane mazePane;

  @FXML
  public void initialize() {

    lpContext.mazeProperty().setValue(mazeFactory.newMaze());

    MazeViewController mazeViewController = new MazeViewController(lpContext.mazeProperty().get());
    mazePane.getChildren().add(mazeViewController);


    mazePane.getChildren().add(aStarPathViewController);

    mazePane.setOnMouseClicked(event -> {
      int discreteX = (int) (event.getX() / WORLD_FIELD_SIZE);
      int discreteY = (int) (event.getY() / WORLD_FIELD_SIZE);
      aStarPathViewController.pathProperty().setValue(pathfinder.calculatePath(pos(2, 2),
                                                                               pos(discreteX, discreteY),
                                                                               lpContext.mazeProperty().get()));
    });
  }

  @FXML
  public void onBackToMenuClicked() {

    lpContext.lpUIStateProperty().setValue(MENU);
  }

  public Pane getMazePane() {

    return mazePane;
  }

  @NotNull
  @Override
  public Node getNode() {

    return gameWorldPane;
  }
}
