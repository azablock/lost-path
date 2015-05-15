package lp.model.position;

import org.jetbrains.annotations.NotNull;

public class Apex {

  @NotNull
  private final Integer x;

  @NotNull
  private final Integer y;

  public Apex(@NotNull final Integer x, @NotNull final Integer y) {

    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Apex apex = (Apex) o;

    return x.equals(apex.x) && y.equals(apex.y);
  }

  @Override
  public int hashCode() {

    int result = x.hashCode();
    result = 31 * result + y.hashCode();
    return result;
  }

  @NotNull
  public Integer getX() {

    return x;
  }

  @NotNull
  public Integer getY() {

    return y;
  }

  @Override
  public String toString() {

    return String.format("Apex{x=%d, y=%d}", x, y);
  }
}
