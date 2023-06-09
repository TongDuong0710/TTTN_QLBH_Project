<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<c:url value="/resources/static" var="url"></c:url>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit User</title>
<!-- BOOTSTRAP STYLES-->
<link href="${url}/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="${url}/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="${url}/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href="${url}/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">
<link href="<c:url value='resources/assets/dist/signin.css' />"
	rel="stylesheet">
</head>
<style>
.order_sell {
	float: right !important;
}

.btn-setting:hover {
	opacity: 0.5;
}

.btn-setting {
	cursor: pointer
}

.btn:hover {
	background-color: #41464b;
	color: white;
}

#btn_huy, .btn_Oke {
	width: 90px;
}

.modal {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #00000040;
	display: none;
}

.modal-form {
	margin-top: 25px;
	margin-left: 18%;
}

.modal-body {
	font-size: medium;
}

.modal-title {
	margin-left: 7%;
	font-weight: 600;
	line-height: 30px;
	font-size: 19px;
}

.modal-content {
	border-radius: 16px;
	width: 500px;
}

#modal-delete {
	padding-top: 6%;
	padding-left: 8%;
}

#modal-success {
	display: none;
	margin-top: 9%;
	margin-left: 14%;
}

.modal-nd {
	max-width: 265px;
	margin: auto;
	margin-top: 5%;
}

.bi-gear {
	color: black
}

.labels {
	font-weight: Bold;
	font-size:15px;
	color: black;
}

.table {
	
}

.input_order {
	width: 92%;
	margin-bottom: 10px;
	background: #0000001c;
	border-radius: 4px;
	border: 1px solid black;
	font-family: revert;
	font-weight: 600;
}

.btn_detail {
	margin-right: 38px;
	margin-left: 12px;
}

.btn_submit {
	margin-right: 5px;
}

.order {
	display: none;
}

.btn-mr20 {
	margin-right: 20px
}

a {
	color: white;
	text-decoration: none;
}

.sign-out:hover {
	opacity: 0.5;
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.product_image {
	width: 16px;
	height: 16px;
}

.input_order {
	
}

.bi-trash {
	color: #000000bf;
	padding-left: 5px;
}

.bi-check-circle-fill {
	color: blue;
}

.modal-ct {
	height: 50px;
	font-size: large;
}
.anhsp{
	max-width: 40px;
    max-height: 40px;
	}
.border-form {
	width: 100%;
	margin-top: 3%;
	border: 1px solid black;
	border-radius: 5px;
	margin-left: 3%;
	margin-right: 3%;
}

.table-view {
	width: 96%;
	margin-left: 2%;
	margin-top: 5%;
}

 .bi-gear{
    color:#df1919;
    padding-right: 11px;
    margin-left: 23%
    }
     .bi-trash{
     color:#23af19;
     }

.title-table {
	background: #120d0db0;
}

.content-title {
	text-align: inherit;
	padding-top: 11px;
	margin-left: 21px;
	padding-bottom: 14px;
	color: white;
	font-family: auto;
}

.bi-check-circle-fill {
	color: blue;
	padding-right: 5px;
}

.bi-exclamation-circle-fill {
	color: #ff5000;
	padding-right: 5px;
}

.link-history {
	color: #dc3545 !important;
	font-weight: 600;
	float: right;
	font-size: large;
	margin-bottom: -15px;
	margin-top: 32px;
}

.link-history:hover {
	opacity: 0.8;
}

.modal-footer {
	margin-top: 0;
}
.validatetion{
	    color: red;
    font-style: italic;
    font-size: initial;
}
.title-form{
	margin-top: 36px;  
    color: #red;
    font-size: 4rem;
    font-family: auto;
    text-align: center;
    font: 1000;
    
}
.tthd{
	color: #blue;
	font-weight: Bold;
	font-size:15px;
}
</style>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/WEB-INF/views/admin/view/nav-bar.jsp"></jsp:include>
		<!-- /. NAV TOP  -->
		<jsp:include page="/WEB-INF/views/admin/view/slide-bar.jsp"></jsp:include>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12" >
						<h2 style="text-align: center;" class="title-form">BILL DETAIL</h2>
						<span class="validatetion">${message}</span>
						
					</div>
				</div>
				<!-- /. ROW  -->
				<hr />

				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						
						<div class="panel panel-default">
							<div class="panel-heading" style="font-weight: bold;">BẢNG CHI TIẾT </div>
							<div class="panel-body">

								<div class="form-group"><label class="labels" style="">SỐ HÓA ĐƠN:    </label><label  style="color: blue; margin-left: 10px">${HD.sohd }</label></div>
									<div class="form-group"><label class="labels" style="">NGÀY LẬP:    </label> <label style="color: blue;  margin-left: 10px">${HD.ngaylaphd }</label></div>
									<div class="form-group"><label class="labels" style="">NHÂN VIÊN LẬP:    </label> <label style="color: blue;  margin-left: 10px">${HD.nhanvien.hoten }</label></div>	
									<div class="form-group"><label class="labels" style="">TỔNG HÓA ĐƠN:    </label> <label style="color: red; margin-left: 10px">${HD.tonggia } USD</label></div>		
							
							</div>
							
							
							<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead style="background-color: #0000000d;">
											<tr>
												<th scope="col">Mã sản phẩm</th>
												<th scope="col">Tên sản phẩm</th>
												<th scope="col">Hình ảnh</th>
												<th scope="col">Đơn giá (USD)</th>
												<th scope="col">Số lượng</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="list" items="${listcthd}">
												<tr>
													<th scope="row">${list.getSanpham().getMasp()}</th>
													<td>${list.getSanpham().getTensp()}</td>
													<td><img class="anhsp" alt=""
														src="/WebBanDoChoi/imageProducts/${list.getSanpham().getHinhanh()}"></td>
													<td>${list.getSanpham().getDongia()}</td>
													<td>${list.getSL()}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>

			</div>
		</div>

		
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="${url}/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="${url}/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="${url}/js/jquery.metisMenu.js"></script>
	<!-- DATA TABLE SCRIPTS -->
	<script src="${url}/js/dataTables/jquery.dataTables.js"></script>
	<script src="${url}/js/dataTables/dataTables.bootstrap.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>

	<!-- CUSTOM SCRIPTS -->
	<script src="${url}/js/custom.js"></script>
	
</body>
</html>