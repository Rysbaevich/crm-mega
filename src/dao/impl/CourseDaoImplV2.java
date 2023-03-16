package dao.impl;

import dao.CourseDao;
import model.Course;
import model.CourseFormat;

import java.sql.*;
import java.util.ArrayList;
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
                            "    price            numeric check (price > 0), " +
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Course courseRes;

        try {
            connection = getConnection();
            String query = "INSERT INTO tb_course (name,price,course_format_id,date_created)" +
                    "values (?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, course.getName());
            preparedStatement.setDouble(2,course.getPrice());
            preparedStatement.setLong(3,course.getCourseFormat().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(course.getDateCreated()));
            preparedStatement.execute();

            String getQuery = "Select * from tb_course as c join tb_course_format as f on " +
                    "c.course_format_id = f.id";
            preparedStatement = connection.prepareStatement(getQuery);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            CourseFormat courseFormat = new CourseFormat();
            courseRes = new Course();
            courseRes.setId(resultSet.getLong("id"));
            courseRes.setName(resultSet.getString("name"));
            courseRes.setPrice(resultSet.getDouble("price"));
            courseRes.setCourseFormat(courseFormat);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }

    @Override
    public Course findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course = null;
        CourseFormat courseFormat;

        try {
            connection = getConnection();
            String query = "select c.id as course_id, c.name as course_name, price, course_format_id, c.date_created as course_dc, " +
                    "tcf.id as cf_id,tcf.name as cf_name,duration_in_week,is_online,lesson_duration,lesson_count_per_week,tcf.date_created as cf_dc " +
                    "from tb_course as c " +
                    "join tb_course_format tcf " +
                    "on tcf.id = c.course_format_id " +
                    "where c.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            courseFormat = new CourseFormat();
            courseFormat.setId(resultSet.getLong("cf_id"));
            courseFormat.setFormatName(resultSet.getString("cf_name"));
            courseFormat.setDurationInWeek(resultSet.getInt("duration_in_week"));
            courseFormat.setOnline(resultSet.getBoolean("is_online"));
            courseFormat.setLessonDuration(resultSet.getInt("lesson_duration"));
            courseFormat.setLessonCountPerWeek(resultSet.getInt("lesson_count_per_week"));
            courseFormat.setDateCreated(resultSet.getTimestamp("cf_dc").toLocalDateTime());

            course = new Course();
            course.setId(resultSet.getLong("course_id"));
            course.setName(resultSet.getString("course_name"));
            course.setPrice(resultSet.getDouble("price"));
            course.setCourseFormat(courseFormat);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseFormat courseFormat;

        try {
            connection = getConnection();
            String query = "select c.id as course_id, c.name as course_name, price, course_format_id, c.date_created as course_dc, " +
                    "tcf.id as cf_id,tcf.name as cf_name,duration_in_week,is_online,lesson_duration,lesson_count_per_week,tcf.date_created as cf_dc " +
                    "from tb_course as c " +
                    "join tb_course_format tcf " +
                    "on tcf.id = c.course_format_id ";
            preparedStatement = getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courseFormat = new CourseFormat();
                courseFormat.setId(resultSet.getLong("cf_id"));
                courseFormat.setFormatName(resultSet.getString("cf_name"));
                courseFormat.setDurationInWeek(resultSet.getInt("duration_in_week"));
                courseFormat.setOnline(resultSet.getBoolean("is_online"));
                courseFormat.setLessonDuration(resultSet.getInt("lesson_duration"));
                courseFormat.setLessonCountPerWeek(resultSet.getInt("lesson_count_per_week"));
                courseFormat.setDateCreated(resultSet.getTimestamp("cf_dc").toLocalDateTime());

                Course course = new Course();
                course.setId(resultSet.getLong("course_id"));
                course.setName(resultSet.getString("course_name"));
                course.setPrice(resultSet.getDouble("price"));
                course.setCourseFormat(courseFormat);
                course.setDateCreated(resultSet.getTimestamp("course_dc").toLocalDateTime());
                courses.add(course);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return courses;
    }

    @Override
    public List<Course> saveAll(List<Course> courses) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String query = "INSERT INTO  tb_course (name, price, course_format_id, date_created)" +
                    "values (?,?,?,?)";
            connection.setAutoCommit(true);
            for (Course course : courses){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, course.getName());
                preparedStatement.setDouble(2,course.getPrice());
                preparedStatement.setLong(3,course.getCourseFormat().getId());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(course.getDateCreated()));
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }
}
