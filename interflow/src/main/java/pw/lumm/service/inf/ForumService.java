package pw.lumm.service.inf;

import pw.lumm.model.Forum;

import java.util.List;

public interface ForumService {
    /*添加版块
    * */
    void addForum(Forum forum);
    /*设置版块状态
    * */
    void setForumStatus(String id, int type);
    /*通过版块id获取该版块信息
    * */
    Forum getFourmById(String id);

    /*通过版主id获取管理的版块
    * */
    List<Forum> getFourmByUserId(String uid);
    /*通过相关类别及版块状态获取版块列表
     * */
    List<Forum> getForum(int type, int status);
}
