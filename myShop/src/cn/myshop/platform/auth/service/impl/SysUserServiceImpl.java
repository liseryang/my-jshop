package cn.myshop.platform.auth.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Service;

import cn.myshop.platform.auth.service.SysUserService;
import cn.myshop.platform.common.base.BaseDaoTemplate;
import cn.myshop.platform.common.base.DataGridModel;
import cn.myshop.platform.entitys.SysUser;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 系统用户管理
 * 
 * @date 2012-09-23 14:21:45
 * @author llliang
 * 
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseDaoTemplate implements SysUserService {
	public BaseDaoTemplate baseDaoTemplate;

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
	 * 获取用户信息
	 * 
	 * @param sysUser
	 * @return
	 */
	public SysUser getSysUser(SysUser sysUser) {
		sysUser = (SysUser) baseDaoTemplate.getIbatisTemplate().queryForObject("getSysUser", sysUser);
		return sysUser;
	}

	/**
	 * 查询系统用户
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SysUser> querySysUser(Map<String, String> userMap) {
		List<SysUser> sysUserlist = baseDaoTemplate.getIbatisTemplate().queryForList("querySysUser", userMap, Integer.parseInt(userMap.get("startRow")),
				Integer.parseInt(userMap.get("endRow")));
		return sysUserlist;
	}

	/**
	 * 查询系统用户
	 */
	public DataGridModel querySysUserData(DataGridModel dataGrid) {
		return baseDaoTemplate.queryForPageDataGrid("querySysUser", dataGrid);
	}

	/**
	 * 添加用户
	 */
	public boolean addSysUser(SysUser sysUser) {
		sysUser.setUserId(String.valueOf(this.getColumnMaxValueForInt("SYS_USER", "USER_ID") + 1));
		sysUser.setInvalidDate(new Date());
		sysUser.setErrLoginCount(0);
		getIbatisTemplate().insert("addSysUser", sysUser);
		return true;
	}

	/**
	 * 批量删除用户
	 */
	@SuppressWarnings("unchecked")
	public int batchDelSysUser(final List<String> list) throws Exception {
		if (list != null) {
			getIbatisTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					for (int i = 0, n = list.size(); i < n; i++) {
						executor.delete("batchDelSysUser", list.get(i));
					}
					executor.executeBatch();
					return null;
				}
			});
		}
		int i = 0;
		return i;
	}

	/**
	 * 修改用户信息
	 */
	public int updateSysUser(SysUser sysUser) {
		return getIbatisTemplate().update("updateSysUser", sysUser);
	}
	
	/**
	 * 修改用户密码
	 * @param sysUser
	 * @return
	 */
	public int changeSysUserPwd(SysUser sysUser) {
		return getIbatisTemplate().update("changeSysUserPwd", sysUser);
	}
}
