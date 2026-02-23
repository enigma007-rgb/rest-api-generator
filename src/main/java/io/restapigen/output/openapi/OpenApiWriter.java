package io.restapigen.output.openapi;

import io.restapigen.domain.ApiSpecification;
import io.restapigen.domain.EntityDefinition;

public final class OpenApiWriter {
    private OpenApiWriter() {
    }

    public static String write(ApiSpecification specification) {
        StringBuilder out = new StringBuilder();
        out.append("openapi: 3.0.1\ninfo:\n");
        out.append("  title: ").append(specification.projectName).append("\n");
        out.append("  version: 1.0.0\n");
        out.append("paths:\n");
        for (EntityDefinition definition : specification.entities) {
            out.append("  ").append(definition.api.resourcePath).append(":\n");
            out.append("    get:\n");
            out.append("      summary: List ").append(definition.entity.name).append("\n");
            out.append("    post:\n");
            out.append("      summary: Create ").append(definition.entity.name).append("\n");
            out.append("  ").append(definition.api.resourcePath).append("/{id}:\n");
            out.append("    get:\n");
            out.append("      summary: Get ").append(definition.entity.name).append(" by id\n");
            out.append("    put:\n");
            out.append("      summary: Update ").append(definition.entity.name).append("\n");
            out.append("    delete:\n");
            out.append("      summary: Delete ").append(definition.entity.name).append("\n");
        }
        return out.toString();
    }
}
