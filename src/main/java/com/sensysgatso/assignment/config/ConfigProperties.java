package com.sensysgatso.assignment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.math.BigDecimal;
import java.util.Map;

/**
Convenient way to provide custom properties.
@see <a href='https://docs.spring.io/spring-boot/docs/2.7.4/reference/html/features.html#features.external-config.typesafe-configuration-properties">Type-safe Configuration Properties</a>
 */
@ConstructorBinding
@ConfigurationProperties(prefix = "sensysgatso")
public class ConfigProperties {
    private final Violation violation;

    public ConfigProperties(Violation violation) {
        this.violation = violation;
    }

    public Violation getViolation() {
        return violation;
    }

    @SuppressWarnings("ClassCanBeRecord")
    public static class Violation {
        private final Map<String, BigDecimal> fees;

        public Violation(Map<String, BigDecimal> fees) {
            this.fees = fees;
        }

        public Map<String, BigDecimal> getFees() {
            return fees;
        }
    }
}
