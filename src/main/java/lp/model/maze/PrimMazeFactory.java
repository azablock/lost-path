package lp.model.maze;

import lp.LpContext;
import lp.model.bounding_box.BoundingBox;
import lp.model.position.Apex;
import lp.model.position.Edge;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.google.common.collect.Collections2.filter;
import static lp.model.DiscreteUtils.*;

@Component
@Primary
public class PrimMazeFactory implements MazeFactory {

  @Autowired
  private LpContext lpContext;

  private Set<Edge> wallGrid;

  private Set<Apex> usedNodePositions;

  private Integer mazeWidth;

  private Integer mazeHeight;

  @Value("20")
  private Integer minMazeSize;

  private Set<Edge> wallPositions;

  @NotNull
  @Override
  public Maze newMaze() {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    initializePrimGrid();
    Apex randomNotUsedNodePosition = pickRandomNotUsedNodePosition();
    addNeighbourWalls(randomNotUsedNodePosition);

    while (!wallPositions.isEmpty()) {

      Edge randomWallPosition = pickRandomWallPosition();
      Apex oppositeSideNodePosition = randomWallPosition.getSecond();

      if (!isNodeUsed(oppositeSideNodePosition)) {

        usedNodePositions.add(oppositeSideNodePosition);
        wallGrid.remove(randomWallPosition);

        addNeighbourWalls(oppositeSideNodePosition);
      } else
        wallPositions.remove(randomWallPosition);
    }

    stopWatch.stop();
    lpContext.setPrimTimeMillis(stopWatch.getTotalTimeMillis());

    return new Maze(BoundingBox.newInstance(mazeWidth, mazeHeight), wallGrid);
  }

  private void addNeighbourWalls(@NotNull final Apex nodePosition) {

    BoundingBox primBoundingBox = BoundingBox.newInstance(mazeWidth, mazeHeight);

    Set<Edge> possibleWallPositions = new CopyOnWriteArraySet<>();
    possibleWallPositions.add(withNorthOf(nodePosition));
    possibleWallPositions.add(withEastOf(nodePosition));
    possibleWallPositions.add(withSouthOf(nodePosition));
    possibleWallPositions.add(withWestOf(nodePosition));

    filter(possibleWallPositions, edge -> !primBoundingBox.contains(edge.getFirst()) || !primBoundingBox.contains(edge.getSecond())).forEach(
        possibleWallPositions::remove);

    wallPositions.addAll(possibleWallPositions);
  }

  @NotNull
  private Edge pickRandomWallPosition() {

    return new ArrayList<>(wallPositions).get(new Random().nextInt(wallPositions.size()));
  }

  @NotNull
  private Apex pickRandomNotUsedNodePosition() {

    return new ArrayList<>(wallGrid).get(new Random().nextInt(wallGrid.size())).getFirst();
  }

  private boolean isNodeUsed(@NotNull final Apex consideredNodePosition) {

    return usedNodePositions.contains(consideredNodePosition);
  }

  private void initializePrimGrid() {

    wallGrid = new HashSet<>();
    usedNodePositions = new HashSet<>();
    wallPositions = new HashSet<>();

    Random random = new Random();
    mazeWidth = random.nextInt(minMazeSize) + 60;
    mazeHeight = random.nextInt(minMazeSize) + 50;

    for (int y = 0; y < mazeHeight; y++)
      for (int x = 0; x < mazeWidth - 1; x++)
        wallGrid.add(Edge.newInstance(pos(x, y), pos(x + 1, y)));

    for (int y = 0; y < mazeHeight - 1; y++)
      for (int x = 0; x < mazeWidth; x++)
        wallGrid.add(Edge.newInstance(pos(x, y), pos(x, y + 1)));
  }
}
