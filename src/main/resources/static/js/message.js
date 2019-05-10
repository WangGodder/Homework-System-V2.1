function editorMessage(bt) {
	var messageId = bt.id.split("_")[1];
	var name = $("#name_" + messageId).val();
	var info = $("#info_" + messageId).val();
	$.ajax({
		url: "editorMessage",
		data: {"messageId":messageId, "name": name, "info":info},
		type: "post",
		success: function(data) {
			if (data.status == 1) {
                alert(data.message);
			} else {
                alert("修改成功");
			}
		},
		error: function() {
			alert("错误");
		}
	})
}

function deleteMessage(bt) {
	var messageId = bt.id.split("_")[1];
	$.ajax({
		url: "deleteMessage",
		data: {"messageId":messageId},
		type: "post",
		success: function(data) {
			if (data.status == 1) {
				alert(data.message)
			} else {
                alert("删除成功");
                goMessage();
			}
		},
		error: function() {
			alert("错误，请以教师身份操作");
		}
	})
}

function addMessage() {
	var teachCourseId = $("#teachCourseId").val();
	var name = $("#name_new").val();
	var info = $("#info_new").val();
	$.ajax({
		url: "createMessage",
		data: {"teachCourseId":teachCourseId, "name": name, "info": info},
		type: "post",
		success: function(data) {
			if (data.status == 1) {
				alert(data.message)
			} else {
                alert("发布成功");
                goMessage();
			}
		},
		error: function() {
			alert("错误，请以教师身份操作");
		}
	})
}