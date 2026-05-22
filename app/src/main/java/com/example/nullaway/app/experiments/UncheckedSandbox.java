package com.example.nullaway.app.experiments;

public final class UncheckedSandbox {
  public String acceptsAndReturnsNull(String input) {
    return input == null ? null : input.trim();
  }
}
