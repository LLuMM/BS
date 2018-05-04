package pw.lumm.dao;

import pw.lumm.model.Forum;

import java.util.List;

public interface ForumMapper {
    void addForum(Forum forum);
    void setForumStatus(int type, String id);
    Forum getFourmById(String id);

    List<Forum> getFourmByUserId(String uid);

    List<Forum> getForum(int type, int status);
}
