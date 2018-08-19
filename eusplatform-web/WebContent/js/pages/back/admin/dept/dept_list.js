$(function(){
	$("button[id^=edit-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-")[1] ;
			dname = $("#dname-" + did).val();
			maxnum = $("#maxnum-" + did).val();
			if (dname == ""|| maxnum =="") { 
				operateAlert(false,"","部门名称和名额上限字段不能为空！") ;
			}else if(!/\d+/.test(maxnum)){
				operateAlert(false,"","名额上限字段必须为数字类型");
			}else if(parseInt(maxnum)<parseInt($("#currnum-" + did).text())){
				operateAlert(false,"","部门人数最大限额不能少于"+parseInt($("#currnum-" + did).text()));
			}else {
				if(window.confirm("确定要执行更新吗？")){
					$.post("pages/back/admin/DeptAction!edit.action",{"did":did,"dname":dname,"maxnum":maxnum},
							function(data){
						operateAlert(data.trim()=="true","修改成功！","修改失败！") ;
					},"text") ;
				}
			}
		}) ;
	}) ;
	$("span[id^=eid-]").each(function(){
		$(this).on("click",function(){
			var eid = "" ;
			temp = this.id.split("-") ;
			for(i = 1 ; i < temp.length ; i ++){
				eid += temp[i] + "-" ;
			}
			eid = eid.substring(0,eid.length-1) ;
			$.post("pages/back/admin/DeptAction!empInfo.action",{"eid":eid},function(data){
				if(data != "false"){
					$("#dname").text(data.dname) ;
					$("#ename").text(data.emp.ename) ;
					$("#phone").text(data.emp.phone) ;
					$("#note").text(data.emp.note) ;
					$("#photo").attr("src","upload/emp/" + data.emp.photo) ;
					$("#hiredate").text(data.time) ;
					$("#title").text(data.title) ;
					$("#userInfo").modal("toggle") ;
				}
			},"json") ;
			
		}) ;
	});
}) ;
