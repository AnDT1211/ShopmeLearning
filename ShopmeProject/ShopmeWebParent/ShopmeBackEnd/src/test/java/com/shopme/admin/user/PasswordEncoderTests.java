package com.shopme.admin.user;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTests {
    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);    // $2a$10$0RtSTIbDY23JnNpSNEXCdu3HqxBItGv0YtK6fhol0nI4aLfQbCgLa
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();
    }
}
