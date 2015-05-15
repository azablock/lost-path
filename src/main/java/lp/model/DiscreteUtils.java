package lp.model;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;

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
}
