package lp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lp.model.maze.Maze;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static lp.LpUIState.MENU;

@Component
public class LpContext {

  @NotNull
  private final ObjectProperty<LpUIState> lpUIState;

  @NotNull
  private final ObjectProperty<Maze> maze;

  public LpContext() {

    lpUIState = new SimpleObjectProperty<>(MENU);
    maze = new SimpleObjectProperty<>();
  }

  @NotNull
  public ObjectProperty<LpUIState> lpUIStateProperty() {

    return lpUIState;
  }

  @NotNull
  public ObjectProperty<Maze> mazeProperty() {

    return maze;
  }
}
