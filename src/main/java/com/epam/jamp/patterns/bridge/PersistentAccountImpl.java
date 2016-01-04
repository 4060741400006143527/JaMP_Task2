package com.epam.jamp.patterns.bridge;

import com.epam.jamp.patterns.db.AccountDao;
import com.epam.jamp.patterns.db.BillDao;
import com.epam.jamp.patterns.model.Account;
import com.epam.jamp.patterns.model.Bill;

import java.math.BigDecimal;
import java.sql.SQLException;

public class PersistentAccountImpl extends PersistentAccount {

    public PersistentAccountImpl(AccountDao accountDao, BillDao billDao) {
        super(accountDao, billDao);
    }

    @Override
    public void open(String accountName) throws SQLException, ClassNotFoundException {
       Account account = accountDao.find(accountName);

        Bill bill = new Bill();
        bill.setAccountId(account.getId());
        bill.setId("BND1586432");
        billDao.save(bill);
    }

    @Override
    public void placeMoney(String billId, BigDecimal amount) throws SQLException, ClassNotFoundException {
        Bill bill = billDao.get(billId);
        bill.setAmount(bill.getAmount().add(amount));
        billDao.update(bill);
    }

    @Override
    public void drawMoney(String billId, BigDecimal amount) throws SQLException, ClassNotFoundException{
        Bill bill = billDao.get(billId);
        bill.setAmount(bill.getAmount().subtract(amount));
        billDao.update(bill);
    }

    @Override
    public void close(String billId) throws SQLException, ClassNotFoundException {
        Bill bill = billDao.get(billId);
        bill.setActive(false);
        billDao.update(bill);
    }
}
