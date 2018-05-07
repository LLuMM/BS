package pw.lumm.service.inf;

import pw.lumm.model.Question;
import pw.lumm.model.SearchItemExample;

public interface SearchService {

    void importAllTitle();
    SearchItemExample search(String keyword);
    void importQuestion(Question question);
    void deleteQuestionById(String id);
    void deleteAllNews();
}
