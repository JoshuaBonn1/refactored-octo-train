package com.example.nullaway.service.boundary;

import java.util.Objects;
import org.jspecify.annotations.Nullable;

public record LookupResult<T extends @Nullable Object>(String key, T value) {
  public LookupResult {
    Objects.requireNonNull(key, "key");
  }

  public boolean found() {
    return value != null;
  }

  public static <T> LookupResult<T> found(String key, T value) {
    return new LookupResult<>(key, Objects.requireNonNull(value, "value"));
  }

  public static LookupResult<@Nullable String> missingString(String key) {
    return new LookupResult<>(key, null);
  }
}
