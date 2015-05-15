package lp.model.maze;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public interface MazeFactory {

  @NotNull
  Maze newMaze();
}
