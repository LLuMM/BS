package pw.lumm.service.inf;

public interface AnsewerServer {


    void addAnswer(String uid, String id, String content, String username);

    void addAnswerToUser(String qid,String fromid, String toid,String content);
}
