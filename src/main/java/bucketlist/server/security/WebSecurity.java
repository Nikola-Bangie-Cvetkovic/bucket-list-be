package bucketlist.server.security;

import bucketlist.server.services.implementation.ClientServiceImpl;
import bucketlist.server.services.implementation.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static bucketlist.server.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private LoginServiceImpl loginServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ClientServiceImpl clientService;

    public WebSecurity(LoginServiceImpl loginServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder,
                       ClientServiceImpl clientService) {
        this.loginServiceImpl = loginServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.clientService = clientService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), clientService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

    //ovo je za CORS a ovako kako je napisano api je potpuno otovoren prema spolja...
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
