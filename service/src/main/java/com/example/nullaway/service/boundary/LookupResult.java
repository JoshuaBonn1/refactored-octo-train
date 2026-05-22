package com.example.nullaway.service.boundary;

import java.util.Objects;

public record LookupResult<T>(String key, T value) {
  public LookupResult {
    Objects.requireNonNull(key, "key");
    Objects.requireNonNull(value, "value");
  }

  public static <T> LookupResult<T> found(String key, T value) {
    return new LookupResult<>(key, value);
  }
}
