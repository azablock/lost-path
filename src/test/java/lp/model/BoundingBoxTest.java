package lp.model;

import lp.error.LpDataException;
import lp.model.bounding_box.BoundingBox;
import lp.model.position.Apex;
import org.junit.Test;

import static lp.model.DiscreteUtils.pos;
import static lp.model.bounding_box.BoundingBox.newInstance;
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
    Apex apex = pos(4, 4);

    // when
    Boolean apexContained = bigBoundingBox.contains(apex);

    // then
    assertThat(apexContained).isTrue();
  }

  @Test
  public void shouldReturnFalseFromContainsWhenCoordinatesNotContained() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    Apex apex = pos(14, 4);

    // when
    Boolean apexContained = bigBoundingBox.contains(apex);

    // then
    assertThat(apexContained).isFalse();
  }

  @Test
  public void shouldReturnTrueFromContainsWhenCoordinatesAreOnBoxEdge() throws Exception {

    // given
    BoundingBox bigBoundingBox = newInstance(pos(1, 1), pos(10, 10));
    Apex apex = pos(9, 4);

    // when
    Boolean apexContained = bigBoundingBox.contains(apex);

    // then
    assertThat(apexContained).isTrue();
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

  @Test
  public void shouldHaveProperBoundingBoxArea() throws Exception {

    //given
    BoundingBox boundingBox = newInstance(2, 2);

    //when
    Integer boundingBoxArea = boundingBox.getHeight() * boundingBox.getWidth();

    //then
    assertThat(boundingBoxArea).isEqualTo(4);
  }

  @Test
  public void shouldContainPositionsInsideBoundingBox() throws Exception {

    //given
    BoundingBox boundingBox = newInstance(pos(0, 0), pos(2, 2));

    //when
    Apex apex1 = pos(0, 0);
    Apex apex2 = pos(1, 0);
    Apex apex3 = pos(0, 1);
    Apex apex4 = pos(1, 1);
    Apex apexOutside = pos(2, 1);

    //then

    assertThat(boundingBox.contains(apex1)).isTrue();
    assertThat(boundingBox.contains(apex2)).isTrue();
    assertThat(boundingBox.contains(apex3)).isTrue();
    assertThat(boundingBox.contains(apex4)).isTrue();
    assertThat(boundingBox.contains(apexOutside)).isFalse();
  }
}