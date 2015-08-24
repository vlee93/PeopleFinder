<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>People Finder</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Home.jsp">ThePeopleFinder</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
      	<li class="active"><a href="#">PeopleFinder</a></li>
      <li><a href="SearchResult.jsp">Search Result</a></li>
      </ul>
    </div>
  </div>
</nav>
<h2> Welcome to the People Finder Database </h2> <br>

<h4> Search the person by last name or Company Name: </h4>

 <form action="PeopleDB" method="post" role="form">
  <div class="form-group">
    <label for="lastname">Last Name or Company Name:</label>
    <input name="lastname">
  </div>
  <input type="submit" value="submit">
</form>


</body>
</html>