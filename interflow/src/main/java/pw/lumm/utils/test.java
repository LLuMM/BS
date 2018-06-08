package pw.lumm.utils;

import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        List list = new ArrayList();
        for (int i = 0; i<10;i++){
            list.add(i);
        }
        list.remove(2);
        for (int i = 0; i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
