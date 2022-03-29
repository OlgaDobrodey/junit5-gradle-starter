package com.itrex.junit.dao;

import lombok.SneakyThrows;

import java.sql.DriverManager;

public class UserDao {

    @SneakyThrows
    public boolean delete(Integer userId){
        try (var conn = DriverManager.getConnection("url", "username", "psw")) {
        }
        return true;
    }
}
