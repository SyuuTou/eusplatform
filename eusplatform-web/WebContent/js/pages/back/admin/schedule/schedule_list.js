$(function() {
	$("a[id^='applyBtn']").each(function(i,n){
		var sid=$(this).attr("id").split("-")[1];
		$(this).on("click",function(){
			$.post("pages/back/admin/schedule/ScheduleAction!taskApply.action",{
			"sid":sid
		},function(data){
			operateAlert(data.trim() == "true","提交成功","提交失败,已提交任务不能再次提交!");
			if(data.trim() == "true"){
				$("#travel-"+sid+" span").next().html("&nbsp;已提交");
			}else{
				$("#applyBtn-"+sid).attr("disabled","disabled").unbind();
				$("#editBtn-"+sid).attr("disabled","disabled").unbind();
				$("#editBtn-"+sid).removeAttr("href");
			}
		},"text");			
		})
	});
	$("a[id^='editBtn']").each(function(i,n){
		var sid=$(this).attr("id").split("-")[1];
		$(this).on("click",function(){
			$.post("pages/back/admin/schedule/ScheduleAction!taskEdit.action",{
			"sid":sid
		},function(data){
			operateAlert(data.trim() == "true","提交成功","提交失败,已提交任务不能再次提交!");
			if(data.trim() =="true"){
//				$("#editBtn-"+sid).attr("href","pages/back/admin/schedule/ScheduleAction!taskEditList.action?sid="+sid);
				$(location).attr("href","pages/back/admin/schedule/ScheduleAction!taskEditList.action?sid="+sid);
			}else{
				$("#editBtn-"+sid).attr("disabled","disabled").unbind();
				$("#editBtn-"+sid).removeAttr("href");
			}
		},"text");			
		})
	});
	$("a[id^='deleteBtn']").each(function(i,n){
		var sid=$(this).attr("id").split("-")[1];
		$(this).on("click",function(){
			$.post("pages/back/admin/schedule/ScheduleAction!taskDelete.action",{
			"sid":sid
		},function(data){
			$("#travel-"+sid).remove();
			 operateAlert(data.trim() == "true","删除成功","删除失败");
		},"text");			
		})
	});
})