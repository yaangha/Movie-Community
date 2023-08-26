package perproject.moviecommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * 암호화 알고리즘 객체 -> Spring Security에서는 비밀번호를 반드시 암호화해야 함
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/homepage")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/login");

        http.authorizeRequests().antMatchers("/join", "/login", "/homepage", "/detail").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
