package mds.tp.api.hackr.configuration.tokenProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@Configuration
public class ClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepositoryStandard(PasswordEncoder passwordEncoder) {

        RegisteredClient registeredClientStandard = RegisteredClient.withId("tokenStandard")
                .clientId("clientIdTpApiStandard")
                .clientSecret(passwordEncoder.encode("clientSecretTpApiStandard"))
                .clientName("Auth TP Api Standard")
                .scope("user.read")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        RegisteredClient registeredClientAdmin = RegisteredClient.withId("tokenAdmin")
                .clientId("clientIdTpApiAdmin")
                .clientSecret(passwordEncoder.encode("clientSecretTpApiAdmin"))
                .clientName("Auth TP Api Admin")
                .scope("admin.read")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        return new InMemoryRegisteredClientRepository(registeredClientStandard, registeredClientAdmin);
    }
}
