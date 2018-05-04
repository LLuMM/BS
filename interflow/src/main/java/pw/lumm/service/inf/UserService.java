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
    /*
    * 用户注册
    * */
    public String register(User user);

    /*用户登录
    * */
    public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /*获取所有版主用户
    * */
    public List<User> getAllUserByForum();

    /*通过用户id获取用户信息
    * */
    User getUserById(String id);

    /*设置用户权限
    * */
    void setUserPrivilege(String id, int status);

    /*查询是否重名
    * */
    Boolean checkUser(String username);

    /*设置用户主页访问量
    * */
    void setVisits(int read);

    /*修改用户信息
    * */
    void updateUser(User user);

    void setFace(String uid, String url);
}
