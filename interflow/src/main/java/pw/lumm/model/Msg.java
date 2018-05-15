package pw.lumm.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Msg {

    private String id;
    private String title;
    @Expose
    private String fromid;
    private String toid;
    @Expose
    private String content;
    @Expose
    private String fromname;
    @Expose
    private String toname;
    @Expose
    private String time;
    private String fid;
    private int readstatus;
    private int type;

    @Expose
    private String to;

    @Override
    public String toString() {
        return "Msg{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", fromid='" + fromid + '\'' +
                ", toid='" + toid + '\'' +
                ", content='" + content + '\'' +
                ", fromname='" + fromname + '\'' +
                ", toname='" + toname + '\'' +
                ", time=" + time +
                ", fid='" + fid + '\'' +
                ", readstatus=" + readstatus +
                ", type=" + type +
                ", to='" + to + '\'' +
                ", userList=" + userList +
                '}';
    }

    @Expose
    List<User> userList = new ArrayList<>();

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }



    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReadstatus() {
        return readstatus;
    }

    public void setReadstatus(int readstatus) {
        this.readstatus = readstatus;
    }


    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
