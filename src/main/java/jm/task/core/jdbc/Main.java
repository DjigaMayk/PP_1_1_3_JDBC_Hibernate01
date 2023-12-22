package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        Util.getConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Zapir", "Ramazanov", (byte) 35);
        userService.saveUser("Bruce", "Buffer", (byte) 40);
        userService.saveUser("Ruslan", "Aushev", (byte) 23);
        userService.saveUser("Djiga", "Mayk", (byte) 28);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();



    }
}

