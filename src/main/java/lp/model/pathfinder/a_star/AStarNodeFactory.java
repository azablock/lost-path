package lp.model.pathfinder.a_star;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public interface AStarNodeFactory {

  @NotNull
  AStarNode newNode(@NotNull final Apex nodePosition,
                    @NotNull final Apex destination,
                    @Nullable Apex parentPosition);
}
