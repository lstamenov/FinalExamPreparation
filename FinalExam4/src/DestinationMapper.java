import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> destinations = new ArrayList<>();
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("([=/\\\\])(?<name>[A-Z][A-Za-z]{2,})\\1");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            destinations.add(matcher.group("name"));
        }
        int travelPoints = getPoints(destinations);
        System.out.println("Destinations: " + String.join(", ", destinations));
        System.out.println("Travel Points: " + travelPoints);
    }

    private static int getPoints(List<String> destinations) {
        int sum = 0;
        for (String s : destinations) {
            sum += s.length();
        }
        return sum;
    }
}
