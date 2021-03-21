import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Pirates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<Integer, Integer>> targetedCities = new TreeMap<>();

        String input = scanner.nextLine();
        while (!input.equals("Sail")){
            String[] townData = input.split("[|]{2}");
            String town = townData[0];
            int population = Integer.parseInt(townData[1]);
            int gold = Integer.parseInt(townData[2]);

            if (targetedCities.containsKey(town)){
                int oldPopulation = 0;
                int oldGold = 0;
                Map<Integer, Integer> map = targetedCities.get(town);
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    oldPopulation = e.getKey();
                    oldGold = e.getValue();
                }
                map.remove(oldPopulation);
                map.put(oldPopulation + population, oldGold + gold);
                targetedCities.put(town, map);
            }else {
                if (population > 0 && gold > 0){
                    Map<Integer, Integer> map = new LinkedHashMap<>();
                    map.put(population, gold);
                    targetedCities.put(town, map);
                }
            }
            input = scanner.nextLine();
        }

        input = scanner.nextLine();
        while (!input.equals("End")){
            String[] commands = input.split("=>");
            String command = commands[0];

            if (command.equals("Plunder")){
                String town = commands[1];
                int population = Integer.parseInt(commands[2]);
                int gold = Integer.parseInt(commands[3]);

                System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n",town, gold, population);
                Map<Integer, Integer> map = targetedCities.get(town);
                int oldPopulation = 0;
                int oldGold = 0;
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    oldPopulation = e.getKey();
                    oldGold = e.getValue();
                }
                map.remove(oldPopulation);
                if (oldPopulation - population < 1 || oldGold - gold < 1){
                    targetedCities.remove(town);
                    System.out.printf("%s has been wiped off the map!%n", town);
                }else {
                    map.put(oldPopulation - population, oldGold - gold);
                    targetedCities.put(town, map);
                }
            }else if (command.equals("Prosper")){
                String town = commands[1];
                int gold = Integer.parseInt(commands[2]);

                if (gold >= 0){
                    Map<Integer, Integer> map = targetedCities.get(town);
                    int key = 0;
                    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                        key = e.getKey();
                    }
                    map.put(key, map.get(key) + gold);
                    System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n",gold, town, map.get(key));
                }else {
                    System.out.println("Gold added cannot be a negative number!");
                }
            }
            input = scanner.nextLine();
        }
        System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n",targetedCities.size());
        for (Map.Entry<String, Map<Integer, Integer>> e : targetedCities.entrySet()) {
            for (Map.Entry<Integer, Integer> e1 : e.getValue().entrySet()) {
                System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", e.getKey(), e1.getKey(), e1.getValue());
            }
        }
    }
}
