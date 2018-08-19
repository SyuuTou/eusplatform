$(function(){
	$("input[verify^='audit-']").on("change",function(){
		value = $(this).attr("value");
		if(1 == value) {
			$("#auditDiv input[verify='audit-2']").removeAttr("checked");
		} else {
			$("#auditDiv input[verify='audit-1']").removeAttr("checked");
		}
	});
	
	
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"audit" : {
				required : true,
			},
			"note" : {
				required : true
			}
		}
	});
	
	$("span[id^=eid-]").each(function(){
		$(this).on("click",function(){
			var eid = "" ;
			temp = this.id.split("-") ;
			for(i = 1 ; i < temp.length ; i ++){
				eid += temp[i] + "-" ;
			}
			eid = eid.substring(0,eid.length-1) ;
			console.log(eid);
			var dname=$("#dname").text();
			$.post("pages/back/admin/DeptAction!empInfo.action",{"eid":eid},function(data){
				if(data != "false"){
					console.log(dname);
					$("#dname").text(dname) ;
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
	
})