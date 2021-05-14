<%@ page import="com.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/products.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
			
				<h1>Product Management</h1>
				
				<form id="formProduct" name="formProduct">
 						Product Name:
 						<input id="productName" name="productName" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						Product Description:
 						<input id="productDesc" name="productDesc" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						Product Manufacture:
 						<input id="productManu" name="productManu" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						Product Price:
 						<input id="productPrice" name="productPrice" type="text" class="form-control form-control-sm">
 						<br>
 						
 						
 						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 						<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
				</form>

				<br>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProductsGrid">
					<%
							Product productObjRead = new Product();
							out.print(productObjRead.readProduct());
					%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>