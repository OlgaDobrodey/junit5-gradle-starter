package com.itrex.junit.service.mockito;

import com.itrex.junit.dao.UserDao;
import com.itrex.junit.dto.User;
import com.itrex.junit.service.user.UserService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceTest {

    private static final User IVAN = User.of(1, "Ivan", "123");

    private UserService service;
    private UserDao userDao;

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
        this.userDao = Mockito.mock(UserDao.class);
        this.service = new UserService(userDao);
    }

    @Test
    void shouldDeleteExistUserTest(){
        //given
        service.add(IVAN);
//        Mockito.doReturn(true).when(userDao).delete(IVAN.getId());
//        Mockito.doReturn(true).when(userDao).delete(Mockito.any());
        Mockito.when(userDao.delete(Mockito.any()))
                .thenReturn(true)
                .thenReturn(false);
        // when
        boolean deleteResult = service.delete(IVAN.getId()); //true
        System.out.println(service.delete(IVAN.getId())); //false
        System.out.println(service.delete(IVAN.getId())); //false

        //then
        assertTrue(deleteResult);
        assertThat(deleteResult).isTrue();
    }


    @AfterEach
    void deleteData() {
        System.out.println("After each: " + this);
    }

}
