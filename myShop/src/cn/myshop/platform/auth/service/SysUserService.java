package cn.myshop.platform.auth.service;

import java.util.List;
import java.util.Map;

import cn.myshop.platform.common.base.DataGridModel;
import cn.myshop.platform.entitys.SysUser;

 /**
  * 系统用户管理
  * @date 2012-08-23 20:59:00
  * @author llliang
  *
  */
public interface SysUserService {
	public SysUser getSysUser(SysUser sysUser);
	public int queryCountSysUser(Map<String, Object> userMap);
	public DataGridModel querySysUserData(DataGridModel dataGrid);
	public boolean addSysUser(SysUser sysUser);
	public int updateSysUser(SysUser sysUser);
	public int batchDelSysUser(List<String> userId)  throws Exception;
	public int changeSysUserPwd(SysUser sysUser);
}
