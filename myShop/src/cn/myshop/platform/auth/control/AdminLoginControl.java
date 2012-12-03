package cn.myshop.platform.auth.control;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.myshop.platform.auth.service.AdminLoginService;
import cn.myshop.platform.common.ConstantPlatform;
import cn.myshop.platform.common.util.MD5Encoder;
import cn.myshop.platform.common.util.properties.PropertiesUtil;
import cn.myshop.platform.entitys.SysUser;

@Controller
@RequestMapping("/sys")
public class AdminLoginControl {
	
	@Autowired
	public AdminLoginService adminLoginService;
	
	/**
	 * 登录请求页面
	 * @return
	 */
	@RequestMapping("/admin_login")
	public ModelAndView  adminLoginPage(){
		return new ModelAndView("sys/login");
	}
	
	/**
	 * 用户登录校验
	 * @param requestSysUser
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginCheck")
	@ResponseBody
	public HashMap<String,Object>  adminLogin(SysUser requestSysUser,HttpSession session){
		HashMap<String,Object> map=new HashMap<String,Object>();
		
		SysUser user=adminLoginService.getSysUser(requestSysUser);
		String infoMess=null;
		
		//该用户不存在
		if(requestSysUser.getUserName()==null||user==null||user.getUserId()==null){
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
		else if(!MD5Encoder.getMD5(requestSysUser.getPassword()).equals(user.getPassword())){
			infoMess=PropertiesUtil.getMessValue("sys_user_pwderr");
		}
		if(infoMess==null){//redirect:/sys/index.do, forward:/jsp/admin/login.jsp 
			map.put("login", true);
			session.setAttribute(ConstantPlatform.SYS_USER_INFO, user);
		}else{
			map.put("login", false);
			map.put("infoMess", infoMess);
		}
		
		return map;  
		
	}
	
	/**
	 * 系统首页
	 * @param session
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView adminIndex(HttpSession session){
		SysUser userInfo=(SysUser)session.getAttribute(ConstantPlatform.SYS_USER_INFO);
		if(userInfo==null){
			return this.adminLoginPage();
		}
		return new ModelAndView("sys/index","userInfo",userInfo);
	}
	
	
	/**
     * 用户注销
     *
     * @param session
     */
	@RequestMapping(value="/logout")
    public ModelAndView logout(HttpSession session) {

        if (session.getAttribute(ConstantPlatform.SYS_USER_INFO) != null) {
            //移除Session对象
            session.removeAttribute(ConstantPlatform.SYS_USER_INFO);
            //清除session
            session.invalidate();
        }
        //返回登陆页面
        return this.adminLoginPage();
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
