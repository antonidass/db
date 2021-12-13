package first;

import ormwork.Balance;
import ormwork.Company;

import java.util.*;
import java.util.stream.Collectors;

public class linqToObject {

    // 1 Все балансы отсортированные по free_cash
    public static void getSorted(List<Balance> resultList) {
        Collections.sort(resultList, (v1, v2) -> { return v1.getFreeCash() - v2.getFreeCash(); });
        System.out.println("Order by free_cash: ");

        for (Balance balance : resultList) {
            System.out.println("id = " + balance.getId() + " free_cash = " + balance.getFreeCash());
        }

        System.out.println();
    }

    // 2 Все балансы с прибылью меньше 10
    public static void getWithCondition(List<Balance> resultList) {
        System.out.println("Balances where income < 10%");
        for (Balance balance : resultList) {
            if (balance.getIncome() < 10.0)
                System.out.println("id = " + balance.getId() + " income = " + balance.getIncome());
        }
        System.out.println();
    }


    // 3 - группировка компаний по сектору
    public static void getGrouped(List<Company> resultList) {
        Map<String, List<Company>> groupedCompanies = resultList.stream().collect(Collectors.groupingBy(Company::getSector));

        System.out.println("Group companies by sector : ");
        for(String key : groupedCompanies.keySet()) {
            System.out.println("key = " + key + "companies = " + groupedCompanies.get(key));
        }
    }

    // 4 - группировка по сектору и страна Россия
    public static void getGroupedWithCondition(List<Company> resultList) {
        Map<String, List<Company>> groupedCompanies = resultList.stream().collect(Collectors.groupingBy(Company::getSector));
        Map<String, List<Company>> resultCompanies = new HashMap<>();

        for (String key : groupedCompanies.keySet()) {
            List<Company> temp = new ArrayList<>();
            for (int i = 0; i < groupedCompanies.get(key).size(); i++) {
                if (Objects.equals(groupedCompanies.get(key).get(i).getCountry(), "Russia")) {
                    temp.add(groupedCompanies.get(key).get(i));
                }
            }
            resultCompanies.put(key, temp);
        }

        System.out.println("Group companies by sector and country is Russia: ");
        for(String key : resultCompanies.keySet()) {
            System.out.println("key = " + key + "companies = " + resultCompanies.get(key));
        }
    }

    // 5 - весь оборот всех компаний
    public static void getAllTurnover(List<Balance> resultList) {
        long sum = 0;
        for (Balance balance : resultList) {
            sum += balance.getTurnover();
        }

        System.out.println("\n\nAll turnover = " + sum + "\n\n");
    }
}
