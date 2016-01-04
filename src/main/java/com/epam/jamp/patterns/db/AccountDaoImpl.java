package com.epam.jamp.patterns.db;

import com.epam.jamp.patterns.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {

    private final ConnectionCreator connectionCreator = ConnectionCreator.getInstance();

    public void save(Account account) throws SQLException, ClassNotFoundException {
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Account (ID, NAME) VALUES (HIBERNATE_SEQUENCE.nextval, ?)")) {
                statement.setString(1, account.getName());
                statement.execute();
            }
        }
    }

    public void update(Account account) throws SQLException, ClassNotFoundException {
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Account SET NAME = ? WHERE ID = ?")) {
                statement.setLong(2, account.getId());
                statement.setString(1, account.getName());
                statement.execute();
            }
        }
    }

    public void remove(Long accountId) throws SQLException, ClassNotFoundException {
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Account WHERE ID = ?")) {
                statement.setLong(1, accountId);
                statement.execute();
            }
        }
    }

    @Override
    public Account find(String accountName) throws SQLException, ClassNotFoundException {
        Account account = null;
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Account WHERE NAME = ?")) {
                statement.setString(1, accountName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        account = parse(resultSet);
                    }
                }
            }
        }
        return account;
    }

    private Account parse(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("ID"));
        account.setName(resultSet.getString("NAME"));
        return account;
    }
}
