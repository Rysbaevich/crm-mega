package dao.impl;

import dao.AddressDao;
import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddressDaoImplV2 implements AddressDao {

    public AddressDaoImplV2() {
        Connection connection = null;
        try {
            connection = getConnection();

            String ddlQuery =
                    "CREATE TABLE IF NOT EXISTS tb_address " +
                            "( " +
                            "    mentor_id bigint unique " +
                            "        constraint fk_mentor references tb_mentor, " +
                            "    country   varchar(100) not null, " +
                            "    region    varchar(100), " +
                            "    city      varchar(100), " +
                            "    district  varchar(100) not null, " +
                            "    street    varchar(100) not null, " +
                            "    apartment varchar(50)  not null, " +
                            "    date_created timestamp" +
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
    public Address save(Address address) {
        return null;
    }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public List<Address> saveAll(List<Address> addresses) {
        return null;
    }
}
