<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>添加人员</title>
    <%@ include file="/jsp/common/public.jsp" %>
	<link rel="stylesheet" href="<%=basePath%>/jsp/admin/css/custom.css" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>/jsp/admin/css/style.css" type="text/css" />
</head>
<body>
	<form id="ff" method="post" novalidate>
	<table class="inputTablea" cellpadding="0" cellspacing="1" border="0" align="center">
				<tr>
					<th width="17%">
						登录名称： 
					</th>
					<td width="33%">
						<input type="text" name="userName" class="" value="" required="true"/>
						<label class="requireField">*</label>
					</td>
					<th width="20%">
						真实姓名：
					</th>
					<td width="30%">
						 <input type="text" name="realName" class="" value="" required="true"/>
						 <label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						密码： 
					</th>
					<td>
						<input type="text"  required="true" name="password"  value="123456" title="默认：123456" />
						<label class="requireField">*</label>
					</td>
					<th>
					用户状态：
					</th>
					<td>
						<select id="status" name="status" panelHeight="80" class="easyui-combobox"  size="2" style="width:100px;">
					   	<option value="0">正常</option>
						<option value="1">停用</option>
						<option value="2">锁定</option>
					   </select>
					</td>
				</tr>
				<tr>
					<th>
						手机号码： 
					</th>
					<td>
						<input type="text" name="mobeilNo" class="formText" value="" />
					</td>
					<th>
						联系电话：
					</th>
					<td>
						<input type="text" name="phoneNo" class="formText" value=""  />
					</td>
				</tr>
				<tr>
					<th>
						邮箱： 
					</th>
					<td>
						<input type="text" name="email" class="formText" value=""  validType="email"/>
						<label class="requireField">*</label>
					</td>
					<th>
						允许登录错误次数：
					</th>
					<td>
						<input type="text" name="maxLoginCount" class="formText" value=""  title="只允许输入零或正整数" />
					</td>
				</tr>
					<tr>
					<th>
						所属机构： 
					</th>
					<td>
						<input type="text" name="orgId" class="formText" value="" />
					</td>
					<th>
						所属部门：
					</th>
					<td>
						<input type="text" name="deptId" class="formText" value="" />
					</td>
				</tr>
			</table>
	    </form>
   <script type="text/javascript">
   $("#status").combobox({
   	valueField:'id',
   	textField:'text'
   });
   </script>
</body>
</html>
