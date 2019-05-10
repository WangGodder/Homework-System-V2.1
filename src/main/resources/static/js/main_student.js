function addCourse() {
	$.ajax({
		url: "addCourse",
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

function studentCourse() {
	$.ajax({
		url: "studentCourse",
		type: "GET",
		data: "",
		success: function(data) {
			$("#subpage").html(data);
		},
		error: function(data) {
			alert("未知错误发生，请重新登录或重试");
			alert(data);
		}
	});
}

function userinfo() {
	$.ajax({
		url: "student_info",
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

$("#btAddCourse").attr("onclick", "addCourse();");
$("#btMyCourse").attr("onclick", "studentCourse();");
$("#userinfo").attr("onclick", "userinfo()");
