package lp.model;

import org.jetbrains.annotations.NotNull;

public interface HasBoundingBox extends HasPosition {

  @NotNull
  BoundingBox getBoundingBox();


}
