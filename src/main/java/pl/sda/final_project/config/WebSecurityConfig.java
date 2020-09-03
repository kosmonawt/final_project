package pl.sda.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {//konfiguracja zabezpieczeń
        http.authorizeRequests()
                .antMatchers("/register","/register/*")// /register/something   -> wpusci; /register/something/somtehing   ->nie wpusci
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .anyRequest().authenticated()//anyRequest jest wskazaniem na wszystkie pozostałe zakresy URLi, authenticated mówi o tym, że użytkownik musi być zalogowany
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login-proces")// to jest url na który zostaną wysłane dane do logowania
                .failureUrl("/login?error=1")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout") //jeśli wyślemy request na ten url, zostaniemy wylogowani
                .logoutSuccessUrl("/login"); // na ten url zostaniemy przekierowani po wylogowaniu
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//konfiguracja odpytywania o haslo i role uzytkownika
        //tu definijemy źródła danych o uzytkownikach
        auth.inMemoryAuthentication()
                .withUser("admin@admin.pl")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN");
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select u.LOGIN, u.PASSWORD, 1 from USER u  where u.LOGIN = ?")
                .authoritiesByUsernameQuery("select u.LOGIN, R.ROLE_NAME, 1 from USER u \n" +
                        "join USER_ROLES UR on u.ID = UR.USER_ID\n" +
                        "join USER_ROLE R on R.ID = UR.ROLES_ID\n" +
                        "where u.LOGIN = ?\n")
                .dataSource(dataSource)  //podajemy, gdzie jest źródło danych (baza danych)
                .passwordEncoder(passwordEncoder);//sposob hashowania haseł
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
