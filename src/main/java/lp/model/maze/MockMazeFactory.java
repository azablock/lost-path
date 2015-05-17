package lp.model.maze;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static lp.model.bounding_box.BoundingBox.newInstance;
import static lp.model.DiscreteUtils.pos;

@Component
public class MockMazeFactory implements MazeFactory {

  @NotNull
  @Override
  public Maze newMaze() {

    return new Maze(newInstance(pos(1, 1), pos(22, 12)), new HashSet<>());
  }
}
