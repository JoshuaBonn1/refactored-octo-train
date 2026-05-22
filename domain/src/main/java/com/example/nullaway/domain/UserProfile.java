package com.example.nullaway.domain;

import java.util.Objects;
import org.jspecify.annotations.Nullable;

public record UserProfile(String id, DisplayName displayName, EmailAddress emailAddress) {
  public UserProfile {
    Objects.requireNonNull(id, "id");
    Objects.requireNonNull(displayName, "displayName");
    Objects.requireNonNull(emailAddress, "emailAddress");
  }

  public static UserProfile anonymous(String id) {
    return new UserProfile(
        id, new DisplayName(null), new EmailAddress("anonymous@example.test"));
  }

  public String preferredName() {
    @Nullable String nickname = displayName.nicknameOrNull();
    if (nickname != null) {
      return nickname;
    }
    return emailAddress.localPart();
  }
}
