package pw.lumm.dao;

import pw.lumm.model.User;

import java.util.List;

/**
 * Created by LM on 2018/4/12.
 */
public interface UserMapper {
    void insertUser(User user);

    List<User> getByNameAndPwd(String username, String password);
}
