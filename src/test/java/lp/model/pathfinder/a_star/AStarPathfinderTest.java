package lp.model.pathfinder.a_star;

import lp.model.maze.Maze;
import lp.model.maze.MazeFactory;
import lp.model.maze.MockMazeFactory;
import lp.model.pathfinder.Pathfinder;
import lp.model.position.Apex;
import lp.test.LpSpringBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static lp.model.DiscreteUtils.pos;
import static lp.model.DiscreteUtils.translated;
import static org.fest.assertions.Assertions.assertThat;

public class AStarPathfinderTest extends LpSpringBaseTest {

  @Autowired
  private Pathfinder pathfinder;

  @Autowired
  private MockMazeFactory mockMazeFactory;

  @Test
  public void shouldGivePathWithOnlyStartAndDestination() throws Exception {

    //given
    Apex start = pos(1, 1);
    Apex destination = pos(2, 1);
    Maze maze = mockMazeFactory.newMaze();
    LinkedList<Apex> path = new LinkedList<>();

    //when
    path.addAll(pathfinder.calculatePath(start, destination, maze));

    //then
    assertThat(path.size()).isEqualTo(2);
  }

  @Test
  public void shouldGiveShortestPath1() throws Exception {

    //given
    Apex start = pos(1, 1);
    Apex destination = pos(3, 1);
    Maze maze = mockMazeFactory.newMaze();
    LinkedList<Apex> path = new LinkedList<>();

    //when
    path.addAll(pathfinder.calculatePath(start, destination, maze));

    //then
    assertThat(path.size()).isEqualTo(3);
    assertThat(path.get(0)).isEqualTo(start);
    assertThat(path.get(1)).isEqualTo(translated(start, 1, 0));
    assertThat(path.get(2)).isEqualTo(destination);
  }

  @Test
  public void shouldGiveShortestPath2() throws Exception {

    //given
    Apex start = pos(1, 1);
    Apex destination = pos(10, 1);
    Maze maze = mockMazeFactory.newMaze();
    LinkedList<Apex> path = new LinkedList<>();

    //when
    path.addAll(pathfinder.calculatePath(start, destination, maze));

    //then
    assertThat(path.size()).isEqualTo(10);
    assertThat(path.get(0)).isEqualTo(start);
    assertThat(path.get(1)).isEqualTo(translated(start, 1, 0));
    assertThat(path.get(2)).isEqualTo(translated(start, 2, 0));
    assertThat(path.get(3)).isEqualTo(translated(start, 3, 0));
    assertThat(path.get(4)).isEqualTo(translated(start, 4, 0));
    assertThat(path.get(5)).isEqualTo(translated(start, 5, 0));
    assertThat(path.get(6)).isEqualTo(translated(start, 6, 0));
    assertThat(path.get(7)).isEqualTo(translated(start, 7, 0));
    assertThat(path.get(8)).isEqualTo(translated(start, 8, 0));
    assertThat(path.get(9)).isEqualTo(destination);
  }
}