package com.oasisvn.middleware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("api/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Cấu hình user mặc định để thực hiện intergration test
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("spring")
                .password(passwordEncoder().encode("secret"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
//                    .antMatchers(HttpMethod.POST, "/api/user/login").permitAll()
//                    .antMatchers(HttpMethod.GET, "/api/category/**").permitAll()
//                    .antMatchers(HttpMethod.GET, "/api/product/**").permitAll()
//                    .antMatchers(HttpMethod.GET, "/api/invoice/**").permitAll()
//                    .antMatchers(HttpMethod.POST, "/api/invoice").permitAll()
                    .antMatchers( "/api/user/login").permitAll()
                    .antMatchers("/api/category/**").permitAll()
                    .antMatchers("/api/product/**").permitAll()
                    .antMatchers("/api/invoice/**").permitAll()
                    .antMatchers( "/api/invoice").permitAll()
                    .antMatchers("/api/user").hasRole("SUDO")
                    .antMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new ApiAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }
}
