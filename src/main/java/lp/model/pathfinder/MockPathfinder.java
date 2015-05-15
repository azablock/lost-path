package lp.model.pathfinder;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;

@Component
@Primary
public class MockPathfinder implements Pathfinder {

  @NotNull
  @Override
  public LinkedList<Apex> calculatePath(@NotNull final Apex start, @NotNull final Apex destination) {

    return new LinkedList<>(Arrays.asList(start, destination));
  }
}
