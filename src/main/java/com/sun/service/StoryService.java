package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.StoryDAO;
import com.sun.model.StoryDetailModel;
import com.sun.model.StoryModel;

@Service
public class StoryService {
	@Autowired
    private StoryDAO storyDAO;

    public List<StoryModel> getAllStory(int currentPage) {
        List<StoryModel> list = storyDAO.getAllStory(currentPage);
        return list;
    }
    
    public int getTotalPage() {
        int total = storyDAO.getTotalPage();
        return total;
    }
    
    public StoryDetailModel getStoryDetail(int storyID){
    	StoryDetailModel model = storyDAO.getStoryDetailModel(storyID);
    	return model;
    }
    
    
    /*Blog cua be*/
    public List<StoryModel> getAllStoryBe(int currentPage) {
        List<StoryModel> list = storyDAO.getAllStoryBe(currentPage);
        return list;
    }
    
    public int getTotalPageBe() {
        int total = storyDAO.getTotalPageBe();
        return total;
    }
    
}
