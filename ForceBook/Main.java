package ForceBook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Side> sides = new ArrayList<>();
        String input = scanner.nextLine();
        while (!input.equals("Lumpawaroo")){
            String[] commands = input.split("->|\\|");
            String delimiter = commands[1];
            if (delimiter.equals("|")){
                String side = commands[0];
                String user = commands[2];
                if (isSuchUser(sides, user) == -1){
                    if (isSuchSide(sides, side) != -1){
                        int index = isSuchSide(sides, side);
                        sides.get(index).addUser(user);
                    }else {
                        Side newSide = new Side(side, new ArrayList<>());
                        newSide.addUser(user);
                        sides.add(newSide);
                    }
                }
            }else if (delimiter.equals("->")){
                String user = commands[0];
                String side = commands[2];
                if (isSuchUser(sides, user) != -1){
                    int indexOfSide = isSuchUser(sides, user);
                    sides.get(indexOfSide).removeUser(user);
                    int index = isSuchSide(sides, side);
                    if (index != -1){
                        sides.get(index).addUser(user);
                    }else {
                        Side newSide = new Side(side, new ArrayList<>());
                        newSide.addUser(user);
                        sides.add(newSide);
                    }
                }else {
                    if (isSuchSide(sides, side) != -1){
                        int index = isSuchSide(sides, side);
                        sides.get(index).addUser(user);
                    }else {
                        Side side1 = new Side(side, new ArrayList<>());
                        side1.addUser(user);
                        sides.add(side1);
                    }
                    System.out.printf("%s joins the %s side!%n", user, side);
                }
            }
            input = scanner.nextLine();
        }
        sides.stream()
                .filter(side -> side.getUsers().size() > 0)
                .sorted(Comparator.comparing(Side::getUsersSize).reversed().thenComparing(Side::getName))
                .forEach(side -> {
                    System.out.printf("Side: %s, Members: %d%n",side.getName(), side.getUsersSize());
                    side.getUsers().stream()
                            .sorted().forEach(user -> System.out.println("! " + user));
                });
    }

    private static int isSuchSide(List<Side> sides, String side) {
        for (Side s : sides) {
            if (s.getName().equals(side)){
                return sides.indexOf(s);
            }
        }
        return -1;
    }

    private static int isSuchUser(List<Side> sides, String user) {
        for (Side s : sides) {
            for (String u : s.getUsers()) {
                if (u.equals(user)){
                    return sides.indexOf(s);
                }
            }
        }
        return -1;
    }
}
