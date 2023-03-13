package dao.impl;

import dao.GroupDao;
import model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GroupDaoImplV2 implements GroupDao {
    public GroupDaoImplV2() {
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
    public Group save(Group group) {
        return null;
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
}
