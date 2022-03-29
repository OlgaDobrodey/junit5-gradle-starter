package com.itrex.junit.service.bbdmokito;

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
    }

    @Test
    void shouldDeleteExistUserTest() {
        service.add(IVAN);
        BDDMockito.given(userDao.delete(any())).willReturn(true);
//        BDDMockito.willReturn(true).given(userDao).delete(IVAN.getId());

        boolean deleteResult = service.delete(IVAN.getId()); //true

        //then
        assertTrue(deleteResult);
        assertThat(deleteResult).isTrue();
    }

    @Test
    void throwExceptionIfDatabaseIsNot() {
        Mockito.doThrow(RuntimeException.class).when(userDao).delete(any());

        assertThrows(RuntimeException.class, () -> service.delete(IVAN.getId()));
    }


    @AfterEach
    void deleteData() {
        System.out.println("After each: " + this);
    }
}
