package lp.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lp.model.position.Apex;
import lp.model.position.HasApexPosition;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static lp.model.DiscreteUtils.*;

public class UserActor implements HasApexPosition {

  @NotNull
  private final ObjectProperty<Apex> apexPosition;

  public UserActor() {

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

  @Override
  public String toString() {

    return String.format("UserActor{apexPosition=%s}", apexPosition);
  }

}
