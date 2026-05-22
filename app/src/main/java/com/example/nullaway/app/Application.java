package com.example.nullaway.app;

import com.example.nullaway.domain.DisplayName;
import com.example.nullaway.domain.EmailAddress;
import com.example.nullaway.domain.UserProfile;
import com.example.nullaway.domain.partial.LegacyPayload;
import com.example.nullaway.service.GreetingService;
import com.example.nullaway.service.UserDirectory;
import java.util.List;

public final class Application {
  private Application() {}

  public static void main(String[] args) {
    System.out.println(run());
  }

  public static String run() {
    UserProfile ada =
        new UserProfile(
            "known-ada", new DisplayName("Ada"), new EmailAddress("ada@example.test"));
    UserDirectory directory = new UserDirectory(List.of(ada, UserProfile.anonymous("guest")));
    GreetingService greetings = new GreetingService(directory);

    return String.join(
        System.lineSeparator(),
        greetings.greetingFor("known-ada"),
        greetings.greetingFor("missing"),
        greetings.legacyGreetingFor(ada),
        greetings.payloadSummary(LegacyPayload.fromNullable(null), false));
  }
}
