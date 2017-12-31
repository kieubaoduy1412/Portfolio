package com.sun.dao;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sun.mapper.Mapper;
import com.sun.model.DetailStoryAdminModel;
import com.sun.model.StoryDetailModel;

@Component
public class AdminDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public DetailStoryAdminModel getStoryDetail(int storyID){
		String sqlStoryDetailForAdmin = "SELECT id,title, content FROM blog.story WHERE ID=" + storyID;
		DetailStoryAdminModel model = this.jdbcTemplate.queryForObject(sqlStoryDetailForAdmin, Mapper.getDetailStoryAdminModel());
    	return model;
    }
	
	public int updateStory(int storyID, String title, String content){
		String sqlUpdateStory = "UPDATE blog.story SET title=?, content=? WHERE ID=?";
		int iResult = jdbcTemplate.update(sqlUpdateStory, new Object[] {
				 title, content, storyID
        });
    	return iResult;
    }
	
	public int deleteStory(int storyID){
		String sqlDeleteStory = "DELETE FROM blog.story WHERE ID=?";
		int iResult = jdbcTemplate.update(sqlDeleteStory, new Object[] {
				  storyID
        });
    	return iResult;
    }
	
	public int addStory(String title, String content,int type, String createDate){
		String sqlAddStory = "INSERT INTO blog.story(title,content,writeID,type,createDate) "
				+ "VALUES (?,?,1,?,?);";
		int iResult = jdbcTemplate.update(sqlAddStory, new Object[] {
				 title, content, type, createDate
        });
		System.out.println("iResult" + iResult);
    	return iResult;
    }
}
