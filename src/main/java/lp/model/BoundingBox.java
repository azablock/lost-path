package lp.model;

import com.google.common.base.Objects;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import lp.error.LpDataException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;
import static lp.model.DiscreteUtils.pos;
import static lp.model.DiscreteUtils.translated;

public class BoundingBox implements Iterable<Apex> {

  @NotNull
  private final Apex topLeft;

  @NotNull
  private final Apex bottomRight;

  private BoundingBox(@NotNull Apex topLeft,
                      @NotNull Apex bottomRight) {

    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }

  public static BoundingBox newInstance(@NotNull Apex topLeft,
                                        @NotNull Apex bottomRight) {

    if (topLeft.getX() >= bottomRight.getX() || topLeft.getY() >= bottomRight.getY())
      throw new LpDataException(format("illegal coordinates, one or more coefficients of top left " +
                                       "corner's coordinates is greater than respective bottom " +
                                       "right corner's coefficient\ntop left: %s\nbottom right: %s",
                                       topLeft, bottomRight));

    return new BoundingBox(topLeft, bottomRight);
  }

  public static BoundingBox newInstance(@NotNull Integer width, @NotNull Integer height) {

    return newInstance(pos(0, 0), translated(pos(0, 0), width, height));
  }

  @NotNull
  public Apex getTopLeft() {

    return topLeft;
  }

  @NotNull
  public Apex getBottomRight() {

    return bottomRight;
  }

  @NotNull
  public Integer getWidth() {

    return bottomRight.getX() - topLeft.getX();
  }

  @NotNull
  public Integer getHeight() {

    return bottomRight.getY() - topLeft.getY();
  }

  @NotNull
  public Boolean contains(@NotNull Apex coordinates) {

    return horizontalRange().contains(coordinates.getX()) &&
           verticalRange().contains(coordinates.getY());
  }

  @NotNull
  public Boolean contains(@NotNull BoundingBox boundingBox) {

    return contains(boundingBox.getTopLeft()) && contains(boundingBox.getBottomRight());
  }

  @NotNull
  public Boolean contains(@NotNull HasBoundingBox hasBoundingBox) {

    return contains(hasBoundingBox.getBoundingBox());
  }

  @NotNull
  public Boolean intersects(@NotNull BoundingBox boundingBox) {

    return horizontalRange().isConnected(boundingBox.horizontalRange()) &&
           verticalRange().isConnected(boundingBox.verticalRange());
  }

  @NotNull
  public Boolean intersects(@NotNull HasBoundingBox hasBoundingBox) {

    return intersects(hasBoundingBox.getBoundingBox());
  }

  private Range<Integer> horizontalRange() {

    return Range.closed(topLeft.getX(), bottomRight.getX());
  }

  private Range<Integer> verticalRange() {

    return Range.closed(topLeft.getY(), bottomRight.getY());
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BoundingBox that = (BoundingBox) o;

    return Objects.equal(topLeft, that.topLeft) &&
           Objects.equal(bottomRight, that.bottomRight);
  }

  @Override
  public int hashCode() {

    return Objects.hashCode(topLeft, bottomRight);
  }

  @Override
  public Iterator<Apex> iterator() {

    Iterable<Integer> horizontal = ContiguousSet.create(horizontalRange(), DiscreteDomain.integers());
    Iterable<Integer> vertical = ContiguousSet.create(verticalRange(), DiscreteDomain.integers());

    List<Apex> containedCoordinates = new ArrayList<>();

    for (Integer x : horizontal)
      for (Integer y : vertical)
        containedCoordinates.add(pos(x, y));

    return containedCoordinates.iterator();
  }

  @Override
  public String toString() {

    return format("BoundingBox{topLeft=%s, bottomRight=%s}", topLeft, bottomRight);
  }

}
