<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>
<%@ include file="/common/head.jsp"%>

</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container" style="margin-top: 50px;">

		<div class="row"
			style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
			<div class="col-sm-12">
				<h3>Tìm tài khoản của bạn</h3>
				<div class="form-group">
					<label>Vui lòng nhập email để tìm kiếm tài
						khoản của bạn.</label> <input type="email" id="email" class="form-control"
						name="password	" placeholder="Enter Email">
				</div>
				<button type="submit" id="sendBtn" class="btn btn-primary">Tìm
					Kiếm</button>
				<button type="reset" class="btn btn-primary">Hủy</button>
				<h5 style="color: red" id="messageReturn"></h5>
			</div>
		</div>
	</div>

	<%@ include file="/common/footer.jsp"%>
</body>

<script>
		$('#sendBtn').click(function (){
			$('#messageReturn').text('');
			var email = $('#email').val();
			var formData = {'email' : email};
			$.ajax({
				url: 'forgotPass',
				type : 'POST',
				data : formData
			}).then(function(data) {
				$('#messageReturn').text('Password has been reset, please check your email');
				setTimeout(function() {
					window.location.href = 'http://localhost:8080/ASM_GD2_PROMAX/index';
				},5*1000); // 5 giây
			}).fail(function(error){
				$('#messReturn').text('Your information is not correct, try again');
			});
		});
	</script>

</html>