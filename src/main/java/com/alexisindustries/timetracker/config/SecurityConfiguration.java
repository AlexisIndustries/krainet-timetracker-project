package com.alexisindustries.timetracker.config;

import com.alexisindustries.timetracker.security.jwt.JwtRequestTokenFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtRequestTokenFilter jwtRequestTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/all").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/projects/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/projects/all").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/projects/save").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/projects/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/projects/{id}").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/records/{id}").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/records/all").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/records/save").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/records/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/records/{id}").hasAnyAuthority("ADMIN", "USER")

                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("v3/api-docs/**").permitAll()
                )
                .addFilterBefore(jwtRequestTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
