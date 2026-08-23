package com.frauddetection.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Application entrypoint.
 *
 * <p>This service is the orchestration and business-logic layer of the fraud
 * detection platform: it persists transactions, calls the Python ML
 * microservice for predictions, derives confidence and risk level, creates
 * alerts, and stores analyst feedback. It contains no ML/preprocessing logic
 * of its own - that boundary is enforced structurally by simply never
 * depending on anything ML-related, only on the {@code MlPredictionClient}
 * (see the {@code client} package) that talks to the already-frozen Python
 * service over HTTP.
 *
 * <p>{@link ConfigurationPropertiesScan} auto-registers the typed
 * {@code @ConfigurationProperties} classes under {@code config.properties}
 * without needing an explicit {@code @EnableConfigurationProperties} list per
 * class.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
