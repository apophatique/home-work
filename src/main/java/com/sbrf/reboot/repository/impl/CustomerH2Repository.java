package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "apophatique";
    private final String PASS = "zxcqwe123";

    public CustomerH2Repository() {
        final String createTableSqlString = "CREATE TABLE IF NOT EXISTS Customer (" +
                "ID IDENTITY PRIMARY KEY," +
                " NAME VARCHAR(20) NOT NULL," +
                " EMAIL VARCHAR(30)" +
                ");";
        try (final Connection conn = getConnection().orElseThrow(() -> new JDBCConnectionException("Unable to establish connection."))) {
            try (final PreparedStatement preparedStatement = conn.prepareStatement(createTableSqlString)) {
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | JDBCConnectionException e) {
            e.printStackTrace();
        }
    }

    private Optional<Connection> getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(DriverManager.getConnection(DB_URL, USER, PASS));
    }

    static class JDBCConnectionException extends Exception {
        public JDBCConnectionException(String message) {
            super(message);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        final String selectAllSQLString = "SELECT * FROM Customer";
        try (final Connection conn = getConnection().orElseThrow(() -> new JDBCConnectionException("Unable to establish connection."))) {
            try (final PreparedStatement preparedStatement = conn.prepareStatement(selectAllSQLString)) {
                try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new Customer(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email")
                        ));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | JDBCConnectionException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        final String insertCustomerSqlString = "INSERT INTO Customer " +
                "(NAME, EMAIL) VALUES (?, ?)";
        try (final Connection conn = getConnection().orElseThrow(() -> new JDBCConnectionException("Unable to establish connection."))) {
            try (final PreparedStatement preparedStatement = conn.prepareStatement(insertCustomerSqlString)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, eMail);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | JDBCConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}


