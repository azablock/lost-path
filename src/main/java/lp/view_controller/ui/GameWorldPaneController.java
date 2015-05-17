package lp.view_controller.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lp.LpContext;
import lp.model.maze.MazeFactory;
import lp.model.pathfinder.Pathfinder;
import lp.view_controller.Anim8Service;
import lp.view_controller.graphics.AStarPathViewController;
import lp.view_controller.graphics.MazeController;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lp.LpUIState.MENU;

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
  private MazeController mazeController;

  @Autowired
  private LpContext lpContext;

  @FXML
  private Pane gameWorldPane;

  @FXML
  private Pane mazePane;

  @FXML
  private TextField primGenerationTimeTextField;

  @FXML
  private TextField aStarTextField;

  @FXML
  private TextField aStarPathLengthTextField;

  @FXML
  public void initialize() {

    mazePane.getChildren().add(mazeController);
    mazePane.getChildren().add(aStarPathViewController);
    mazePane.getChildren().add(mazeController.getUserActorViewController());

    mazePane.setOnMouseClicked(mazeController::handle);
    mazePane.setOnMouseMoved(mazeController::handle);

    primGenerationTimeTextField.setText("Prim generation: " + lpContext.getPrimTimeMillis() + " ms");

    aStarPathViewController.pathProperty().addListener((observable, oldPath, newPath) -> {

      aStarTextField.setText("A* pathfinding: " + lpContext.getAStarTimeMillis() + " ms");
      aStarPathLengthTextField.setText("A* path length: " + newPath.size());
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
