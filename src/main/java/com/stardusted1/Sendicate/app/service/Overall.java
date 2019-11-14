package com.stardusted1.Sendicate.app.service;

import com.stardusted1.Sendicate.app.core.users.User;

import java.util.LinkedList;

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
