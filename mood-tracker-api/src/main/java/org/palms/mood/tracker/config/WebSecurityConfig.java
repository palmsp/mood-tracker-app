package org.palms.mood.tracker.config;

import org.palms.mood.tracker.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * @param auth {@link AuthenticationManagerBuilder}
     * @throws Exception error
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return {@link AuthenticationManager}
     * @throws Exception error
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param httpSecurity {@link HttpSecurity}
     * @throws Exception error
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                .cors().and()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/auth", "/auth/register").permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
        return source;
    }
}
