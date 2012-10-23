<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>人员管理</title>
    <%@ include file="/jsp/common/public.jsp" %>
 	<script>
		var products = [
		    {productid:'FI-SW-01',name:'Koi'},
		    {productid:'K9-DL-01',name:'Dalmation'},
		    {productid:'RP-SN-01',name:'Rattlesnake'},
		    {productid:'RP-LI-02',name:'Iguana'},
		    {productid:'FL-DSH-01',name:'Manx'},
		    {productid:'FL-DLH-02',name:'Persian'},
		    {productid:'AV-CB-01',name:'Amazon Parrot'}
		];
	
		function productFormatter(value){
			for(var i=0; i<products.length; i++){
				if (products[i].productid == value) return products[i].name;
			}
			return value;
		}
		$(function(){
			var lastIndex;
			$('#tt').datagrid({
				url:'<%=basePath%>/sys/querySysUser.do?sysUser.orgId=123&deptId=456',
				fitColumns:true,
				pagination:true,
				rownumbers:false,
				singleSelect:false,
				frozenColumns:[[
	                {field:'ck',checkbox:true}
				]],
				toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
					
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('appendRow',{
							itemid:'',
							productid:'',
							listprice:'',
							unitprice:'',
							attr1:'',
							status:''
						});
						lastIndex = $('#tt').datagrid('getRows').length-1;
						$('#tt').datagrid('selectRow', lastIndex);
						$('#tt').datagrid('beginEdit', lastIndex);
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						var row = $('#tt').datagrid('getSelected');
						if (row){
							var index = $('#tt').datagrid('getRowIndex', row);
							$('#tt').datagrid('deleteRow', index);
						}
					}
				},'-',{
					text:'accept',
					iconCls:'icon-save',
					handler:function(){
						$('#tt').datagrid('acceptChanges');
					}
				},'-',{
					text:'撤销更改',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}],
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			});
		});
		
		
	   var status = [
		    {statusid:0,name:'正常'},
		    {statusid:1,name:'停用'},
		    {statusid:2,name:'锁定'}
		];
		  function statusFormatter(value){
			for(var i=0; i<status.length; i++){
				if (status[i].statusid == value) return status[i].name;
			}
			return value;
		}
	</script>
</head>
<body>
  <div class="page_title" >人员管理</div>
	<table id="tt" style="width:auto;height:auto;" title="人员列表" iconCls="icon-edit" singleSelect="false" idField="itemid">
		<thead>
			<tr>
				<th field="userId" width="10%">编号</th>
				<th field="userName" width="10%">登录名称</th>
				<th field="realName" width="15%" align="center">真实姓名</th>
				<th field="email" width="15%" editor="text" align="center">邮箱</th>
				<th field="lastLogin" width="15%" align="center">最近登录时间</th>
				<th field="ipAddress" width="10%" align="center">最近使用IP</th>
				<th field="status" width="6%" align="center" formatter="statusFormatter" editor="{type:'combobox',options:{valueField:'statusid',textField:'name',data:status,required:true,editable:false}}">状态</th>
			</tr>
		</thead>
	</table>
	
</body>
</html>