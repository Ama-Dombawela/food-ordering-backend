package lk.ijse.food_ordering_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lk.ijse.food_ordering_backend.security.CustomUserDetailsService;
import lk.ijse.food_ordering_backend.security.JWTFilter;
import lombok.RequiredArgsConstructor;

// Configures Spring Security and JWT protection.
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTFilter jwtFilter;
    private final CustomUserDetailsService customUserDetailsService;

    // Define which routes are public and which are protected
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF - not needed for REST APIs
                .csrf(AbstractHttpConfigurer::disable)

                // Define route permissions
                .authorizeHttpRequests(auth -> auth
                        // Public routes - no token needed
                        .requestMatchers("/api/auth/**").permitAll()

                        // Admin only routes
                        .requestMatchers("/api/foods/**").hasRole("ADMIN")
                        .requestMatchers("/api/categories/**").hasRole("ADMIN")
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // Customer routes
                        .requestMatchers("/api/cart/**").hasRole("CUSTOMER")
                        .requestMatchers("/api/orders/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/payments/**").hasAnyRole("CUSTOMER", "ADMIN")

                        // All other requests need authentication
                        .anyRequest().authenticated())

                // Use stateless session - no session stored on server
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Set authentication provider
                .authenticationProvider(authenticationProvider())

                // Add JWT filter before default auth filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Password encoder - BCrypt hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication provider - connects UserDetailsService and PasswordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Authentication manager - used in AuthService for login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
