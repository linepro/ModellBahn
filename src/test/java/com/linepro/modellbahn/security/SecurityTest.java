/**
 * 
 */
package com.linepro.modellbahn.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author JohnG
 *
 */
public class SecurityTest {

    private static final String[] PASSWORDS  = {
        "password",
        "Password",
        "P4ssw0rd",
        "P!55Word",
        "IAmAGod"
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
    public void testGetPasswordEncoder() throws Exception {
        PasswordEncoder encoder = security.getPasswordEncoder();
        for (String password : PASSWORDS) {
            System.out.println(password + "=" + encoder.encode("Password"));
        }
    }

}
