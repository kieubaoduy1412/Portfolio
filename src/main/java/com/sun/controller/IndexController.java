
package com.sun.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.model.UserModel;
import com.sun.service.IndexService;


@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "abc")
    public String gotoPage() {
        // System.out.println("Xuong");
        // JSONObject json = new JSONObject();
        List<UserModel> list = indexService.getAllUser();
        // System.out.println("Controller: " + list.size());
        return "aaa"; // Go to web
        // json.put("LIST_SIZE", list.size());
        // return parseToJsonString(json);
    }

    @ResponseBody
    @RequestMapping(value = "abcd")
    public String gotoPage1(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        List<UserModel> list = indexService.getAllUser();
        // return "aaa"; // Go to web
        json.put("LIST_SIZE", list);
        String messages = new String(parseToJsonString(json));
        System.out.println("Message: " + messages);
        return messages;
    }
    

    public static String parseToJsonString(JSONObject js) throws ConnectException {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            // You need to turn off the serialization feature
            // FAIL_ON_EMPTY_BEANS. You can do that by setting the following on
            // your ObjectMapper:
            mapper.disable(Feature.FAIL_ON_EMPTY_BEANS);
            json = mapper.writeValueAsString(js);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }
}
