package lp.model.pathfinder.a_star;

import lp.model.position.Apex;
import lp.model.position.HasApexPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AStarNode extends HasApexPosition {

  @NotNull
  Integer getHeuristicValue();

  @NotNull
  Integer getMovementCost();

  @Nullable
  Apex getParentPosition();

  @NotNull
  default Integer getOverallCost() {

    return getMovementCost() + getHeuristicValue();
  }
}
