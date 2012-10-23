package cn.myshop.app.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.myshop.app.service.TestService;

@Controller
@RequestMapping("/test")
public class TestAction {
	private TestService testService;

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	@RequestMapping(value="/info",method=RequestMethod.POST)
	public ModelAndView getInfo(HttpServletRequest request) {
		//System.out.println("----"+testService.getOrderId()+user.getName());
		return new ModelAndView("test", "info", request.getParameter("name"));
	}

}
