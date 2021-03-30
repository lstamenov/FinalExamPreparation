package SoftUniParkingOOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<User> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String command = data[0];
            if (command.equals("register")){
                String username = data[1];
                String plate = data[2];
                if (isSuchUser(username, users) == -1){
                    User user = new User(username, plate);
                    users.add(user);
                    System.out.printf("%s registered %s successfully%n",username, plate);
                }else {
                    int index = isSuchUser(username, users);
                    System.out.printf("ERROR: already registered with plate number %s%n", users.get(index).getPlate());
                }
            }else if (command.equals("unregister")){
                String username = data[0];
                if (isSuchUser(username, users) != -1){
                    int index = isSuchUser(username, users);
                    users.remove(index);
                    System.out.printf("%s unregistered successfully%n", username);
                }else {
                    System.out.printf("ERROR: user %s not found%n", username);
                }
            }
        }
        users.forEach(user -> System.out.println(user.toString()));
    }
    static int isSuchUser(String username, List<User> users){
        for (User u : users) {
            if (u.getName().equals(username)){
                return users.indexOf(u);
            }
        }
        return -1;
    }
}
