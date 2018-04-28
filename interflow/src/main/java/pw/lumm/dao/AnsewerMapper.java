package pw.lumm.dao;

import pw.lumm.model.Answer;

public interface AnsewerMapper {

    public void addAnswer(Answer answer);

    void addAnswerToUser(Answer answer);
}
