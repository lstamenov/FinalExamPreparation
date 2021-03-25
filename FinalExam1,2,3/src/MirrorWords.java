import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> words = new LinkedHashMap<>();

        String text = scanner.nextLine();
        Pattern pattern = Pattern.compile("([#@])(?<word1>[A-Za-z]{3,})\\1\\1(?<word2>[A-Za-z]{3,})\\1");
        Matcher matcher = pattern.matcher(text);
        int countPairs = 0;
        if (matcher.find()){
            countPairs++;
            String fw = matcher.group("word1");
            String sw = matcher.group("word2");
            if (areMirrors(fw, sw)){
                words.put(fw, sw);
            }
            while (matcher.find()){
                countPairs++;
                String firstWord = matcher.group("word1");
                String secondWord = matcher.group("word2");
                if (areMirrors(firstWord, secondWord)){
                    words.put(firstWord, secondWord);
                }
            }
            System.out.printf("%d word pairs found!%n", countPairs);
        }else {
            System.out.println("No word pairs found!");
        }
        int count = 0;
        if (words.size() > 0){
            System.out.println("The mirror words are:");
            for (Map.Entry<String, String> e : words.entrySet()) {
                count++;
                if (count == words.size()){
                    System.out.printf("%s <=> %s",e.getKey(), e.getValue());
                }else {
                    System.out.printf("%s <=> %s, ",e.getKey(), e.getValue());
                }
            }
        }else {
            System.out.println("No mirror words!");
        }
    }

    private static boolean areMirrors(String firstWord, String secondWord) {
        if (firstWord.length() == secondWord.length()){
            for (int i = 0; i < firstWord.length(); i++) {
                if (firstWord.charAt(i) != secondWord.charAt(secondWord.length() - 1 - i)){
                    return false;
                }
            }
        }else {
            return false;
        }
        return true;
    }
}
