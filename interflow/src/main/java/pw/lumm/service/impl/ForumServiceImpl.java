package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.ForumMapper;
import pw.lumm.model.Forum;
import pw.lumm.service.inf.ForumService;
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumMapper forumMapper;
    @Override
    public void addForum(Forum forum) {
        forumMapper.addForum(forum);
    }

    @Override
    public void setForumStatus(String id, int type) {
        forumMapper.setForumStatus(type,id);
    }

    @Override
    public Forum getFourmById(String id) {
        Forum forum = forumMapper.getFourmById(id);
        return forum;
    }
}
