package cn.myshop.platform.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.myshop.platform.auth.service.AdminLoginService;
import cn.myshop.platform.common.base.BaseDaoTemplate;
import cn.myshop.platform.entitys.SysUser;

@Service("adminLoginService")
public class AdminLoginServiceImpl  implements AdminLoginService{
	
	@Autowired
	public BaseDaoTemplate baseDaoTemplate;
	
	public SysUser adminLogin(SysUser sysUser) {
		
		return this.getSysUser(sysUser);
	}
	
	/**
	 * 查询用户信息
	 * @param sysUser
	 * @return
	 */
	public SysUser getSysUser(SysUser sysUser){
		sysUser=(SysUser) baseDaoTemplate.getIbatisTemplate().queryForObject("getSysUser",sysUser);
		return sysUser;
	}

}
