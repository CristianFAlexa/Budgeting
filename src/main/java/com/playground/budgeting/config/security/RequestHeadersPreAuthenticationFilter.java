package com.playground.budgeting.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RequestHeadersPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader("user_id");
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return EMPTY;
    }
}
