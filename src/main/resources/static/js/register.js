function vaildata() {
	var password = document.getElementById("password").value;
	var resure = document.getElementById("resure").value;
	var submit = document.getElementById("submit");
	var error = document.getElementById("error");
	if (password == resure) {
		submit.disabled = false;
		error.innerHTML = "";
	} else {
		submit.disabled = true;
		error.innerHTML = "两次输入密码不同";
	}
}


function registerStudent() {
	var name = $("#name").val();
	var id = $("#id").val();
	var tel = $("#tel").val();
	var ps1 = $("#ps1").val();
	var ps2 = $("#ps2").val();
	var gradeId = $("#grade").val();
	if (ps1 != ps2) {
		alert("俩次输入密码不同");
		$("#ps1").val("");
		$("#ps2").val("");
		return;
	}
	else {
		var data = {"studentId":id, "name": name, "tel": tel, "password":ps2, "gradeId":gradeId};
		$.ajax({
			url: "registerStudent",
			data: data,
			type: "POST",
			success: function(data) {
				if (data.status == 1) {
					alert("该学号已被注册，请向管理员反应");
				}
				else {
					alert("注册成功");
					window.location.href = "main_student";
				}
			},
			error: function() {
				alert("错误发生，请联系管理员");
			}
		})
	}
}
