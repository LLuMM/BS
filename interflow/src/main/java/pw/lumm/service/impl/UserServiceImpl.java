package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.UserMapper;
import pw.lumm.model.Friends;
import pw.lumm.model.Msg;
import pw.lumm.model.User;
import pw.lumm.service.inf.MessageService;
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

    @Autowired
    private MessageService messageService;


    private String MSG_REFUSE = "拒绝了你的请求！";
    private String MSG_AGREE = "同意了你的请求！";

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
        userMapper.setface(uid, url);
    }

    @Override
    public void addfriend(String userid, String friendid, String msg) {
        Date date = new Date();
        Friends friends = new Friends();
        friends.setUserid(userid);
        friends.setDate(date);
        friends.setFriendid(friendid);
        friends.setId(UUID.randomUUID().toString());
        friends.setStatus(0);

        User fromuser = userMapper.getUserById(userid);
        User touser = userMapper.getUserById(friendid);

        Msg message = new Msg();
        message.setId(UUID.randomUUID().toString());
        message.setToname(touser.getUsername());
        message.setToid(touser.getUid());
        message.setTime(date);
        message.setFromid(fromuser.getUid());
        message.setFromname(fromuser.getUsername());
        message.setContent(msg);
        message.setType(1);
        try {
            messageService.addMessage(message);
            userMapper.addfriend(friends);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void friendRequest(String fromid, String toid, int status) {
        Date date = new Date();
        User fromuser = userMapper.getUserById(fromid);
        User touser = userMapper.getUserById(toid);
        Msg msg = new Msg();
        msg.setId(UUID.randomUUID().toString());
        msg.setToname(touser.getUsername());
        msg.setToid(touser.getUid());
        msg.setTime(date);
        msg.setFromid(fromuser.getUid());
        msg.setFromname(fromuser.getUsername());
        if (status == 1) {
            msg.setContent(fromuser.getUsername() + MSG_AGREE);
        } else if (status == 2) {
            msg.setContent(fromuser.getUsername() + MSG_REFUSE);
        }
        try {
            messageService.addMessage(msg);
            String id = userMapper.getFriendId(fromid, toid);
            userMapper.setFriendStatus(id, status);
        } catch (Exception e) {

        }

    }
}
