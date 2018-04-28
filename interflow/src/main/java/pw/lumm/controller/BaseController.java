package pw.lumm.controller;

import com.google.gson.Gson;
import pw.lumm.utils.Response;

/**
 * Created by LM on 2018/4/19.
 */
public class BaseController {
    protected  Gson gson = new Gson();
    protected Response response = new Response();

}
