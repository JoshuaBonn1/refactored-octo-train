package com.example.nullaway.service;

import com.example.nullaway.domain.UserProfile;
import com.example.nullaway.domain.partial.LegacyPayload;
import com.example.nullaway.service.boundary.AliasCatalog;
import com.example.nullaway.service.internal.LegacyNormalizer;
import com.example.nullaway.service.internal.deep.StrictNormalizer;
import java.util.Objects;
import org.jspecify.annotations.Nullable;

public final class GreetingService {
  private final UserDirectory directory;
  private final AliasCatalog aliasCatalog;
  private final LegacyNormalizer legacyNormalizer;

  public GreetingService(UserDirectory directory) {
    this.directory = Objects.requireNonNull(directory, "directory");
    this.aliasCatalog = new AliasCatalog();
    this.legacyNormalizer = new LegacyNormalizer();
  }

  public String greetingFor(String id) {
    @Nullable UserProfile profile = directory.find(id);
    if (profile == null) {
      return "Hello, guest";
    }
    return "Hello, " + aliasCatalog.firstResolvedAlias(profile);
  }

  public String legacyGreetingFor(UserProfile profile) {
    String normalized =
        legacyNormalizer.trimToDefault(
            profile.displayName().nicknameOrNull(), profile.emailAddress().localPart());
    return "Hello, " + StrictNormalizer.normalize(normalized);
  }

  public String payloadSummary(LegacyPayload payload, boolean includeValue) {
    @Nullable String raw = payload.rawValueOrNull(includeValue);
    if (raw == null) {
      return "payload missing";
    }
    return StrictNormalizer.normalize(raw);
  }
}
