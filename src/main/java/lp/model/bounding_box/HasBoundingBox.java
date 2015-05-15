package lp.model.bounding_box;

import lp.model.position.HasPosition;
import org.jetbrains.annotations.NotNull;

public interface HasBoundingBox extends HasPosition {

  @NotNull
  BoundingBox getBoundingBox();


}
