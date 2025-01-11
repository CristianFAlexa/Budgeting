package com.playground.budgeting.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;

import java.util.List;
import java.util.Set;

public class GrantedAuthoritiesAuthenticationDetailsSource implements
    AuthenticationDetailsSource<HttpServletRequest, PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails> {

    @Override
    public PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        var userRoles = context.getHeader("user_roles");
        var authorities = StringUtils.isBlank(userRoles)
            ? List.<GrantedAuthority>of()
            : getAuthorities(userRoles);

        return new PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails(context, authorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(String userRoles) {
        return Set.of(userRoles.split(","))
            .stream()
            .map(SimpleGrantedAuthority::new)
            .toList();
    }
}
