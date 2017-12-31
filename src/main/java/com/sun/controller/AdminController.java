package com.sun.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.model.DetailStoryAdminModel;
import com.sun.model.StoryModel;
import com.sun.service.AdminService;

@Controller
public class AdminController {
	@Autowired
    private AdminService adminService;
	
	@ResponseBody
	@RequestMapping(value = "loginToAdmin")
    public String loginToBlog(HttpServletResponse response, HttpSession session, 
    		@RequestParam("username") final String username,
    		@RequestParam("password") final String password) throws ConnectException {
		JSONObject json = new JSONObject();
		boolean isAllowed;
		
        if(username.equals("root") && password.equals("Ba@od14121")){
        	session.setAttribute("BLOG_ADMIN", "ok");
        	isAllowed = true;
        } else {
        	isAllowed = false;
        }
        json.put("IS_ALLOWED", isAllowed);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "getDetailStoryAdmin")
    public String getDetailStory(HttpServletResponse response, @RequestParam("storyID")
    final int storyID) throws IOException {
        JSONObject json = new JSONObject();
        DetailStoryAdminModel list = adminService.getStoryDetail(storyID);
        // return "aaa"; // Go to web
        json.put("DETAIL_STORY", list);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "updateStoryAdmin")
    public String updateStoryAdmin(HttpServletResponse response, 
    		@RequestParam("storyID") final int storyID,
    		@RequestParam("title") final String title,
    		@RequestParam("content") final String content) throws IOException {
        JSONObject json = new JSONObject();
        System.out.println("AdminstoryID:" + storyID);
        int row = adminService.updateStory(storyID,title,content);
        // return "aaa"; // Go to web
        json.put("UPDATE_ROW", row);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "deleteStoryAdmin")
    public String deleteStoryAdmin(HttpServletResponse response, 
    		@RequestParam("storyID") final int storyID) throws IOException {
        JSONObject json = new JSONObject();
        int row = adminService.deleteStory(storyID);
        // return "aaa"; // Go to web
        json.put("UPDATE_ROW", row);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "addStoryAdmin")
    public String addStoryAdmin(HttpServletResponse response, 
    		@RequestParam("title") final String title,
    		@RequestParam("content") final String content,
    		@RequestParam("type") final int type,
    		@RequestParam("createDate") final String createDate) throws IOException {
        JSONObject json = new JSONObject();
        int row = adminService.addStory(title,content,type,createDate);
        // return "aaa"; // Go to web
        json.put("UPDATE_ROW", row);
        String messages = new String(parseToJsonString(json));
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
