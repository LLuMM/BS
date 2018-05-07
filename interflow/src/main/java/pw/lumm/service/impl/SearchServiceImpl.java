package pw.lumm.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.lumm.model.*;
import pw.lumm.service.inf.NewsService;
import pw.lumm.service.inf.QuestionService;
import pw.lumm.service.inf.SearchService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    NewsService newsService;
    @Autowired
    QuestionService questionService;
    @Autowired
    private SolrServer server;

    @Override
    public void importQuestion(Question question) {

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", question.getId());
        document.addField("news_title", question.getTitle());
        document.addField("date", question.getTime());
        document.addField("source", question.getUname());
        try {
            server.add(document);
            server.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void importAllTitle() {
        List<News> newsList = newsService.getAllNews();
        List<Question> questions = questionService.getAllQuestion();

        try {
            if (newsList != null && newsList.size() > 0) {
                for (int i = 0; i < newsList.size(); i++) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", newsList.get(i).getN_id());
                    document.addField("img_url", newsList.get(i).getImgurl());
                    document.addField("news_url",newsList.get(i).getUrl());
                    document.addField("news_title", newsList.get(i).getTitle());
                    document.addField("type", newsList.get(i).getType());
                    document.addField("date", newsList.get(i).getDate());
                    document.addField("source", newsList.get(i).getSource());
                    server.add(document);
                }

            }
            if (questions != null && questions.size() > 0) {

                for (int i = 0; i < questions.size(); i++) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", questions.get(i).getId());
                    document.addField("news_title", questions.get(i).getTitle());
                    document.addField("date", questions.get(i).getTime());
                    document.addField("source", questions.get(i).getUname());
                    server.add(document);
                }
            }
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SearchItemExample search(String keyword) {
        SolrQuery query=new SolrQuery();
        query.setQuery(keyword);
        query.set("df", "news_title");
        query.setHighlight(true);
        query.addHighlightField("news_title");
        query.setHighlightSimplePre("<font style='color: red'>");
        query.setHighlightSimplePost("</font>");

        SearchItemExample example = null;
        try {
            example = search(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return example;
    }


    public SearchItemExample search(SolrQuery query) throws SolrServerException{
        SearchItemExample example = new SearchItemExample();
        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();
        int numFound = (int) results.getNumFound();
        example.setCount(numFound);
        List<SearchItem> itemSearchs=new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument solrDocument : results) {
            SearchItem itemSearch=new SearchItem();
            itemSearch.setId((String)solrDocument.get("id"));
            itemSearch.setTitle((String) solrDocument.get("news_title"));
            itemSearch.setImg_url((String) solrDocument.get("img_url"));
            itemSearch.setNews_url((String) solrDocument.get("news_url"));
            itemSearch.setDate((String) solrDocument.get("date"));
            itemSearch.setSource((String) solrDocument.get("source"));
            String item_title="";
            List<String> list = highlighting.get(solrDocument.get("id")).get("news_title");
            if (list.size()>0||list!=null) {
                item_title=list.get(0);
            }else {
                item_title=(String) solrDocument.get("news_title");
            }
            itemSearch.setTitle(item_title);
            itemSearchs.add(itemSearch);
        }
        example.setSearchItems(itemSearchs);
        return example;
    }

    public void deleteAllNews(){
        try {
            server.deleteByQuery("type:0");
            server.deleteByQuery("type:1");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestionById(String id){
        try {
            server.deleteById(id);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
