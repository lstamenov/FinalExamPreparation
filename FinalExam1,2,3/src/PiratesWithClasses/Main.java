package PiratesWithClasses;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Town> towns = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("Sail")) {
            String[] townData = input.split("[|]{2}");
            String townName = townData[0];
            int population = Integer.parseInt(townData[1]);
            int gold = Integer.parseInt(townData[2]);

            if (isSuchTown(townName, towns) != -1) {
                int index = isSuchTown(townName, towns);
                Town town = towns.get(index);
                town.setGold(town.getGold() + gold);
                town.setPopulation(town.getPopulation() + population);
                towns.set(index, town);
            } else {
                Town town = new Town(townName, population, gold);
                towns.add(town);
            }

            input = scanner.nextLine();
        }

        input = scanner.nextLine();
        while (!input.equals("End")) {
            String[] commands = input.split("=>");
            String command = commands[0];

            if (command.equals("Plunder")) {
                String townName = commands[1];
                int population = Integer.parseInt(commands[2]);
                int gold = Integer.parseInt(commands[3]);

                if (isSuchTown(townName, towns) != -1) {
                    int index = isSuchTown(townName, towns);
                    Town town = towns.get(index);
                    town.plunder(population, gold);
                    towns.set(index, town);
                    if (town.getGold() < 1 || town.getPopulation() < 1){
                        System.out.printf("%s has been wiped off the map!%n", town.getName());
                        towns.remove(index);
                    }
                }
            } else if (command.equals("Prosper")) {
                String townName = commands[1];
                int gold = Integer.parseInt(commands[2]);
                if (isSuchTown(townName, towns) != -1){
                    int index = isSuchTown(townName, towns);
                    Town town = towns.get(index);
                    town.prosper(gold);
                    towns.set(index, town);
                }
            }
            input = scanner.nextLine();
        }

        System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n", towns.size());
        towns.stream()
                .sorted(Comparator.comparing(Town::getGold).thenComparing(Town::getName).reversed())
                .forEach(town -> System.out.println(town.toString()));
    }

    private static int isSuchTown(String townName, List<Town> towns) {
        for (Town t : towns) {
            if (t.getName().equals(townName)){
                return towns.indexOf(t);
            }
        }
        return -1;
    }
}
