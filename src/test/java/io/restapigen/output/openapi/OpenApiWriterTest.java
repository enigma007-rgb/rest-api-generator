package io.restapigen.output.openapi;

import io.restapigen.domain.ApiSpecification;
import io.restapigen.domain.ApiSpec;
import io.restapigen.domain.EntityDefinition;
import io.restapigen.domain.EntitySpec;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenApiWriterTest {

    @Test
    void rendersCrudPathsForEntities() {
        ApiSpecification specification = new ApiSpecification(
                "users-api",
                "com.example.generated",
                List.of(new EntityDefinition(
                        new EntitySpec("User", "users", "Long", List.of()),
                        new ApiSpec("/api/users", true, true, true),
                        List.of()
                )),
                List.of()
        );

        String yaml = OpenApiWriter.write(specification);

        assertTrue(yaml.contains("openapi: 3.0.1"));
        assertTrue(yaml.contains("/api/users:"));
        assertTrue(yaml.contains("/api/users/{id}:"));
        assertTrue(yaml.contains("summary: Create User"));
    }
}
