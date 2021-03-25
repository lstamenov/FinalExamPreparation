import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Map<String, Long> emojis = new LinkedHashMap<>();
        Pattern numPattern = Pattern.compile("\\d");
        Matcher numMatcher = numPattern.matcher(str);
        long treshold = 1;
        while (numMatcher.find()){
            treshold *= Long.parseLong(numMatcher.group());
        }
        Pattern pattern = Pattern.compile("(::|\\*\\*)(?<emoji>[A-Z][a-z]{2,})(\\1)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            long asciiValue = getAsciiValue(matcher.group("emoji"));
            emojis.put(matcher.group(), asciiValue);
        }

        System.out.println("Cool threshold: " + treshold);
        System.out.printf("%d emojis found in the text. The cool ones are:%n", emojis.size());
        for (Map.Entry<String, Long> e: emojis.entrySet()) {
            if (e.getValue() > treshold) {
                System.out.println(e.getKey());
            }
        }
    }

    private static long getAsciiValue(String emoji) {
        long sum = 0;
        for (int i = 0; i < emoji.length(); i++) {
            sum += emoji.charAt(i);
        }
        return sum;
    }
}
