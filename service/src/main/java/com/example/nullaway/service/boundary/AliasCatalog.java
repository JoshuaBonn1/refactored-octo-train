package com.example.nullaway.service.boundary;

import com.example.nullaway.domain.UserProfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jspecify.annotations.Nullable;

public final class AliasCatalog {
  public List<@Nullable String> rawAliases(UserProfile profile) {
    List<@Nullable String> aliases = new ArrayList<>();
    aliases.add(profile.displayName().nicknameOrNull());
    aliases.add(profile.emailAddress().localPart());
    aliases.add(null);
    return Collections.unmodifiableList(aliases);
  }

  public List<String> resolvedAliases(UserProfile profile) {
    List<String> resolved = new ArrayList<>();
    for (@Nullable String alias : rawAliases(profile)) {
      if (alias != null && !alias.isBlank()) {
        resolved.add(alias.trim());
      }
    }
    return List.copyOf(resolved);
  }

  public String firstResolvedAlias(UserProfile profile) {
    List<String> aliases = resolvedAliases(profile);
    if (aliases.isEmpty()) {
      return profile.preferredName();
    }
    return aliases.get(0);
  }
}
