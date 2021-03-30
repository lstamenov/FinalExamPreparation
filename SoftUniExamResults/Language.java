package SoftUniExamResults;

import java.util.Map;

public class Language {
    private final String name;
    private final Map<String, Integer> userResults;
    private int participants = 1;


    Language(String name, Map<String, Integer> userResults){
        this.name = name;
        this.userResults = userResults;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getUserResults() {
        return userResults;
    }

    public void addUser(String user, int grade) {
        if (userResults.get(user) != null) {
            if (userResults.get(user) < grade) {
                userResults.put(user, grade);
            }
        }else {
            userResults.put(user, grade);
        }
        participants += 1;
    }
    public int getUserResultsSize(){
        return participants;
    }
}
