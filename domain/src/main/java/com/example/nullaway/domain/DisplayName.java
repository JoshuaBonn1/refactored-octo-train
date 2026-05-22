package com.example.nullaway.domain;

import org.jspecify.annotations.Nullable;

public final class DisplayName {
  private final @Nullable String nickname;

  public DisplayName(@Nullable String nickname) {
    this.nickname = blankToNull(nickname);
  }

  public @Nullable String nicknameOrNull() {
    return nickname;
  }

  private static @Nullable String blankToNull(@Nullable String value) {
    if (value == null || value.isBlank()) {
      return null;
    }
    return value.trim();
  }
}
