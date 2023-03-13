package dao.impl;

import dao.MentorDao;
import model.Mentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MentorDaoImplV2 implements MentorDao {
    public MentorDaoImplV2() {
        Connection connection = null;
        try {
            connection = getConnection();

            String ddlQuery =
                    "CREATE TABLE IF NOT EXISTS tb_mentor " +
                            "( " +
                            "    id           serial primary key, " +
                            "    name         varchar(50) not null, " +
                            "    surname      varchar(50) not null, " +
                            "    phone        varchar(12) not null unique, " +
                            "    email        varchar(50) not null unique, " +
                            "    experience   numeric     not null " +
                            "        constraint tb_mentor_experience_check " +
                            "            check (experience > (1)::numeric), " +
                            "    salary_for_lesson numeric check (salary_for_lesson > 0)," +
                            "    date_created timestamp   not null " +
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
    public Mentor save(Mentor mentor) {
        return null;
    }

    @Override
    public Mentor findById(Long id) {
        return null;
    }

    @Override
    public List<Mentor> findAll() {
        return null;
    }

    @Override
    public List<Mentor> saveAll(List<Mentor> mentors) {
        return null;
    }
}
