package br.com.dbc.vemser.checkout.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests((authz) ->  authz
                        .antMatchers("/auth","/").permitAll()
                        .antMatchers("/pagamento/**").permitAll()
                        .antMatchers("/gerador/**").permitAll()
                        .antMatchers("/pedido/**").permitAll()
                        .antMatchers("/admin/alterar-senha/**").permitAll()
                        .antMatchers("/admin/enviar-email/**").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/produto/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/sobremesa/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/produto/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/lanche/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/combo/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/bebida/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/acompanhamento/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/sobremesa/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/produto/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/lanche/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/combo/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/bebida/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/acompanhamento/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/sobremesa/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/produto/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/lanche/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/combo/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/bebida/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/acompanhamento/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/sobremesa/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/produto/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/lanche/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/combo/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/bebida/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/acompanhamento/**").permitAll()
                        .anyRequest().denyAll()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
