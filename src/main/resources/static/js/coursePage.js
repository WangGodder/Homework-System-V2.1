function goResource() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "resources",
		data: {"teachCourseId": teachCourseId},
		type: "GET",
		success: function(data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("错误发生");
		}

	})
}

function homework() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "homeworks",
		data: {"teachCourseId": teachCourseId},
		type: "GET",
		success: function (data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("错误发生");
		}
	})
	
}

function goDiscussion() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "discussions",
		data: {"teachCourseId": teachCourseId},
		type: "GET",
		success: function (data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("错误发生");
		}
	})
}