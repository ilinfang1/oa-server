package org.oaoa.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // CORS专用过滤器链（最高优先级）
    @Bean
    @Order(0)
    public SecurityFilterChain corsFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .cors(withDefaults())  // 启用CORS配置
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().permitAll()  // 临时放行所有请求
                );
        return http.build();
    }

    // 主安全过滤器链
    @Bean
    @Order(1)
    public SecurityFilterChain mainFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/security/login",
                                "/res/verifycode",
                                "/api/public/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(withDefaults());
        return http.build();
    }
}