package mds.tp.api.hackr.tpApi.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifyMailClientConfig {

    @Value("${hunter.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor verifyMailApiKeyInterceptor() {
        return template -> template.uri(template.url() + "&api_key=" + this.apiKey);
    }
}

