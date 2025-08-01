package com.andre.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * WebSecurityConfig is a configuration class for Spring Security.
 * It sets up CORS (Cross-Origin Resource Sharing) and HTTP Basic authentication.
 * This configuration allows requests from specific origins and methods.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final String[] ALLOWED_HEADERS = {
            "Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"

    };

    @Bean
    public SecurityFilterChain myOtherFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors
                        .configurationSource(myWebsiteConfigurationSource())
                )
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    UrlBasedCorsConfigurationSource myWebsiteConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
