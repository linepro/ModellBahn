package com.linepro.modellbahn.security;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@Configuration(PREFIX + "AuthorizationServerConfig")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public DataSource dataSource;
}
