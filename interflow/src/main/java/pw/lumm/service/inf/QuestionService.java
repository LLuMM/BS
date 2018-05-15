package pw.lumm.service.inf;

import pw.lumm.model.Answer;
import pw.lumm.model.Forum;
import pw.lumm.model.Question;

import java.util.List;

public interface QuestionService {

    /*版块id获取版块下的所有文章
    * */
    List<Question> getQuestionByForunmId(String id) throws Exception ;

    /*
    * 添加版块
    * */
    void addForum(String loginid, int type,String ftitle,String fcontent) throws Exception ;

    /*设置版块状态
    * */
    void setForumStatus(String id, int type) throws Exception ;


    /*添加文章
    * */
    void addQuestion(String loginid,  String fid, String title, String value_content) throws Exception ;

    /*通过文章id获取文章信息
    * */
    Question getQuestionById(String id) throws Exception ;

    /*通过文章id获取回复列表
    * */
    List<Answer> getAnswerByQid(String id) throws Exception ;

    /*设置文章置顶
    * */
    void setTop(String id, int status) throws Exception ;

    /*设置文章状态（热门/禁用）
    * */
    void setStatus(String id, int status) throws Exception ;

    /*通过用户id获取相关的所有文章
    * */
    List<Question> getQuestionByUserId(String uid) throws Exception ;


    /*通过id删除文章
    * */
    void deleteByQid(String id) throws Exception ;

    List<Question> getAllQuestion() throws Exception ;

    void setFrompic(String uid, String url) throws Exception ;
}
