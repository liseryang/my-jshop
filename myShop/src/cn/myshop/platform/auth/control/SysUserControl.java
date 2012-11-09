package cn.myshop.platform.auth.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.myshop.platform.auth.service.SysUserService;
import cn.myshop.platform.common.base.DataGridModel;
import cn.myshop.platform.common.util.RequestParmConvert;
import cn.myshop.platform.entitys.SysUser;

@Controller
@RequestMapping("/sys")
public class SysUserControl {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/user_mng")
	public ModelAndView  userPage(SysUser sysUser,HashMap map) throws ServletException, IOException{
		//querySysUserList(map);
		return new ModelAndView("sys/sysmng/sys_user_mng","","");
	}
	
	@RequestMapping("/querySysUser")
	@ResponseBody
	public DataGridModel querySysUserList(DataGridModel dataGrid,HttpServletRequest request){
		RequestParmConvert.intropectToDataGrid(request,dataGrid);
//		List<SysUser> userList=sysUserService.querySysUser(dataGrid.getQueryMap());
//		sysUserService.querySysUserData(dataGrid);
//		dataGrid.setTotal(userList.size()+10);
//		dataGrid.setRows(userList);
		return sysUserService.querySysUserData(dataGrid);
	}
	
	
	@RequestMapping("/user_add")
	public ModelAndView  userAddPage() throws ServletException, IOException{
		//querySysUserList(map);
		return new ModelAndView("sys/sysmng/sys_user_add","","");
	}
}
