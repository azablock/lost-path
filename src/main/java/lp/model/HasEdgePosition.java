package lp.model;

import org.jetbrains.annotations.NotNull;

public interface HasEdgePosition {

  @NotNull
  Edge getEdgePosition();

  @NotNull
  default Apex getFirst() {

    return getEdgePosition().getFirst();
  }

  @NotNull
  default Apex getSecond() {

    return getEdgePosition().getSecond();
  }
}
