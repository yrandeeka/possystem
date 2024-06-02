package com.ijse.possystem.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ijse.possystem.security.jwt.AuthEntryPoint;
import com.ijse.possystem.security.jwt.AuthTokenFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPoint unAuthorizedHandler;

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//password hashing -- > password convert into non human readable.
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
        .csrf(csrf->csrf.disable())
        .exceptionHandling(exception->exception.authenticationEntryPoint(unAuthorizedHandler))//auth entry point
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//state management-statelss
        .authorizeHttpRequests(auth->auth
        .requestMatchers("/auth/***").permitAll()//if has this path no need authorization
        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());//set the authentication provider
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);//security filter authTokenFilter.java

        return http.build();
    }
}
