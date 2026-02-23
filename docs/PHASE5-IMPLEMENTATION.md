# Phase 5 Implementation

Phase 5 adds advanced platform features focused on documentation export and container-ready output.

## Delivered

- OpenAPI-first output mode:
  - New command: `openapi --prompt ...` or `openapi --file ...`
  - CLI now prints OpenAPI YAML directly for a parsed specification
  - Implementation:
    - `src/main/java/io/restapigen/output/openapi/OpenApiWriter.java`
    - `src/main/java/io/restapigen/cli/Main.java`
- Shared OpenAPI rendering:
  - Documentation plugin now uses shared writer
  - Implementation:
    - `src/main/java/io/restapigen/plugins/DocumentationGeneratorPlugin.java`
- Docker artifact generation plugin:
  - Generates `Dockerfile` and `docker-compose.yml` in output ZIP
  - Controlled by config flag: `features.dockerArtifacts`
  - Implementation:
    - `src/main/java/io/restapigen/plugins/DockerGeneratorPlugin.java`
    - `src/main/java/io/restapigen/plugins/BuiltInPlugins.java`

## Config and schema updates

- Runtime config model includes:
  - `features.dockerArtifacts`
- Schema includes:
  - `schemas/rest-api-generator-config.schema.json`

## Tests

- ZIP generation test now asserts Docker artifacts:
  - `src/test/java/io/restapigen/codegen/CodeGeneratorTest.java`
- OpenAPI writer rendering test:
  - `src/test/java/io/restapigen/output/openapi/OpenApiWriterTest.java`
