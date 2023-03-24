package Model;

import java.util.UUID;

public class User {
    String uuid;
    String userName;

    public User(String userName) {
        this.userName = userName;
        this.uuid =  UUID.randomUUID().toString().replace("-", "");
    }

    public String getUserName() {
        return userName;
    }

}
