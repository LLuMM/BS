package pw.lumm.model;
public class Question {

    private String id;
    private String title;
    private String content;
    private String time;
    private String userid;
    private String commentTo;
    private int hits;
    private int stick;
    private int status;
    private String fid;
    private String uname;
    private String filepath;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", userid='" + userid + '\'' +
                ", commentTo='" + commentTo + '\'' +
                ", hits=" + hits +
                ", stick=" + stick +
                ", status=" + status +
                ", fid='" + fid + '\'' +
                ", uname='" + uname + '\'' +
                ", filepath='" + filepath + '\'' +
                ", frompic='" + frompic + '\'' +
                '}';
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFrompic() {
        return frompic;
    }

    public void setFrompic(String frompic) {
        this.frompic = frompic;
    }

    private String frompic;


    public String getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(String commentTo) {
        this.commentTo = commentTo;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getStick() {
        return stick;
    }

    public void setStick(int stick) {
        this.stick = stick;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
