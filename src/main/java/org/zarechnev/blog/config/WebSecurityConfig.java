package org.zarechnev.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.zarechnev.blog.constant.ControllerPathURLs.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * TODO Необходимо логировать процесс входа и выхода пользователя на сайт
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            ROOT_URL_PATH,
                            ARTICLE_URL_PATH_PATTERN,
                            CSS_URL_PATH_PATTERN,
                            JS_URL_PATH_PATTERN
                    ).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage(LOGIN_URL_PATH)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl(LOGOUT_URL_PATH)
                    .logoutSuccessUrl(ROOT_URL_PATH)
                    .permitAll();

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    /**
     * TODO Сделать нормальную авторизацию с БД-бином для хранения информации о пользователях
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}