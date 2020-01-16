package com.task.phones.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fono")
public class FonoApiProperties {

    private Fono fono;

    public FonoApiProperties() {
        this.fono = new Fono();
    }

    @Data
    public static class Fono {
        private String token;
    }
}
