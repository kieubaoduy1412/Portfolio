package com.sun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sun.mapper.Mapper;
import com.sun.model.StoryDetailModel;
import com.sun.model.StoryModel;

@Component
public class StoryDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    private String sqlGetTotalPage = "SELECT COUNT(*) FROM blog.story;";
    private String sqlGetTotalPageBe = "SELECT COUNT(*) FROM blog.story;";
    private String sqlGetFirstID = "SELECT ID FROM blog.story ORDER BY id ASC LIMIT 1;";
    private String sqlGetLastID = "SELECT ID FROM blog.story ORDER BY id DESC LIMIT 1;";
    

    public List<StoryModel> getAllStory(int currentPage) {
    	String sqlGetAllStory = "SELECT * FROM blog.story ORDER BY id DESC LIMIT " + currentPage*5 + ", 5" ;
        List<StoryModel> list = this.jdbcTemplate.query(sqlGetAllStory, Mapper.getAllStory());
        return list;
    }
    
    public List<StoryModel> getAllStoryBe(int currentPage) {
    	String sqlGetAllStory = "SELECT * FROM blog.story ORDER BY id DESC LIMIT " + currentPage*5 + ", 5" ;
        List<StoryModel> list = this.jdbcTemplate.query(sqlGetAllStory, Mapper.getAllStory());
        return list;
    }
    
    public int getTotalPage(){
    	int total = this.jdbcTemplate.queryForObject(sqlGetTotalPage, Integer.class);
    	return total;
    }
    
    public int getTotalPageBe(){
    	int total = this.jdbcTemplate.queryForObject(sqlGetTotalPageBe, Integer.class);
    	return total;
    }
    
    public StoryDetailModel getStoryDetailModel(int storyID){
    	String sqlGetStoryDetailButNotFirst = 
    			"SELECT a.title, a.content, a.type, b.nextStoryTitle, b.nextStoryID, c.previousStoryTitle, c.previousStoryID	FROM blog.story a "
    			+ "JOIN (SELECT title AS nextStoryTitle, id AS nextStoryID FROM blog.story WHERE ID = (SELECT min(ID) FROM blog.story WHERE id > " + storyID + ")) b "
    			+ "JOIN (SELECT title AS previousStoryTitle, id AS previousStoryID FROM blog.story WHERE ID = (select max(ID) from blog.story where id < " + storyID + ")) c "
    			+ "WHERE a.ID = " + storyID;
    	String sqlGetStoryDetailLast = 
    			"SELECT a.title, a.content, a.type, c.previousStoryTitle, c.previousStoryID FROM blog.story a "
    			+ "JOIN (SELECT title AS previousStoryTitle, id AS previousStoryID FROM blog.story WHERE ID = (select max(ID) from blog.story where id < " + storyID + ")) c "
    			+ "WHERE a.ID = " + storyID;
    	String sqlGetStoryDetailFirst = 
    			"SELECT a.title, a.content, a.type, b.nextStoryTitle, b.nextStoryID FROM blog.story a "
    			+ "JOIN (SELECT title AS nextStoryTitle, id AS nextStoryID FROM blog.story WHERE ID = (SELECT min(ID) FROM blog.story WHERE id > " + storyID + ")) b "
    			+ "WHERE a.ID = " + storyID;
    	StoryDetailModel model = null;
    	if(storyID == getFirstStoryID()){
    		model = this.jdbcTemplate.queryForObject(sqlGetStoryDetailFirst, Mapper.getDetailStoryModelFirst());
    	} else if(storyID == getLastStoryID()){
    		model = this.jdbcTemplate.queryForObject(sqlGetStoryDetailLast, Mapper.getDetailStoryModelLast());
    	} else {
    		model = this.jdbcTemplate.queryForObject(sqlGetStoryDetailButNotFirst, Mapper.getDetailStoryModelButNotFirst());
    	}
        return model;
    }
    
    public int getLastStoryID(){
    	int index = this.jdbcTemplate.queryForObject(sqlGetLastID, Integer.class);
    	return index;
    }
    
    public int getFirstStoryID(){
    	int index = this.jdbcTemplate.queryForObject(sqlGetFirstID, Integer.class);
    	return index;
    }
}
