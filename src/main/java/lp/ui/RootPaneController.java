package lp.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RootPaneController {

  @Autowired
  private MazePaneController mazePaneController;

  @FXML
  private StackPane rootPane;
}
