package lp.model.pathfinder.a_star;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.lang.String.format;
import static lp.model.pathfinder.a_star.AStarConstants.*;

public class AStarTile implements AStarNode {

  @NotNull
  private final Integer heuristicValue;

  @NotNull
  private Integer movementCost;

  @Nullable
  private Apex parentPosition;

  @NotNull
  private final Apex nodePosition;

  public AStarTile(@NotNull Integer heuristicValue,
                   @NotNull final Apex nodePosition, @Nullable Apex parentPosition) {

    this.heuristicValue = heuristicValue;
    this.parentPosition = parentPosition;
    this.nodePosition = nodePosition;
    movementCost = VER_HOR_MOVEMENT_COST;
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

    return nodePosition;
  }

  @Override
  public String toString() {

    return format("AStarTile{heuristicValue=%d, movementCost=%d, parentPosition=%s, nodePosition=%s}",
                  heuristicValue, movementCost, parentPosition, nodePosition);
  }
}
