package com.example.nullaway.app.experiments.marked;

import java.util.Locale;
import org.jspecify.annotations.Nullable;

public final class MarkedExperiment {
  public String describe(@Nullable String value) {
    if (value == null) {
      return "missing";
    }
    return value.toUpperCase(Locale.ROOT);
  }
}
