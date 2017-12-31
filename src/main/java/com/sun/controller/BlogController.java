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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.model.StoryModel;
import com.sun.model.UserModel;
import com.sun.service.StoryService;

@Controller
public class BlogController {
	@Autowired
    private StoryService storyService;
	
	@ResponseBody
    @RequestMapping(value = "getAllStoryForBlogPage")
    public String getAllStoryForBlogPage(HttpServletResponse response, @RequestParam("currentPage")
    final int currentPage) throws IOException {
        JSONObject json = new JSONObject();
        List<StoryModel> list = storyService.getAllStory(currentPage-1);
        json.put("ALL_STORY", list);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "getTotalPage")
    public String getTotalPage(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        int total = storyService.getTotalPage();
        json.put("INT_TOTAL_PAGE", total);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
	@RequestMapping(value = "loginToBlogBe")
    public String loginToBlogBe(HttpServletResponse response, HttpSession session, 
    		@RequestParam("password") final String password) throws ConnectException {
		JSONObject json = new JSONObject();
		boolean isAllowed;
		
        if(password.equals("14071207")){
        	session.setAttribute("BLOG_USER", "ok");
        	isAllowed = true;
        } else {
        	isAllowed = false;
        }
        json.put("IS_ALLOWED", isAllowed);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "getAllStoryForBlogBePage")
    public String getAllStoryForBlogBePage(HttpServletResponse response, @RequestParam("currentPage")
    final int currentPage) throws IOException {
        JSONObject json = new JSONObject();
        List<StoryModel> list = storyService.getAllStoryBe(currentPage-1);
        json.put("ALL_STORY", list);
        String messages = new String(parseToJsonString(json));
        return messages;
    }
	
	@ResponseBody
    @RequestMapping(value = "getTotalPageBe")
    public String getTotalPageBe(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        int total = storyService.getTotalPageBe();
        json.put("INT_TOTAL_PAGE", total);
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
