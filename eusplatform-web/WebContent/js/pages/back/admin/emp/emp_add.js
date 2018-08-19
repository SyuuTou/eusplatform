$(function(){
	

	
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
			"eid" : {
				required : true
				//remote : {
//				url : "check.jsp", // 后台处理程序
//				type : "post", // 数据发送方式
//				dataType : "html", // 接受数据格式
//				data : { // 要传递的数据
//					code : function() {
//						return $("#code").val();
//					}
//				},
//				dataFilter : function(data, type) {
//					if (data.trim() == "true")
//						return true;
//					else
//						return false;
//				}
//}
			} ,
			"password" : {
				required : true
			},
			"ename" : {
				required : true
			} ,
			"phone" : {
				required : true 
			},
			"jid" : {
				required : true 
			},
			"did" : {
				required : true 
			},
//			"salary" : {
//				required : true ,
//				number : true 
//			},
			"pic" : {
				required : true ,
				accept : ["jpg","png","gif","bmp"]
			},
			"note" : {
				required : true
			}
		}
	});
	
	//监听薪水输入的变化，对职位等级进行联动
	$("input[id='salary']").on("blur",function() {
		selectLevelId = $("#lid").val();
		console.log(selectLevelId);
		console.log("薪水框焦点发生变化,职位等级为" + selectLevelId);
		if(selectLevelId >= 0) {
			hisal = parseFloat($("#lid option[value='" + selectLevelId + "']").attr("hisal"));
			losal = parseFloat($("#lid option[value='" + selectLevelId + "']").attr("losal"));
			sal = parseFloat($(this).val());
			if(isNaN(sal)) {
				sal = 0;
			}
			console.log("薪水为："+sal +",最低薪水："+losal +"最高薪资："+hisal);
			if(sal < losal || sal > hisal) {
				$("#jidMsg").text("输入薪资不符,请输入 " + losal + "~" + hisal + "之内的薪资");
				console.log("输入薪资不符");
			} else {
				console.log("输入正确");
				$("#jidMsg").text("");
			}
		}
	});
	
	//监听职位等级变化，发生变化对薪资输入做判断，若输入薪资有误则提示。
	$("#lid").on("change",function(){
		selectLevelId = $("#lid").val();
		hisal = parseFloat($("#lid option[value='" + selectLevelId + "']").attr("hisal"));
		losal = parseFloat($("#lid option[value='" + selectLevelId + "']").attr("losal"));
		sal = parseFloat($("input[id='salary']").val());
		if(isNaN(sal) || sal == null || "" == sal) {
			return;
		}
		if(selectLevelId >= 0) {
			if(isNaN(sal)) {
				sal = 0;
			}
			console.log("薪水为："+sal +",最低薪水："+losal +"最高薪资："+hisal);
			if(sal < losal || sal > hisal) {
				$("#jidMsg").text("输入薪资不符,请输入 " + losal + "~" + hisal + "之内的薪资");
				console.log("输入薪资不符");
			} else {
				console.log("输入正确");
				$("#jidMsg").text("");
			}
		}
	});
	
})