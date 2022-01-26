<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit User</title>
<%@ include file="/common/head.jsp"%>

</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container" style="margin-top: 50px;">
    
            <div class="row" style="border: 1px darkgrey solid; border-radius: 10px;width: 50%; margin: 0 auto; padding: 20px;" >
                <div class="col-sm-12">
                <h3>Edit User</h3>
           
              
                    <div class="form-group">
                        <label>UserName</label>
                        <input type="text" class="form-control" id="username" name="username" value="${user != null ? user.username : ''}" placeholder="Enter UserName">
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" class="form-control"  id="password" name="password" value="${user != null ? user.password : ''}" placeholder="Enter password">
                    </div>
               
                     <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control"  id="email" name="email" value="${user != null ? user.email : ''}"  placeholder="Enter Email">
                        <input type="hidden" id="isEdit" value="${isEdit}" class="form-control">
                    </div>
                    
              
                    
                    <button  type="button" id="submitBtn" class="btn btn-primary" >Submit</button>
                    <button type="button" id="resetBtn" class="btn btn-primary">Cancel</button>
                           
                
                ${message}
                </div>
            </div>
        </div>

	<%@ include file="/common/footer.jsp"%>
</body>
<script type="text/javascript">
var usernameOrigin,passwordOrigin, emailOrigin;

$(document).ready(function(){
	usernameOrigin = $('#username').val();
	passwordOrigin	= $('#password').val(); 
	emailOrigin	= $('#email').val();
	 
}); 

$('#resetBtn').click(function(){
	  $('#username').val(usernameOrigin);
	  $('#password').val(passwordOrigin); 
	 	$('#email').val(emailOrigin);
	
	
});  
$('#submitBtn').click(function(){
		
	$('#message').text();
		var url;
		var isEdit = $('#isEdit').val();
		if(isEdit =='true'){
			
			url= '/ASM_GD2_PROMAX/admin/user?action=edit&username=' + usernameOrigin;
		}else{
			url= '/ASM_GD2_PROMAX/admin/user?action=add';
		}
	   var formData ={
			   
			   'username': $('#username').val(),
			   'password': $('#password').val(),
	   			'email' : $('#email').val()
	   			
	   };
	   $.ajax({
		  	  url: url,
			  type: 'POST',
			  data : formData
				   
	   }).then(function(data){
		   window.location.href = "http://localhost:8080/ASM_GD2_PROMAX/admin/user?action=view";
	   }).fail(function(error){
		   $('#message').text("Vui lòng thử lại!");
	   });
});
</script>
</html>