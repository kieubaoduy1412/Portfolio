package com.sun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.AdminDAO;
import com.sun.model.DetailStoryAdminModel;
import com.sun.model.StoryDetailModel;

@Service
public class AdminService {
	@Autowired
    private AdminDAO adminDAO;
	
	public DetailStoryAdminModel getStoryDetail(int storyID){
		DetailStoryAdminModel model = adminDAO.getStoryDetail(storyID);
    	return model;
    }
	
	public int updateStory(int storyID, String title, String content){
		int row = adminDAO.updateStory(storyID,title,content);
    	return row;
    }
	
	public int addStory(String title, String content,int type, String createDate){
		int row = adminDAO.addStory(title,content,type,createDate);
    	return row;
    }
	
	public int deleteStory(int storyID){
		int row = adminDAO.deleteStory(storyID);
    	return row;
    }
}
