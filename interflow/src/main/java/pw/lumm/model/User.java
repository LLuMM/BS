package pw.lumm.model;

import java.util.Date;

/**
 * Created by LM on 2018/4/12.
 */
public class User {
    private String uid;
    private String username;
    private String showname;
    private String password;
    private String email;
    private String sex;
    private String uwork;
    private int post;
    private String sign;
    private String face;
    private Date joindate;
    private Date lastlogin;
    private int isbest;
    private String userfav;
    private String userinfo;
    private int userhidden;
    private int userviews;
    private int privilege;

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getUwork() {
        return uwork;
    }

    public void setUwork(String uwork) {
        this.uwork = uwork;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public int getIsbest() {
        return isbest;
    }

    public void setIsbest(int isbest) {
        this.isbest = isbest;
    }

    public String getUserfav() {
        return userfav;
    }

    public void setUserfav(String userfav) {
        this.userfav = userfav;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public int getUserhidden() {
        return userhidden;
    }

    public void setUserhidden(int userhidden) {
        this.userhidden = userhidden;
    }

    public int getUserviews() {
        return userviews;
    }

    public void setUserviews(int userviews) {
        this.userviews = userviews;
    }
}
