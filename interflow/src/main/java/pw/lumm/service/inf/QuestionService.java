package pw.lumm.service.inf;

import pw.lumm.model.Answer;
import pw.lumm.model.Forum;
import pw.lumm.model.Question;

import java.util.List;

public interface QuestionService {
    List<Forum> getForum(int type, int status);
    Forum getForumById(String id);
    List<Question> getQuestionByForunmId(String id);
    void addForum(String loginid, int type,String ftitle,String fcontent);
    void setForumStatus(String id, int type);

    void addQuestion(String loginid,  String fid, String title, String value_content);

    Question getQuestionById(String id);

    List<Answer> getAnswerByQid(String id);
}
