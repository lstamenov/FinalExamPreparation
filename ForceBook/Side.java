package ForceBook;

import java.util.List;

public class Side {
    private final String name;
    private final List<String> users;

    Side(String name, List<String> users){
        this.name= name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public List<String> getUsers() {
        return users;
    }

    public void addUser(String username){
        boolean flag = false;
        for (String user : users) {
            if (user.equals(username)) {
                flag = true;
                break;
            }
        }
        if (!flag){
            users.add(username);
        }
    }

    public void removeUser(String username) {
        int index = -1;
        for (String u : users) {
            if (u.equals(username)){
                index = users.indexOf(u);
            }
        }
        if (index != -1){
            users.remove(index);
        }
    }

    public int getUsersSize(){
        return users.size();
    }
}
