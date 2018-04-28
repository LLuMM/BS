package pw.lumm.dao;

import pw.lumm.model.Forum;

public interface ForumMapper {
    void addForum(Forum forum);
    void setForumStatus(int type, String id);
    Forum getFourmById(String id);
}
