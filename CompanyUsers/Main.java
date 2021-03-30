package CompanyUsers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Company> companies = new ArrayList<>();
        String input = scanner.nextLine();
        while (!input.equals("End")){
            String[] data = input.split(" -> ");
            String companyName = data[0];
            String employeeId = data[1];
            if (isSuchCompany(companyName, companies) != -1){
                int index = isSuchCompany(companyName, companies);
                if (!isSuchID(employeeId, companies.get(index))){
                    companies.get(index).addEmployee(employeeId);
                }
            }else {
                Company company = new Company(companyName, new ArrayList<>());
                company.addEmployee(employeeId);
                companies.add(company);
            }
            input = scanner.nextLine();
        }
        companies.stream()
                .sorted(Comparator.comparing(Company::getName))
                .forEach(company -> {
                    System.out.println(company.getName());
                    company.getEmployees()
                            .forEach(e -> System.out.println("-- " + e));
                });
    }

    private static boolean isSuchID(String employeeId, Company company) {
        for (String id : company.getEmployees()) {
            if (id.equals(employeeId)){
                return true;
            }
        }
        return false;
    }

    private static int isSuchCompany(String companyName, List<Company> companies){
        for (Company company : companies) {
            if (company.getName().equals(companyName)){
                return companies.indexOf(company);
            }
        }
        return -1;
    }
}
