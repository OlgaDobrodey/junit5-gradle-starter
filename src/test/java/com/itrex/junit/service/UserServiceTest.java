package com.itrex.junit.service;

import com.itrex.junit.dto.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) -> beforeAll not static
public class UserServiceTest {

    private UserService service;

    @BeforeAll
    static void init(){
        System.out.println("Before all: ");
    }

    @BeforeEach
    void prepare(){
        System.out.println("Before each: "+ this);
        service = new UserService();
    }

    @Test
    void usersEmptyIfNoUserAdded() {
        System.out.println("Test1: "+ this);

        List<User> all = service.getAll();
        assertTrue(all.isEmpty());
    }

    @Test
    void users_empty_if_no_user_added() {
        System.out.println("Test1.1: "+ this);
        List<User> all = service.getAll();
        assertTrue(all.isEmpty(), "ERROR"); //message if ERROR
        assertAll("Assert All",
                ()->assertTrue(true),
                ()-> assertFalse(false)
                );
    }

    @Test
    void userSizeIfUserAdded(){
        System.out.println("Test2: "+ this);

        service.add(new User());
        service.add(new User());

        List<User> all = service.getAll();
        assertEquals(2,all.size());
    }

    @AfterEach
    void deleteData(){
        System.out.println("After each: "+ this);
    }

    @AfterAll
    static void close(){
        System.out.println("After all: ");
    }

}
