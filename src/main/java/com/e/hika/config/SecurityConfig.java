package com.e.hika.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/test/**").permitAll()
                                    .requestMatchers("/teacher/**").permitAll()
                                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin(
                        form -> form.defaultSuccessUrl("/swagger-ui/index.html", true)
                )
                .csrf(csrf -> csrf.disable()
                )
//                .httpBasic(Customizer.withDefaults())
                .httpBasic(form -> form.disable())
        ;

        return http.build();
    }

}
