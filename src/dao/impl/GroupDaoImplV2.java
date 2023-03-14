package dao.impl;

import dao.GroupDao;
import model.Group;

import java.sql.*;
import java.util.List;

public class GroupDaoImplV2 implements GroupDao {
    public GroupDaoImplV2() {
        Connection connection = null;
        try {
            System.out.println("Connecting to Group ...");
            connection = getConnection();
            System.out.println("Connection succeed o Group");

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
            System.out.println("Connection failed");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    @Override
    public Group save(Group group) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Group groupResult = null;
        try {
            System.out.println("Connecting...");
            connection = getConnection();
            System.out.println("Connection succeed");

            String query = "INSERT INTO tb_group(name, room, start_time, date_created) " +
                    " VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getRoom());
            preparedStatement.setTime(3, Time.valueOf(group.getStartTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(group.getDateCreated()));
            preparedStatement.execute();

            String querySelect =
                    " SELECT * FROM tb_group" +
                            " ORDER BY id DESC LIMIT 1;";
            preparedStatement = connection.prepareStatement(querySelect);
            groupResult = new Group();
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            groupResult.setId(resultSet.getLong("id"));
            groupResult.setName(resultSet.getString("name"));
            groupResult.setRoom(resultSet.getString("room"));
            groupResult.setStartTime(resultSet.getTime("start_time").toLocalTime());
            groupResult.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

            for (int i = 0; i < group.getStudents().size(); i++) {
                String queryForStudent =
                        "INSERT INTO student_group(student_id, group_id) " +
                                "VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(queryForStudent);
                preparedStatement.setLong(1, groupResult.getId());
                preparedStatement.setLong(2, group.getStudents().get(i).getId());
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
        return groupResult;
    }

    @Override
    public Group findById(Long id) {
        return null;
    }

    @Override
    public List<Group> findAll() {
        return null;
    }

    @Override
    public List<Group> saveAll(List<Group> groups) {
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return GroupDao.super.getConnection();
    }

    @Override
    public void close(AutoCloseable closeable) {
        GroupDao.super.close(closeable);
    }

//    @Override
//    public Group save(Group group) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Group groupResult = null;
//        try {
//            System.out.println("Connecting...");
//            connection = getConnection();
//            System.out.println("Connection succeed");
//
//            String query = "INSERT INTO tb_group(name, room, start_time) " +
//                    " VALUES (?, ?, ?, ?)";
//            preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1, group.getName());
//            preparedStatement.setString(2, group.getRoom());
//            preparedStatement.setTime(3, Time.valueOf(group.getStartTime()));
//            preparedStatement.setTimestamp(4, Timestamp.valueOf(group.getDateCreated()));
//            preparedStatement.execute();
//
//            String querySelect =
//                    " SELECT * FROM tb_manager" +
//                            " ORDER BY id DESC LIMIT 1;";
//            preparedStatement = connection.prepareStatement(querySelect);
//
//            groupResult = new Group();
//
//            resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            group.setId(resultSet.getLong("id"));
//            group.setName(resultSet.getString("name"));
//            group.setRoom(resultSet.getString("room"));
//          /  group.setMentor(resultSet.getBigDecimal("mentor_id"));
//            group.setStudents((List<Student>) resultSet.getArray("students"));
//            group.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());
//
//        } catch (SQLException e) {
//            System.err.println("SQL exception");
//            e.printStackTrace();
//        } finally {
//            close(resultSet);
//            close(preparedStatement);
//            close(connection);
//        }
//        return groupResult;
//    }
//    }
}