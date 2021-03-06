package lp.model.pathfinder.a_star;

import lp.error.LpDataException;
import lp.model.maze.Maze;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AStarGrid implements Iterable<AStarNode> {

  @NotNull
  private final Map<Apex, AStarNode> openNodes;

  @NotNull
  private final Map<Apex, AStarNode> closedNodes;

  @NotNull
  private final Maze maze;

  public AStarGrid(@NotNull final Maze maze) {

    this.maze = maze;
    openNodes = new HashMap<>();
    closedNodes = new HashMap<>();
  }

  public void markAsOpen(@NotNull final AStarNode node) {

    openNodes.put(node.getApexPosition(), node);
  }

  public void markAsClosed(@NotNull final AStarNode node) throws LpDataException {

    if (!openNodes.containsValue(node))
      throw new LpDataException("cannot mark node as closed, because node wasn't in openNodes collection");

    openNodes.remove(node.getApexPosition());
    closedNodes.put(node.getApexPosition(), node);
  }

  @NotNull
  public Maze getMaze() {

    return maze;
  }

  @NotNull
  public Map<Apex, AStarNode> getOpenNodes() {

    return openNodes;
  }

  @NotNull
  public Map<Apex, AStarNode> getClosedNodes() {

    return closedNodes;
  }

  @Override
  public Iterator<AStarNode> iterator() {

    return openNodes.values().iterator();
  }
}
