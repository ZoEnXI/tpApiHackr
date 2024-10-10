package mds.tp.api.hackr.tpApi.configuration.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
        RegisteredClient registeredClient = RegisteredClient.withId("client-id-tp-api")
                .clientId("clientIdTpApi")
                .clientSecret(passwordEncoder.encode("clientSecretTpApi"))
                .clientName("Auth TP Api")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("admin")
                .scope("standard")
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }
}
