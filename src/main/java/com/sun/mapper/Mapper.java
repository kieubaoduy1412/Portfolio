
package com.sun.mapper;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sun.model.DetailStoryAdminModel;
import com.sun.model.StoryDetailModel;
import com.sun.model.StoryModel;
import com.sun.model.UserModel;

public class Mapper {
    public static RowMapper<UserModel> getAccount() {
        /*
         * RowMapper<UserModel> mapper = new RowMapper<UserModel>() {
         * @Override public UserModel mapRow(ResultSet rs, int rowNum) throws
         * SQLException { UserModel obj = new UserModel();
         * obj.setAccountID(rs.getString("AccountID"));
         * obj.setPassword(rs.getString("Password")); return obj; } }; return
         * mapper;
         */

        RowMapper<UserModel> mapper = new RowMapper<UserModel>() {
            public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserModel obj = new UserModel();
                obj.setAccountID(rs.getString("AccountID"));
                try {
                    obj.setPassword( new String(rs.getString("Password").getBytes("UTF-8"), "ISO-8859-1"));
                    System.out.println(
                            new String(rs.getString("Password").getBytes("UTF-8"), "ISO-8859-1"));
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return obj;
            }
        };
        return mapper;
    }
    
    public static RowMapper<StoryModel> getAllStory() {

        RowMapper<StoryModel> mapper = new RowMapper<StoryModel>() {
            public StoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            	StoryModel obj = new StoryModel();
                obj.setStoryID(rs.getInt("ID"));
                try {
					obj.setTitle(new String(rs.getString("title").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setContent(new String(rs.getString("content").getBytes("UTF-8"), "ISO-8859-1"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                obj.setWriteID(rs.getInt("writeID"));
                obj.setType(rs.getInt("type"));
                obj.setCreateDate(rs.getString("createDate"));
                return obj;
            }
        };
        return mapper;
    }
    
    public static RowMapper<StoryDetailModel> getDetailStoryModelButNotFirst() {
        RowMapper<StoryDetailModel> mapper = new RowMapper<StoryDetailModel>() {
            public StoryDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            	StoryDetailModel obj = new StoryDetailModel();
                try {
					obj.setTitle(new String(rs.getString("title").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setContent(new String(rs.getString("content").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setType(rs.getInt("type"));
					obj.setNextStoryTitle(new String(rs.getString("nextStoryTitle").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setPreviousStoryTitle(new String(rs.getString("previousStoryTitle").getBytes("UTF-8"), "ISO-8859-1"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                obj.setNextStoryID(rs.getInt("nextStoryID"));
                obj.setPreviousStoryID(rs.getInt("previousStoryID"));
                return obj;
            }
        };
        return mapper;
    }
    
    public static RowMapper<StoryDetailModel> getDetailStoryModelFirst() {
        RowMapper<StoryDetailModel> mapper = new RowMapper<StoryDetailModel>() {
            public StoryDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            	StoryDetailModel obj = new StoryDetailModel();
                try {
					obj.setTitle(new String(rs.getString("title").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setContent(new String(rs.getString("content").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setType(rs.getInt("type"));
					obj.setNextStoryTitle(new String(rs.getString("nextStoryTitle").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setPreviousStoryTitle("");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                obj.setNextStoryID(rs.getInt("nextStoryID"));
                obj.setPreviousStoryID(0);
                return obj;
            }
        };
        return mapper;
    }
    
    public static RowMapper<StoryDetailModel> getDetailStoryModelLast() {
        RowMapper<StoryDetailModel> mapper = new RowMapper<StoryDetailModel>() {
            public StoryDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            	StoryDetailModel obj = new StoryDetailModel();
                try {
					obj.setTitle(new String(rs.getString("title").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setContent(new String(rs.getString("content").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setType(rs.getInt("type"));
					obj.setNextStoryTitle(new String(""));
					obj.setPreviousStoryTitle(new String(rs.getString("previousStoryTitle").getBytes("UTF-8"), "ISO-8859-1"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                obj.setNextStoryID(0);
                obj.setPreviousStoryID(rs.getInt("previousStoryID"));
                return obj;
            }
        };
        return mapper;
    }
    
    public static RowMapper<DetailStoryAdminModel> getDetailStoryAdminModel() {
        RowMapper<DetailStoryAdminModel> mapper = new RowMapper<DetailStoryAdminModel>() {
            public DetailStoryAdminModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            	DetailStoryAdminModel obj = new DetailStoryAdminModel();
            	obj.setId(rs.getInt("ID"));
                try {
					obj.setTitle(new String(rs.getString("title").getBytes("UTF-8"), "ISO-8859-1"));
					obj.setContent(new String(rs.getString("content").getBytes("UTF-8"), "ISO-8859-1"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                return obj;
            }
        };
        return mapper;
    }
}
