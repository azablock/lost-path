package lp.model.pathfinder.a_star;

import com.google.common.collect.ImmutableMap;
import lp.model.maze.Maze;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AStarGrid implements Iterable<AStarNode> {

  @NotNull
  private final Map<Apex, AStarNode> aStarNodes;

  @NotNull
  private final Maze maze;

  public AStarGrid(@NotNull final Maze maze) {

    this.maze = maze;
    aStarNodes = new HashMap<>();
  }

  public void addToGrid(@NotNull final Map<Apex, AStarNode> nodes) {

    aStarNodes.putAll(nodes);
  }

  @NotNull
  public Map<Apex, AStarNode> getAStarNodes() {

    return ImmutableMap.copyOf(aStarNodes);
  }

  @NotNull
  public Maze getMaze() {

    return maze;
  }

  @Override
  public Iterator<AStarNode> iterator() {

    return aStarNodes.values().iterator();
  }
}
