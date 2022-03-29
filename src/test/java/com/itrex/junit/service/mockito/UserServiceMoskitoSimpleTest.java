package com.itrex.junit.service.mockito;

import com.itrex.junit.dao.UserDao;
import com.itrex.junit.dto.User;
import com.itrex.junit.service.user.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class})
public class UserServiceMoskitoSimpleTest {
    private static final User IVAN = User.of(1, "Ivan", "123");

    @InjectMocks
    private UserService service;
    @Mock(lenient = true)//Unnecessary stubbings detected.
    private UserDao userDao;
    @Captor
    private ArgumentCaptor<Integer> argumentCaptor;

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
        Mockito.when(userDao.delete(any())).thenReturn(true);
    }

    @Test
    void shouldDeleteExistUserTest() {
        //given
        service.add(IVAN);

        // when
        boolean deleteResult = service.delete(IVAN.getId()); //true

        //then
        assertTrue(deleteResult);
        assertThat(deleteResult).isTrue();

        Mockito.verify(userDao, Mockito.atLeast(2)).delete(argumentCaptor.capture());
        Mockito.verify(userDao, Mockito.atMost(4)).delete(argumentCaptor.capture());
//        Mockito.verifyNoInteractions(spyUserDao);

        assertThat(argumentCaptor.getValue()).isEqualTo(25);
    }

    @Test
    void throwExceptionIfDatabaseIsNot(){
        Mockito.doThrow(RuntimeException.class).when(userDao).delete(any());

        assertThrows(RuntimeException.class,()->service.delete(IVAN.getId()));
    }


    @AfterEach
    void deleteData() {
        System.out.println("After each: " + this);
    }
}
