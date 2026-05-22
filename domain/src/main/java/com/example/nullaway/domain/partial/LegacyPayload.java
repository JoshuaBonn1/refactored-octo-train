package com.example.nullaway.domain.partial;

public final class LegacyPayload {
  private final String value;

  private LegacyPayload(String value) {
    this.value = value;
  }

  public static LegacyPayload fromNullable(String value) {
    return new LegacyPayload(value == null ? "legacy-missing" : value);
  }

  public String rawValueOrNull(boolean includeValue) {
    return includeValue ? value : null;
  }
}
