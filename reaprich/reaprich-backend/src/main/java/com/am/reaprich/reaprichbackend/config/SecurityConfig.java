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

                .antMatchers("/v1/user/bankdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers("/v1/user/kycdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers("/v1/user/actor/outlet").hasAnyRole(ADMIN.name(),TD.name())

                .antMatchers("/v1/self/**").hasAnyRole(ADMIN.name(), TD.name(), SP_OUTLET.name(), OUTLET.name())
                .antMatchers("/v1/user/actor/customer").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers("/v1/user/address").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers(GET,"/v1/inventory/item").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers("/v1/inventory/allitems").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET,"/v1/inventory/itemoffer").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers( "/v1/inventory/itemoffer/all").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers( "/v1/inventory/offersonitem").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                //.antMatchers( "/v1/inventory/warehouse/items").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers( "/v1/inventoryops/purchaserequest").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers( "/v1/inventoryops/purchaserequest").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers( "/v1/inventoryops/purchaserequest/cancel").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers( "/v1/inventoryops/itemstransfer/accept").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())



                .antMatchers(GET, "/v1/user/**").hasAnyRole(ADMIN.name(),TD.name())
                //.antMatchers(POST, "/v1/user/**").hasAnyRole(ADMIN.name())
                //.antMatchers(PUT, "/v1/user/**").hasAnyRole(ADMIN.name())
                //.antMatchers(DELETE, "/v1/user/**").hasAnyRole(ADMIN.name())

                .antMatchers(GET, "/v1/provider/**").hasAnyRole(ADMIN.name(), TD.name(), SP_OUTLET.name(), OUTLET.name())
                //.antMatchers(POST, "/v1/provider/**").hasAnyRole(ADMIN.name())
                //.antMatchers(PUT, "/v1/provider/**").hasAnyRole(ADMIN.name())
                //.antMatchers(DELETE, "/v1/provider/**").hasAnyRole(ADMIN.name())

                .antMatchers(GET, "/v1/user/actor/outlet").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(POST, "/v1/user/actor/outlet").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(PUT, "/v1/user/actor/outlet").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(DELETE, "/v1/user/actor/outlet").hasAnyRole(ADMIN.name(),TD.name())


                .antMatchers(GET, "/v1/user/actor/customer").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(POST, "/v1/user/actor/customer").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                //.antMatchers(POST, "/v1/user/actor/customer").hasAnyAuthority(OUTLET_CREATE.name())
                .antMatchers(PUT, "/v1/user/actor/customer").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(DELETE, "/v1/user/actor/customer").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers(GET, "/v1/user/address").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(POST, "/v1/user/address").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(PUT, "/v1/user/address").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(DELETE, "/v1/user/address").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers(GET, "/v1/user/bankdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(POST, "/v1/user/bankdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(PUT, "/v1/user/bankdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(DELETE, "/v1/user/bankdetail").hasAnyRole(ADMIN.name(),TD.name())

                .antMatchers(GET, "/v1/user/kycdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(POST, "/v1/user/kycdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers(PUT, "/v1/user/kycdetail").hasAnyRole(ADMIN.name(),TD.name())
                .antMatchers("/v1/user/kycdetail").hasAnyRole(ADMIN.name(),TD.name())

                .antMatchers(GET, "/v1/self/**").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(PUT, "/v1/self/**").hasAnyRole(TD.name(), SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers(GET, "/v1/inventory/item").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                //.antMatchers(GET, "/v1/inventory/allitem").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(POST, "/v1/inventory/allitems").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/itemoffer").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/itemoffer/all").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/offersonitem").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(GET, "/v1/inventory/warehouse/items").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers(GET, "/v1/inventoryops/purchaserequest").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(POST, "/v1/inventoryops/purchaserequest").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(PUT, "/v1/inventoryops/purchaserequest/cancel").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())
                .antMatchers(PUT, "/v1/inventoryops/itemstransfer/accept").hasAnyRole(SP_OUTLET.name(), OUTLET.name(), ADMIN.name())

                .antMatchers("/v1/user/**").hasAnyRole(ADMIN.name())
                .antMatchers("/v1/provider/**").hasAnyRole(ADMIN.name())
                //.antMatchers("/v1/self/**").hasAnyRole(ADMIN.name())
                .antMatchers("v1/inventory/**").hasAnyRole((ADMIN.name()))
                .antMatchers("/v1/inventoryops/**").hasAnyRole((ADMIN.name()))

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
