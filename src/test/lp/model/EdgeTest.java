package lp.model;

import lp.error.LpDataException;
import org.junit.Test;

import static lp.model.DiscreteUtils.pos;
import static lp.model.Edge.newInstance;
import static org.fest.assertions.Assertions.assertThat;

public class EdgeTest {

  @Test
  public void shouldMarkEdgesAsEqual() throws Exception {

    //given
    Edge firstEdge = newInstance(pos(0, 0), pos(1, 0));

    //when
    Edge secondEdge = newInstance(pos(1, 0), pos(0, 0));

    //then
    assertThat(firstEdge.equals(secondEdge)).isTrue();
  }

  @Test(expected = LpDataException.class)
  public void shouldThrowExceptionCausedByNonAdjacentCoordinates() throws Exception {

    //given
    Edge firstEdge = newInstance(pos(0, 0), pos(1, 0));

    //when
    Edge secondEdge = newInstance(pos(0, 0), pos(11, 0));

    //then
    //LpDataException thrown
  }
}