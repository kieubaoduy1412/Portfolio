
package com.sun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.IndexDAO;
import com.sun.model.UserModel;

@Service
public class IndexService {
    @Autowired
    private IndexDAO indexDAO;

    public List<UserModel> getAllUser() {
        List<UserModel> list = indexDAO.getAllUser();
        return list;
    }
}
