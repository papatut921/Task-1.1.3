package jm.task.core.jdbc.util;

public class Util {
    public static void main(String[] args) {

    // реализуйте настройку соеденения с БД
    Connection connection = null;
    Statement statement = null;

        System.out.println("Connecting to database");
        Class.forName("com.PostgresSQL.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User", "jm_task_core", "dba");
        System.out.println("Connecting to successful");
}
}
