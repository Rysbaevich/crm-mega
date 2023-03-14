package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<Model> {
    Model save(Model model);
    Model findById(Long id);
    List<Model> findAll();
    List<Model> saveAll(List<Model> models);


    default Connection getConnection() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/crm_mega";
        final String USERNAME = "postgres";
        final String PASSWORD = "123";

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    default void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
