package com.example.nullaway.domain.partial.marked;

import java.util.Objects;

public record MarkedToken(String value) {
  public MarkedToken {
    Objects.requireNonNull(value, "value");
  }

  public boolean hasPrefix(String prefix) {
    return value.startsWith(prefix);
  }
}
