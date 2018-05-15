package pw.lumm.service.inf;

import pw.lumm.model.Question;
import pw.lumm.model.SearchItemExample;

public interface SearchService {

    void importAllTitle() throws Exception ;
    SearchItemExample search(String keyword) throws Exception ;
    void importQuestion(Question question) throws Exception ;
    void deleteQuestionById(String id) throws Exception ;
    void deleteAllNews() throws Exception ;
}
