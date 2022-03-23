package com.itrex.junit.service;

import com.itrex.junit.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceExceptions {

    private static final User IVAN = User.of(1, "Ivan", "123");
    private static final User PETR = User.of(2, "Petr", "111");

    private UserService service;

    {
        assertTrue(true);
    }

    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
        service = new UserService();
    }

    @Test
    void throwExceptionIfUsernameOrPasswordIsNull() {
        try {
            service.login(null, "123");
            fail("login should throw ex on null username");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    void throwExceptionIfUsernameOrPasswordIsNull_second() {
        assertAll(
                () -> {
                    var ex = assertThrows(IllegalArgumentException.class, () -> service.login(null, "123"));
                    assertEquals(ex.getMessage(), "username or password is null");
                },
                () -> assertThrows(IllegalArgumentException.class, () -> service.login("username", null))
        );
    }
}

