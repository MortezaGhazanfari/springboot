package de.allianz.springboot.config;

import de.allianz.springboot.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
      return http

                .authorizeHttpRequests((auth)->auth// hat ein Username und Password
                .requestMatchers("/todo").permitAll()
                .anyRequest().authenticated()) // hat die erforderliche Rechte
                .httpBasic()
                .and()
              .formLogin()
              .and()
              .csrf()
              .disable()
                .build();

    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = getSampleNormalUserDetails();
        UserDetails admin = getSampleAdminUserDetails();
        UserDetails analyst = getSampleAnalystUserDetails();
        return new InMemoryUserDetailsManager(user,admin,analyst);
    }

    public UserDetails getSampleAdminUserDetails(){
        String username="admin";
        String rawPassword="adminpass";
        String encodedPassowrd=passwordEncoder.encode(rawPassword);

        return User.builder()
                .username(username)
                .password(encodedPassowrd)
                .authorities(Role.ADMIN.getGrantedAutorities())
                .build();
    }
    public UserDetails getSampleNormalUserDetails(){
        String username="user";
        String rawPassword="userpass";
        String encodedPassowrd=passwordEncoder.encode(rawPassword);

        return User.builder()
                .username(username)
                .password(encodedPassowrd)
                .authorities(Role.USER.getGrantedAutorities())
                .build();
    }
    public UserDetails getSampleAnalystUserDetails(){
        String username="analyst";
        String rawPassword="analystpass";
        String encodedPassowrd=passwordEncoder.encode(rawPassword);

        return User.builder()
                .username(username)
                .password(encodedPassowrd)
                .authorities(Role.ANALYST.getGrantedAutorities())
                .build();
    }


}
