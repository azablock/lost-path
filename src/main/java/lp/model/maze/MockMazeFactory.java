package lp.model.maze;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static lp.model.BoundingBox.newInstance;
import static lp.model.DiscreteUtils.pos;

@Component
@Primary
public class MockMazeFactory implements MazeFactory {

  @NotNull
  @Override
  public Maze newMaze() {

    return new Maze(newInstance(pos(0, 0), pos(10, 12)), new HashSet<>());
  }
}
