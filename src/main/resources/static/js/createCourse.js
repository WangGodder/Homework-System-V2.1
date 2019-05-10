var names = [];
$(".courseNames").each(function () {
	names.push($(this).val());
});

$("#courseName").change(function() {
	if ($.inArray($(this).val(), names) > -1) {
		if (!confirm("本课程名称已存在，你确定要使用与其他课程相同的名称吗？")) {
			$(this).val("");
		}
	}
});


function createCourse() {
	var courseName = $("#courseName").val();
	var courseInfo = $("#courseInfo").val();
	var courseType = $("#courseType").val();
	$.ajax({
		url: "createNewCourse",
		data: {"courseName": courseName, "courseInfo": courseInfo, "courseType":courseType},
		type: "POST",
		success: function(data) {
			if (data.status == 1) {
				alert("发生错误，请以教师或管理身份登陆并重试");
			}
			else {
				alert("创建成功");
				createCoursePage();
			}
		},
		error: function() {
			alert("错误发生");
		}
	})
}