package SoftUniExamResults;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Language> languages = new ArrayList<>();
        Map<String, Integer> users = new TreeMap<>();
        String input = scanner.nextLine();
        while (!input.equals("exam finished")){
            String[] userData = input.split("-");
            String username = userData[0];
            if (userData.length == 2){
                users.remove(username);
            }else {
                String languageName = userData[1];
                int points = Integer.parseInt(userData[2]);
                if (isSuchLanguage(languageName, languages) != -1){
                    int index = isSuchLanguage(languageName, languages);
                    languages.get(index).addUser(username, points);
                    if (users.containsKey(username)){
                        if (points > users.get(username)){
                            users.put(username, points);
                        }
                    }else {
                        users.put(username, points);
                    }
                }else {
                    Language language = new Language(languageName, new TreeMap<>());
                    language.getUserResults().put(username, points);
                    languages.add(language);
                    users.put(username, points);
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Results:");
        users.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .forEach(stringIntegerEntry -> System.out.printf("%s | %d%n",stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
        System.out.println("Submissions:");
        languages.stream()
                .sorted(Comparator.comparing(Language::getUserResultsSize).reversed().thenComparing(Language::getName))
                .forEach(language -> System.out.printf("%s - %d%n",language.getName(), language.getUserResultsSize()));
    }

    private static int isSuchLanguage(String languageName, List<Language> languages) {
        for (Language l : languages) {
            if (l.getName().equals(languageName)){
                return languages.indexOf(l);
            }
        }
        return -1;
    }
}
