package com.cloudhebat.cobaspringotel;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtelLoggingConfig {

    private final OpenTelemetry openTelemetry;

    public OtelLoggingConfig(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }

    @PostConstruct
    public void setup() {
        OpenTelemetryAppender.install(openTelemetry);
    }
}