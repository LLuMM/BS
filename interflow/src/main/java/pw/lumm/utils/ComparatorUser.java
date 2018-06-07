package pw.lumm.utils;

import pw.lumm.model.User;

import java.util.Comparator;

public class ComparatorUser implements Comparator {
    public int compare(Object obj0, Object obj1) {
        User user0=(User)obj0;
        User user1=(User)obj1;
        String status1 = user0.getStatus()+"";
        String status2 = user1.getStatus()+"";
        int flag=status2.compareTo(status1);

            return flag;
    }
}
