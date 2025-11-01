package com.spring_security.usap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Manam BCrypt algorithm vadutunnam
        return new BCryptPasswordEncoder();
    }

    // 2. UserDetailsService Bean
    @Bean
    public UserDetailsService userDetailsService() {

        // Manam oka custom user ni create chestunnam
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder().encode("pass")) // Password ni encode chesi istunnam
                .roles("ADMIN") // User ki ADMIN role istunnam
                .build();

        // Alage inkoka normal user ni create cheddam
        UserDetails normalUser = User.withUsername("user")
                .password(passwordEncoder().encode("pass123"))

//                PasswordEncoder encoder = new BCryptPasswordEncoder(); 😂will be outside only
//                .passwordEncoder(encoder::encode)
//                .password("plainTextPassword")  // Will be encoded

                .roles("USER")
                .build();

        // Ee users ni in-memory lo store chestunnam
        // Real-time lo ikkada DB nunchi teche logic rastam
        return new InMemoryUserDetailsManager(adminUser, normalUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        // Rule 1: /admin ane URL ni kevalam 'ADMIN' role unnavalle chudali
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // Rule 2: /user ane URL ni 'ADMIN' or 'USER' evaraina chudochu
                        .requestMatchers("/user").hasAnyRole("ADMIN", "USER")
                        // Rule 3: Home page (/) ni evaraina chudochu (login avvakapoina)
                        .requestMatchers("/").permitAll()
                        // Rule 4: Migilina ye request ayina (e.g., /contact) login ayi undali
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults()); // Manaki aa default login page kavali

        return http.build();
    }

}
