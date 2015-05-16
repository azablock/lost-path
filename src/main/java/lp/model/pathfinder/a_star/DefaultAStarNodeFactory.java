package lp.model.pathfinder.a_star;

import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DefaultAStarNodeFactory implements AStarNodeFactory {

  @NotNull
  @Override
  public AStarNode newNode(@NotNull Apex nodePosition, @NotNull Apex destination, @Nullable Apex parentPosition) {

    return new AStarNode(calculateHeuristicValue(nodePosition, destination),
                         nodePosition, parentPosition);
  }

  @NotNull
  private Integer calculateHeuristicValue(@NotNull final Apex nodePosition, @NotNull final Apex destination) {

    return Math.abs(nodePosition.getX() - destination.getX()) +
           Math.abs(nodePosition.getY() - destination.getY());
  }

}
