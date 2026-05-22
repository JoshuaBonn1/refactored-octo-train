package com.example.nullaway.domain;

import java.util.Objects;

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
    return displayName.nickname().orElse(emailAddress.localPart());
  }
}
