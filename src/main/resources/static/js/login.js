function login_student() {
	var id = $("#stu_username_hide").val();
	var ps = $("#stu_password_hide").val();
	var data = {"id": id, "password":ps};

	$.ajax({
		url: "login_student",
		type: "POST",
		data: data,
		async: false,
		success: function(data) {
			if (data.status == 1) {
				alert(data.message);
				$("#stu_password_hide").val = "";
			}	
			else {
				window.location.href = "main_student";
			}
		},
		error: function(){
			alert("error");
		}
	});
}

function login_teacher() {
	var id = $("#tea_username_hide").val();
	var ps = $("#tea_password_hide").val();
	var data = {"id": id, "password":ps};

	$.ajax({
		url: "login_teacher",
		type: "POST",
		data: data,
		async: false,
		success: function(data) {
            if (data.status == 1) {
                alert(data.message);
                $("#stu_password_hide").val = "";
            }
            else {
                window.location.href = "main_teacher";
            }
		},
		error: function(){
			alert("error");
		}
	});
}
