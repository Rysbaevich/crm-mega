package dao.impl;

import dao.CourseDao;
import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoImplV2 implements CourseDao {
    public CourseDaoImplV2() {
        Connection connection = null;
        try {
            connection = getConnection();

            String ddlQuery =
                    "CREATE TABLE IF NOT EXISTS tb_course " +
                            "( " +
                            "    id               serial primary key, " +
                            "    name             varchar(30) not null, " +
                            "    course_format_id bigint      not null " +
                            "        constraint fk_course " +
                            "            references tb_course_format, " +
                            "    date_created     timestamp   not null " +
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
    public Course save(Course course) {
        return null;
    }

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public List<Course> saveAll(List<Course> courses) {
        return null;
    }
}
