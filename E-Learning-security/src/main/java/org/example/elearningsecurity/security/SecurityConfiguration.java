package org.example.elearningsecurity.security;

import lombok.AllArgsConstructor;
import org.example.elearningsecurity.user.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, "/courses/**", "/lessons/**", "/resources/**", "/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/courses", "/lessons", "/resources")
                            .hasAuthority(RoleType.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.PUT, "/courses/**", "/lessons/**", "/resources/**")
                            .hasAuthority(RoleType.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.DELETE, "/courses/**", "/lessons/**", "/resources/**")
                            .hasAuthority(RoleType.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8005"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }

}
