package pw.lumm.model;

import java.util.Date;

public class Answer {
  /*  `Id` varchar(50) NOT NULL,
  `Content` varchar(1000) DEFAULT NULL,
  `SupportCnt` int(11) DEFAULT '0',
            `OpposeCnt` int(11) DEFAULT '0',
            `AnswerTo` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
            `UserId` varchar(50) DEFAULT NULL,
  `accept` int(11) NOT NULL DEFAULT '0' COMMENT '是否采纳',
            `praise` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`Id`)*/

  private String aid;
  private String content;
  private String supportCnt;
    private String opposeCnt;
    private String answerTo;
    private Date time;
    private String uid;
    private String accept;
    private String qid;
    private String username;
    private String toid;
    private String questionTitle;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSupportCnt() {
        return supportCnt;
    }

    public void setSupportCnt(String supportCnt) {
        this.supportCnt = supportCnt;
    }

    public String getOpposeCnt() {
        return opposeCnt;
    }

    public void setOpposeCnt(String opposeCnt) {
        this.opposeCnt = opposeCnt;
    }

    public String getAnswerTo() {
        return answerTo;
    }

    public void setAnswerTo(String answerTo) {
        this.answerTo = answerTo;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
