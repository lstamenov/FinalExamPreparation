package PlantDiscovery;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private final String name;
    private int rarity;
    private List<Integer> ratings;

    Plant(String name, int rarity){
        this.name = name;
        this.rarity = rarity;
        this.ratings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void rate(int rating){
        ratings.add(rating);
    }

    public void update(int rarity){
        setRarity(rarity);
    }

    public void reset(){
        setRatings(ratings = new ArrayList<>());
    }

    public static double getAverage(List<Integer> ratings){
        double sum = 0;
        if (ratings.size() == 0){
            return 0.00;
        }
        for (int r : ratings) {
            sum += r;
        }
        return sum / ratings.size();
    }
}
