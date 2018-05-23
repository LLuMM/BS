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
    public String register(User user) throws Exception;

    /*用户登录
    * */
    public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, Exception;

    /*获取所有版主用户
    * */
    public List<User> getAllUserByForum() throws Exception;

    /*通过用户id获取用户信息
    * */
    User getUserById(String id) throws Exception;

    /*设置用户权限
    * */
    void setUserPrivilege(String id, int status) throws Exception;

    /*查询是否重名
    * */
    Boolean checkUser(String username) throws Exception;

    /*设置用户主页访问量
    * */
    void setVisits(int read) throws Exception;

    /*修改用户信息
    * */
    void updateUser(User user) throws Exception ;

    void setFace(String uid, String url) throws Exception ;

    void addfriend(String userid, String friendid, String msg) throws Exception ;

    void friendRequest(String mid, int status) throws Exception ;

    List<User> getFriendsById(String uid) throws Exception ;

    void setStatus(String username, int status) throws Exception;
}
