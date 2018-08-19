<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%!
	public static final String INDEX_URL = "pages/back/index.jsp" ;	

	public static final String DEPT_LIST_URL = "pages/back/admin/DeptAction!list.action" ;
	public static final String DEPT_ADD_URL = "pages/back/admin/dept/dept_add.jsp" ;
	
	public static final String EMP_ADD_URL = "pages/back/admin/EmpAction!preAdd.action" ;
	public static final String EMP_LIST_URL = "pages/back/admin/EmpAction!listPro.action" ;
	
	public static final String SCHEDULE_ADD_URL = "pages/back/admin/schedule/ScheduleAction!addPre.action" ;
	public static final String SCHEDULE_LIST_SELF_URL = "pages/back/admin/schedule/schedule_list_self.jsp" ;
	
	public static final String SCHEDULE_AUDIT_URL = "pages/back/admin/schedule/ScheduleAction!preAudit.action" ;
	public static final String SCHEDULE_LIST_URL = "pages/back/admin/schedule/ScheduleAction!preAudit.action" ;
	
	public static final String EMP_SCHEDULE_PREPARE_URL = "pages/back/admin/schedule/ScheduleSelfAction!listEmp.action" ;
	public static final String EMP_SCHEDULE_LIST_URL = "pages/back/admin/center/emp_schedule_list_history.jsp" ;
%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="upload/emp/${photo}" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${ename}</p>
			</div> 
		</div>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header"><i class="fa fa-university"></i> 员工统一调度平台</li>
				<c:if test="${fn:contains(allRoles,'dept') || fn:contains(allRoles,'chief')}">
					<li class="treeview ${param.mi==1 ? 'active' : ''}"><a href="${basePath}<%=INDEX_URL%>"> <i
							class="fa fa-institution"></i> <span>部门管理</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<c:if test="${fn:contains(allActions,'dept:add')}">
							<li class="${param.msi==11 ? 'active' : ''}"><a href="<%=DEPT_ADD_URL%>"><i class="fa fa-cube"></i>
								增加部门</a></li>
						</c:if>
						<c:if test="${fn:contains(allActions,'dept:list') || fn:contains(allActions,'chief:deptlist')}">
							<li class="${param.msi==12 ? 'active' : ''}"><a href="<%=DEPT_LIST_URL%>"><i class="fa fa-object-group"></i>
								部门列表</a></li>
						</c:if>
					</ul></li>
				</c:if>
				<c:if test="${fn:contains(allRoles,'emp') || fn:contains(allRoles,'chief')}">
					<li class="treeview ${param.mi==2 ? 'active' : ''}"><a href="${basePath}<%=INDEX_URL%>"> <i
							class="fa fa-sitemap"></i> <span>雇员管理</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<c:if test="${fn:contains(allActions,'emp:add')}">
							<li class="${param.msi==21 ? 'active' : ''}"><a href="<%=EMP_ADD_URL%>"><i class="fa fa-user-plus"></i>
								增加雇员</a></li>
						</c:if>
						<c:if test="${fn:contains(allActions,'emp:list') || fn:contains(allActions,'chief:emplist')}">
							<li class="${param.msi==22 ? 'active' : ''}"><a href="<%=EMP_LIST_URL%>"><i class="fa fa-users"></i>
								雇员列表</a></li>
						</c:if>
					</ul></li>
				</c:if>
				<c:if test="${fn:contains(allRoles,'schedule')}">
					<li class="treeview ${param.mi==3 ? 'active' : ''}"><a href="${basePath}<%=INDEX_URL%>"> <i class="fa  fa-car"></i>
						<span>调度安排</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<c:if test="${fn:contains(allActions,'schedule:add')}">
							<li class="${param.msi==31 ? 'active' : ''}"><a href="<%=SCHEDULE_ADD_URL%>"><i
								class="fa fa-train"></i> 调度申请</a></li>
						</c:if>
						<c:if test="${fn:contains(allActions,'schedule:self')}">
							<li class="${param.msi==32 ? 'active' : ''}"><a href="<%=SCHEDULE_LIST_SELF_URL%>"><i
								class="fa fa-history"></i> 我的申请</a></li>
						</c:if>
					</ul></li>
				</c:if>
				<c:if test="${fn:contains(allRoles,'audit')}">
					<li class="treeview  ${param.mi==4 ? 'active' : ''}"><a href="${basePath}<%=INDEX_URL%>"> <i class="fa fa-bitbucket-square"></i>
						<span>调度审核</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<c:if test="${fn:contains(allActions,'audit:prepare')}">
							<li class="${param.msi==41 ? 'active' : ''}"><a href="<%=SCHEDULE_AUDIT_URL%>"><i
								class="fa fa-plane"></i> 待审核申请</a></li>
						</c:if>
						<c:if test="${fn:contains(allActions,'audit:list')}">
							<li class="${param.msi==42 ? 'active' : ''}"><a href="<%=SCHEDULE_LIST_URL%>"><i
								class="fa fa-tasks"></i> 申请列表</a></li>
						</c:if>
					</ul></li>
				</c:if>
				<c:if test="${fn:contains(allRoles,'center')}">
					<li class="treeview  ${param.mi==5 ? 'active' : ''}"><a href="${basePath}<%=INDEX_URL%>"> <i class="fa fa-home"></i>
						<span>我的任务</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<c:if test="${fn:contains(allActions,'center:prepare')}">
							<li class="${param.msi==51 ? 'active' : ''}"><a href="<%=EMP_SCHEDULE_PREPARE_URL%>"><i
								class="fa fa-calendar"></i> 待处理任务</a></li>
						</c:if>
						<c:if test="${fn:contains(allActions,'center:history')}">
							<li class="${param.msi==52 ? 'active' : ''}"><a href="<%=EMP_SCHEDULE_LIST_URL%>"><i
								class="fa fa-list-alt"></i> 历史任务</a></li>
						</c:if>
					</ul></li>
				</c:if>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>