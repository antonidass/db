import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class LoadData {
    private Connection connection = null;

    private void loadBalance() throws SQLException, IOException {
        String sqlQuery = "INSERT INTO " +
                          "Balance(free_cash, stocks_amount, turnover, total_assets, income) " +
                          "VALUES (?, ?, ?, ?, ?)";

        CSVReader readerBalance = new CSVReader(new FileReader("src/main/resources/data/balance.csv"));
        List<String[]> balance = readerBalance.readAll();

        for (int i = 0; i < balance.size(); i++) {
            Integer freeCash = Integer.valueOf(balance.get(i)[0]);
            Integer stockAmount = Integer.valueOf(balance.get(i)[1]);
            Integer turnover = Integer.valueOf(balance.get(i)[2]);
            Integer totalAssets = Integer.valueOf(balance.get(i)[3]);
            Double income = Double.valueOf(balance.get(i)[4]);

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, freeCash);
            statement.setInt(2, stockAmount);
            statement.setInt(3, turnover);
            statement.setInt(4, totalAssets);
            statement.setDouble(5, income);

            statement.executeUpdate();
        }
    }

    private void loadCompany() throws SQLException, IOException {
        String sqlQuery = "INSERT INTO " +
                          "Company(ticker, company_name, foundation_date, sector, country, balance_id, shadow) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?)";

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/company.csv"));
        List<String[]> company = reader.readAll();

        for (int i = 0; i < company.size(); i++) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, company.get(i)[0]);
            statement.setString(2, company.get(i)[1]);
            statement.setString(3, company.get(i)[2]);
            statement.setString(4, company.get(i)[3]);
            statement.setString(5, company.get(i)[4]);
            statement.setInt(6, Integer.parseInt(company.get(i)[5]));
            statement.setString(7, company.get(i)[6]);

            statement.executeUpdate();
        }
    }

    private void loadCompanyOwner() throws SQLException, IOException {
        String sqlQuery = "INSERT INTO " +
                          "CompanyOwner(owner_name, age, date_registration, sex, country, balance_id) " +
                          "VALUES (?, ?, ?, ?, ?, ?)";

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/owner.csv"));
        List<String[]> company = reader.readAll();

        for (int i = 0; i < company.size(); i++) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, company.get(i)[0]);
            statement.setInt(2, Integer.parseInt(company.get(i)[1]));
            statement.setString(3, company.get(i)[2]);
            statement.setString(4, company.get(i)[3]);
            statement.setString(5, company.get(i)[4]);
            statement.setInt(6, Integer.parseInt(company.get(i)[5]));

            statement.executeUpdate();
        }
    }

    private void loadStockExchange() throws SQLException, IOException {
        String sqlQuery = "INSERT INTO " +
                "StockExchange(date_foundation, name_stockexchange, available) " +
                "VALUES (?, ?, ?)";

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/stock_exchange.csv"));
        List<String[]> exchange = reader.readAll();

        for (int i = 0; i < exchange.size(); i++) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, exchange.get(i)[0]);
            statement.setString(2, exchange.get(i)[1]);
            statement.setString(3, exchange.get(i)[2]);

            statement.executeUpdate();
        }
    }

    private void loadStockMarket() throws SQLException, IOException {
        String sqlQuery = "INSERT INTO " +
                "StockMarket(ticker, owner_id, stock_exchange, weekend) " +
                "VALUES (?, ?, ?, ?)";

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/market.csv"));
        List<String[]> exchange = reader.readAll();

        for (int i = 0; i < exchange.size(); i++) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, exchange.get(i)[0]);
            statement.setInt(2, Integer.parseInt(exchange.get(i)[1]));
            statement.setInt(3, Integer.parseInt(exchange.get(i)[2]));
            statement.setString(4, exchange.get(i)[3]);

            statement.executeUpdate();
        }
    }

    private void initConnection() {
        try  {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "password");

            if (connection != null) {
                System.out.println("Connected !");
            } else {
                System.out.println("Failed !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTables() throws SQLException, IOException {
        initConnection();
        loadBalance();
        loadCompany();
        loadCompanyOwner();
        loadStockExchange();
        loadStockMarket();
    }
}
