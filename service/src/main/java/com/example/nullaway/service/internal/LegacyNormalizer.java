package com.example.nullaway.service.internal;

public final class LegacyNormalizer {
  public String trimToDefault(String value, String fallback) {
    if (value == null || value.isBlank()) {
      return fallback;
    }
    return value.trim();
  }

  public String returnsNullWhenBlank(String value) {
    if (value == null || value.isBlank()) {
      return null;
    }
    return value.trim();
  }
}
