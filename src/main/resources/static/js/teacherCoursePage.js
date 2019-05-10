function goResource() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "resources",
		data: {"teachCourseId": teachCourseId},
		type: "POST",
		success: function(data) {
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

function homework() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "homeworks",
		data: {"teachCourseId": teachCourseId},
		type: "POST",
		success: function (data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("错误发生");
		}
	})
	
}


function goMessage() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "courseMessage",
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

function studentList() {
	var teachCourseId = $("#teachCourseId").val();
	$.ajax({
		url: "studentList",
		data: {"teachCourseId": teachCourseId, "bigger":"", "smaller":""},
		type: "POST",
		success: function (data) {
			$("#subpage").html(data);
		},
		error: function() {
			alert("错误发生,请重新登陆");
		}
	})
} 
