package pw.lumm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.UserMapper;
import pw.lumm.model.Friends;
import pw.lumm.model.Msg;
import pw.lumm.model.User;
import pw.lumm.service.inf.MessageService;
import pw.lumm.service.inf.UserService;
import pw.lumm.utils.ComparatorUser;
import pw.lumm.utils.DateUtil;
import pw.lumm.utils.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    public String register(User user) throws Exception {

        user.setUid(UUID.randomUUID().toString());
        user.setJoindate(DateUtil.format(new Date()));

        String pwd = MD5.Encode(user.getPassword());
        user.setPassword(pwd);
        userMapper.insertUser(user);
        return "success";

    }

    @Override
    public User login(String username, String password) throws Exception {

        String pwd = MD5.Encode(password);
        List<User> list = userMapper.getByNameAndPwd(username, pwd);
        if (list != null) {
            User user = list.get(0);
            user.setLastlogin(DateUtil.format(new Date()));
            userMapper.updateUser(user);
            return list.get(0);
        } else
            return null;
    }

    @Override
    public List<User> getAllUserByForum() throws Exception {
        List<User> users = userMapper.getAllUserByForum();
        return users;
    }

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> users =  userMapper.getAllUser();
        if (users.size()>0&&users!=null){
            return  users;
        }
        return null;
    }

    @Override
    public User getUserById(String id) throws Exception {
        User user = userMapper.getUserById(id);
        if (user != null)
            return user;
        else
            return null;
    }

    @Override
    public void setUserPrivilege(String id, int status) throws Exception {
        userMapper.setUserPrivilege(id, status);
    }

    @Override
    public Boolean checkUser(String username) throws Exception {
        User user = userMapper.checkUser(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public void setVisits(int read) throws Exception {
        userMapper.setVisits(read);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userMapper.updateUser(user);
    }

    @Override
    public void setFace(String uid, String url) throws Exception {
        userMapper.setface(uid, url);
    }

    @Override
    public void addfriend(String userid, String friendid, String msg) throws Exception {
        Date date = new Date();
        Friends friends = new Friends();
        friends.setUserid(userid);
        friends.setDate(DateUtil.format(date));
        friends.setFriendid(friendid);
        friends.setId(UUID.randomUUID().toString());
        friends.setStatus(0);

        User fromuser = userMapper.getUserById(userid);
        User touser = userMapper.getUserById(friendid);

        Msg message = new Msg();
        message.setId(UUID.randomUUID().toString());
        message.setToname(touser.getUsername());
        message.setToid(touser.getUid());
        message.setTime(DateUtil.format(date));
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
    public void friendRequest(String mid, int status) throws Exception {


        Msg msg1 = messageService.getMessageById(mid);
        msg1.setReadstatus(1);
        messageService.setMessasgeStatus(msg1);
        Date date = new Date();
        User fromuser = userMapper.getUserById(msg1.getToid());

        User touser = userMapper.getUserById(msg1.getFromid());
        Msg msg = new Msg();
        msg.setId(UUID.randomUUID().toString());
        msg.setToname(touser.getUsername());
        msg.setToid(touser.getUid());
        msg.setTime(DateUtil.format(date));
        msg.setFromid(fromuser.getUid());
        msg.setFromname(fromuser.getUsername());
        if (status == 1) {
            msg.setContent(fromuser.getUsername() + MSG_AGREE);
        } else if (status == 2) {
            msg.setContent(fromuser.getUsername() + MSG_REFUSE);
        }
        try {
            messageService.addMessage(msg);
            String id = userMapper.getFriendId(msg1.getFromid(), msg1.getToid());
            userMapper.setFriendStatus(id, status);
        } catch (Exception e) {

        }

    }

    @Override
    public List<User> getFriendsById(String uid) throws Exception {
        List<User> users = new ArrayList<>();
        List<Friends> friends = userMapper.getFriendsidByUid(uid);
        User user = null;
        if (friends.size() > 0) {
            for (int i = 0; i < friends.size(); i++) {
                if (uid.equals(friends.get(i).getUserid())) {
                    user = userMapper.getUserById(friends.get(i).getFriendid());
                } else if (uid.equals(friends.get(i).getFriendid())) {

                    user = userMapper.getUserById(friends.get(i).getUserid());
                }
                users.add(user);
            }
            ComparatorUser comparator = new ComparatorUser();
            Collections.sort(users, comparator);
        }
        if (users != null && users.size() > 0)
            return users;
        else
            return null;
    }

    @Override
    public void setStatus(String username, int status) throws Exception {
        userMapper.setStatus(username, status);
    }

    @Override
    public Friends checkfriend(String userid, String friendid) {
        Friends friends = userMapper.checkfriend(userid, friendid);
        if (friends != null) {
            return friends;
        }
        return null;
    }

    @Test
    public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(MD5.Encode("123"));
    }
}
