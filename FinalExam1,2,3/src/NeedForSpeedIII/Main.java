package NeedForSpeedIII;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Car> cars = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] carDetails = scanner.nextLine().split("\\|");
            String model = carDetails[0];
            int mileage = Integer.parseInt(carDetails[1]);
            int fuel = Integer.parseInt(carDetails[2]);

            Car car = new Car(model, mileage, fuel);
            cars.add(car);
        }

        String input = scanner.nextLine();
        while (!input.equals("Stop")){
            String[] commands = input.split(" : ");
            String command = commands[0];

            switch (command){
                case "Drive":
                    String carModel = commands[1];
                    int distance = Integer.parseInt(commands[2]);
                    int fuel = Integer.parseInt(commands[3]);
                    if (isSuchCar(carModel, cars) != -1){
                        int index = isSuchCar(carModel, cars);
                        cars.get(index).drive(distance, fuel);
                        if (cars.get(index).getMileage() >= 100000){
                            cars.remove(index);
                        }
                    }
                    break;
                case "Refuel":
                    carModel = commands[1];
                    fuel = Integer.parseInt(commands[2]);
                    if (isSuchCar(carModel, cars) != -1){
                        int index = isSuchCar(carModel, cars);
                        cars.get(index).refuel(fuel);
                    }
                    break;
                case "Revert":
                    carModel = commands[1];
                    int kilometres = Integer.parseInt(commands[2]);
                    if (isSuchCar(carModel, cars) != -1){
                        int index = isSuchCar(carModel, cars);
                        cars.get(index).revert(kilometres);
                    }
            }
            input = scanner.nextLine();
        }
        cars.stream()
                .sorted(Comparator.comparing(Car::getMileage).reversed().thenComparing(Car::getModel))
                .forEach(car -> System.out.println(car.toString()));
    }

    private static int isSuchCar(String carModel, List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getModel().equals(carModel)) {
                return i;
            }
        }
        return -1;
    }

    static class Car {
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
}
