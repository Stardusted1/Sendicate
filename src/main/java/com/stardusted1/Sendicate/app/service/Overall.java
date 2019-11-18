package com.stardusted1.Sendicate.app.service;

import com.stardusted1.Sendicate.app.core.users.User;

import java.util.LinkedList;

// TODO: 17.11.2019 при наступлении нового времени суток, проверить все посылки
public class Overall {
    public static LinkedList<User> Users = new LinkedList<>();

    public static User GetUser(int id) {
        return Users.get(id);
    }
    public static LinkedList GetUsers() {
        return Users;
    }
    public static User AddUser(User user) {
        Users.add(user);
        return user;
    }
}
