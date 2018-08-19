<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/split_page.js"></script>
<script type="text/javascript" src="js/pages/back/admin/schedule/schedule_emp_edit.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="3"/>
			<jsp:param name="msi" value="32"/>
		</jsp:include>
		<div class="content-wrapper text-left">
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-list"></span>&nbsp;出差人员列表</strong>
				</div>
				<div class="panel-body">
					<button class="btn btn-danger btn-lg" id="addBtn">
						<span class="glyphicon glyphicon-plus-sign"></span>&nbsp;增加出差人员</button>
					<table id="travelEmpTable" class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center"><strong>照片</strong></th>
								<th class="text-center"><strong>雇员编号</strong></th>
								<th class="text-center"><strong>姓名</strong></th>
								<th class="text-center"><strong>级别</strong></th>
								<th class="text-center"><strong>部门</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${allEmps}" var="emp">
							<tr id="travel-${emp.eid}">
								<td class="text-center">
									<img src="upload/emp/nophoto.png" style="width:20px;"/> 
								</td>
								<td class="text-center">${emp.eid}</td>
								<td class="text-center">${emp.ename}</td>
								<td class="text-center">${allLevels[emp.lid].title}</td>
								<td class="text-center">${allDepts[emp.did-1].dname}</td>
								<td class="text-center">
									<button class="btn btn-danger btn-xs" id="remove-${emp.eid}-<%=request.getParameter("sid")%>">
										<span class="glyphicon glyphicon-remove"></span>&nbsp;移除</button>
								</td>
							</tr>
						</c:forEach>	 
								<input id="sid" type="hidden" value="<%=request.getParameter("sid")%>"/>
								
						</tbody>
					</table>
					<div id="splitBarDiv" style="float:right">
					</div>
				</div>
				<div class="panel-footer">
					<jsp:include page="/pages/plugins/include_alert.jsp"/>
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	
<div class="modal fade" id="userInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="form-group" id="didDiv">
					<!-- 定义表单提示文字 -->
					<label class="col-md-2 control-label" for="did">员工所在部门：</label>
					<div class="col-md-5">
						<select id="did" name="did" class="form-control">
							<option value="">====== 请选择雇员所在部门 ======</option>
						</select>
					</div>
				</div>
			</div>
			<div class="modal-body">
				<div id="memberBasicInfo">
					<table class="table table-condensed table-hover" id="empTable">
						<thead>
							<tr>
								<th class="text-center"><strong>照片</strong></th>
								<th class="text-center"><strong>雇员编号</strong></th>
								<th class="text-center"><strong>姓名</strong></th>
								<th class="text-center"><strong>级别</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<div id="pagecontrol" style="float:right">
					<ul class="pagination"><ul>
					</div>
			</div>
		</div>
	</div>
</div>
	
<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
