package com.epam.jamp.patterns.db;

import com.epam.jamp.patterns.model.Account;

import java.sql.SQLException;

public interface AccountDao {

    void save(Account account) throws SQLException, ClassNotFoundException;

    void update(Account account) throws SQLException, ClassNotFoundException;

    void remove(Long accountId) throws SQLException, ClassNotFoundException;

    Account find(String accountName) throws SQLException, ClassNotFoundException;
}
