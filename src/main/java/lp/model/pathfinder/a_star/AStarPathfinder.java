package lp.model.pathfinder.a_star;

import lp.model.maze.Maze;
import lp.model.pathfinder.Pathfinder;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.google.common.collect.Collections2.filter;
import static lp.model.DiscreteUtils.pos;

@Component
@Primary
public class AStarPathfinder implements Pathfinder {

  @NotNull
  private final AStarGrid aStarGrid;

  @NotNull
  private final Apex start;

  @NotNull
  private final Apex destination;

  public AStarPathfinder(@NotNull final Apex start, @NotNull final Apex destination, @NotNull final Maze maze) {

    this.start = start;
    this.destination = destination;
    aStarGrid = new AStarGrid(maze);
  }

  @NotNull
  @Override
  public LinkedList<Apex> calculatePath(@NotNull Apex start, @NotNull Apex destination) {

    return null;
  }

  private void addNeighbourNodes(@NotNull final AStarNode node) {

    List<Apex> possibleNeighbourNodePositions = new ArrayList<>();

    possibleNeighbourNodePositions.add(pos(node.getX() - 1, node.getY()));
    possibleNeighbourNodePositions.add(pos(node.getX() + 1, node.getY()));
    possibleNeighbourNodePositions.add(pos(node.getX(), node.getY() + 1));
    possibleNeighbourNodePositions.add(pos(node.getX(), node.getY() - 1));

    filter(possibleNeighbourNodePositions, apex -> !aStarGrid.getMaze().getBoundingBox().contains(apex)).forEach(
        possibleNeighbourNodePositions::remove);

    Map<Apex, AStarNode> neighboursToAdd = new HashMap<>();

    possibleNeighbourNodePositions.forEach(apex -> neighboursToAdd.put(apex, new AStarTile(calculateHeuristicValue(apex), apex)));

    aStarGrid.addToGrid(neighboursToAdd);
  }

  @NotNull
  private Integer calculateHeuristicValue(@NotNull final Apex nodePosition) {

    return Math.abs(nodePosition.getX() - destination.getX()) +
           Math.abs(nodePosition.getY() - destination.getY());
  }
}
