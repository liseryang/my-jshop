package cn.myshop.platform.auth.control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.myshop.platform.auth.service.AdminLoginService;
import cn.myshop.platform.common.ConstantPlatform;
import cn.myshop.platform.common.util.properties.PropertiesUtil;
import cn.myshop.platform.entitys.SysUser;

@Controller
@RequestMapping("/sys")
public class AdminLoginControl {
	
	@Autowired
	public AdminLoginService adminLoginService;
	
	@RequestMapping("/admin_login")
	public ModelAndView  loginPage(SysUser sysUser) throws ServletException, IOException{
		return new ModelAndView("sys/login");
	}
	
	@RequestMapping("/loginCheck")
	public ModelAndView  adminLogin(SysUser sysUser) throws ServletException, IOException{
		SysUser user=adminLoginService.getSysUser(sysUser);
		String infoMess=null;
		
		//该用户不存在
		if(sysUser.getUserName()==null||user==null||user.getUserId()==null){
			infoMess=PropertiesUtil.getMessValue("sys_user_noexist");
		}
		//用户已被停用
		else if(ConstantPlatform.SYSUSER_STATUS_STOP.equals(user.getStatus())){
			infoMess=PropertiesUtil.getMessValue("sys_user_stop");
		}
		//用户被锁定
		else if(ConstantPlatform.SYSUSER_STATUS_LOCK.equals(user.getStatus())){
			infoMess=PropertiesUtil.getMessValue("sys_user_lock");
		}
		//密码错误
		else if(sysUser.getPassword()!=null&&!sysUser.getPassword().equals(user.getPassword())){
			infoMess=PropertiesUtil.getMessValue("sys_user_passerr");
		}
		if(infoMess!=null){//"redirect" forward:/jsp/admin/login.jsp new RedirectView( "../jsp/admin/index.htm
			return new ModelAndView("sys/login","infoMess",infoMess);   
		}
		
		return new ModelAndView("redirect:/sys/index.do");// new ModelAndView("home");
	}
	
	@RequestMapping("/index")
	public ModelAndView adminIndex(){
		HashMap userInfo=new HashMap();
		userInfo.put("userName", "刘理良");
		userInfo.put("roleName", "超级管理员");
		return new ModelAndView("sys/index","userInfo",userInfo);
	}
	
	
	@RequestMapping("/main")
	public ModelAndView frameTop(){
		return new ModelAndView("sys/main");
	}
	
	@RequestMapping("/left")
	public ModelAndView frameLeft(){
		return new ModelAndView("admin/left");
	}
	
	
}
