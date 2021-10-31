import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class GenerateData {
    public static void createCompany() throws IOException {
        String company = "src/main/resources/data/company.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(company));

        String names = "Apple:Orange:Juice:Microsoft:SberBank:Maxim:Huntington:Palantir:AMD:NVIDIA:Amazon:Tesla:Virgin:Lukoil:Nickel:NLT:Hospital:Simon:Samsung:Toshiba:BOSCH:McDonalds:BurgerKing:KFC:Papita:Mindal:Mouse:HP:Asus:Acer:Meuzau:Alalane:Mirrow:Reebol:Adidas:Nike:Vox:Marshal";
        String subNames = "Corporation:Company:Enterprise:Empire:Galactic:Holder:Host:Inc";
        Random random = new Random();

        ArrayList<Integer> balanceId = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            balanceId.add(i + 1);
        }
        Collections.shuffle(balanceId);

        String[] Names = names.split(":");
        String[] SubNames = subNames.split(":");
        String[] sectors =  {"Technology", "Semiconductors", "CommunicationServices", "Consumer Cyclical", "Healtcare", "Financial", "Industrials", "Energy", "Materials"};
        String[] contries = {"USA", "Russia", "China", "Japan", "Germany", "UK", "France", "Italy"};

        String[] isShadow = {"YES", "NO"};

        String tickers[] = new String[1000];
        int count = 0;
        while (count != 1000) {
            char[] ticker = new char[random.nextInt(4) + 3];
            for (int j = 0; j < ticker.length; j++) {
                ticker[j] = (char)('a' + random.nextInt(26));
            }

            String tic = new String(ticker);
            if (!Arrays.stream(tickers).anyMatch(tic::equals)) {
                tickers[count] = tic;
                count++;
            }
        }

        for (int i = 0; i < 1000; i++) {

            String fullTicker = tickers[i];
            String fullName = Names[random.nextInt(Names.length)] + SubNames[random.nextInt(SubNames.length)];
            String foundationDate = String.valueOf(random.nextInt(122) + 1900) + "-" + String.valueOf(random.nextInt(13) + 1) + "-" + String.valueOf(random.nextInt(32) + 1) ;
            String sector = sectors[random.nextInt(sectors.length)];
            String country = contries[random.nextInt(contries.length)];
            String shadow = String.valueOf(isShadow[random.nextInt(2)]);

            String[] record = {fullTicker, fullName, foundationDate, sector, country, balanceId.get(i).toString(), shadow};
            writer.writeNext(record);
        }
        writer.close();
    }

    public static void createOwner() throws IOException {
        String company = "src/main/resources/data/owner.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(company));

        Random random = new Random();
        ArrayList<Integer> balanceId = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            balanceId.add(i + 1);
        }
        Collections.shuffle(balanceId);

        String names[] = {"Igor", "Ivan", "Alina", "Maria", "Ksenia", "Anton", "Vitalik", "Pavel", "Jonh", "Jorge"};
        String[] contries = {"USA", "Russia", "China", "Japan", "Germany", "UK", "France", "Italy"};
        String[] sex = {"M", "F"};


        for (int i = 0; i < 1000; i++) {
            String name = names[random.nextInt(names.length)];
            String age =  String.valueOf(random.nextInt(75) + 18);
            String foundationDate = String.valueOf(random.nextInt(122) + 1900) + "-" + String.valueOf(random.nextInt(13) + 1) + "-" + String.valueOf(random.nextInt(32) + 1) ;
            String seX = sex[random.nextInt(2)];
            String country = contries[random.nextInt(contries.length)];

            String[] record = {name, age, foundationDate, seX, country, balanceId.get(i).toString()};

            writer.writeNext(record);
        }
        writer.close();
    }

    public static void createBalance() throws IOException {
        String company = "src/main/resources/data/balance.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(company));

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String freeCash = String.valueOf(random.nextInt(100000));
            String stocksAmount = String.valueOf(random.nextInt(10000000));
            String turnOver = String.valueOf(random.nextInt(100000000));
            String total = String.valueOf(random.nextInt(10000000));
            String income = String.valueOf(random.nextDouble() * 100);

            String[] record = {freeCash, stocksAmount, turnOver, total, income};
            writer.writeNext(record);
        }
        writer.close();
    }

    public static void createStockMarket() throws IOException, CsvException {
        String company = "src/main/resources/data/market.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(company));

        CSVReader readerCompany = new CSVReader(new FileReader("src/main/resources/data/company.csv"));
        String[] sex = {"YES", "NO"};

        List<String[]> allCompanies = readerCompany.readAll();
        Collections.shuffle(allCompanies);

        List<Integer> ownersId = new ArrayList<>(1000);
        for (int i = 0; i< 1000; i++) {
            ownersId.add(i+1);
        }
        Collections.shuffle(ownersId);

        List<Integer> balancesId = new ArrayList<>(1000);
        for (int i = 0; i< 1000; i++) {
            balancesId.add(i+1);
        }
        Collections.shuffle(balancesId);

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String ticker = String.valueOf(allCompanies.get(i)[0]);
            String ownerID = String.valueOf(ownersId.get(i));
            String stockExchangeID = String.valueOf(balancesId.get(i));
            String weeekend = String.valueOf(sex[random.nextInt(2)]);

            String[] record = {ticker, ownerID, stockExchangeID, weeekend};
            writer.writeNext(record);
        }
        writer.close();
    }


    public static void createStocksExchange() throws IOException, CsvException {
        String company = "src/main/resources/data/stock_exchange.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(company));

        CSVReader readerCompany = new CSVReader(new FileReader("src/main/resources/data/stock_exchange.csv"));
        String[] sex = {"YES", "NO"};
        String[] contries = {"SPdaba", "Russianaac", "Chinadac", "JAPANdac", "NASDSAQ", "USAbIr", "MOsbirja", "NYSE"};

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String ticker = String.valueOf(contries[random.nextInt(contries.length)]);
            String foundationDate = String.valueOf(random.nextInt(122) + 1900) + "-" + String.valueOf(random.nextInt(13) + 1) + "-" + String.valueOf(random.nextInt(32) + 1) ;

            String available = String.valueOf(sex[random.nextInt(2)]);

            String[] record = {ticker, foundationDate, available};
            writer.writeNext(record);
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException, CsvException, SQLException {
//        createBalance();
//        createCompany();
//        createOwner();
//        createStocksExchange();
//        createStockMarket();

        LoadData loader = new LoadData();
        loader.loadTables();




    }
}


