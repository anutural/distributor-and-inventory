package com.am.reaprich.reaprichbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.am.reaprich.reaprichbackend.data.entities.auth.Permission.*;
import static com.am.reaprich.reaprichbackend.data.entities.auth.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/v1/auth/**","/v1/provider/")
                .permitAll()

                .antMatchers("/v1/self/**").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name())

                .antMatchers("/v1/user/**").hasAnyRole(ADMIN.name())
                .antMatchers("/v1/provider/**").hasAnyRole(ADMIN.name())
                .antMatchers("/v1/self/**").hasAnyRole(ADMIN.name())
                .antMatchers("v1/inventory/**").hasAnyRole((ADMIN.name()))

                .antMatchers(GET, "/v1/user/**").hasAnyAuthority(ADMIN_READ.name(), TD_READ.name())
                .antMatchers(POST, "/v1/user/**").hasAnyAuthority(ADMIN_CREATE.name())
                .antMatchers(PUT, "/v1/user/**").hasAnyAuthority(ADMIN_UPDATE.name())
                .antMatchers(DELETE, "/v1/user/**").hasAnyAuthority(ADMIN_DELETE.name())

                .antMatchers(GET, "/v1/provider/**").hasAnyAuthority(ADMIN_READ.name(), TD_READ.name())
                .antMatchers(POST, "/v1/provider/**").hasAnyAuthority(ADMIN_CREATE.name())
                .antMatchers(PUT, "/v1/provider/**").hasAnyAuthority(ADMIN_UPDATE.name())
                .antMatchers(DELETE, "/v1/provider/**").hasAnyAuthority(ADMIN_DELETE.name())

                .antMatchers(GET, "/v1/user/actor/outlet").hasAnyAuthority(TD_READ.name())
                .antMatchers(POST, "/v1/user/actor/outlet").hasAnyAuthority(TD_CREATE.name())
                .antMatchers(PUT, "/v1/user/actor/outlet").hasAnyAuthority(TD_UPDATE.name())
                .antMatchers(DELETE, "/v1/user/actor/outlet").hasAnyAuthority(TD_DELETE.name())

                .antMatchers(GET, "/v1/user/actor/customer").hasAnyAuthority(TD_READ.name(), SP_OUTLET_READ.name(), OUTLET_READ.name())
                .antMatchers(POST, "/v1/user/actor/customer").hasAnyAuthority(TD_CREATE.name(), SP_OUTLET_CREATE.name(), OUTLET_CREATE.name())
                .antMatchers(PUT, "/v1/user/actor/customer").hasAnyAuthority(TD_UPDATE.name(), SP_OUTLET_UPDATE.name(), OUTLET_UPDATE.name())
                .antMatchers(DELETE, "/v1/user/actor/customer").hasAnyAuthority(TD_DELETE.name(), SP_OUTLET_DELETE.name(), OUTLET_DELETE.name())

                .antMatchers(GET, "/v1/user/address").hasAnyAuthority(TD_READ.name(), SP_OUTLET_READ.name(), OUTLET_READ.name())
                .antMatchers(POST, "/v1/user/address").hasAnyAuthority(TD_CREATE.name(), SP_OUTLET_CREATE.name(), OUTLET_CREATE.name())
                .antMatchers(PUT, "/v1/user/address").hasAnyAuthority(TD_UPDATE.name(), SP_OUTLET_UPDATE.name(), OUTLET_UPDATE.name())
                .antMatchers(DELETE, "/v1/user/address").hasAnyAuthority(TD_DELETE.name(), SP_OUTLET_DELETE.name(), OUTLET_DELETE.name())

                .antMatchers(GET, "/v1/user/bankdetail").hasAnyAuthority(TD_READ.name(), SP_OUTLET_READ.name())
                .antMatchers(POST, "/v1/user/bankdetail").hasAnyAuthority(TD_CREATE.name(), SP_OUTLET_CREATE.name())
                .antMatchers(PUT, "/v1/user/bankdetail").hasAnyAuthority(TD_UPDATE.name(), SP_OUTLET_UPDATE.name())
                .antMatchers(DELETE, "/v1/user/bankdetail").hasAnyAuthority(TD_DELETE.name(), SP_OUTLET_DELETE.name())

                .antMatchers(GET, "/v1/user/kycdetail").hasAnyAuthority(TD_READ.name(), SP_OUTLET_READ.name())
                .antMatchers(POST, "/v1/user/kycdetail").hasAnyAuthority(TD_CREATE.name(), SP_OUTLET_CREATE.name())
                .antMatchers(PUT, "/v1/user/kycdetail").hasAnyAuthority(TD_UPDATE.name(), SP_OUTLET_UPDATE.name())
                .antMatchers("/v1/user/kycdetail").hasAnyAuthority(TD_DELETE.name(), SP_OUTLET_DELETE.name())

                .antMatchers(GET, "/v1/self/**").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(PUT, "/v1/self/**").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers(GET, "/v1/inventory/item").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/allitem").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/itemoffer").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/itemoffer/all").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }

}
