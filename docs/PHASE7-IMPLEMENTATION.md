# Phase 7 Implementation

Phase 7 focuses on two mandatory production gaps:

1. DTO mapper generation (MapStruct)
2. Spring Data pageable repository scaffolding

## Delivered

- New plugin: `mapper-generator`
  - Generates `${Entity}Mapper` interfaces with MapStruct annotations.
  - file: `src/main/java/io/restapigen/plugins/MapperGeneratorPlugin.java`
  - template: `src/main/resources/templates/spring-boot-3-standard/mapper.java.tpl`
- Mapper plugin registration and defaults:
  - `src/main/java/io/restapigen/plugins/BuiltInPlugins.java`
  - `src/main/java/io/restapigen/core/config/GenerationConfig.java`
- Repository scaffolding upgraded to Spring Data:
  - `JpaRepository<..., Long>`
  - `JpaSpecificationExecutor<...>`
  - template: `src/main/resources/templates/spring-boot-3-standard/repository.java.tpl`
- Service scaffolding upgraded:
  - uses mapper + repository
  - pageable/sort/filter flow with `PageRequest`, `Sort`, `Specification`
  - template: `src/main/resources/templates/spring-boot-3-standard/service.java.tpl`
- Controller scaffolding upgraded:
  - `@RestController`
  - base `@GetMapping` and `/search` endpoint for pagination/sorting/filter
  - template: `src/main/resources/templates/spring-boot-3-standard/controller.java.tpl`

## Tests

- `src/test/java/io/restapigen/codegen/CodeGeneratorTest.java` now verifies:
  - generated mapper file exists
  - mapper contains `@Mapper(componentModel = "spring")`
  - repository extends Spring Data interfaces
  - controller contains `/search` mapping
  - service contains mapper conversion calls
