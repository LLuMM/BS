package pw.lumm.dao;

import pw.lumm.model.User;

import java.util.List;

/**
 * Created by LM on 2018/4/12.
 */
public interface UserMapper {
    void insertUser(User user);

    List<User> getByNameAndPwd(String username, String password);
    List<User> getAllUserByForum();

    User getUserById(String id);

    void setUserPrivilege(String id, int status);

    User checkUser(String username);

    void setVisits(int read);

    void updateUser(User user);
    void setface(String uid,String url);
}
