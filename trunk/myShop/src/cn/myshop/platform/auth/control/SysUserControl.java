package cn.myshop.platform.auth.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.myshop.platform.auth.service.SysUserService;
import cn.myshop.platform.common.ConstantPlatform;
import cn.myshop.platform.common.base.DataGridModel;
import cn.myshop.platform.common.util.MD5Encoder;
import cn.myshop.platform.common.util.RequestParmConvert;
import cn.myshop.platform.common.util.properties.PropertiesUtil;
import cn.myshop.platform.entitys.SysUser;

@Controller
@RequestMapping("/sys")
public class SysUserControl  extends HttpServlet{

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/mngSysUser_page")
	public ModelAndView  userPage(SysUser sysUser) {
		return new ModelAndView("sys/sysmng/mng_sys_user");
	}
	
	@RequestMapping("/querySysUser")
	@ResponseBody
	public DataGridModel querySysUserList(DataGridModel dataGrid,HttpServletRequest request){
		
		RequestParmConvert.intropectToDataGrid(request,dataGrid);
		//dataGrid.getQueryMap().put("userId", 1);
		//dataGrid.getQueryMap().put("userName", "admin");
		return sysUserService.querySysUserData(dataGrid);
	}
	
	
	@RequestMapping("/addSysUser_page")
	public ModelAndView  addSysUserPage(){
		return new ModelAndView("sys/sysmng/add_sys_user");
	}
	
	@RequestMapping("/addSysUser")
	@ResponseBody
	public Map<String,String>  addSysUser(SysUser sysUser){
		Map<String,String> msg=new HashMap<String,String>();
		try{
		  sysUser.setPassword(MD5Encoder.getMD5(sysUser.getPassword()));
		  sysUserService.addSysUser(sysUser);
		  msg.put("msg", PropertiesUtil.getMessValue("sys_save_succeed"));
		}catch(Exception e){
		  msg.put("msg",PropertiesUtil.getMessValue("sys_save_error"));
		  e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("/viewSysUser_page")
	public ModelAndView  sysUserViewPage() throws ServletException, IOException{
		return new ModelAndView("sys/sysmng/view_sys_user");
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/updateSysUser")
	@ResponseBody
	public Map<String,String>  updateSysUser(SysUser sysUser){
		Map<String,String> msg=new HashMap<String,String>();
		int result=0;
		try{
		  result=sysUserService.updateSysUser(sysUser);
		}catch(Exception e){
		  e.printStackTrace();
		}finally{
		   if(result==1){
			    msg.put("msg", PropertiesUtil.getMessValue("sys_save_succeed"));
			  }else{
				msg.put("msg",PropertiesUtil.getMessValue("sys_save_error"));
			  }
		return msg;	
		}
	}
	
	
	@RequestMapping("/batchDelSysUser")
	@ResponseBody
	public Map<String,String>  batchDelSysUser(@RequestParam("userId") List<String> userId) {
		Map<String,String> msg=new HashMap<String,String>();
		try{
		  sysUserService.batchDelSysUser(userId);
		  msg.put("msg", PropertiesUtil.getMessValue("sys_del_succeed"));
		}catch(Exception e){
		  msg.put("msg",PropertiesUtil.getMessValue("sys_del_error"));
		  e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("/changeUserPassword")
	@ResponseBody
	public Map<String,Object> changeUserPassword(HttpServletRequest request,HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		String msg=null;
		boolean result=false;
		try{
			SysUser sessionSysUser=(SysUser)session.getAttribute(ConstantPlatform.SYS_USER_INFO);
			if(sessionSysUser==null||sessionSysUser.getUserName()==null){
				msg= "用户会话超时或者未登录，请登录后再进行修改！";
				map.put("msg", msg);
				return map;
			}
			
			String newPwd=request.getParameter("newPwd");
			String newPwd2=request.getParameter("newPwd2");
			if(newPwd==null||!newPwd.equals(newPwd2)){
				msg= "新密码输入有误，请重新输入！";
			}
			
			SysUser sysUser=new SysUser();
			sysUser.setUserName(sessionSysUser.getUserName());
			sysUser=sysUserService.getSysUser(sysUser);
			if(!sysUser.getPassword().equals(MD5Encoder.getMD5(request.getParameter("oldPwd")))){
				msg= "原密码输入不正确，请重新输入！";
			}
			
			sysUser.setPassword(MD5Encoder.getMD5(newPwd));
			if(msg==null&&sysUserService.changeSysUserPwd(sysUser)==1){
				result=true;
				msg= "修改密码成功！";
			}
		}catch(Exception e){
			msg= "系统异常，修改密码失败，请联系管理员！";
			e.printStackTrace();
		}
		map.put("msg", msg);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Map<String,Object> checkUserName(HttpServletRequest request){
		Map<String,Object> map=RequestParmConvert.intropectToMap(request);
		try{
			int countUser=sysUserService.queryCountSysUser(map);
			if(countUser==0){
				map.put("msg", "名字可以使用");
				map.put("result", true);
			}else{
				map.put("msg", "名字已经存在");
				map.put("result", false);
			}
		}catch(Exception e){
			map.put("msg", "系统异常请稍后再试！");
			map.put("result", false);
		}	
		return map;
	}
}
