package lp.view_controller.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import lp.LpContext;
import lp.model.maze.MazeFactory;
import lp.view_controller.graphics.MazeViewController;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lp.LpUIState.MENU;

@Component
public class GameWorldPaneController implements NodeController {

  @Autowired
  private MazeFactory mazeFactory;

  @Autowired
  private LpContext lpContext;

  @FXML
  private StackPane gameWorldPane;

  @FXML
  private Pane mazePane;

  @FXML
  public void initialize() {

    mazePane.getChildren().add(new MazeViewController(mazeFactory.newMaze()));
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
