package cn.myshop.platform.common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.myshop.platform.common.base.DataGridModel;

/**
 * 转换requestParameters到Map中
 * @date 2012-09-24 20:10:58
 * @author llliang
 *
 */
public class RequestParmConvert {
	
	/**
	 * 转换requestParameters到Map中
	 * @param HttpServletRequest request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> intropectToMap(HttpServletRequest request) {
		Enumeration e = request.getParameterNames();
		Map<String, String> parmMap = new HashMap<String, String>();
		String name =null;
		while (e.hasMoreElements()) {
			name= (String) e.nextElement();
			parmMap.put(name, request.getParameter(name));
		}
		setPaginationRows(parmMap);
		return parmMap;
	}


	 /**
	  * 转换requestParameters到DataGridModel中
	  * @param request
	  * @param dataGrid
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public static DataGridModel intropectToDataGrid(HttpServletRequest request,DataGridModel dataGrid) {
		if(StringUtils.isNotEmpty(request.getParameter("rows"))){
			dataGrid.setPageRows(Integer.valueOf(request.getParameter("rows")));
		}
		dataGrid.setQueryMap(intropectToMap(request));
		return dataGrid;
	}
	
	/**
	 * 设置分页查询数据条数
	 * @param map
	 */
	private static void setPaginationRows(Map<String,String> map){
		if(StringUtils.isNotEmpty(map.get("rows"))&&StringUtils.isNotEmpty(map.get("page"))){
			int page=Integer.valueOf(map.get("page"));
			int pageRows=Integer.valueOf(map.get("rows"));
			map.put("startRow", String.valueOf((page-1)*pageRows));
			map.put("endRow", String.valueOf((page-1)*pageRows+pageRows));
			
		}
	}
}
