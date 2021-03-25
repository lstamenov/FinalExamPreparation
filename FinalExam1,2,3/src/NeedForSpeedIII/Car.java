package NeedForSpeedIII;

public class Car {
    private final String model;
    private int mileage;
    private int fuel;

    Car(String model, int mileage, int fuel){
        this.model = model;
        this.mileage = mileage;
        this.fuel = fuel;
    }

    public int getFuel() {
        return fuel;
    }

    public int getMileage() {
        return mileage;
    }

    public String getModel() {
        return model;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void drive(int distance, int fuelNeeded){
        if (fuelNeeded > fuel){
            System.out.println("Not enough fuel to make that ride");
        }else {
            setMileage(mileage + distance);
            setFuel(fuel - fuelNeeded);
            System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", getModel(), distance, fuelNeeded);
            if (getMileage() >= 100000){
                System.out.printf("Time to sell the %s!%n", getModel());
            }
        }
    }

    public void refuel(int fuelToFill){
        if (getFuel() + fuelToFill > 75){
            int fuelFilled = fuelToFill - (fuel + fuelToFill - 75);
            setFuel(75);
            System.out.printf("%s refueled with %d liters%n",getModel(), fuelFilled);
        }else {
            setFuel(fuel + fuelToFill);
            System.out.printf("%s refueled with %d liters%n",getModel(), fuelToFill);
        }
    }

    public void revert(int kilometres){
        if (getMileage() - kilometres <= 10000){
            setMileage(10000);
        }else {
            setMileage(mileage - kilometres);
            System.out.printf("%s mileage decreased by %d kilometers%n",getModel(), kilometres);
        }
    }

    @Override
    public String toString() {
        return String.format("%s -> Mileage: %d kms, Fuel in the tank: %d lt.",getModel(), getMileage(), getFuel());
    }
}
