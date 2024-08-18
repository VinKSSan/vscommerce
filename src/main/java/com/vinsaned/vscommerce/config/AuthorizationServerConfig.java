@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {

    @Bean
    public OAuth2AuthorizationServerSettings authorizationServerSettings() {
        return OAuth2AuthorizationServerSettings.builder()
                .issuer("http://localhost:8080")
                .build();
    }

    @Bean
    public InMemoryRegisteredClientRepository registeredClientRepository() {
        // Configure your registered clients here
        return new InMemoryRegisteredClientRepository(/* Your registered clients */);
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator() {
        return new DefaultOAuth2TokenGenerator();
    }

    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new OAuth2AuthorizationService();
    }

    // Define any additional beans required for your configuration
}