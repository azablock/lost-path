package lp.model.position;

import org.jetbrains.annotations.NotNull;

public interface HasApexPosition extends HasPosition {

  @NotNull
  Apex getApexPosition();

  @NotNull
  default Integer getX() {

    return getApexPosition().getX();
  }

  @NotNull
  default Integer getY() {

    return getApexPosition().getY();
  }
}
