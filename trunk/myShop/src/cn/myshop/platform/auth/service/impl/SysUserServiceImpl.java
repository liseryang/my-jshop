package cn.myshop.platform.auth.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.myshop.platform.auth.service.SysUserService;
import cn.myshop.platform.common.base.BaseDaoTemplate;
import cn.myshop.platform.entitys.SysUser;

import com.ibatis.sqlmap.engine.execution.SqlExecutorback;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

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
