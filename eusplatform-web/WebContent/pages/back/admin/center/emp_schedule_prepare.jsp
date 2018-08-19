<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<%!
	public static final String EMP_TASK_EDIT_URL = "pages/back/admin/schedule/schedule_edit.jsp" ;
public static final String SCHEDULE_SHOW_URL = "pages/back/admin/schedule/schedule_show.jsp" ;
%>
<script type="text/javascript" src="js/pages/back/admin/schedule/schedule_list_self.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="5"/>
			<jsp:param name="msi" value="51"/>
		</jsp:include>
		<div class="content-wrapper text-left">
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-list"></span>&nbsp;个人待处理任务清单</strong>
				</div>
				<div class="panel-body">
					<div>
						<jsp:include page="/pages/plugins/split_plugin_search_bar.jsp"/>
					</div>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center"><strong>状态</strong></th>
								<th class="text-center"><strong>申请标题</strong></th>
								<th class="text-center"><strong>任务时间</strong></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${allSchedules}" var="schedule">
							<tr id="travel-1">
								<td class="text-center"><span class="text-info"><span class="glyphicon glyphicon-flag"></span>&nbsp;正在进行</span></td>
								<td class="text-center">
 									<a id="showBtn-${schedule.sid }" onmouseover="this.style.cursor='hand'" href="pages/back/admin/schedule/ScheduleSelfAction!listSchedule.action?title=${schedule.title}">${schedule.title}</a>
								</td>
								<td class="text-center">${schedule.sdate}</td>
							</tr> 
						</c:forEach>
						
						</tbody>
					</table>
					<div id="splitBarDiv" style="float:right">
						<jsp:include page="/pages/plugins/split_plugin_page_bar.jsp"/> 
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
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
