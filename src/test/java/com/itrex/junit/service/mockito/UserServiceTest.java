package com.itrex.junit.service.mockito;

import com.itrex.junit.dao.UserDao;
import com.itrex.junit.dto.User;
import com.itrex.junit.service.user.UserService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceTest {

    private static final User IVAN = User.of(1, "Ivan", "123");

    private UserService service;
    private UserService serviceSpy;
    private UserDao userDao;
    private UserDao spyUserDao;

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
        this.spyUserDao = Mockito.spy(new UserDao());
        this.service = new UserService(userDao);
        this.serviceSpy = new UserService(spyUserDao);

    }

    @Test
    void shouldDeleteExistUserTest() {
        //given
        service.add(IVAN);
//        Mockito.doReturn(true).when(userDao).delete(IVAN.getId());
//        Mockito.doReturn(true).when(userDao).delete(Mockito.any());
        Mockito.when(userDao.delete(any()))
                .thenReturn(true)
                .thenReturn(false);
        // when
        boolean deleteResult = service.delete(IVAN.getId()); //true
        System.out.println(service.delete(IVAN.getId())); //false
        System.out.println(service.delete(IVAN.getId())); //false

        //then
        assertTrue(deleteResult);
        assertThat(deleteResult).isTrue();
        //atLeast - 2 and more
        //Mockito.time(2) ==2
        Mockito.verify(userDao, Mockito.atLeast(2)).delete(Mockito.any());
        Mockito.verify(userDao, Mockito.atMost(4)).delete(Mockito.any());
        Mockito.verifyNoInteractions(spyUserDao);
    }

    @Test
    void shouldDeleteExistUserSpyTest() {
        //given
        service.add(IVAN);
        Mockito.doReturn(true).when(spyUserDao).delete(Mockito.any());

        // when
        boolean deleteResult = serviceSpy.delete(IVAN.getId()); //true
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(spyUserDao, Mockito.times(1)).delete(argumentCaptor.capture());

        //then
        assertThat(argumentCaptor.getValue()).isEqualTo(25); //см class com.itrex.junit.service.user.UserService method delete
        assertThat(argumentCaptor.getAllValues()).isNotIn(IVAN.getId());

        assertTrue(deleteResult);
        assertThat(deleteResult).isTrue();
    }

    @AfterEach
    void deleteData() {
        System.out.println("After each: " + this);
    }

}
