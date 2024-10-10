package mds.tp.api.hackr.tpApi.configuration.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;


@Configuration
public class ProviderConfig {

    @Bean
    public AuthorizationServerSettings providerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://auth-server:8081/resource-server")
                .build();
    }
}

