<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer class="bg-light text-center text-white">
  <!-- Grid container -->
  <div class="container p-4 pb-0">

    <!-- Section: Social media -->
    <section class="mb-4">
    
      <!-- Facebook -->
      <a
        class="btn btn-primary btn-floating m-1"
        style="background-color: #3b5998;"
        href="https://www.facebook.com/tinhhtps14843"
        role="button"
        ><i class="fab fa-facebook-f"></i
      ></a>

      <!-- Twitter -->
      <a
        class="btn btn-primary btn-floating m-1"
        style="background-color: #55acee;"
        href="https://twitter.com/Tnh64187341"
        role="button"
        ><i class="fab fa-twitter"></i
      ></a>

      <!-- Google -->
      <a
        class="btn btn-primary btn-floating m-1"
        style="background-color: #dd4b39;"
        href="#!"
        role="button"
        ><i class="fab fa-google"></i
      ></a>

      <!-- Instagram -->
      <a
        class="btn btn-primary btn-floating m-1"
        style="background-color: #ac2bac;"
        href="#!"
        role="button"
        ><i class="fab fa-instagram"></i
      ></a>

      <!-- Linkedin -->
      <a
        class="btn btn-primary btn-floating m-1"
        style="background-color: #0082ca;"
        href="#!"
        role="button"
        ><i class="fab fa-linkedin-in"></i
      ></a>
      <!-- Github -->
      <a
        class="btn btn-primary btn-floating m-1"
        style="background-color: #333333;"
        href="#!"
        role="button"
        ><i class="fab fa-github"></i
      ></a>
    </section>
    <!-- Section: Social media -->
  </div>
  <!-- Grid container -->

  <!-- Copyright -->
  <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
    © 2020 Copyright:
    <a class="text-white" href="https://caodang.fpt.edu.vn/">https://caodang.fpt.edu.vn</a>
  </div>
  <!-- Copyright -->
</footer>



    
    <script src="<c:url value='/templates/user/js/plugins.js'/>"></script>
    <script>
        $(window).on("load", function() {
            $('body').addClass('loaded');
        });
    </script>
    <script>
		$('#changePassBtn').click(function (){
			$('#messageChangePass').text('');
			var currentPass = $('#currentPass').val();
			var newPass = $('#newPass').val();
			var formData = {
					'currentPass' : currentPass,
					'newPass' : newPass
					};
			$.ajax({
				url: 'changePass',
				type : 'POST',
				data : formData
			}).then(function(data) {
				$('#messageChangePass').text('Your password has been changed successfully');				
			}).fail(function(error){
				$('#messageChangePass').text('Your password is not correct, try again');
			});
		});
	</script>