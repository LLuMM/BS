package pw.lumm.model;

/**
 * Created by LM on 2018/4/12.
 */
public class User {
    private String id;




    private String username;
    private String sex;
    public User(String id, String username, String sex) {
        this.id = id;
        this.username = username;
        this.sex = sex;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
