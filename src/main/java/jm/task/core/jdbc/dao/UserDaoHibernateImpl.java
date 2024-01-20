package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class    UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    private static final  String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mydbtest.users (" +
            "  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
            "  name VARCHAR(45) NOT NULL," +
            "  last_name VARCHAR(45) NOT NULL," +
            "  age TINYINT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS mydbtest.users";
    private static final String SAVE_USER = "INSERT INTO mydbtest.users (name, last_name, age) VALUES (?, ?, ?)";
    private static final String REMOVE_USER = "DELETE FROM mydbtest.users WHERE id=?";
    private static final String GET_USERS = "SELECT * FROM mydbtest.users";
    private static final String CLEAN_TABLE = "DELETE FROM mydbtest.users";



    private final SessionFactory sessionFactory = Util.getSessionFactory();



    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }



    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
           return session.createQuery("FROM User ", User.class).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CLEAN_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
