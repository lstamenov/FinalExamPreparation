import java.util.Scanner;

public class SecretChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String concealedMessage = scanner.nextLine();
        String input = scanner.nextLine();
        while (!input.equals("Reveal")){
            String[] commands = input.split(":\\|:");
            String command = commands[0];

            switch (command){
                case "InsertSpace":
                    int index = Integer.parseInt(commands[1]);
                    concealedMessage = insertSpace(concealedMessage, index);
                    System.out.println(concealedMessage);
                    break;
                case "Reverse":
                    String substring = commands[1];
                    if (concealedMessage.contains(substring)){
                        concealedMessage = reverseSubstring(concealedMessage, substring);
                        System.out.println(concealedMessage);
                    }else {
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll":
                    substring = commands[1];
                    String replacement = commands[2];
                    concealedMessage = concealedMessage.replace(substring,replacement);
                    System.out.println(concealedMessage);
                    break;
            }


            input = scanner.nextLine();
        }
        System.out.println("You have a new text message: " + concealedMessage);
    }

    private static String reverseSubstring(String concealedMessage, String substring) {
        StringBuilder stringBuilder = new StringBuilder(substring);
        int index = concealedMessage.indexOf(substring);
        StringBuilder stringBuilderMessage = new StringBuilder(concealedMessage);
            stringBuilderMessage.replace(index, index + stringBuilder.length(), "");
        for (int i = 0; i < stringBuilder.length() / 2; i++) {
            char oldChar = stringBuilder.charAt(i);
            char newChar = stringBuilder.charAt(stringBuilder.length() - 1 - i);
            stringBuilder.setCharAt(i, newChar);
            stringBuilder.setCharAt((stringBuilder.length() - 1) - i, oldChar);
        }

        for (int i = 0; i < stringBuilder.length(); i++) {
            stringBuilderMessage.append(stringBuilder.charAt(i));
        }
        return stringBuilderMessage.toString();
    }

    private static String insertSpace(String concealedMessage, int index) {
        String subString = concealedMessage.substring(0, index);
        subString += " ";
        for (int i = index; i < concealedMessage.length(); i++) {
            subString += concealedMessage.charAt(i);
        }
        concealedMessage = subString;
        return concealedMessage;
    }

}
