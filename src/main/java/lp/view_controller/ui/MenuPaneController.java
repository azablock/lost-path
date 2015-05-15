package lp.view_controller.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import lp.LpContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lp.LpUIState.IN_GAME;

@Component
public class MenuPaneController implements NodeController {

  @Autowired
  private LpContext lpContext;

  @FXML
  private BorderPane menuPane;

  @FXML
  public void onStartClicked() {

    lpContext.lpUIStateProperty().setValue(IN_GAME);
  }

  @FXML
  public void onExitClicked() {

    System.exit(0);
  }

  @NotNull
  @Override
  public Node getNode() {

    return menuPane;
  }

}
