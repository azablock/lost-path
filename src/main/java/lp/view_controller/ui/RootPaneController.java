package lp.view_controller.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import lp.LpContext;
import lp.LpUIState;
import lp.view_controller.Anim8Service;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static lp.LpUIState.MENU;

@Component
public class RootPaneController {

  @Autowired
  private MenuPaneController menuPaneController;

  @Autowired
  private GameWorldPaneController gameWorldPaneController;

  @Autowired
  private LpContext lpContext;

  @Autowired
  private Anim8Service anim8Service;

  @FXML
  private GridPane rootPane;

  @FXML
  public void initialize() {

    lpContext.lpUIStateProperty().addListener((observable, currentUIState, newUIState) -> {

      NodeController currentController = getNodeController(currentUIState);
      NodeController newController = getNodeController(newUIState);

      anim8Service.fadeChainSwap(currentController.getNode(), newController.getNode()).play();
    });
  }

  @NotNull
  private NodeController getNodeController(@NotNull LpUIState uiState) {

    return uiState == MENU ? menuPaneController : gameWorldPaneController;
  }
}
