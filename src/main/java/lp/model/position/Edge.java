package lp.model.position;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import lp.error.LpDataException;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

public class Edge {

  @NotNull
  private final Apex first;

  @NotNull
  private final Apex second;

  private Edge(@NotNull final Apex first, @NotNull final Apex second) {

    this.first = first;
    this.second = second;
  }

  @NotNull
  public static Edge newInstance(@NotNull final Apex first, @NotNull final Apex second) {

    Integer xDistance = Math.abs(first.getX() - second.getX());
    Integer yDistance = Math.abs(first.getY() - second.getY());

    if (!Range.closed(0, 1).containsAll(ImmutableList.of(xDistance, yDistance)) || first.equals(second))
      throw new LpDataException(format("illegal pair of coordinates - pair: (first=%s, second=%s) is not adjacent", first, second));

    return new Edge(first, second);
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Edge edge = (Edge) o;

    return (first.equals(edge.first) && second.equals(edge.second)) ||
           (first.equals(edge.second) && second.equals(edge.first));
  }

  @Override
  public int hashCode() {

    return first.hashCode() + second.hashCode();
  }

  @NotNull
  public Apex getFirst() {

    return first;
  }

  @NotNull
  public Apex getSecond() {

    return second;
  }

  @Override
  public String toString() {

    return String.format("Edge{first=%s, second=%s}", first, second);
  }
}
