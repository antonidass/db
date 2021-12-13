package first;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ormwork.Company;

import java.util.List;

public class linqToJson {
    public static void writeToJson(List<Company> companies) {
        Gson gson = new Gson();
        // create document
        String jsonCompany = gson.toJson(companies);

        // 1 read from json
        List<Company> companiesFromJson = gson.fromJson(jsonCompany, new TypeToken<List<Company>>(){}.getType());
        System.out.println(companiesFromJson + "\n\n\n");

        // 2 update json
        for (Company company : companiesFromJson) {
            company.setCompanyName(company.getCompanyName() + "updated");
        }

        jsonCompany = gson.toJson(companiesFromJson);
        System.out.println(jsonCompany + "\n\n\n");

        // 3 add json
        companiesFromJson.add(companies.get(companies.size() - 1));
        jsonCompany = gson.toJson(companiesFromJson);
        System.out.println(jsonCompany + "\n\n");
    }
}
