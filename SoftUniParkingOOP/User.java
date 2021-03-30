package SoftUniParkingOOP;

public class User {
    private final String name;
    private final String plate;

    User(String name, String plate){
        this.name = name;
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return String.format("%s => %s",getName(), getPlate());
    }
}
