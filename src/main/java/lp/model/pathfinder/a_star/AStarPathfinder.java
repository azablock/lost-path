package lp.model.pathfinder.a_star;

import lp.error.LpDataException;
import lp.model.maze.Maze;
import lp.model.pathfinder.Pathfinder;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static lp.model.DiscreteUtils.pos;

@Component
@Primary
public class AStarPathfinder implements Pathfinder {

  @Autowired
  private AStarNodeFactory aStarNodeFactory;

  @NotNull
  private AStarGrid aStarGrid;

  @NotNull
  private Apex start;

  @NotNull
  private Apex destination;

  @NotNull
  @Override
  public LinkedList<Apex> calculatePath(@NotNull Apex start, @NotNull Apex destination, @NotNull final Maze maze) throws LpDataException {

    aStarGrid = new AStarGrid(maze);

    LinkedList<Apex> path = new LinkedList<>();
    path.addLast(start);

    if (start.equals(destination))
      throw new LpDataException("start and destination are at the same position");

    AStarNode startNode = aStarNodeFactory.newNode(start, destination, null);
    aStarGrid.markAsOpen(startNode);
    addNeighbourNodes(startNode);
    aStarGrid.markAsClosed(startNode);

    while (!aStarGrid.getOpenNodes().containsKey(destination)) {

      AStarNode withSmallestOverallCost = getNodeWithSmallestOverallCost();
      aStarGrid.markAsClosed(withSmallestOverallCost);
      addNeighbourNodes(withSmallestOverallCost);

    }

    //wypelniam path na koncu

    return path;
  }

  private void addNeighbourNodes(@NotNull final AStarNode node) {

    List<Apex> possibleNeighbourNodePositions = new ArrayList<>();

    possibleNeighbourNodePositions.add(pos(node.getX() - 1, node.getY()));
    possibleNeighbourNodePositions.add(pos(node.getX() + 1, node.getY()));
    possibleNeighbourNodePositions.add(pos(node.getX(), node.getY() + 1));
    possibleNeighbourNodePositions.add(pos(node.getX(), node.getY() - 1));

    filter(possibleNeighbourNodePositions, apex -> !aStarGrid.getMaze().getBoundingBox().contains(apex)).forEach(
        possibleNeighbourNodePositions::remove);

    filter(possibleNeighbourNodePositions, apex -> !aStarGrid.getOpenNodes().containsKey(apex) && !aStarGrid.getClosedNodes().containsKey(apex))
        .forEach(apex -> aStarGrid.markAsOpen(aStarNodeFactory.newNode(apex, destination, node.getApexPosition())));

    /**
     *@TODO reparent test i spojrzec na te 2 filtry
     */

  }

  @NotNull
  private AStarNode getNodeWithSmallestOverallCost() {

    LinkedList<AStarNode> nodes = new LinkedList<>(aStarGrid.getOpenNodes().values());
    nodes.sort(aStarGrid);

    return nodes.getFirst();
  }
}
