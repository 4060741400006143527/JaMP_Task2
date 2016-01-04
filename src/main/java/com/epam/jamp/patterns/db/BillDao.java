package com.epam.jamp.patterns.db;

import com.epam.jamp.patterns.model.Bill;

import java.sql.SQLException;

public interface BillDao {

    void save(Bill bill) throws SQLException, ClassNotFoundException;

    void update(Bill bill) throws SQLException, ClassNotFoundException;

    void remove(String billId) throws SQLException, ClassNotFoundException;

    Bill get(String billId)throws SQLException, ClassNotFoundException;
}
