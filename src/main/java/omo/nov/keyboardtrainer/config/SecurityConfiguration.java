package omo.nov.keyboardtrainer.config;


import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.exception.ForbiddenException;
import omo.nov.keyboardtrainer.jwt.JwtFilter;
import omo.nov.keyboardtrainer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    @Autowired
    private final JwtFilter jwtFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    public SecurityConfiguration(@Lazy JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    public static User getOwnSecurityInformation() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(cors -> cors.configurationSource(request -> {
                    if (request.getHeader("Postman-Token") != null) {
                        throw new ForbiddenException("Forbiddden");
                    }
                    System.out.println(request.getRemoteAddr());
//                    if (userRepository.existsByDeviceIpAndIsBannedTrue(request.getRemoteAddr())) {
//                        throw new ForbiddenException("Forbidden");
//                    }
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("https://yoshdasturchi.uz");
                    corsConfiguration.addAllowedMethod("*");
                    corsConfiguration.addAllowedHeader("*");
                    return corsConfiguration;
                })).
                csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/**", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html").permitAll().anyRequest().authenticated());
        httpSecurity.sessionManagement(httpsecure -> httpsecure.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}