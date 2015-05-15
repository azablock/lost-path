package lp.model.pathfinder.a_star;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.lang.String.format;

public class AStarTile implements AStarNode {

  @NotNull
  private final Integer heuristicValue;

  @NotNull
  private Integer movementCost;

  @Nullable
  private Apex parentPosition;

  @NotNull
  private final Apex position;

  public AStarTile(@NotNull Integer heuristicValue, @NotNull final Apex position) {

    this.heuristicValue = heuristicValue;
    this.position = position;
    parentPosition = null;
    movementCost = 0;
  }

  @NotNull
  public Apex getPosition() {

    return position;
  }

  @NotNull
  @Override
  public Integer getHeuristicValue() {

    return heuristicValue;
  }

  @NotNull
  @Override
  public Integer getMovementCost() {

    return movementCost;
  }

  @Nullable
  @Override
  public Apex getParentPosition() {

    return parentPosition;
  }

  @NotNull
  @Override
  public Apex getApexPosition() {

    return position;
  }

  @Override
  public String toString() {

    return format("AStarTile{heuristicValue=%d, movementCost=%d, parentPosition=%s, position=%s}",
                  heuristicValue, movementCost, parentPosition, position);
  }
}
