package com.linepro.modellbahn.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * OAuth2ClientContext that forwards token from  the SecurityContext if none is all ready set.
 */
@Slf4j
public class OAuth2TokenForwardingContext extends DefaultOAuth2ClientContext {

	private static final long serialVersionUID = 2535021952088624127L;

    /**
     * Gets the access token from the security context (if available); if no token is available OAuth2RestTemplate will
     * fetch one.
     *
     * @return the access token.
     */
    @Override
    public OAuth2AccessToken getAccessToken() {
        if (super.getAccessToken() == null) {
            // No token at the moment, a new one will be fetched unless we have one in the security context
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof OAuth2Authentication) {
                OAuth2Authentication oAuthAuthentication = (OAuth2Authentication) authentication;

                if (oAuthAuthentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuthAuthentication.getDetails();

                    if (StringUtils.hasText(details.getTokenValue())) {
                        log.info("Forwarding token from SecurityContext");

                        // Apply the security context token to the OAuth2 context rather than just return it because OAuth2 context decodes it.
                        setAccessToken(new DefaultOAuth2AccessToken(details.getTokenValue()));
                    }
                }
            }
        }

        // Refetch the token in case we updated it above.
        return super.getAccessToken();
    }
}