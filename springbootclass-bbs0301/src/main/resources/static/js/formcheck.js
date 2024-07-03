$(function() {
	
	// 삭제하기 버튼이 클릭되면
		$("#detailDelete").on("click", function() {
			let pass = $("#pass").val();
			if(pass.length <= 0) {
				alert("비밀번호를 입력하세요");
				return false;
			}
			
			$("#rPass").val(pass);
			$("#checkForm").attr("action", "delete");
			$("#checkForm").attr("method", "post");
			$("#checkForm").submit();
		})
	
	// 수정 폼 유효성 검사
	$("#updateForm").on("submit", function(e) {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력하세요.");
			return false;
		}
		
		if($("#title").val().length <= 0) {
			alert("제목을 입력하세요.");
			return false;
		}
		
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		if($("#content").val().length <= 0) {
			alert("내용을 입력하세요.");
			return false;
		}
	});
	
	// 수정하기 버튼이 클릭되면
	$("#detailUpdate").on("click", function() {
		let pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "updateForm");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	})
	
	$("#writeForm").on("submit", function(e) {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력하세요.");
			return false;
		}
		
		if($("#title").val().length <= 0) {
			alert("제목을 입력하세요.");
			return false;
		}
		
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		if($("#content").val().length <= 0) {
			alert("내용을 입력하세요.");
			return false;
		}
	});
})