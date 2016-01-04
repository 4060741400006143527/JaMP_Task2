package com.epam.jamp.patterns.bridge;

import com.epam.jamp.patterns.db.AccountDao;
import com.epam.jamp.patterns.db.BillDao;

import java.math.BigDecimal;
import java.sql.SQLException;

public abstract class PersistentAccount {

    protected AccountDao accountDao;
    protected BillDao billDao;

    protected PersistentAccount(AccountDao accountDao, BillDao billDao) {
        this.accountDao = accountDao;
        this.billDao = billDao;
    }

    public abstract void open(String accountName) throws SQLException, ClassNotFoundException;

    public abstract void placeMoney(String billId, BigDecimal amount) throws SQLException, ClassNotFoundException;

    public abstract void drawMoney(String billId, BigDecimal amount) throws SQLException, ClassNotFoundException;

    public abstract void close(String billId) throws SQLException, ClassNotFoundException;
}
