package com.sun.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
	@RequestMapping(value = "portfolioEN")
    public String goToPortfolioEN(HttpServletResponse response) throws IOException {
    	return "portfolioEN";
    }
    
    @RequestMapping(value = "portfolioJP")
    public String goToportfolioJP(HttpServletResponse response) throws IOException {
    	return "portfolioJP";
    }
    
    @RequestMapping(value = "blogBe")
    public String goToBlogBePage(HttpServletResponse response, HttpSession session) throws IOException {
    	if(session.getAttribute("BLOG_USER")=="ok"){
    		return "blog-be";
    	}
    	return "blog-be-login";
    }
    
    @RequestMapping(value = "blog")
    public String goToBlogPage(HttpServletResponse response, HttpSession session) throws IOException {
    	if(session.getAttribute("BLOG_USER")=="ok"){
    		return "blog-be";
    	}
    	return "blog-be-login";
    }
    
    @RequestMapping(value = "admin")
    public String goToAdminLoginPage(HttpServletResponse response, HttpSession session) throws IOException {
    	if(session.getAttribute("BLOG_ADMIN")=="ok"){
    		return "admin";
    	}
    	return "admin-login";
    }
}
