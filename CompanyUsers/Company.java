package CompanyUsers;

import java.util.List;

public class Company {
    private final String name;
    private List<String> employees;

    Company(String name, List<String> employees){
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void addEmployee(String name){
        employees.add(name);
    }
}
