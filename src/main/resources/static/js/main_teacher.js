function teachCourse() {
	$.ajax({
		url: "teachCourse",
		type: "GET",
		async: true,
		data: "",
		success: function(data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("未知错误发生，请重新登录或重试");
		}
	});
}

function teacherCourse() {
	$.ajax({
		url: "teacherCourse",
		type: "GET",
		data: "",
		success: function(data) {
			$("#subpage").html(data);
		},
		error: function(data) {
			alert("未知错误发生，请重新登录或重试");
		}
	});
}

function userinfo() {
	$.ajax({
		url: "teacher_info",
		type: "GET",
		data: "",
		success: function(data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("未知错误发生，请重新登录或重试");
		}
	});
}

function createCoursePage() {
	$.ajax({
		url: "createCourse",
		type: "GET",
		data: "",
		success: function(data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("未知错误发生，请重新登录或重试");
		}
	})
}

$("#btTeachCourse").attr("onclick", "teachCourse();");
$("#btMyCourse").attr("onclick", "teacherCourse();");
$("#btCreateCourse").attr("onclick", "createCoursePage()");
$("#userinfo").attr("onclick", "userinfo()");
