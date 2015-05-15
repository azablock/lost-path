package lp.model;

import lp.error.LpDataException;
import org.junit.Test;

import static lp.model.BoundingBox.*;
import static lp.model.DiscreteUtils.pos;
import static org.fest.assertions.Assertions.assertThat;

public class BoundingBoxTest {

  @Test(expected = LpDataException.class)
  public void shouldThrowCtDataExceptionForTopLeftAfterBottomRight() throws Exception {

    // given
    Apex topLeft = pos(10, 10);
    Apex bottomRight = pos(5, 15);

    // when
    newInstance(topLeft, bottomRight);

    // then
    // CtDataException thrown
  }

  @Test(expected = LpDataException.class)
  public void shouldThrowCtDataExceptionForTopLeftBelowBottomRight() throws Exception {

    // given
    Apex topLeft = pos(5, 15);
    Apex bottomRight = pos(10, 10);

    // when
    newInstance(topLeft, bottomRight);

    // then
    // LpDataException thrown
  }

  @Test(expected = LpDataException.class)
  public void shouldThrowCtDataExceptionForEqualXs() throws Exception {

    // given
    Apex topLeft = pos(5, 5);
    Apex bottomRight = pos(5, 10);

    // when
    newInstance(topLeft, bottomRight);

    // then
    // LpDataException thrown
  }

  @Test(expected = LpDataException.class)
  public void shouldThrowCtDataExceptionForEqualYs() throws Exception {

    // given
    Apex topLeft = pos(5, 5);
    Apex bottomRight = pos(10, 5);

    // when
    newInstance(topLeft, bottomRight);

    // then
    // LpDataException thrown
  }

  @Test
  public void shouldReturnTrueFromContainsWhenCoordinatesContained() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    Apex coordinates = pos(4, 4);

    // when
    Boolean coordinatesContained = bigBoundingBox.contains(coordinates);

    // then
    assertThat(coordinatesContained).isTrue();
  }

  @Test
  public void shouldReturnFalseFromContainsWhenCoordinatesNotContained() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    Apex coordinates = pos(14, 4);

    // when
    Boolean coordinatesContained = bigBoundingBox.contains(coordinates);

    // then
    assertThat(coordinatesContained).isFalse();
  }

  @Test
  public void shouldReturnTrueFromContainsWhenCoordinatesAreOnBoxEdge() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    Apex coordinates = pos(10, 4);

    // when
    Boolean coordinatesContained = bigBoundingBox.contains(coordinates);

    // then
    assertThat(coordinatesContained).isTrue();
  }

  @Test
  public void shouldReturnTrueFromContainsWhenBoundingBoxIsContained() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    BoundingBox smallBoundingBox = newInstance(pos(2, 2), pos(4, 4));

    // when
    Boolean bigBoxContainsSmallBox = bigBoundingBox.contains(smallBoundingBox);

    // then
    assertThat(bigBoxContainsSmallBox).isTrue();
  }

  @Test
  public void shouldReturnFalseFromContainsWhenBoundingBoxIsIntersecting() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    BoundingBox smallBoundingBox = newInstance(pos(2, 2), pos(14, 4));

    // when
    Boolean bigBoxContainsSmallBox = bigBoundingBox.contains(smallBoundingBox);

    // then
    assertThat(bigBoxContainsSmallBox).isFalse();
  }

  @Test
  public void shouldReturnFalseFromContainsWhenBoundingBoxIsNotContained() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    BoundingBox smallBoundingBox = newInstance(pos(22, 22), pos(24, 24));

    // when
    Boolean bigBoxContainsSmallBox = bigBoundingBox.contains(smallBoundingBox);

    // then
    assertThat(bigBoxContainsSmallBox).isFalse();
  }

  @Test
  public void shouldReturnTrueFromIntersectWhenBoundingBoxIsIntersecting() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    BoundingBox smallBoundingBox = newInstance(pos(2, 2), pos(14, 4));

    // when
    Boolean bigBoxIntersectsSmallBox = bigBoundingBox.intersects(smallBoundingBox);

    // then
    assertThat(bigBoxIntersectsSmallBox).isTrue();
  }

  @Test
  public void shouldReturnTrueFromIntersectWhenBoundingBoxIsIntersectingWithoutContainedCorners() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(2, 4), pos(10, 10));
    BoundingBox smallBoundingBox = newInstance(pos(6, 2), pos(8, 12));

    // when
    Boolean bigBoxIntersectsSmallBox = bigBoundingBox.intersects(smallBoundingBox);

    // then
    assertThat(bigBoxIntersectsSmallBox).isTrue();
  }
}