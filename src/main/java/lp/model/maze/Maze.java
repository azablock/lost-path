package lp.model.maze;

import lp.model.BoundingBox;
import lp.model.Edge;
import lp.model.HasBoundingBox;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Maze implements HasBoundingBox {

  @NotNull
  private final BoundingBox boundingBox;

  @NotNull
  private final Set<Edge> wallPositions;

  public Maze(@NotNull final BoundingBox boundingBox, @NotNull final Set<Edge> wallPositions) {

    this.boundingBox = boundingBox;
    this.wallPositions = wallPositions;
  }

  @NotNull
  @Override
  public BoundingBox getBoundingBox() {

    return boundingBox;
  }

  @Override
  public String toString() {

    return String.format("Maze{boundingBox=%s, wallPositions=%s}", boundingBox, wallPositions);
  }
}
