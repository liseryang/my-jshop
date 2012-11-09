package cn.myshop.platform.common.base;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.myshop.platform.common.util.ReflectUtil;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * 公共DAO操作类，代替ibatis的SqlMapClientDaoSupport，并整合jdbcTemplate
 * 
 * @date 2012-08-11 17:24:24
 * @author llliang
 * 
 */

public class BaseDaoTemplate {

	private JdbcTemplate jdbcTemplate;
	private SqlMapClientTemplate ibatisTemplate;
	private SqlExecutor sqlExecutor;

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public SqlMapClientTemplate getIbatisTemplate() {
		return ibatisTemplate;
	}

	public void setIbatisTemplate(SqlMapClientTemplate ibatisTemplate) throws SQLException {
		this.ibatisTemplate = ibatisTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) throws SQLException {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setEnableLimit(boolean enableLimit) {
		if (sqlExecutor instanceof LimitSqlExecutor) {
			((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
		}
	}

	@SuppressWarnings("deprecation")
	public void initialize() throws Exception {
		if (sqlExecutor != null) {
			SqlMapClient sqlMapClient = ibatisTemplate.getSqlMapClient();
			if (sqlMapClient instanceof ExtendedSqlMapClient) {
				ReflectUtil.setFieldValue(((ExtendedSqlMapClient) sqlMapClient).getDelegate(), "sqlExecutor", SqlExecutor.class, sqlExecutor);
			}
		}
	}

	/**
	 * 根据ibatis statementId获取查询sql语句，并返回该sql查询结果总记录数
	 * 
	 * @param statementId （statementId 必须为查询类sql）
	 * @param objectParm
	 * @return
	 */
	public int getQueryCount(String statementId, Object objectParm) {
		try {
			SqlMapClientImpl sqlmapClient = (SqlMapClientImpl) getIbatisTemplate().getSqlMapClient();
			MappedStatement mappedStatement = sqlmapClient.getMappedStatement(statementId);
			Sql stmtSql = mappedStatement.getSql();
			SessionScope sessionScope = new SessionScope();
			sessionScope.setSqlMapClient(sqlmapClient);
			StatementScope statementScope = new StatementScope(sessionScope);
			sessionScope.incrementRequestStackDepth();
			mappedStatement.initRequest(statementScope);
			String querySql = stmtSql.getSql(statementScope, objectParm).toUpperCase();
			// 确保执行的sql为查询类型sql
			if (querySql.trim().indexOf("SELECT") != 0) {
				throw new Exception("The currently executing SQL statement, not the query sql。ibatis statement id："+statementId);
			}
			String countSql = " select count(1) dataCount from (" + querySql + ") t1 ";
			int count = getJdbcTemplate().queryForInt(countSql);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 基于ibatis的物理分页方法，返回类型为List
	 * 
	 * @param statementId
	 * @param dataGrid
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List queryForPageList(String statementId, DataGridModel dataGrid) {
		if(StringUtils.isNotEmpty(statementId)||dataGrid==null)return null;
		int page=dataGrid.getPage();
		int pageRows=dataGrid.getPageRows();
		List rows=	getIbatisTemplate().queryForList(statementId,dataGrid.getQueryMap(),(page-1)*pageRows,(page-1)*pageRows+pageRows);
		return rows;
	}

	/**
	 *  基于ibatis的物理分页方法，返回类型为DataGridModel
	 * 
	 * @param statementId
	 * @param dataGrid
	 * @return DataGridModel
	 */
	@SuppressWarnings("unchecked")
	public DataGridModel queryForPageDataGrid(String statementId,DataGridModel dataGrid) {
		if(StringUtils.isEmpty(statementId)||dataGrid==null)return null;
		
		dataGrid.setTotal(getQueryCount(statementId,dataGrid.getQueryMap()));
		
		int page=dataGrid.getPage();
		int pageRows=dataGrid.getPageRows();
		List rows=	getIbatisTemplate().queryForList(statementId,dataGrid.getQueryMap(),(page-1)*pageRows,(page-1)*pageRows+pageRows);
		dataGrid.setRows(rows);
		return dataGrid;
	}

	
	public static void main(String[] ab) {
		String a = "    select * select ";
		int abc = a.trim().indexOf("select");
		System.out.print(abc);
	}
}
