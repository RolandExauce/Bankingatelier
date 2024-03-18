package com.thymeleaf.bankingatelier.config;
import com.thymeleaf.bankingatelier.service.UserHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration // configuration class is consists of multiple beans
@EnableWebSecurity
public class SecurityConfig  {

    @Bean // simple component with a getter and setter
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserHandlerService userHandlerService(){
        return new UserHandlerService();
    }

    //disable csrf, in production you should not disable it though
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(
                        request -> request.requestMatchers("/home/registration", "/home/authenticate")
                                .permitAll()
                                .anyRequest().authenticated()  //all other paths will be authenticated
                ).formLogin(form -> form.loginPage("/home/authenticate").permitAll()
                        .defaultSuccessUrl("/home", true)).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder  authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userHandlerService());
        return authenticationManagerBuilder.build();
    }
}
