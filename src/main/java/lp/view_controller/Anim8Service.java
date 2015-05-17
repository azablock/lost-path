package lp.view_controller;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import lp.model.position.Apex;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.function.Consumer;

import static javafx.animation.Interpolator.EASE_IN;
import static javafx.animation.Interpolator.EASE_OUT;

@Component
public class Anim8Service {

  @Value("200.0")
  private Double fadeDurationMillis;

  @Value("70.0")
  private Double translateDurationMillis;

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
  public Transition pathTransition(@NotNull final Node node, @NotNull final LinkedList<Circle> shapePath) {

    PathTransition pathTransition = new PathTransition();

    Path translationPath = new Path();
    translationPath.getElements().add(new MoveTo(shapePath.getFirst().getCenterX(), shapePath.getFirst().getCenterY()));

    shapePath.forEach(shape -> translationPath.getElements().add(new LineTo(shape.getCenterX(), shape.getCenterY())));

    pathTransition.setDuration(Duration.millis(translateDurationMillis * shapePath.size()));
    pathTransition.setPath(translationPath);
    pathTransition.setNode(node);

    return pathTransition;
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
