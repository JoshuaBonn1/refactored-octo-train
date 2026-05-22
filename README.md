# NullAway JSpecify Showcase

This repository is a Java 25, Maven-based multi-module sample for trying NullAway in JSpecify mode. The parent POM configures Error Prone with NullAway and uses `OnlyNullMarked=true`, so NullAway checks code that is explicitly marked with JSpecify `@NullMarked`.

## Modules

- `domain` - core records and value objects, with marked, unmarked, and missing package-info examples.
- `service` - service layer using domain types and nullable generic type-use examples.
- `app` - tiny executable entry point that wires the modules together.
- `violations` - opt-in module with intentional NullAway failures, enabled only by profile.

## Nullness layout

| Location | Marking style | Purpose |
| --- | --- | --- |
| `com.example.nullaway.domain` | `package-info.java` with `@NullMarked` | Normal checked domain code. |
| `com.example.nullaway.domain.partial` | No `package-info.java` | Missing package marking; not checked by `OnlyNullMarked`. |
| `com.example.nullaway.domain.partial.marked` | Nested `package-info.java` with `@NullMarked` | Demonstrates that subpackages can opt in independently. |
| `com.example.nullaway.domain.partial.unmarked` | `package-info.java` with `@NullUnmarked` | Explicit legacy/unannotated boundary. |
| `com.example.nullaway.service` | `package-info.java` with `@NullMarked` | Checked service code consuming nullable APIs safely. |
| `com.example.nullaway.service.internal` | No `package-info.java` | A legacy helper package intentionally left unchecked. |
| `com.example.nullaway.service.internal.deep` | Nested `package-info.java` with `@NullMarked` | A deeper package that opts back in. |
| `com.example.nullaway.app.experiments` | No `package-info.java` | Sandbox code that `RequireExplicitNullMarking` warns about. |
| `com.example.nullaway.app.experiments.marked` | Nested `package-info.java` with `@NullMarked` | Checked experiment package under an unchecked parent. |

## Build

Use a JDK 25 or newer toolchain. The project intentionally sets `maven.compiler.release` to `25` and the Enforcer plugin fails fast on older JDKs.

```bash
mvn verify
```

To see NullAway diagnostics, enable the profile that adds the intentional failures module:

```bash
mvn -Pnullaway-demo-failures compile
```

The default build should pass. The profile build should fail with NullAway errors such as dereferencing a `@Nullable` value and returning `null` from a non-null method.

## Key Maven settings

The parent `pom.xml` configures the compiler with:

- `-Xplugin:ErrorProne`
- `-Xep:NullAway:ERROR`
- `-Xep:RequireExplicitNullMarking:WARN`
- `-XepOpt:NullAway:OnlyNullMarked=true`
- `-XepOpt:NullAway:JSpecifyMode=true`
- `-XepOpt:NullAway:WarnOnGenericInferenceFailure=true`

The `.mvn/jvm.config` file includes the module exports/openings Error Prone needs when running on strongly encapsulated JDKs.
