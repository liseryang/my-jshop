package cn.myshop.platform.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.myshop.platform.auth.service.SysUserService;
import cn.myshop.platform.common.base.BaseDaoTemplate;
import cn.myshop.platform.common.base.DataGridModel;
import cn.myshop.platform.entitys.SysUser;

/**
 * 系统用户管理
 * @date 2012-09-23 14:21:45
 * @author llliang
 *
 */
@Service("sysUserService")
public class SysUserServiceImpl  implements SysUserService{
	public  BaseDaoTemplate baseDaoTemplate;
	 
	public BaseDaoTemplate getBaseDaoTemplate() {
		return baseDaoTemplate;
	}

	public void setBaseDaoTemplate(BaseDaoTemplate baseDaoTemplate) {
		this.baseDaoTemplate = baseDaoTemplate;
	}


	public SysUser adminLogin(SysUser sysUser) {
		
		return this.getSysUser(sysUser);
	}
	
	
	/**
	 * 查看系统用户信息
	 * @param sysUser
	 * @return
	 */
	public SysUser getSysUser(SysUser sysUser){
		sysUser=(SysUser) baseDaoTemplate.getIbatisTemplate().queryForObject("getSysUser",sysUser);
		return sysUser;
	}
	
	/**
	 * 查询系统用户
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SysUser> querySysUser(Map<String,String> userMap) {
		   List<SysUser> sysUserlist =baseDaoTemplate.getIbatisTemplate().queryForList("querySysUser",userMap,Integer.parseInt(userMap.get("startRow")),Integer.parseInt(userMap.get("endRow")));
		return sysUserlist;
	}
	 
	public DataGridModel querySysUserData(DataGridModel dataGrid){
		  return baseDaoTemplate.queryForPageDataGrid("querySysUser", dataGrid);
	}

	public SysUser addSysUser(SysUser sysUser) {
		// TODO Auto-generated method stub
		return null;
	}


	public SysUser batchDelSysUser(HashMap userMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public SysUser updateSysUser(SysUser sysUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
