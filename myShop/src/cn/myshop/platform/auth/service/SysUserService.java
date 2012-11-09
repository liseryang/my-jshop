package cn.myshop.platform.auth.service;

import java.util.HashMap;
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
	public List<SysUser> querySysUser(Map<String,String> userMap);
	public DataGridModel querySysUserData(DataGridModel dataGrid);
	public SysUser addSysUser(SysUser sysUser);
	public SysUser updateSysUser(SysUser sysUser);
	public SysUser batchDelSysUser(HashMap userMap);
}
