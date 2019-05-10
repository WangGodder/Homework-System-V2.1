	function goHomework(bt) {
		var homeworkId = bt.id.split("_")[1];
		var tourl = "goHomework/" + homeworkId;
		var teachCourseId = $("#teachCourseId").val();
		$.ajax({
			url: tourl,
			type: "GET",
			data: {"teachCourseId": teachCourseId},
			success: function(data) {
				$("#subpage").html(data);
			}, 
			error: function(data) {
				alert("错误，请重试");
			}
		});
	}
	function showScoreBox() {
		var screenwidth,screenheight,mytop,getPosLeft,getPosTop;
		screenwidth = $(window).width();  
		screenheight = $(window).height();  
		//获取滚动条距顶部的偏移  
		mytop = $(document).scrollTop();  
		//计算弹出层的left  
		getPosLeft = screenwidth/2 - 260;  
		//计算弹出层的top  
		getPosTop = screenheight/2 - 150;  
		//css定位弹出层  
		$("#scoreBox").css({"left":getPosLeft,"top":getPosTop, "display":"block"});  
		$("#scoreBox").fadeIn("fast");

	}
	function hideScoreBox() {
		$("#scoreBox").hide();
	}
	function homeworkScore() {
		var homeworkIds = [];
		$(".homeworkIds").each(function() {
			homeworkIds.push($(this).val());
		});
		var data = {};
		homeworkIds.forEach(function (currentValue) {
			data[currentValue] = $("#proportion_" + currentValue).val();
		});
		$.ajax({
			url: "homeworkScore/" + $("#teachCourseId").val(),
			data: data,
			type: "POST",
			success: function(data) {
				if (data.status == 1) {
					alert(data.message);
				}
				else {
					alert("操作成功， 请去学生名单中查询");
				}
			},
			error: function () {
				alert("error");
			}
		})
	}
	
	function editorHomework(bt) {
		var homeworkId = bt.id.split("_")[1];
		var data = {"homeworkId": homeworkId, "name": $("#name_"+homeworkId).val(), "info": $("#info_"+homeworkId).val(), "deadline": $("#deadline_" + homeworkId).val()};
		$.ajax({
			url: "editorHomework",
			data: data,
			type: "POST",
			success: function(data) {
				if (data.status == 1) {
					alert(data.message);
				}
				else {
					alert("修改成功");
					homework();
				}
			},
			error: function() {
				alert("错误发生");
			}
		})
	}
	
	function deleteHomework(bt) {
		var homeworkId = bt.id.split("_")[1];
		$.ajax({
			url: "deleteHomework",
			data: {"homeworkId": homeworkId},
			type: "POST",
			success: function(data) {
				if (data.status == 1) {
					alert("错误，请以教师身份登陆进行操作");
				}
				else {
					alert("删除成功");
					homework();
				}
			}
		})
	}
	
	function addHomework() {
		var name = $("#name_new").val();
		if (name == null || name == "") {
			alert("作业名称不能为空");
			return;
		}
		var info = $("#info_new").val();
		var deadline = $("#deadline_new").val();
		if (deadline == null) {
			alert("截至日期不能为空");
			return;
		}
		var teachCourseId = $("#teachCourseId").val();
		var format = $("#format_new").val();
		$.ajax({
			url: "addHomework",
			data: {"name":name, "info":info, "deadline":deadline, "teachCourseId":teachCourseId, "format": format},
			type: "POST",
			success: function(data) {
				if (data.status == 1) {
					alert(data.message);
				}
				else {
					alert("创建课程成功");
					homework();
				}
			},
			error: function() {
				// alert("错误");
                homework();
			}
		})
	}