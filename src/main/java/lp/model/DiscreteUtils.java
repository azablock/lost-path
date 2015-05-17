package lp.model;

import lp.model.position.Apex;
import lp.model.position.Edge;
import org.jetbrains.annotations.NotNull;

import static lp.model.position.Edge.newInstance;

public class DiscreteUtils {

  private DiscreteUtils() {

  }

  @NotNull
  public static Apex pos(@NotNull Integer x, @NotNull Integer y) {

    return new Apex(x, y);
  }

  @NotNull
  public static Apex translated(Apex apex, @NotNull Integer u, @NotNull Integer v) {

    return pos(apex.getX() + u, apex.getY() + v);
  }

  public static Apex north(Apex coordinates) {

    return pos(coordinates.getX(), coordinates.getY() - 1);
  }

  public static Apex east(Apex coordinates) {

    return pos(coordinates.getX() + 1, coordinates.getY());
  }

  public static Apex south(Apex coordinates) {

    return pos(coordinates.getX(), coordinates.getY() + 1);
  }

  public static Apex west(Apex coordinates) {

    return pos(coordinates.getX() - 1, coordinates.getY());
  }

  public static Edge withNorthOf(Apex coordinates) {

    return newInstance(coordinates, north(coordinates));
  }

  public static Edge withEastOf(Apex coordinates) {

    return newInstance(coordinates, east(coordinates));
  }

  public static Edge withSouthOf(Apex coordinates) {

    return newInstance(coordinates, south(coordinates));
  }

  public static Edge withWestOf(Apex coordinates) {

    return newInstance(coordinates, west(coordinates));
  }
}
