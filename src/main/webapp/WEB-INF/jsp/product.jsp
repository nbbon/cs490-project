<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container">

		<form:form method="POST" modelAttribute="productForm" class="form-signin">
			<h2 class="form-signin-heading">Create your account</h2>
			
			<spring:bind path="productForm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="productNumber" class="form-control"
						placeholder="productNumber" autofocus="true"></form:input>
					<form:errors path="productNumber"></form:errors>
				</div>
			</spring:bind>
			
			
			<spring:bind path="productForm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="productName" class="form-control"
						placeholder="productName" autofocus="true"></form:input>
					<form:errors path="productName"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="price">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="price" class="form-control"
						placeholder="price"></form:input>
					<form:errors path="price"></form:errors>
				</div>
			</spring:bind>
			
			
			<spring:bind path="description">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="description" class="form-control"
						placeholder="description"></form:input>
					<form:errors path="description"></form:errors>
				</div>
			</spring:bind>
			
						
			
			<spring:bind path="category">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:select  path="category">
   					 <form:option value="NONE"> --SELECT--</form:option>
    				<form:options items="${nameCategories}" itemLabel="categoryName" itemValue= "categoryID"></form:options>
   					 </form:select>					
					<form:errors path="category"></form:errors>
				</div>
			</spring:bind>
			
			
		
			
			<spring:bind path="stock.quantity">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<%-- <form:input type="text" path="stock.quantity" class="form-control"
						placeholder="quantity"></form:input> --%>
						
						<form:input path="stock.quantity" />
					<form:errors path="stock.quantity"></form:errors>
				</div>
			</spring:bind>
			
	
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
		</form:form>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>