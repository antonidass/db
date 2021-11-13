import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {


    // 1 - Выполнить скалярный запрос
    // Средняя прибыль владельцов с именем name_owner
    public static void avgIncome(String name_owner, Connection connection) throws SQLException {
        String query = "SELECT AVG(income) FROM Balance JOIN CompanyOwner ON CompanyOwner.balance_id = Balance.id WHERE owner_name = ?";
        double income = 0.0;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name_owner);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            income = resultSet.getDouble(1);
        }

        System.out.println("Average " + name_owner + "'s income = " + income + "\n\n");
    }

    // 2 - Выполнить запрос с несколькими соединениями (JOIN)
    // По входному id_owner вывести имена компаний которыми он владеет
    public static void getOwnerCompanies(Connection connection, int id_owner) throws SQLException {
        String query =  "SELECT stockmarket.owner_id, companyowner.owner_name, stockmarket.ticker, company.company_name  FROM Company " +
                        "JOIN StockMarket ON Company.ticker = StockMarket.ticker" +
                        " JOIN CompanyOwner ON stockmarket.owner_id = companyowner.id " +
                        "WHERE stockmarket.owner_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_owner);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("id_owner --- owner_name --- ticker     --- company_name");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "      --- " +
                               resultSet.getString(2) + "     --- " +
                                resultSet.getString(3) + "        " +
                                resultSet.getString(4));
        }
        System.out.println("\n");
    }


    // 3 - Выполнить запрос с ОТВ и оконными функциями
    // Вывод компаний с прибылью меньше income процентов
    public static void getBalanceCompanies(Connection connection, double income) throws SQLException {
        String query = "SELECT company_name, sector, income, row_number() OVER (PARTITION BY sector ORDER BY income) FROM Company JOIN Balance ON Balance.id = Company.balance_id WHERE income < ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, income);
        ResultSet resultSet = preparedStatement.executeQuery();


        System.out.format("%20s%26s%20s%20s\n", "company_name", "sector", "income", "row_number");
        while (resultSet.next()) {
            System.out.format("%20s%26s%20s%20s\n", resultSet.getString(1),
                    resultSet.getString(2), resultSet.getDouble(3),
                    resultSet.getString(4));
        }
        System.out.println("\n");
    }


    // 4. Выполнить запрос к метаданным;
    // Вывод таблиц из схемы
    public static void getTables(Connection connection, String schema) throws SQLException {
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, schema);

        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("tables\n");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        System.out.println("\n");
    }

    // 5. Вызвать скалярную функцию (написанную в третьей лабораторной работе);
    // Вывод суммы свободного кэша в заданном секторе
    public static void sumFreeCash(Connection connection, String sector_name) throws SQLException {
        String query = "{? = call SumFreeCash(?)}";
        int sum = 0;

        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.setString(2, sector_name);
        callableStatement.executeUpdate();
        sum = callableStatement.getInt(1);

        System.out.println("SumFreeCash companies in sector " + sector_name + "  = " + sum + "\n\n");
    }


    // 6. Вызвать многооператорную или табличную функцию (написанную в третьей работе)
    // Вывод краткой информации о владельце (имя, возраст, оборот)
    public static void getShortInfo(Connection connection, int id_owner) throws SQLException {
        String query = "SELECT * FROM OwnerShortRow(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_owner);

        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Short info about user with id = " + id_owner + " : ");
        System.out.format("%10s%10s%10s\n", "name", "age", "turnover");

        while (resultSet.next()) {
            System.out.format("%10s%10s%10s\n", resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3));
        }

        System.out.println("\n");
    }

    // 7. Вызвать хранимую процедуру (написанную в третьей лабораторной работе);
    //  Обновить кэш владельца баланса с id = id_balance на increment
    public static void updateBalance(Connection connection, int id_balance, int increment) throws SQLException {
        String query = "CALL UpdateFreeCash(?, ?)";

        PreparedStatement callableStatement = connection.prepareStatement(query);
        callableStatement.setInt(1, id_balance);
        callableStatement.setInt(2, increment);
        callableStatement.execute();
        System.out.println("Balance table was updated!" + "\n\n");
    }

    // 8. Вызвать системную функцию или процедуру;
    // Вывести название текущей таблицы
    public static void currentDatabase(Connection connection) throws SQLException {
        String query = "SELECT current_database()";

        PreparedStatement callableStatement = connection.prepareStatement(query);
        callableStatement.executeQuery();

        ResultSet resultSet = callableStatement.getResultSet();
        while (resultSet.next()) {
            System.out.println("Current database = " + resultSet.getString(1) + "\n\n");
        }
    }

    // 9. Создать таблицу в базе данных, соответствующую тематике БД;
    // Таблица людей с шорт позициями
    public static void createTable(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS ShortPeoples (" +
                "id INT PRIMARY KEY," +
                "available_short INT, " +
                "debt INT)";

        Statement statement = connection.createStatement();
        statement.execute(query);

        System.out.println("Success!\n\n");
    }

    // 10. Выполнить вставку данных в созданную таблицу с использованием инструкции INSERT или COPY
    // Вставить в таблицу значения
    public static  void insertIntoTable(Connection connection, int id, int as, int debt) throws SQLException {
        String query = "INSERT INTO shortpeoples(id, available_short, debt) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, as);
        preparedStatement.setInt(3, debt);
        preparedStatement.execute();

        System.out.println("Success!");
    }

    public static void  delete(Connection connection, String ticker) throws SQLException {
        String query = "DELETE FROM Company WHERE Company.ticker = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ticker);

        preparedStatement.execute();

        System.out.println("SUCCESS");
    }

    static String menu[] = {
            "1. Scalar query.",
            "2. Multiple join query",
            "3. Common table expression with window function.",
            "4. Querying metadata.",
            "5. Function call.",
            "6. Table function call.",
            "7. Stored function call.",
            "8. System stored function call.",
            "9. Create new table.",
            "10. Insert into new table.",
            "12 Delete",
            "11. Exit."
    };

    static Scanner scanner = new Scanner(System.in);


    public static Connection makeConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    static void printMenu()  {
        for (String paragraph: menu)  {
            System.out.println(paragraph);
        }
    }

    static void chooseAction(Connection conn) throws SQLException  {
        System.out.print("Choose action: ");
        int action = scanner.nextInt();

        while (action > 12 || action < 1)  {
            System.out.print("Invalid input. Enter action again: ");
            action = scanner.nextInt();
        }

        switch (action)  {
            case 1:
                avgIncome("Igor", conn);
                break;
            case 2:
                getOwnerCompanies(conn, 484);
                break;
            case 3:
                getBalanceCompanies(conn, 10.0);
                break;
            case 4:
                getTables(conn, "public");
                break;
            case 5:
                sumFreeCash(conn, "Technology");
                break;
            case 6:
                getShortInfo(conn, 1000);
                break;
            case 7:
                updateBalance(conn, 1000, 11);
                break;
            case 8:
                currentDatabase(conn);
                break;
            case 9:
                createTable(conn);
                break;
            case 10:
                insertIntoTable(conn, new Random().nextInt() % 100 + 100, 5, 5);
                break;
            case 12:
                delete(conn, "wmtjp");
                break;
            default:
                conn.close();
                scanner.close();
                System.exit(0);
        }
    }

    public static void run(Connection conn) throws SQLException  {
        printMenu();
        chooseAction(conn);
        run(conn);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = makeConnection();
        run(connection);
    }
}
