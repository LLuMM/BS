package pw.lumm.model;

import java.util.Date;

public class Msg {
    private String id;
    private String title;
    private String fromid;
    private String toid;
    private String content;
    private String fromname;
    private String toname;
    private Date time;
    private String fid;

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
                '}';
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
