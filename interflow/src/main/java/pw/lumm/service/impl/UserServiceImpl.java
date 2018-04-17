package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.UserMapper;
import pw.lumm.model.User;
import pw.lumm.service.inf.UserService;

/**
 * Created by LM on 2018/4/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    public String insert(User user){
        System.out.println(user.getUsername());
        userMapper.insertUser(user);
        return "success";
    }
}
