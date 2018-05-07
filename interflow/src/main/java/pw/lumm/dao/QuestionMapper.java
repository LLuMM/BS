package pw.lumm.dao;

import pw.lumm.model.Answer;
import pw.lumm.model.Forum;
import pw.lumm.model.Question;

import java.util.List;

public interface QuestionMapper {
    List<Forum> getForum(int type, int status);

    List<Question> getQuestionByForunmId (String id);

    void addQuestion(Question question);

    Question getQuestionById(String id);

    List<Answer> getAnswerByQid(String id);

    void setHitById(String id, int hit);

    void setStatus(String id, int status);

    void setTop(String id, int status);

    List<Question> getQuestionByUserId(String uid);

    void deleteQuestionById(String id);


    List<Question> getAllQuestion();

    void setFrompic(String uid, String url);
}
