

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

function viewCoursePage() {
	$.ajax({
		url: "viewCourse",
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

function viewLog() {
	$.ajax({
		url: "viewLog",
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

function viewSensitiveWord() {
	$.ajax({
		url: "viewSensitiveWord",
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

function viewTeacher() {
	$.ajax({
		url: "viewTeacher",
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

function viewGrade() {
	$.ajax({
		url: "viewGrade",
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

function viewNotice() {
	$.ajax({
		url: "viewNotice",
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

$("#btViewCourse").attr("onclick", "viewCoursePage();");
$("#btCreateCourse").attr("onclick", "createCoursePage()");
$("#btViewLog").attr("onclick", "viewLog()");
$("#btViewSensitiveWord").attr("onclick", "viewSensitiveWord()");
$("#btViewTeacher").attr("onclick", "viewTeacher()");
$("#btViewGrade").attr("onclick", "viewGrade()");
$("#btViewNotice").attr("onclick", "viewNotice()");
