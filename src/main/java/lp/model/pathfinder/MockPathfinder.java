package lp.model.pathfinder;

import lp.model.maze.Maze;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;

@Component
public class MockPathfinder implements Pathfinder {

  @NotNull
  @Override
  public LinkedList<Apex> calculatePath(@NotNull Apex start, @NotNull Apex destination, @NotNull Maze maze) {

    return new LinkedList<>(Arrays.asList(start, destination));
  }
}
