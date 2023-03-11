package dao.impl;

import dao.ManagerDao;
import model.Manager;

import java.sql.*;


public class ManagerDaoImplV2 implements ManagerDao {
    private final String URL = "jdbc:postgresql://localhost:5432/crm_mega";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "'";

    public ManagerDaoImplV2() {
        try {
            System.out.println("Connecting...");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Manager manager) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            System.out.println("Connecting...");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection succeed");
//            Statement statement = connection.createStatement();

   /*         String query = "INSERT INTO tb_manager(name, surname, phone, date_created, salary, email) " +
                    " VALUES ('" + manager.getName() + "', '" + manager.getSurname() + "', " +
                    "'" + manager.getPhone() + "', '"+manager.getDateCreated()+"', '"+manager.getSalary()+"', " +
                    "'"+manager.getEmail()+"')";
*/
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


            /*String query = String.format(
                    "INSERT INTO tb_manager " +
                    "(name, surname, phone, salary, date_created, email) " +
                    "VALUES ('%s', '%s', '%s', %f, '%s', '%s');",
                    manager.getName(),
                    manager.getSurname(),
                    manager.getPhone(),
                    manager.getSalary(),
                    manager.getDateCreated(),
                    manager.getEmail());*/
//            statement.execute(query);

        } catch (SQLException e) {
            System.err.println("SQL exception");
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public Manager[] findAll() {
        return new Manager[0];
    }
}
