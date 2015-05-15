package lp.error;

import org.jetbrains.annotations.NotNull;

public class LpDataException extends RuntimeException {

  public LpDataException(@NotNull String message) {

    super(message);
  }
}
