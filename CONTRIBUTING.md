# Contributing

Thanks for helping improve REST API Generator.

## Quick setup

1. Fork and clone the repo.
2. Use Java 17+.
3. Run tests:

```bash
./gradlew clean test
```

## Workflow

- Create branches from `develop`.
- Name branches like `feature/...`, `fix/...`, or `docs/...`.
- Open PRs into `develop`.
- When ready for release, open a PR from `develop` to `main`.

## Development tips

- Keep changes focused and small.
- Add or update tests when changing generation behavior.
- Avoid committing generated artifacts or build output.

## PR checklist

- Tests pass locally.
- Docs updated if behavior changes.
- No unresolved template placeholders in generated output.
