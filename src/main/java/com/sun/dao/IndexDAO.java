
package com.sun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sun.mapper.Mapper;
import com.sun.model.UserModel;

@Component
public class IndexDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sqlGetAllUser = "SELECT * FROM atmdb.account;";

    public List<UserModel> getAllUser() {
        List<UserModel> list = this.jdbcTemplate.query(sqlGetAllUser, Mapper.getAccount());
        return list;
    }
}
