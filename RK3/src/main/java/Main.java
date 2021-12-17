import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.Scanner;

public class Main {
    public static void startFirst(Session session) {
        // Найти все отделы в которых нет сотрудников моложе 25
//        Query query = session.createQuery("from Records JOIN HibernateUtil");
    }

    public static void startSecond(Session session) {

    }

    public static void startThird(Session session) {

    }


    public static void main(String[] args) {
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
