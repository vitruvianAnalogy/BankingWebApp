<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Forget Password</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="<%=request.getContextPath()%>/css/plugins/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<%=request.getContextPath()%>/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<style>
    body {
        margin-top: 50px;
        position: relative;
    }
</style>
    
</head>



<body style="background:url('http://localhost:8080/SafeMoneyCorp/images/background.jpg')">
 <%-- <jsp:include page="/pages/sidebar.jsp"></jsp:include>  --%>
<div id="page-wrapper">
<br>
<br><h3>Forget Password</h3>
<br>
<form class="form-horizontal" role="form" action="secQuestionValidation" method="post"  style="padding-left: 2%; padding-top: 2%;"> 
	<!-- <form class="form-horizontal" role="form" action="LoginController" method="post"> -->
  
  
  <div class="form-group">
  
    <label for="Q1" class="control-label">${secQuestions.question1 }</label>
    
      <input type="text" id="answer1" class="form-control"  name="answer1">
  
    <br/>
     <label for="Q2" class="control-label">${secQuestions.question2 }</label>
    
      <input type="text" class="form-control"  id="answer2" name="answer2">
 
    <br/>
    <label for="Q3" class="control-label">${secQuestions.question3 }</label>
    
      <input type="text" class="form-control" id="answer3" name="answer3">
  
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    
    </div>
  </div>
  <div class="form-group">
    <div class="">
    <br>
      <input type="submit" class="btn btn-primary" value="Generate OTP"/>
      
      
      <%
				if(request.getAttribute("IncorrectAnswers") != null)
				{%>
				<p style="position:absolute;left:505px;top:470px;color:red;font-size:13px" class="label label-warning">* ${IncorrectAnswers} *</p>	
				<%}%>
      
 
    </div>
  </div>
</form>
</div>
<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>