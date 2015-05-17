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

  @NotNull
  private long aStarTimeMillis;

  @NotNull
  private long primTimeMillis;

  public LpContext() {

    lpUIState = new SimpleObjectProperty<>(MENU);
    maze = new SimpleObjectProperty<>();
    aStarTimeMillis = 0;
    primTimeMillis = 0;
  }

  @NotNull
  public ObjectProperty<LpUIState> lpUIStateProperty() {

    return lpUIState;
  }

  public long getAStarTimeMillis() {

    return aStarTimeMillis;
  }

  public void setAStarTimeMillis(@NotNull long aStarTimeMillis) {

    this.aStarTimeMillis = aStarTimeMillis;
  }

  public long getPrimTimeMillis() {

    return primTimeMillis;
  }

  public void setPrimTimeMillis(@NotNull long primTimeMillis) {

    this.primTimeMillis = primTimeMillis;
  }

  @NotNull
  public ObjectProperty<Maze> mazeProperty() {

    return maze;
  }
}
