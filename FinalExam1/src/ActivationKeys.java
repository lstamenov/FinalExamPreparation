import java.util.Scanner;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String activationKey = input;

        input = scanner.nextLine();
        while (!input.equals("Generate")){
            String[] commands = input.split(">>>");
            String command = commands[0];

            switch (command){
                case "Contains":
                    String substring = commands[1];
                    if (activationKey.contains(substring)){
                        System.out.printf("%s contains %s%n", activationKey, substring);
                    }else {
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    String upOrLow = commands[1];
                    int startIndex = Integer.parseInt(commands[2]);
                    int endIndex = Integer.parseInt(commands[3]);
                    switch (upOrLow){
                        case "Upper":
                            int count = 0;
                            String s = activationKey.substring(startIndex, endIndex).toUpperCase();
                            StringBuilder newKey = new StringBuilder(activationKey);
                            for (int i = startIndex; i < endIndex; i++) {
                                newKey.setCharAt(i, s.charAt(count));
                                count++;
                            }
                            activationKey = newKey.toString();
                            break;
                        case "Lower":
                            count = 0;
                            s = activationKey.substring(startIndex, endIndex).toLowerCase();
                            newKey = new StringBuilder(activationKey);
                            for (int i = startIndex; i < endIndex; i++) {
                                newKey.setCharAt(i, s.charAt(count));
                                count++;
                            }
                            activationKey = newKey.toString();
                            break;
                    }
                    System.out.println(activationKey);
                    break;
                case "Slice":
                    startIndex = Integer.parseInt(commands[1]);
                    endIndex = Integer.parseInt(commands[2]);
                    StringBuilder newKey = new StringBuilder(activationKey);

                    newKey.replace(startIndex, endIndex, "");
                    activationKey = newKey.toString();
                    System.out.println(activationKey);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println("Your activation key is: " + activationKey);
    }
}
