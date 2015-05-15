package lp.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lp.model.position.Apex;
import lp.model.position.HasApexPosition;
import org.jetbrains.annotations.NotNull;

import static lp.model.DiscreteUtils.*;

public class PlayerActor implements HasApexPosition {

  @NotNull
  private final ObjectProperty<Apex> apexPosition;

  public PlayerActor() {

    apexPosition = new SimpleObjectProperty<>(pos(0, 0));
  }

  @NotNull
  public ObjectProperty<Apex> apexPositionProperty() {

    return apexPosition;
  }

  @NotNull
  @Override
  public Apex getApexPosition() {

    return apexPosition.get();
  }
}
