package lp.model.pathfinder;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public interface Pathfinder {

  @NotNull
  LinkedList<Apex> calculatePath(@NotNull final Apex start, @NotNull final Apex destination);
}
