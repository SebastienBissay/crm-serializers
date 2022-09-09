package groupe3.crm.security;

import groupe3.crm.service.implementation.UserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Sebastien Bissay
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserServiceImplementation userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserServiceImplementation userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/login/**") 
                .permitAll()
                .antMatchers("/distributeur*")
                .hasRole("CUSTOMER")
                .antMatchers("/manage/products/**")
                .hasRole("PROVIDER")
                .antMatchers("/manage/**")
                .hasRole("ADMIN")
                .antMatchers("/manage/users/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(); // On active le formulaire de login
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true); // on configure notre propre page de login au lieu de la page par défaut

//        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }
}
