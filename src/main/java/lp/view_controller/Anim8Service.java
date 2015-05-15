package lp.view_controller;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static javafx.animation.Interpolator.EASE_IN;
import static javafx.animation.Interpolator.EASE_OUT;

@Component
public class Anim8Service {

  private Double fadeDurationMillis = 200.0;

  private Duration fadeDuration;

  @NotNull
  public Transition fadeOut(@NotNull final Node node) {

    return fadeOut(node, false);
  }

  @NotNull
  public Transition fadeOut(@NotNull final Node node, final boolean nodeVisibleAfterFade) {

    FadeTransition fadeTransition = new FadeTransition(fadeDuration, node);

    fadeTransition.setInterpolator(EASE_OUT);
    fadeTransition.setFromValue(node.getOpacity());
    fadeTransition.setToValue(0.0);
    fadeTransition.setOnFinished(actionEvent -> node.setVisible(nodeVisibleAfterFade));

    return fadeTransition;
  }

  @NotNull
  public Transition fadeIn(@NotNull Node node) {

    return fadeIn(node, 1.0);
  }

  @NotNull
  public Transition fadeIn(@NotNull Node node, @NotNull Double nodeOpacityAfterFade) {

    FadeTransition fadeTransition = new FadeTransition(fadeDuration, node);

    fadeTransition.setInterpolator(EASE_IN);
    fadeTransition.setFromValue(node.getOpacity());
    fadeTransition.setToValue(nodeOpacityAfterFade);

    if (!node.isVisible())
      node.setVisible(true);

    return fadeTransition;
  }

  @NotNull
  public Transition fadeChainSwap(@NotNull Node nodeToHide, @NotNull Node nodeToReveal) {

    Transition fadeOut = fadeOut(nodeToHide);
    Transition fadeIn = fadeIn(nodeToReveal);

    return new SequentialTransition(fadeOut, fadeIn);
  }

  @PostConstruct
  private void init() {

    fadeDuration = new Duration(fadeDurationMillis);
  }
}
