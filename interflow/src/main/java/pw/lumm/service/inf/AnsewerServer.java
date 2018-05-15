package pw.lumm.service.inf;

import pw.lumm.model.Answer;

import java.util.List;

public interface AnsewerServer {


    /*添加回复
    * */
    void addAnswer(String uid, String id, String content, String username)  throws Exception ;

    /*对用户回复
    * */
    void addAnswerToUser(String qid,String fromid, String toid,String content,int type)  throws Exception ;

    /*通过用户id获取回复信息
    * */
    List<Answer> getAnswerByUserId(String uid)  throws Exception ;

    /*对新闻类进行评论
    * */
    void addAnswerToNews(String uid, String nsid, String content, String username)  throws Exception ;

    /*通过新闻的id获取评论
    * */
    List<Answer> getAnswerByNewsId(String n_id)  throws Exception ;

    /*通过文章id删除评论
    * */
    void deleteByQid(String id)  throws Exception ;
}
