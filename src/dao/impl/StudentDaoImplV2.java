package dao.impl;

import dao.StudentDao;
import model.Group;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImplV2 implements StudentDao {
    public StudentDaoImplV2() {
        Connection connection = null;
        try {
            connection = getConnection();

            String ddlQuery =
                    "CREATE TABLE IF NOT EXISTS tb_group " +
                            "( " +
                            "    id           serial primary key, " +
                            "    name         varchar(50) not null, " +
                            "    room         varchar(50) not null, " +
                            "    start_time   time, " +
                            "    mentor_id    bigint " +
                            "        constraint fk_mentor_id references tb_mentor (id), " +
                            "    course_id    bigint " +
                            "        constraint fk_group_id references tb_course (id), " +
                            "    date_created timestamp   not null " +
                            ");" +
                            "" +
                            "CREATE TABLE IF NOT EXISTS tb_student " +
                            "( " +
                            "    id           serial primary key, " +
                            "    name         varchar(50) not null, " +
                            "    surname      varchar(50) not null, " +
                            "    phone        varchar(12) not null unique, " +
                            "    email        varchar(50) not null unique, " +
                            "    dob          date, " +
                            "    date_created timestamp   not null " +
                            ");" +
                            "" +
                            "CREATE TABLE IF NOT EXISTS student_group " +
                            "( " +
                            "    student_id bigint not null " +
                            "        constraint fk_student_id references tb_student (id), " +
                            "    group_id   bigint not null " +
                            "        constraint fk_group_id references tb_group (id) " +
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
    public Student save(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student studentResult = null;
        try {
            System.out.println("Connecting...");
            connection = getConnection();
            System.out.println("Connection succeed");

            String query = "INSERT INTO tb_student(name, surname, phone, date_created, dob, email) " +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(student.getDateCreated()));
            preparedStatement.setDate(5, Date.valueOf(student.getDob()));
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.execute();

            String querySelect =
                    " SELECT * FROM tb_student" +
                            " ORDER BY id DESC LIMIT 1;";
            preparedStatement = connection.prepareStatement(querySelect);
            studentResult = new Student();
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            studentResult.setId(resultSet.getLong("id"));
            studentResult.setName(resultSet.getString("name"));
            studentResult.setSurname(resultSet.getString("surname"));
            studentResult.setPhone(resultSet.getString("phone"));
            studentResult.setEmail(resultSet.getString("email"));
            studentResult.setDob(resultSet.getDate("dob").toLocalDate());
            studentResult.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

            for (int i = 0; i < student.getGroups().size(); i++) {
                String queryForGroup =
                        "INSERT INTO student_group(student_id, group_id) " +
                                "VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(queryForGroup);
                preparedStatement.setLong(1, studentResult.getId());
                preparedStatement.setLong(2, student.getGroups().get(i).getId());
                preparedStatement.execute();
            }


        } catch (SQLException e) {
            System.err.println("SQL exception");
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return studentResult;
    }

    @Override
    public Student findById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        Group group;
        try {
            System.out.println("Connecting...");
            connection = getConnection();
            System.out.println("Connection succeed");

            String querySelect =
                    "SELECT s.id st_id, s.name as st_name, s.surname st_surname, phone, email, dob, s.date_created st_date_created, " +
                    "       group_id, tg.name g_name, start_time, room " +
                    "       FROM tb_student as s " +
                    "    join student_group sg on s.id = sg.student_id " +
                    "    join tb_group tg on sg.group_id = tg.id " +
                    "    where s.id = ?";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setLong(1, id);
            student = new Student();
            List<Group> groups = new ArrayList<>();

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student.setId(resultSet.getLong("st_id"));
            student.setName(resultSet.getString("st_name"));
            student.setSurname(resultSet.getString("st_surname"));
            student.setPhone(resultSet.getString("phone"));
            student.setEmail(resultSet.getString("email"));
            student.setDob(resultSet.getDate("dob").toLocalDate());
            student.setDateCreated(resultSet.getTimestamp("st_date_created").toLocalDateTime());

            do {
                group = new Group();
                group.setId(resultSet.getLong("group_id"));
                group.setName(resultSet.getString("g_name"));
                group.setStartTime(resultSet.getTime("start_time").toLocalTime());
                group.setRoom(resultSet.getString("room"));
                groups.add(group);
            } while (resultSet.next());
            student.setGroups(groups);

        } catch (SQLException e) {
            System.err.println("SQL exception");
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return student;

    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return null;
    }
}
