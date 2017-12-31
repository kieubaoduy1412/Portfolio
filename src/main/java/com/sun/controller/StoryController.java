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

import com.sun.model.StoryDetailModel;
import com.sun.model.StoryModel;
import com.sun.service.StoryService;

@Controller
public class StoryController {
	@Autowired
    private StoryService storyService;
	
	/*@RequestMapping(value = "storyBe")
    public String goToStoryBePage(@RequestParam("s") final int storyID, ModelMap model, HttpSession session) throws IOException {
    	if(session.getAttribute("BLOG_USER")=="ok"){
    		model.addAttribute("STORY_ID", storyID);
    		return "story";
    	}
    	return "portfolioEN";
    }*/
	
	@RequestMapping(value = "test")
    public String goToStoryPage(@RequestParam("s") final int storyID, ModelMap model, HttpSession session) throws IOException {
		model.addAttribute("STORY_ID", storyID);
		return "test";
    }
	
	@ResponseBody
    @RequestMapping(value = "getDetailStory")
    public String getAllStoryForBlogPage(HttpServletResponse response, @RequestParam("storyID")
    	final int storyID) throws IOException {
        JSONObject json = new JSONObject();
        StoryDetailModel model = storyService.getStoryDetail(storyID);
        json.put("ALL_STORY", model);
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
