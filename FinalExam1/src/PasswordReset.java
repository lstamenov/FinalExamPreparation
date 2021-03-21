import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        String input = scanner.nextLine();
        while (!input.equals("Done")){
            String[] commands = input.split("\\s+");
            String command = commands[0];

            switch (command){
                case "TakeOdd":
                    password = takeOdd(password);
                    System.out.println(password);
                    break;
                case "Cut":
                    int index = Integer.parseInt(commands[1]);
                    int length = Integer.parseInt(commands[2]);
                    password = cut(index, length, password);
                    System.out.println(password);
                    break;
                case "Substitute":
                    String substring = commands[1];
                    String substitute = commands[2];
                    password = replaceSubstring(substring, substitute, password);
                    break;
            }


            input = scanner.nextLine();
        }
        System.out.println("Your password is: " + password);
    }

    private static String replaceSubstring(String substring, String substitute, String password) {
        if (password.contains(substring)){
            password = password.replace(substring, substitute);
            System.out.println(password);
        }else {
            System.out.println("Nothing to replace!");
        }
        return password;
    }

    private static String cut(int index, int length, String password) {
        StringBuilder stringBuilder = new StringBuilder(password);
        stringBuilder.replace(index, index + length, "");
        password = stringBuilder.toString();
        return password;
    }

    private static String takeOdd(String password) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < password.length(); i++) {
            if (i % 2 != 0){
                stringBuilder.append(password.charAt(i));
            }
        }
        password = stringBuilder.toString();
        return password;
    }
}
