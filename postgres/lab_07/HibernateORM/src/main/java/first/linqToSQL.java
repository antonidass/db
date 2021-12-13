package first;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.BalanceORM;
import orm.CompanyORM;
import ormwork.Balance;
import ormwork.Company;

import java.util.List;

public class linqToSQL {
    public static void initORMS(Session session) {
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Balance");
        List<Balance> balanceList = query.getResultList();

        for (Balance balance : balanceList) {
            BalanceORM balanceORM = new BalanceORM();
            balanceORM.setFreeCash(balance.getFreeCash());
            balanceORM.setIncome(balance.getIncome());
            balanceORM.setTurnover(balance.getTurnover());
            balanceORM.setTotalAssets(balance.getTotalAssets());
            balanceORM.setStocksAmount(balance.getStocksAmount());
            session.save(balanceORM);
        }

        query = session.createQuery("from Company");
        List<Company> companiesList = query.getResultList();

        Query query1 = session.createQuery("from BalanceORM");
        List<BalanceORM> balanceORMS = query1.getResultList();

        for (int i = 0; i < 1000; i++) {
            Company company = companiesList.get(i);
            CompanyORM companyORM = new CompanyORM();
            companyORM.setCompanyName(company.getCompanyName());
            companyORM.setCountry(company.getCountry());
            companyORM.setSector(company.getSector());
            companyORM.setShadow(company.getShadow());
            companyORM.setFoundationDate(company.getFoundationDate());
            companyORM.setTicker(company.getTicker());
            companyORM.setBalanceORM(balanceORMS.get(i));
            session.save(companyORM);
        }

        transaction.commit();
    }


    public static void start(Session session) throws InterruptedException {
//        initORMS(session);

        // 1 select
        Query query1 = session.createQuery("from BalanceORM where id > 10 AND id < 20");
        List<BalanceORM> balanceORMS = query1.getResultList();
        System.out.println(balanceORMS + "\n\n\n");

        // 2 select with join
        query1 = session.createQuery("from CompanyORM as comp join comp.balanceORM as bal");
        List<?> list = query1.getResultList();

        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            CompanyORM companyORM = (CompanyORM)row[0];
            BalanceORM balanceORM = (BalanceORM)row[1];
            System.out.println("company_ticker = " + companyORM.getTicker() + " companyBalance_id = " + companyORM.getBalanceORM() + " balance = " + balanceORM.getId());
        }
        System.out.println("\n\n");

        // 3
        // add new obj
        BalanceORM balanceORM = new BalanceORM();
        balanceORM.setFreeCash(11);
        balanceORM.setIncome((float) 11.1);
        balanceORM.setTurnover(1);
        balanceORM.setTotalAssets(2);
        balanceORM.setStocksAmount(6);

        Transaction transaction = session.beginTransaction();
        session.save(balanceORM);
        transaction.commit();

        // update
        Thread.sleep(8000);
        transaction = session.beginTransaction();
        balanceORM.setStocksAmount(1000000);
        session.update(balanceORM);
        transaction.commit();

        // delete
        Thread.sleep(8000);
        transaction = session.beginTransaction();
        session.delete(balanceORM);
        transaction.commit();



        // 4
       transaction = session.beginTransaction();
       query1 = session.createSQLQuery("call update_free_cash(1000, 222)");
       query1.executeUpdate();
       transaction.commit();
    }
}
