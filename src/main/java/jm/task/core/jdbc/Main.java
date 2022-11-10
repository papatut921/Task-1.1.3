package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov",(byte) 25);
        userService.saveUser("Petr","Petrov",(byte) 26);
        userService.saveUser("Sidor","Sidorov",(byte) 27);
        userService.saveUser("Pup","Pupkin",(byte) 28);
        List <User> userList = userService.getAllUsers();
        for (User user:userList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
