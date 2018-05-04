package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.UserMapper;
import pw.lumm.model.User;
import pw.lumm.service.inf.UserService;
import pw.lumm.utils.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LM on 2018/4/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(User user) {

        user.setUid(UUID.randomUUID().toString());
        user.setJoindate(new Date());
        try {
            String pwd = MD5.Encode(user.getPassword());
            user.setPassword(pwd);
            userMapper.insertUser(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


    }

    @Override
    public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String pwd = MD5.Encode(password);
        List<User> list = userMapper.getByNameAndPwd(username, pwd);
        if (list != null)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<User> getAllUserByForum() {
        List<User> users = userMapper.getAllUserByForum();
        return users;
    }

    @Override
    public User getUserById(String id) {
        User user = userMapper.getUserById(id);
        if (user != null)
            return user;
        else
            return null;
    }

    @Override
    public void setUserPrivilege(String id, int status) {
        userMapper.setUserPrivilege(id, status);
    }

    @Override
    public Boolean checkUser(String username) {
        User user = userMapper.checkUser(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public void setVisits(int read) {
        userMapper.setVisits(read);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void setFace(String uid, String url) {
        userMapper.setface(uid,url);
    }
}
