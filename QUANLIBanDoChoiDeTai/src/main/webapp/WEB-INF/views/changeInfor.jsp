<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@include file="/WEB-INF/views/include/taglib.jsp"%>
 <%@include file="/WEB-INF/views/include/header.jsp"%>
 <script>
 document.getElementById("TenPage").innerHTML = "Change Information";
 
 </script>
	<section class="padding-top100 login-page">
		<div class="container">
			<div class="row" style="display: flex; justify-content: center">
				<div class="col-sm-6 col-xs-12 mt-40">
					<div class="login-content">
					<!--ĐIỀN CODE VÀO  -->
					<h4 >Information</h4>
					
						<div>${message }</div>
						<img style="width: 20%; height: auto;" src="files/${KHACHHANG.hinhAnh}" >
						<form:form action="user/updateInfor.htm"
							modelAttribute="KHACHHANG" enctype="multipart/form-data">
							<div class="form-group">
								<label for="input-email" class="control-label">Avatar</label>
								<input name="photo" type="file" class="form-control" style="border: none;outline: none;"/>
							</div>
							<div class="form-group">
								<label for="input-email" class="control-label">Full Name</label>
								<form:input path="hoTen" type="text" class="form-control" />
							</div>
							
							<form:input path="maKH" style="display: none"></form:input>
							
							<div class="form-group">
								<label for="input-email" class="control-label">Address</label>
								<form:input path="diaChi" type="text" class="form-control" />
							</div>
							<div class="form-group">
								<label for="input-email" class="control-label">Tel
									Number</label>
								<form:input path="sdt" type="text" class="form-control" />
							</div>
							<div class="form-group">
								<label for="input-email" class="control-label">Email</label>
								<form:input path="email" type="text" class="form-control" />
							</div>
							<div class="site-btn" >
								<button style="border-radius: 30px" name="btnCreateAccount"
									class="btn btn-1">
									<a class="btn btn-1"> <span class="txt">Update Information</span> 
									<span class="round"><i class="fa fa-chevron-right"></i></span>
									</a>
								</button>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>

	</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>
