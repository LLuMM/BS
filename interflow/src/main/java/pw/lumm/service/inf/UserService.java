package pw.lumm.service.inf;

import pw.lumm.model.Forum;
import pw.lumm.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by LM on 2018/4/17.
 */
public interface UserService {
    public String register(User user);

    public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    public List<User> getAllUserByForum();
    User getUserById(String id);

    void setUserPrivilege(String id, int status);
}
