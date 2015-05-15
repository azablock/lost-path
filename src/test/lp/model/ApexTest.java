package lp.model;

import org.junit.Test;

import static lp.model.DiscreteUtils.*;
import static org.fest.assertions.Assertions.assertThat;

public class ApexTest {

  @Test
  public void shouldMarkApexesAsEqual() throws Exception {

    //given
    Apex first = pos(0, 0);

    //when
    Apex second = pos(0, 0);

    //then
    assertThat(first.equals(second)).isTrue();
  }

  @Test
  public void shouldMarkApexesAsNotEqual() throws Exception {

    //given
    Apex first = pos(0, 0);

    //when
    Apex second = pos(10, 0);

    //then
    assertThat(first.equals(second)).isFalse();
  }
}