package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        
        UserDetails user = User.builder()
            .username("recepcion_user")
            .password(passwordEncoder().encode("utp2026"))
            .roles("USER")
            .build();

        
        UserDetails admin = User.builder()
            .username("director_admin")
            .password(passwordEncoder().encode("utp2026"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        
                    .requestMatchers(HttpMethod.GET, "/api/productos").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/api/productos/**").hasRole("USER")
                        
                    .requestMatchers(HttpMethod.POST, "/api/productos").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/productos/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/productos/**").hasRole("ADMIN")
        );

        
        http.httpBasic(Customizer.withDefaults());

        
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}