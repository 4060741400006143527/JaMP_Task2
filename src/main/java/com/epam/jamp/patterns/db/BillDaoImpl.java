package com.epam.jamp.patterns.db;

import com.epam.jamp.patterns.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDaoImpl implements BillDao {

    private final ConnectionCreator connectionCreator = ConnectionCreator.getInstance();

    @Override
    public void save(Bill bill) throws SQLException, ClassNotFoundException {
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Bill (ID, ACCOUNT_ID, AMOUNT, IS_ACTIVE) VALUES (?, ?, ?, ?)")) {
                statement.setString(1, bill.getId());
                statement.setLong(2, bill.getAccountId());
                statement.setBigDecimal(3, bill.getAmount());
                statement.setBoolean(4, bill.isActive());
                statement.execute();
            }
        }
    }

    @Override
    public void update(Bill bill) throws SQLException, ClassNotFoundException {
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Bill SET AMOUNT = ? WHERE ID = ?")) {
                statement.setBigDecimal(1, bill.getAmount());
                statement.setString(2, bill.getId());
                statement.execute();
            }
        }
    }

    @Override
    public void remove(String billId) throws SQLException, ClassNotFoundException {
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Bill WHERE ID = ?")) {
                statement.setString(1, billId);
                statement.execute();
            }
        }
    }

    @Override
    public Bill get(String billId) throws SQLException, ClassNotFoundException {
        Bill bill = null;
        try (Connection connection = connectionCreator.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Bill WHERE ID = ?")) {
                statement.setString(1, billId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bill = parse(resultSet);
                    }
                }
            }
        }
        return bill;
    }

    private Bill parse(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        bill.setId(resultSet.getString("ID"));
        bill.setAccountId(resultSet.getLong("ACCOUNT_ID"));
        bill.setAmount(resultSet.getBigDecimal("AMOUNT"));
        bill.setActive(resultSet.getBoolean("IS_ACTIVE"));
        return bill;
    }
}
