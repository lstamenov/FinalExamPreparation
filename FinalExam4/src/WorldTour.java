import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tour = scanner.nextLine();
        String input = scanner.nextLine();
        while (!input.equals("Travel")){
            String[] commands = input.split(":");
            String command = commands[0];
            switch (command){
                case "Add Stop":
                    int index = Integer.parseInt(commands[1]);
                    String stop = commands[2];
                    if (index >= 0 && index < tour.length()){
                        tour = addStop(tour, stop, index);
                    }
                    System.out.println(tour);
                    break;
                case "Remove Stop":
                    int startIndex = Integer.parseInt(commands[1]);
                    int endIndex = Integer.parseInt(commands[2]);
                    if (startIndex < endIndex && startIndex >= 0 &&
                            startIndex < tour.length() &&
                            endIndex < tour.length()){
                        tour = removeStop(startIndex, endIndex, tour);
                    }
                    System.out.println(tour);
                    break;
                case "Switch":
                    String oldString = commands[1];
                    String newString = commands[2];
                    if (tour.contains(oldString)){
                        tour = tour.replace(oldString, newString);
                    }
                    System.out.println(tour);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Ready for world tour! Planned stops: " + tour);
    }

    private static String removeStop(int startIndex, int endIndex, String tour) {
        String substring = tour.substring(startIndex, endIndex + 1);
        tour = tour.replace(substring, "");
        return tour;
    }

    private static String addStop(String tour, String stop, int index) {
        String addedStopStr = "";
        String substring = tour.substring(index);
        addedStopStr += tour.substring(0, index);
        addedStopStr += stop;
        addedStopStr += substring;
        return addedStopStr;
    }
}
