package PiratesWithClasses;

public class Town {
    String name;
    int population;
    int gold;

    Town(String name, int population, int gold){
        this.name = name;
        this.population = population;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void prosper(int goldToAdd){
        if (goldToAdd >= 0){
            setGold(gold + goldToAdd);
            System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n",goldToAdd, name, gold);
        }else {
            System.out.println("Gold added cannot be a negative number!");
        }
    }

    public void plunder(int peopleKilled, int goldStolen){
        setGold(gold - goldStolen);
        setPopulation(population - peopleKilled);
        System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", name, goldStolen, peopleKilled);
    }

    @Override
    public String toString() {
        return String.format("%s -> Population: %d citizens, Gold: %d kg",name, population, gold);
    }
}
