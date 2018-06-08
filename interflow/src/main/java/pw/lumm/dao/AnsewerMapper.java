package pw.lumm.dao;

import pw.lumm.model.Answer;

import java.util.List;

public interface AnsewerMapper {

    void addAnswer(Answer answer);
    List<Answer> getAnswerByUserId(String uid);
    List<Answer> getAnswerByNewId(String n_id);
    void deleteByQid(String id);

    void setFace(String uid, String url);
}
