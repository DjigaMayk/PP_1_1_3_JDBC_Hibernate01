package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    private static SessionFactory sessionFactory;

    private static Connection connection;


    public static SessionFactory getSessionFactory() {
        
        try {
            sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
            System.out.println("Connection is succeeded");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection is failed");

        }
        return sessionFactory;
    }





    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Class.forName(DRIVER);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return connection;
    }
}
