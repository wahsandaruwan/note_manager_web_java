<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Note Manager</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand">Note Manager</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Note List</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${note != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${note == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${note != null}">
            			Edit Note
            		</c:if>
						<c:if test="${note == null}">
            			Add New Note
            		</c:if>
					</h2>
				</caption>

				<c:if test="${note != null}">
					<input type="hidden" name="id" value="<c:out value='${note.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Note Title : </label> <input type="text"
						value="<c:out value='${note.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Note Category : </label> <input type="text"
						value="<c:out value='${note.category}' />" class="form-control"
						name="category" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Note Description : </label> <input type="text"
						value="<c:out value='${note.description}' />" class="form-control"
						name="description" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>