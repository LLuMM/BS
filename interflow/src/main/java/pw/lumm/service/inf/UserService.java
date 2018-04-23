package pw.lumm.service.inf;

import pw.lumm.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LM on 2018/4/17.
 */
public interface UserService {
    public String register(User user);

    public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
