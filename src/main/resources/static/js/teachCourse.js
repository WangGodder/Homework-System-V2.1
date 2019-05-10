$("#courseSelect").bind("change", function() {
	var courseId = $(this).val();
	$("#courseId").html(courseId);
	$("#courseInfo").html($("#courseInfo_" + courseId).val());
	$("#courseType").html($("#courseType_" + courseId).val());
})

$("#gradeSelect").bind("change", function() {
	var gradeId = $(this).val();
	$("#gradeId").html(gradeId);
	$("#gradeInfo").html($("#gradeInfo_" + gradeId).val());
})

function teachNewCourse() {
	var courseId = $("#courseSelect").val();
	var gradeId = $("#gradeSelect").val();
	var date = $("#date").val();
	var ispublic = $("#public").val();
	$.ajax({
		url: "teachNewCourse",
		data: {"courseId": courseId, "gradeId": gradeId, "date": date, "ispublic":ispublic},
		type: "POST",
		success: function(data) {
			if (data.status == 1) {
				alert(data.message);
			}
			else {
				alert("任课成功");
				teacherCourse();
			}
		},
		error: function() {
			alert("错误");
		}
	})
}