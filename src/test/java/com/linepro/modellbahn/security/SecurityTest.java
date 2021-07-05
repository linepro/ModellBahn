/**
 * 
 */
package com.linepro.modellbahn.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author JohnG
 *
 */
public class SecurityTest {

    private static final String[][] PASSWORDS  = {
        { "Password", "$2a$10$MbNfLBG245kQ01dvWmiyn.RH8bhFJQEwvxM1/MP1fzHBL/unhPv1e" },
        { "IAmAGod", "$2a$10$qMxTrsr2sJsGtk5gY2DntuffR2iO5lfW1r99v3btdpWwJaDo82x0G" }
    };

    private Security security;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    protected void setUp() throws Exception {
        security = new Security();
    }

    /**
     * Test method for {@link com.linepro.modellbahn.security.Security#getPasswordEncoder()}.
     */
    @Test
    void passwordEncoder() throws Exception {
        PasswordEncoder encoder = security.getPasswordEncoder();

        for (String[] password : PASSWORDS) {
            assertTrue(encoder.matches(password[0], password[1]));
        }
    }
}
