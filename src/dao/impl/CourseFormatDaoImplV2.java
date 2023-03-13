package dao.impl;

import dao.CourseFormatDao;
import model.CourseFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseFormatDaoImplV2 implements CourseFormatDao {
    public CourseFormatDaoImplV2() {
        Connection connection = null;
        try {
            connection = getConnection();

            String ddlQuery =
                    "CREATE TABLE IF NOT EXISTS tb_course_format " +
                            "( " +
                            "    id                    serial primary key, " +
                            "    name                  varchar(30) not null, " +
                            "    duration_in_week      integer     not null, " +
                            "    is_online             boolean default false, " +
                            "    lesson_duration       integer     not null, " +
                            "    lesson_count_per_week integer     not null, " +
                            "    date_created          timestamp   not null " +
                            ");";

            PreparedStatement preparedStatement = connection.prepareStatement(ddlQuery);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public CourseFormat save(CourseFormat courseFormat) {
        return null;
    }

    @Override
    public CourseFormat findById(Long id) {
        return null;
    }

    @Override
    public List<CourseFormat> findAll() {
        return null;
    }

    @Override
    public List<CourseFormat> saveAll(List<CourseFormat> courseFormats) {
        return null;
    }
}
