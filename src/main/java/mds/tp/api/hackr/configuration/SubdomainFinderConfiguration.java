package mds.tp.api.hackr.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubdomainFinderConfiguration {

    @Value("${securityTrail.apiKey}")
    private String apiKey;

    @Bean
    public RequestInterceptor verifyMailApiKeyInterceptor() {
        return template -> template.header("APIKEY", this.apiKey);
    }

}
