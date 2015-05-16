package lp.model.maze;

import lp.model.bounding_box.BoundingBox;
import lp.model.position.Edge;
import lp.model.bounding_box.HasBoundingBox;
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

  @NotNull
  public Set<Edge> getWallPositions() {

    return wallPositions;
  }

  @Override
  public String toString() {

    return String.format("Maze{boundingBox=%s, wallPositions=%s}", boundingBox, wallPositions);
  }
}
