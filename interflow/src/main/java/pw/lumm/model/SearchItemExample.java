package pw.lumm.model;

import java.io.Serializable;
import java.util.List;

public class SearchItemExample implements Serializable {
    List<SearchItem> searchItems;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SearchItem> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }
}
