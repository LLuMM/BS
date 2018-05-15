package pw.lumm.service.inf;

import pw.lumm.model.Msg;
import pw.lumm.model.Notic;

import java.util.List;

public interface MessageService {

    /*通过用户id获取信息列表
    * */
    List<Msg> getMessageById(String id) throws Exception ;
    /*添加信息
    * */
    void addMessage(Msg msg) throws Exception ;
    /*添加公告
    * */
    void addNotic(String notic) throws Exception ;
    /*获取所有公告
    * */
    List<Notic> getNotics() throws Exception ;
    /*删除公告
    * */
    void deleteNotice(String id)  throws Exception ;
    /*设置信息状态
    * */
    void setMessasgeStatus(Msg msg)  throws Exception ;
    /*通过信息的状态（已读）删除相关用户的信息
    * */
    void deleteByStatus(String id) throws Exception ;
    /*通过信息id删除信息
    * */
    void deleteMsgById(String id)  throws Exception ;

    void chatMessage(String fromid,String toid)  throws Exception ;
}
