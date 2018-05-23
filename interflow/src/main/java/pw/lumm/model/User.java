package pw.lumm.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by LM on 2018/4/12.
 */
public class User{
    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", post=" + post +
                ", sign='" + sign + '\'' +
                ", face='" + face + '\'' +
                ", joindate='" + joindate + '\'' +
                ", lastlogin='" + lastlogin + '\'' +
                ", isbest=" + isbest +
                ", userfav='" + userfav + '\'' +
                ", userinfo='" + userinfo + '\'' +
                ", userhidden=" + userhidden +
                ", userviews=" + userviews +
                ", privilege=" + privilege +
                ", visits=" + visits +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", city='" + city + '\'' +
                ", verifycode='" + verifycode + '\'' +
                '}';
    }

    @Expose
    private String uid;
    @Expose
    private String username;
    private String password;
    private String email;
    private int post;
    private String sign;
    private String face;
    private String joindate;
    private String lastlogin;
    private int isbest;
    private String userfav;
    private String userinfo;
    private int userhidden;
    private int userviews;
    private int privilege;
    private int visits;
    private String phone;
    private int status;
    private String city;
    private String verifycode;

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

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

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
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
