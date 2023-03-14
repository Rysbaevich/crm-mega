package dao.impl;

import dao.ManagerDao;
import model.Manager;

import java.sql.*;
import java.util.List;


public class ManagerDaoImplV2 implements ManagerDao {

//    "CREATE TABLE IF NOT EXISTS tb_group " +
//            "( " +
//            "    id           serial primary key, " +
//            "    name         varchar(50) not null, " +
//            "    room         varchar(50) not null, " +
//            "    start_time   time, " +
//            "    mentor_id    bigint " +
//            "        constraint fk_mentor_id references tb_mentor (id), " +
//            "    course_id    bigint " +
//            "        constraint fk_group_id references tb_course (id), " +
//            "    date_created timestamp   not null " +
//            ");" +




    public ManagerDaoImplV2() {
        Connection connection = null;
        try {
            System.out.println("Connecting...");
            connection = getConnection();
            System.out.println("Connection succeed");

            String ddlQuery =
                    "CREATE TABLE IF NOT EXISTS tb_manager ( " +
                            "  id SERIAL PRIMARY KEY, " +
                            "  name VARCHAR(30) NOT NULL, " +
                            "  surname VARCHAR(50) NOT NULL, " +
                            "  phone VARCHAR(12) NOT NULL, " +
                            "  email VARCHAR(100) NOT NULL, " +
                            "  salary DECIMAL NOT NULL, " +
                            "  date_created TIMESTAMP NOT NULL " +
                            ");";

            PreparedStatement preparedStatement = connection.prepareStatement(ddlQuery);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } finally {
            close(connection);
        }

    }

    @Override
    public Manager save(Manager manager) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager managerResult = null;
        try {
            System.out.println("Connecting...");
            connection = getConnection();
            System.out.println("Connection succeed");

            String query = "INSERT INTO tb_manager(name, surname, phone, date_created, salary, email) " +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, manager.getName());
            preparedStatement.setString(2, manager.getSurname());
            preparedStatement.setString(3, manager.getPhone());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(manager.getDateCreated()));
            preparedStatement.setDouble(5, manager.getSalary());
            preparedStatement.setString(6, manager.getEmail());
            preparedStatement.execute();

            String querySelect =
                    " SELECT * FROM tb_manager" +
                            " ORDER BY id DESC LIMIT 1;";
            preparedStatement = connection.prepareStatement(querySelect);

            managerResult = new Manager();

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            managerResult.setId(resultSet.getLong("id"));
            managerResult.setName(resultSet.getString("name"));
            managerResult.setSurname(resultSet.getString("surname"));
            managerResult.setPhone(resultSet.getString("phone"));
            managerResult.setEmail(resultSet.getString("email"));
            managerResult.setSalary(resultSet.getDouble("salary"));
            managerResult.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

        } catch (SQLException e) {
            System.err.println("SQL exception");
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return managerResult;
    }

    @Override
    public Manager findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager manager = new Manager();

        try {
            connection = getConnection();

            String query = "SELECT * FROM tb_manager WHERE id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            manager.setId(resultSet.getLong("id"));
            manager.setName(resultSet.getString("name"));
            manager.setSurname(resultSet.getString("surname"));
            manager.setPhone(resultSet.getString("phone"));
            manager.setEmail(resultSet.getString("email"));
            manager.setSalary(resultSet.getDouble("salary"));
            manager.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }

        return manager;
    }

    @Override
    public List<Manager> findAll() {
        return null;
    }

    @Override
    public List<Manager> saveAll(List<Manager> managers) {
        return null;
    }
}
