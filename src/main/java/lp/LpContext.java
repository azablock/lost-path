package lp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static lp.LpUIState.MENU;

@Component
public class LpContext {

  @NotNull
  private ObjectProperty<LpUIState> lpUIState;

  public LpContext() {

    lpUIState = new SimpleObjectProperty<>(MENU);
  }

  @NotNull
  public ObjectProperty<LpUIState> lpUIStateProperty() {

    return lpUIState;
  }
}
