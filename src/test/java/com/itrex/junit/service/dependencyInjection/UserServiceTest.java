package com.itrex.junit.service.dependencyInjection;

import com.itrex.junit.dto.User;
import com.itrex.junit.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({UserServiceParamResolver.class})
public class UserServiceTest {

    private static final User IVAN = User.of(1, "Ivan", "123");
    private static final User PETR = User.of(2, "Petr", "111");

    private UserService service;

    UserServiceTest(TestInfo testInfo) {
        System.out.println();
    }

    @BeforeAll
    static void init() {
        System.out.println("Before all: ");
    }

    @BeforeEach
    void prepare(UserService userService) {
        System.out.println("Before each: " + this);
        service = userService;
    }

    @Test
    @Tag("login")//https://www.baeldung.com/junit-filtering-tests
    void loginSuccessIfUserExist() {
        service.add(IVAN);
        Optional<User> userIfPresent = service.login(IVAN.getUsername(), IVAN.getPassword());

        assertTrue(userIfPresent.isPresent());
        userIfPresent.ifPresent(user -> assertEquals(IVAN, user));
    }

    @Test
    @Tag("login") // start from command line
    void loginFailIfPasswordIsNotCorrect() {
        service.add(IVAN);
        Optional<User> userIfPresent = service.login(IVAN.getUsername(), "124");

        assertFalse(userIfPresent.isPresent());
    }

    //we can inject UserService in method (no used method BeforeEach)
    @Test
    void loginFailIfUserDoesNotExist(UserService userService) {
        service.add(IVAN);
        Optional<User> userIfPresent = service.login("IVAN.getUsername()", IVAN.getPassword());

        assertTrue(userIfPresent.isEmpty());
    }

    @AfterEach
    void deleteData() {
        System.out.println("After each: " + this);
    }

    @AfterAll
    static void close() {
        System.out.println("After all: ");
    }

    @Nested
    class UserTest{

        @Test
        void usersEmptyIfNoUserAdded() {
            System.out.println("Test1: " + this);

            List<User> all = service.getAll();
            assertTrue(all.isEmpty());
        }

        @Test
        void users_empty_if_no_user_added() {
            System.out.println("Test1.1: " + this);
            List<User> all = service.getAll();
            assertTrue(all.isEmpty(), "ERROR"); //message if ERROR
            assertAll("Assert All",
                    () -> assertTrue(true),
                    () -> assertFalse(false)
            );
        }

        @Test
        void userSizeIfUserAdded() {
            System.out.println("Test2: " + this);

            service.add(IVAN);
            service.add(PETR);

            List<User> all = service.getAll();
            assertEquals(2, all.size());
        }
    }
}
