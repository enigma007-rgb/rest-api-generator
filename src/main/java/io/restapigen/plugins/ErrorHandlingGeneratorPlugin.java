package io.restapigen.plugins;

import io.restapigen.core.plugin.GeneratedFile;
import io.restapigen.core.plugin.GeneratorPlugin;
import io.restapigen.core.plugin.PluginContext;
import io.restapigen.domain.ApiSpecification;

import java.util.List;

public final class ErrorHandlingGeneratorPlugin implements GeneratorPlugin {
    @Override
    public String getName() {
        return "error-handling-generator";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public List<GeneratedFile> generate(ApiSpecification specification, PluginContext context) {
        String strategy = context.config().standards().errorHandling().strategy();
        if (strategy == null || "none".equalsIgnoreCase(strategy)) {
            return List.of();
        }

        String basePackage = context.config().project().basePackage();
        String javaBase = "src/main/java/" + context.basePackagePath();

        String errorResponse = "package " + basePackage + ".error;\n\n"
                + "import java.time.Instant;\n\n"
                + "public record ErrorResponse(String code, String message, Instant timestamp, String path) {}\n";

        String notFound = "package " + basePackage + ".error;\n\n"
                + "public class ResourceNotFoundException extends RuntimeException {\n"
                + "    public ResourceNotFoundException(String message) {\n"
                + "        super(message);\n"
                + "    }\n"
                + "}\n";

        String handler = "package " + basePackage + ".error;\n\n"
                + "import org.springframework.http.HttpStatus;\n"
                + "import org.springframework.http.ResponseEntity;\n"
                + "import org.springframework.web.bind.annotation.ControllerAdvice;\n"
                + "import org.springframework.web.bind.annotation.ExceptionHandler;\n"
                + "import org.springframework.web.context.request.WebRequest;\n\n"
                + "import java.time.Instant;\n\n"
                + "@ControllerAdvice\n"
                + "public class GlobalExceptionHandler {\n\n"
                + "    @ExceptionHandler(ResourceNotFoundException.class)\n"
                + "    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, WebRequest request) {\n"
                + "        ErrorResponse body = new ErrorResponse(\"NOT_FOUND\", ex.getMessage(), Instant.now(), request.getDescription(false));\n"
                + "        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);\n"
                + "    }\n\n"
                + "    @ExceptionHandler(Exception.class)\n"
                + "    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex, WebRequest request) {\n"
                + "        ErrorResponse body = new ErrorResponse(\"INTERNAL_ERROR\", ex.getMessage(), Instant.now(), request.getDescription(false));\n"
                + "        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);\n"
                + "    }\n"
                + "}\n";

        return List.of(
                new GeneratedFile(javaBase + "/error/ErrorResponse.java", errorResponse),
                new GeneratedFile(javaBase + "/error/ResourceNotFoundException.java", notFound),
                new GeneratedFile(javaBase + "/error/GlobalExceptionHandler.java", handler)
        );
    }
}
