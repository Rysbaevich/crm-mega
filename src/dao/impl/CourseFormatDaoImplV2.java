package dao.impl;

import dao.CourseFormatDao;
import model.CourseFormat;

import java.sql.*;
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO tb_course_format " +
                    "(name,duration_in_week,is_online, lesson_duration, lesson_count_per_week,date_created)" +
                    "values (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,courseFormat.getFormat());
            preparedStatement.setInt(2,courseFormat.getDurationInWeek());
            preparedStatement.setBoolean(3,courseFormat.isOnline());
            preparedStatement.setInt(4,courseFormat.getLessonDuration());
            preparedStatement.setInt(5,courseFormat.getLessonCountPerWeek());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(courseFormat.getDateCreated()));
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }

    @Override
    public CourseFormat findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseFormat courseFormat = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM tb_course_format where id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            courseFormat = new CourseFormat();
            courseFormat.setId(resultSet.getLong(1));
            courseFormat.setFormat(resultSet.getString(2));
            courseFormat.setDurationInWeek(resultSet.getInt(3));
            courseFormat.setOnline(resultSet.getBoolean(4));
            courseFormat.setLessonDuration(resultSet.getInt(5));
            courseFormat.setLessonCountPerWeek(resultSet.getInt(6));
            courseFormat.setDateCreated(resultSet.getTimestamp(7).toLocalDateTime());

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return courseFormat;
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
