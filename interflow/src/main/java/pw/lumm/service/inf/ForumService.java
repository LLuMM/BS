package pw.lumm.service.inf;

import pw.lumm.model.Forum;

public interface ForumService {
    void addForum(Forum forum);
    void setForumStatus(String id, int type);
    Forum getFourmById(String id);
}
