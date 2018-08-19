did = 1 ;

function deleteEmpFun(eid,sid) {
	// console.log(eid + " = " + sid) ;
	$.post("pages/back/admin/schedule/ScheduleAction!deleteEmp.action",{"sid":sid,"eid":eid},function(data){
		$("#travel-" + eid).remove() ;
		console.log("travel-"+eid);
		operateAlert(data.trim()=="true","出差人员信息移除成功！","出差人员信息移除失败！") ;
	},"text") ;
}

$(function(){
	$("button[id^=remove-]").each(function(){
		eid = this.id.substring(this.id.indexOf("-") + 1,this.id.lastIndexOf("-")) ;
		sid = this.id.substring(this.id.lastIndexOf("-") + 1) ;
//		eid = $("#eid").val() ;	// 取得指定组件的value
//		sid = $("#sid").val() ;
		$(this).on("click",function(){
			console.log(eid+"*****"+sid);
			deleteEmpFun(eid,sid) ;
			console.log("travel-"+eid);
		})
	}) ;
	$("#did").on("change",function(){
		did = $(this).val() ;
		console.log(did);
		loadData() ;
	}) ;
	
	$(addBtn).on("click",function(){
		// Ajax异步读取用户信息
		// 将异步加载信息填充到模态窗口的组件之中
		$.post("pages/back/admin/schedule/ScheduleAction!listDept.action", {
			
		}, function(data) {
			$("#did option:gt(0)").remove() ;
			$.each(data.dept,function(i,n){
				$("#did").append("<option value='"+n.did+"'>"+n.dname+"</option>");
			});
		},"json");
		loadData() ;
		$("#userInfo").modal("toggle") ;	// 显示模态窗口
		
	}) ;
})
function loadData() {	// 该函数名称一定要固定，不许修改
	// 如果要想进行分页的处理列表前首先查询出部门编号
	// console.log("部门编号：" + did) ;
	did = $("#did").val() ;	// 取得指定组件的value
	sid = $("#sid").val() ;
	if(did==""){
		did=0;
	}
	$.post("pages/back/admin/schedule/ScheduleAction!listDeptEmp.action", {
		"did" : did,
		"cp" : jsCommonCp, 
		"ls" : jsCommonLs,
		"sid" : sid
	}, function(data) {
		console.log(data);
		$("#empTable tr:gt(0)").remove() ;
		for (var x = 0 ; x < data.all.allEmps.length ; x ++) {
			addTableRow(data.all.allEmps[x].photo,data.all.allEmps[x].eid,data.all.allEmps[x].ename,data.all.allEmps[x].salary,data.all.allEmps[x].lid) ;
		}
		console.log("----------"+data.all.allRecorders);
		createSplitBar(data.all.allRecorders) ;	// 创建分页控制项
//		$("button[id^=addEmp-]").each(function(){
//			eid = this.id.substring(this.id.indexOf("-") + 1,this.id.lastIndexOf("-")) ;
//			sid = this.id.substring(this.id.lastIndexOf("-") + 1) ;
//			$(this).on("click",function(){
//				$("#travelEmp-"+eid).remove();
//			})
//		}) ;
	}, "json");
}
function addTableRow(photo,eid,ename,sal,lid) {
	level = "普通员工" ;
	if (lid == "0") {
		level = "总裁" ;
	} else if (lid == "1") {
		level = "总监" ;
	} else if (lid == "2") {
		level = "部门经理" ;
	}else if(lid == "3"){
		level = "普通员工" ;
	}
	
	row = 	"	<tr id='travelEmp-"+eid+"'>" + 
			"		<td class='text-center'>" +
			"			<img src='upload/emp/"+photo+"' style='width:20px;'/> " +
			"		</td>" +
			"		<td class='text-center'>"+eid+"</td>" +
			"		<td class='text-center'>"+ename+"</td>" +
			"		<td class='text-center'>"+level+"</td>" +
			"		<td class='text-center'>" +
			"			<button class='btn btn-danger btn-xs' id='addEmp-"+eid+"'>" +
			"				<span class='glyphicon glyphicon-plus-sign'></span>&nbsp;增加</button>" +
			"		</td>" + 
			"	</tr> " ;
	$(empTable).append(row) ;
	$("#addEmp-" + eid).on("click",function(){
//		tid = $("#tid").val() ;
		sid = $("#sid").val() ;
		console.log(sid);
		$.post("pages/back/admin/schedule/ScheduleAction!addEmp.action",{"eid":eid,"sid":sid},function(data){
//			if (data.status == true) {	// 待出发用户添加完成
			console.log("emp---"+data.flag);
			if(data.flag.trim()=="true"){
				$("#travelEmp-" + eid).remove() ;
				rowInfo = 	"<tr id='travel-"+eid+"'>" + 
							"	<td class='text-center'>" +
							"		<img src='upload/emp/"+photo+"' style='width:20px;'/> " +
							"	</td>" +
							"	<td class='text-center'>"+data.emp.eid+"</td>" +
							"	<td class='text-center'>"+data.emp.ename+"</td>" +
							"	<td class='text-center'>"+data.level.title+"</td>" +
							"	<td class='text-center'>"+data.dept.dname+"</td>" +
							"	<td class='text-center'>" +
							"		<button class='btn btn-danger btn-xs' id='remove-"+data.emp.eid+"-"+sid+"'>" +
							"			<span class='glyphicon glyphicon-remove'></span>&nbsp;移除</button>" +
							"	</td>" +
							"</tr> " ;
				$("#travelEmpTable").append(rowInfo) ;
				$("#remove-"+data.emp.eid+"-"+sid).on("click",function(){
					deleteEmpFun(eid,sid) ;
				}) ;
			}	
//			} else {
				$("#userInfo").modal("toggle") ;
				operateAlert(data.flag.trim()=="true","添加成功","该雇员已经有了项目安排无法添加到本次出差人员之内！") ;
//			}
		},"json") ;
	}) ;
}

