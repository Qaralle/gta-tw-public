package itmo.p33101.gtav_territorywar.config;

import itmo.p33101.gtav_territorywar.security.jwt.JwtTokenFilter;
import itmo.p33101.gtav_territorywar.security.jwt.JwtTokenProvider;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    private static final String LOGIN_ENDPOINT = "/api/auth/**";
    private static final String LOGIN_PAGE = "/application";
    private static final String REFRESH_ENDPOINT = "/api/refresh/**";
    private static final String STATIC_CONTENT = "/static/**";
    private static final String SOURSES_CONTENT = "/sourses/**";



    public SecurityConfig(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() //ДЛЯ ПОСТМЕНА
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(LOGIN_PAGE).permitAll()
                .antMatchers("/api/app/notifications/**").permitAll()
                .antMatchers("/api/app/gangs").permitAll()
                .antMatchers(STATIC_CONTENT).permitAll()
                .antMatchers(SOURSES_CONTENT).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider, userService), UsernamePasswordAuthenticationFilter.class);


    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(REFRESH_ENDPOINT);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtTokenProvider.getUserDetailsService());
    }
}
