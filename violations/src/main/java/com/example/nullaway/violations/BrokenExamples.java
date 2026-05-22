package com.example.nullaway.violations;

import java.util.ArrayList;
import java.util.List;
import org.jspecify.annotations.Nullable;

public final class BrokenExamples {
  public String dereferenceNullable(@Nullable String value) {
    return value.trim();
  }

  public String returnNullFromMarkedPackage() {
    return null;
  }

  public List<String> listWithNullElement() {
    List<String> names = new ArrayList<>();
    names.add(null);
    return names;
  }
}
