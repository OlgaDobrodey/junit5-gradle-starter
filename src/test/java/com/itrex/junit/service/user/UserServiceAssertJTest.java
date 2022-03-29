package com.itrex.junit.service.user;

import com.itrex.junit.dto.User;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

//AssertJ
//@TestMethodOrder(MethodOrderer.DisplayName.class) -not good practic
public class UserServiceAssertJTest {
    private static final User IVAN = User.of(1, "Ivan", "123");
    private static final User PETR = User.of(2, "Petr", "111");

    private UserService service;

    @BeforeAll
    static void init() {
        System.out.println("Before all: ");
    }

    @AfterAll
    static void close() {
        System.out.println("After all: ");
    }

    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
        service = new UserService(null);
    }

    @Test
    @Order(2)
    void usersEmptyIfNoUserAdded() {
        System.out.println("Test1: " + this);

        List<User> all = service.getAll();
        assertThat(all).isEmpty();
//        assertTrue(all.isEmpty());
    }

    @Test
    @Order(1)
    void userSizeIfUserAdded() {
        System.out.println("Test2: " + this);

        service.add(IVAN);
        service.add(PETR);

        List<User> all = service.getAll();
        assertThat(all).hasSize(2);
//        assertEquals(2, all.size());
    }

    @Test
    void loginSuccessIfUserExist() {
        service.add(IVAN);
        Optional<User> userIfPresent = service.login(IVAN.getUsername(), IVAN.getPassword());

        assertThat(userIfPresent).isPresent();
        userIfPresent.ifPresent(user -> assertThat(user).isEqualTo(IVAN));
//        assertTrue(userIfPresent.isPresent());
//        userIfPresent.ifPresent(user -> assertEquals(IVAN, user));
    }

    @Test
    void loginFailIfPasswordIsNotCorrect() {
        service.add(IVAN);
        Optional<User> userIfPresent = service.login(IVAN.getUsername(), "124");

        assert (userIfPresent).isEmpty();
//        assertFalse(userIfPresent.isPresent());
    }

    @Test
    void loginFailIfUserDoesNotExist() {
        service.add(IVAN);
        Optional<User> userIfPresent = service.login("IVAN.getUsername()", IVAN.getPassword());

        assertThat(userIfPresent).isEmpty();
//        assertTrue(userIfPresent.isEmpty());
    }

    @Test
    void testMapUsersConvertedTOMApBYId() {
        service.add(IVAN, PETR);

        Map<Integer, User> userMap = service.getAllConvertedById();
        userMap.forEach((k, u) -> System.out.println(k + " " + u));

        assertAll(
                () -> assertThat(userMap).hasSize(2),
                () -> assertThat(userMap).containsKeys(IVAN.getId(), PETR.getId()),
                () -> assertThat(userMap).containsValues(IVAN, PETR)
        );
    }

    @AfterEach
    void deleteData() {
        System.out.println("After each: " + this);
    }
}
