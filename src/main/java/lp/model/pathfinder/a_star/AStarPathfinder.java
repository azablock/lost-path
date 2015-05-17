package lp.model.pathfinder.a_star;

import lp.LpContext;
import lp.error.LpDataException;
import lp.model.maze.Maze;
import lp.model.pathfinder.Pathfinder;
import lp.model.position.Apex;
import lp.model.position.Edge;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.google.common.collect.Collections2.filter;
import static lp.model.DiscreteUtils.pos;
import static lp.model.pathfinder.a_star.AStarConstants.VER_HOR_MOVEMENT_COST;

@Component
@Primary
public class AStarPathfinder implements Pathfinder {

  @Autowired
  private AStarNodeFactory aStarNodeFactory;

  @Autowired
  private LpContext lpContext;

  private AStarGrid aStarGrid;

  private Apex destination;

  private AStarNode currentNode;

  @NotNull
  @Override
  public LinkedList<Apex> calculatePath(@NotNull final Apex start,
                                        @NotNull final Apex destination,
                                        @NotNull final Maze maze) throws LpDataException {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    aStarGrid = new AStarGrid(maze);
    this.destination = destination;

    if (start.equals(destination))
      throw new LpDataException("start and destination are at the same position");

    currentNode = aStarNodeFactory.newNode(start, destination, null);
    aStarGrid.markAsOpen(currentNode);
    addNeighbourNodes(currentNode, getNeighbourPositions(currentNode.getApexPosition()));
    aStarGrid.markAsClosed(currentNode);

    while (!aStarGrid.getOpenNodes().containsKey(destination)) {

      currentNode = getNodeWithSmallestOverallCost();
      aStarGrid.markAsClosed(currentNode);
      addNeighbourNodes(currentNode, getNeighbourPositions(currentNode.getApexPosition()));

      getNeighbourPositions(currentNode.getApexPosition()).forEach(apex -> {

        AStarNode neighbour = aStarGrid.getOpenNodes().get(apex);
        if (neighbour.getParentPosition() == null)
          neighbour.reparent(currentNode.getApexPosition());

        else if (mustBeReparented(apex))
          neighbour.reparent(currentNode.getApexPosition());
      });
    }

    currentNode = aStarGrid.getOpenNodes().get(destination);

    LinkedList<Apex> path = new LinkedList<>();
    Apex currentPositionInPath = currentNode.getApexPosition();

    while (!currentPositionInPath.equals(start)) {
      path.addFirst(currentPositionInPath);
      currentNode = aStarGrid.getClosedNodes().get(currentNode.getParentPosition());
      currentPositionInPath = currentNode.getApexPosition();
    }

    path.addFirst(currentPositionInPath);

    stopWatch.stop();
    lpContext.setAStarTimeMillis(stopWatch.getTotalTimeMillis());

    return path;
  }

  @NotNull
  private Set<Apex> getNeighbourPositions(@NotNull final Apex currentPosition) {

    Set<Apex> possibleNeighbourNodePositions = new CopyOnWriteArraySet<>();

    possibleNeighbourNodePositions.add(pos(currentPosition.getX() - 1, currentPosition.getY()));
    possibleNeighbourNodePositions.add(pos(currentPosition.getX() + 1, currentPosition.getY()));
    possibleNeighbourNodePositions.add(pos(currentPosition.getX(), currentPosition.getY() + 1));
    possibleNeighbourNodePositions.add(pos(currentPosition.getX(), currentPosition.getY() - 1));

    filter(possibleNeighbourNodePositions, apex -> !aStarGrid.getMaze().getBoundingBox().contains(apex) ||
                                                   aStarGrid.getMaze().getWallPositions().contains(Edge.newInstance(currentPosition, apex)) ||
                                                   (aStarGrid.getOpenNodes().containsKey(apex) || aStarGrid.getClosedNodes().containsKey(apex)))
        .forEach(possibleNeighbourNodePositions::remove);

    return possibleNeighbourNodePositions;
  }

  private void addNeighbourNodes(@NotNull final AStarNode node, @NotNull final Set<Apex> neighbourPositions) {

    neighbourPositions.forEach(apex -> {

      AStarNode newNode = aStarNodeFactory.newNode(apex, destination, node.getApexPosition());
      newNode.addMovementCost(VER_HOR_MOVEMENT_COST + node.getMovementCost());
      aStarGrid.getOpenNodes().put(apex, newNode);
    });
  }

  private boolean mustBeReparented(@NotNull final Apex nodePosition) {

    return (currentNode.getOverallCost() + VER_HOR_MOVEMENT_COST) <
           (aStarGrid.getOpenNodes().get(nodePosition).getOverallCost());
  }

  @NotNull
  private AStarNode getNodeWithSmallestOverallCost() {

    LinkedList<AStarNode> nodes = new LinkedList<>(aStarGrid.getOpenNodes().values());
    Collections.sort(nodes);

    return nodes.getFirst();
  }
}
