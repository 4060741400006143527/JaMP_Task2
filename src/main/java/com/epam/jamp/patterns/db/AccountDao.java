package com.epam.jamp.patterns.db;

import com.epam.jamp.patterns.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDao {

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
                statement.setLong(1, account.getId());
                statement.setString(2, account.getName());
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
}
