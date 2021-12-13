package ormwork;

import first.linqToJson;
import first.linqToObject;
import first.linqToSQL;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void startFirst(Session session) {
        Query query = session.createQuery("from Balance");
        List<Balance> resultList = query.getResultList();

        Query query2 = session.createQuery("from Company ");
        List<Company> resultListCompany = query2.getResultList();

        linqToObject.getSorted(resultList);
        linqToObject.getWithCondition(resultList);
        linqToObject.getGrouped(resultListCompany);
        linqToObject.getGroupedWithCondition(resultListCompany);
        linqToObject.getAllTurnover(resultList);

    }

    public static void startSecond(Session session) {
        Query query2 = session.createQuery("from Company ");
        List<Company> resultListCompany = query2.getResultList();

        linqToJson.writeToJson(resultListCompany);
    }

    public static void startThird(Session session) throws InterruptedException {
        linqToSQL.start(session);
    }


    public static void main(String[] args) throws InterruptedException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                startFirst(session);
            } else if (choice == 2) {
                startSecond(session);
            } else if (choice == 3) {
                startThird(session);
            } else {
                break;
            }
        }

        session.close();
        HibernateUtil.close();
    }
}
