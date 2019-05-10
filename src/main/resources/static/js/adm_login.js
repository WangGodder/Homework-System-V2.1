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
			if (data == "wrong") {
				alert("教职工号或密码错误");
				$("#tea_password_hide").val = "";
			}	
			if (data == "success") {
				window.location.href = "main_teacher";
			}
		},
		error: function(){
			alert("error");
		}
	});
}

function login_administrator() {
	var id = $("#adm_username_hide").val();
	var ps = $("#adm_password_hide").val();
	var data = {"id": id, "password":ps};

	$.ajax({
		url: "login_administrator",
		type: "POST",
		data: data,
		async: false,
		success: function(data) {
			if (data == "wrong") {
				alert("教职工号或密码错误");
				$("#adm_password_hide").val = "";
			}	
			if (data == "success") {
				window.location.href = "main_administrator";
			}
		},
		error: function(){
			alert("error");
		}
	});
}

