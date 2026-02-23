# Phase 6 Implementation

Phase 6 targets two mandatory production gaps:

1. Global error handling scaffold generation
2. Pagination/sorting/filtering scaffolding

## Delivered

- New plugin: `error-handling-generator`
  - `ErrorResponse` record
  - `ResourceNotFoundException`
  - `GlobalExceptionHandler` with not-found and fallback handlers
  - file: `src/main/java/io/restapigen/plugins/ErrorHandlingGeneratorPlugin.java`
- Plugin registered and enabled by default:
  - `src/main/java/io/restapigen/plugins/BuiltInPlugins.java`
  - `src/main/java/io/restapigen/core/config/GenerationConfig.java`
- Repository scaffolding now includes list method with:
  - pagination (`page`, `size`)
  - sorting (`sortBy`, `sortDir`)
  - simple text filtering (`filter`)
  - template: `src/main/resources/templates/spring-boot-3-standard/repository.java.tpl`
- Service and controller scaffolding now pass pagination/sorting/filtering args through:
  - templates:
    - `src/main/resources/templates/spring-boot-3-standard/service.java.tpl`
    - `src/main/resources/templates/spring-boot-3-standard/controller.java.tpl`

## Tests

- `src/test/java/io/restapigen/codegen/CodeGeneratorTest.java` now verifies:
  - generated error-handling files exist
  - pagination/sorting/filtering method signatures exist in generated controller/service/repository
