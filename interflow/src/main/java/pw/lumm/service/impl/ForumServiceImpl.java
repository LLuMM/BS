package pw.lumm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.dao.ForumMapper;
import pw.lumm.model.Forum;
import pw.lumm.service.inf.ForumService;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumMapper forumMapper;

    @Override
    public void addForum(Forum forum) throws Exception  {
        forumMapper.addForum(forum);
    }

    @Override
    public void setForumStatus(String id, int type) throws Exception  {
        forumMapper.setForumStatus(type, id);
    }

    @Override
    public Forum getFourmById(String id)  throws Exception {
        Forum forum = forumMapper.getFourmById(id);
        if (forum != null)
            return forum;
        else {
            return null;
        }
    }

    @Override
    public List<Forum> getFourmByUserId(String uid)  throws Exception {
        List<Forum> forums = forumMapper.getFourmByUserId(uid);
        if (forums != null && forums.size() > 0)
            return forums;
        else
            return null;
    }
    @Override
    public List<Forum> getForum(int type, int status)  throws Exception {
        List<Forum> forumList = forumMapper.getForum(type, status);
        return forumList;
    }


}
