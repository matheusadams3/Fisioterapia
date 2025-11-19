package com.adsimepac.fisioterapia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa CSRF apenas para facilitar o desenvolvimento
                .csrf().disable()
                .authorizeRequests()
                // Permite acesso a página de login e recursos estáticos
                .antMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                // Qualquer outra requisição precisa estar autenticada
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")               // Página de login customizada
                .loginProcessingUrl("/login")      // URL que processa o login
                .defaultSuccessUrl("/pacientes", false) // Redireciona para /pacientes após login
                .failureUrl("/login?error=true")  // Redireciona em caso de erro
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)       // Limpa sessão ao deslogar
                .deleteCookies("JSESSIONID")       // Remove cookies da sessão
                .logoutSuccessUrl("/login?logout=true")
                .permitAll();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("admin@clinica.com")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
