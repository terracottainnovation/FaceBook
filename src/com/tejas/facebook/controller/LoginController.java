package com.tejas.facebook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.tejas.facebook.common.MappingURL;

@Controller
public class LoginController {
	
static Logger LOGGER  = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value=MappingURL.FIRST_PAGE_URL, method=RequestMethod.GET)
	public ModelAndView initializePage(HttpServletRequest request, HttpServletResponse response){
		
		LOGGER.info("Loading Initial page ---");
		ModelAndView  modelAndView = new ModelAndView(com.tejas.facebook.common.View.FACEBOOK_LOGIN_VIEW);
		return modelAndView;
	}
}
