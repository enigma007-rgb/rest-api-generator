package io.restapigen.plugins;

import io.restapigen.core.plugin.GeneratedFile;
import io.restapigen.core.plugin.GeneratorPlugin;
import io.restapigen.core.plugin.PluginContext;
import io.restapigen.domain.ApiSpecification;
import io.restapigen.output.openapi.OpenApiWriter;

import java.util.List;

public final class DocumentationGeneratorPlugin implements GeneratorPlugin {
    @Override
    public String getName() {
        return "documentation-generator";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public List<GeneratedFile> generate(ApiSpecification specification, PluginContext context) {
        if ("none".equalsIgnoreCase(context.config().standards().documentation().tool())) {
            return List.of();
        }
        return List.of(new GeneratedFile("src/main/resources/openapi.yaml", OpenApiWriter.write(specification)));
    }
}
