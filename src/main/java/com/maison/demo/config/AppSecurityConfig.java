package com.maison.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 // .headers().frameOptions().disable().and() // disable X-Frame-Options
//                 // By default, Spring Security enables CSRF protection for all the HTTP methods
//                 // which results in data change like POST, DELETE, UPDATE
//                 // .csrf().disable() // disable csrf
//                 .csrf().ignoringAntMatchers(
//                         "/saveMsg"
//                     // ,"/h2-console/**"
//                 ).ignoringAntMatchers("/users/**").and()
//                 .authorizeRequests()
//                 .mvcMatchers("/dashboard").authenticated()
//                 .mvcMatchers("/profiles").authenticated()
//                 .mvcMatchers("/messages").hasRole("ADMIN")
//                 .mvcMatchers("/home").permitAll()
//                 // .mvcMatchers(HttpMethod.GET, "/home").permitAll()
//                 .mvcMatchers("/holiodays/**").permitAll()
//                 .mvcMatchers("/contact").permitAll()
//                 .mvcMatchers("/saveMsg").permitAll()
//                 .mvcMatchers("/courses").permitAll()
//                 .mvcMatchers("/about").permitAll()
//                 .mvcMatchers("/login").permitAll()
//                 .mvcMatchers("/users/**").permitAll()
//                 // .anyRequest()
//                 // .permitAll() // Permit all requests inside web application
//                 // .denyAll() // Deny all requests inside web application
//                 // Specify login page
//                 .and().formLogin().loginPage("/login")
//                 .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
//                 .and()
//                 .logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
//                 // .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
//                 .and().httpBasic();
//     }
//
//     // @Override
//     // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     //     auth.inMemoryAuthentication()
//     //         .withUser("user").password("p12345678").roles("USER")
//     //         .and()
//     //         .withUser("admin").password("p12345678").roles("ADMIN")
//     //         .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//     // }
//
//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }

// @Configuration
// public class AppSecurityConfig {
//
//     /**
//      * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
//      * to move towards a component-based security configuration. It is recommended to create a bean
//      * of type SecurityFilterChain for security related configurations.
//      * @param http
//      * @return SecurityFilterChain
//      * @throws Exception
//      */
//     @Bean
//     SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//         http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/h2-console/**").and()
//             .authorizeRequests()
//             .mvcMatchers("/dashboard").authenticated()
//             .mvcMatchers("/profiles").authenticated()
//             .mvcMatchers("/messages").hasRole("ADMIN")
//             .mvcMatchers("/home").permitAll()
//             .mvcMatchers("/holidays/**").permitAll()
//             .mvcMatchers("/contact").permitAll()
//             .mvcMatchers("/saveMsg").permitAll()
//             .mvcMatchers("/courses").permitAll()
//             .mvcMatchers("/about").permitAll()
//             .mvcMatchers("/login").permitAll()
//             .and().formLogin().loginPage("/login")
//             .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
//             .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
//             .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
//             .and().httpBasic();
//
//         http.headers().frameOptions().disable();
//
//         return http.build();
//
//     }
//
//     @Bean
//     public InMemoryUserDetailsManager userDetailsService() {
//
//         UserDetails admin = User.withDefaultPasswordEncoder()
//                                 .username("user")
//                                 .password("12345")
//                                 .roles("USER")
//                                 .build();
//         UserDetails user = User.withDefaultPasswordEncoder()
//                                .username("admin")
//                                .password("54321")
//                                .roles("ADMIN")
//                                .build();
//         return new InMemoryUserDetailsManager(user, admin);
//     }
// }

@Configuration
public class AppSecurityConfig {

    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/users/**").and()
            .authorizeRequests()
            .mvcMatchers("/dashboard").authenticated()
            .mvcMatchers("/profiles").authenticated()
            .mvcMatchers("/messages").hasRole("ADMIN")
            .mvcMatchers("/admin/**").hasRole("ADMIN")
            .mvcMatchers("/home").permitAll()
            .mvcMatchers("/holidays/**").permitAll()
            .mvcMatchers("/contact").permitAll()
            .mvcMatchers("/saveMsg").permitAll()
            .mvcMatchers("/courses").permitAll()
            .mvcMatchers("/about").permitAll()
            .mvcMatchers("/login").permitAll()
            .mvcMatchers("/users/**").permitAll()
            .and().formLogin().loginPage("/login")
            .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
            .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
            .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
