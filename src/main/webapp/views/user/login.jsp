<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<%@ include file="/common/head.jsp"%>

</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container" style="margin-top: 50px;">
    
            <div class="row" style="border: 1px darkgrey solid; border-radius: 10px;width: 50%; margin: 0 auto; padding: 20px;" >
                <div class="col-sm-12">
                <h3>Đăng Nhập</h3>
           
                <form id="login-form" action="login" method="post">
                    <div class="form-group">
                        <label>UserName</label>
                        <input type="text" class="form-control" name="username" placeholder="Enter UserName">
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" class="form-control" name="password" placeholder="Enter password">
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
                    </div>
                    
                    <button  type="submit" class="btn btn-primary">Đăng nhập</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                    <p style="margin: 10px; font-size: 16px;"><a href="sign-up">Chưa có tài khoản?</a></p>                         
                </form>
                ${message}
                </div>
            </div>
        </div>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>