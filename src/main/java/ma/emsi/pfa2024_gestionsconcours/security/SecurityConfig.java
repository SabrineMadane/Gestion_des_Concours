package ma.emsi.pfa2024_gestionsconcours.security;

import ma.emsi.pfa2024_gestionsconcours.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests((requests) -> requests
                        .requestMatchers(
                                "/admin/**","/ajouterConcours","/supprimerConcours","/modifierConcours",
                                "/ajouterCandidat","/candidatList","/supprimerCandidat","/modifierCandidat"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "/user/**"
                        ).hasRole("USER")
                        .requestMatchers(
                                "/", "/webjars/**","/concoursList"
                        ).permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/redirectUser", true)
                        .permitAll()
                );
        return http.build();
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public InMemoryUserDetailsManager  inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("Sabrine").password(bCryptPasswordEncoder().encode("1234")).roles("USER","ADMIN").build(),
                User.withUsername("you").password(bCryptPasswordEncoder().encode("azerty")).roles("USER").build()
        );
    }
    @Controller
    public class RedirectController {
        @GetMapping("/redirectUser")
        public String redirectUser(Authentication authentication) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/indexAdmin";
            } else {
                return "redirect:/user/index";
            }
        }
}}
