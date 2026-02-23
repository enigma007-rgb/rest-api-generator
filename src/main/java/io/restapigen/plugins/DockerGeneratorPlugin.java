package io.restapigen.plugins;

import io.restapigen.core.plugin.GeneratedFile;
import io.restapigen.core.plugin.GeneratorPlugin;
import io.restapigen.core.plugin.PluginContext;
import io.restapigen.domain.ApiSpecification;

import java.util.List;

public final class DockerGeneratorPlugin implements GeneratorPlugin {
    @Override
    public String getName() {
        return "docker-generator";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public List<GeneratedFile> generate(ApiSpecification specification, PluginContext context) {
        if (!context.config().features().dockerArtifacts()) {
            return List.of();
        }

        String dockerfile = "FROM eclipse-temurin:17-jre\n"
                + "WORKDIR /app\n"
                + "COPY build/libs/*.jar app.jar\n"
                + "EXPOSE 8080\n"
                + "ENTRYPOINT [\"java\",\"-jar\",\"/app/app.jar\"]\n";

        String compose = "version: '3.9'\n"
                + "services:\n"
                + "  " + specification.projectName + ":\n"
                + "    build: .\n"
                + "    ports:\n"
                + "      - \"8080:8080\"\n"
                + "    environment:\n"
                + "      SPRING_PROFILES_ACTIVE: docker\n";

        return List.of(
                new GeneratedFile("Dockerfile", dockerfile),
                new GeneratedFile("docker-compose.yml", compose)
        );
    }
}
