package com.example.nullaway.service.internal.deep;

import org.jspecify.annotations.Nullable;

public final class StrictNormalizer {
  private StrictNormalizer() {}

  public static String normalize(String value) {
    String trimmed = value.trim();
    return trimmed.isEmpty() ? "(blank)" : trimmed;
  }

  public static @Nullable String emptyToNull(String value) {
    String trimmed = value.trim();
    return trimmed.isEmpty() ? null : trimmed;
  }
}
