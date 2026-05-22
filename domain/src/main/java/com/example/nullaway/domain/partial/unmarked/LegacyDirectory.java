package com.example.nullaway.domain.partial.unmarked;

import com.example.nullaway.domain.DisplayName;

public final class LegacyDirectory {
  public String findNickname(String userId) {
    if (userId.startsWith("known-")) {
      return new DisplayName("Legacy " + userId.substring("known-".length()))
          .nicknameOrNull();
    }
    return null;
  }
}
