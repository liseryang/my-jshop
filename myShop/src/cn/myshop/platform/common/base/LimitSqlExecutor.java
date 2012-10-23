package cn.myshop.platform.common.base;

import java.sql.Connection;
import java.sql.SQLException;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;

public class LimitSqlExecutor extends SqlExecutor {
	// private static final Log logger =
	// LogFactory.getLog(LimitSqlExecutor.class);

	private boolean enableLimit = true;

	public boolean isEnableLimit() {
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.enableLimit = enableLimit;
	}

	@Override
	public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults,
			RowHandlerCallback callback) throws SQLException {
		if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS) && supportsLimit()) {
			sql = Dialect.getLimitString(conn.getMetaData().getDatabaseProductName(), sql, skipResults, maxResults);
			// if(logger.isDebugEnabled()){
			// logger.debug(sql);
			// }
			skipResults = NO_SKIPPED_RESULTS;
			maxResults = NO_MAXIMUM_RESULTS;
		}
		super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
	}

	public boolean supportsLimit() {
		if (enableLimit) {
			return true;
		}
		return false;
	}

}
