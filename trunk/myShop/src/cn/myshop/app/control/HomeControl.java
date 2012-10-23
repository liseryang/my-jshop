package cn.myshop.app.control;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.myshop.app.service.TestService;
 
@Controller
public class HomeControl  {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/home")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
		Map map=new HashMap();
		map.put("password", request.getParameter("password"));
		//map.put("size", list.size());
		map.put("orderId",testService.getOrderId());
		int[] i=testService.delAndUpdate();
		map.put("delSize",i[0]);
		map.put("updateSize",i[1]);
		return new ModelAndView("home","message",map);
	}
	 
}
