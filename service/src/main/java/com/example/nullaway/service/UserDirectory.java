package com.example.nullaway.service;

import com.example.nullaway.domain.UserProfile;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jspecify.annotations.Nullable;

public final class UserDirectory {
  private final Map<String, UserProfile> profiles;

  public UserDirectory(Collection<UserProfile> profiles) {
    this.profiles =
        profiles.stream()
            .collect(Collectors.toUnmodifiableMap(UserProfile::id, Function.identity()));
  }

  public @Nullable UserProfile find(String id) {
    Objects.requireNonNull(id, "id");
    return profiles.get(id);
  }

  public UserProfile require(String id) {
    @Nullable UserProfile profile = find(id);
    if (profile == null) {
      throw new IllegalArgumentException("Unknown user: " + id);
    }
    return profile;
  }
}
