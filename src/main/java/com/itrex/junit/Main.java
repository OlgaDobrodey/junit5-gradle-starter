package com.itrex.junit;

import com.itrex.junit.dto.User;

public class Main {

    public static void main(String[] args) {
        User of = User.of(1, "aer", "d");
        System.out.println(of);

    }
}
