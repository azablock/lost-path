package lp.ui;

import lp.model.maze.Maze;
import lp.model.maze.MazeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MazePaneController {

  @Autowired
  private MazeFactory mazeFactory;

  private Maze maze;


}
