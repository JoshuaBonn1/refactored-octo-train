package com.example.nullaway.domain;

import java.util.Objects;

public record EmailAddress(String value) {
  public EmailAddress {
    Objects.requireNonNull(value, "value");
    if (!value.contains("@")) {
      throw new IllegalArgumentException("Email address must contain '@': " + value);
    }
  }

  public String localPart() {
    return value.substring(0, value.indexOf('@'));
  }
}
