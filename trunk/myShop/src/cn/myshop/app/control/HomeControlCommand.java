package cn.myshop.app.control;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HomeControlCommand implements Controller {

	 
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 List list=testService;
 		return new ModelAndView("home","message","list.size()");
	}
}
