<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
</head>

<style>
   h2 {
      text-align: center;
   }
   table {
      width: 100%;
   }
   #outter {
      display: block;
      width: 60%;
      margin: auto;
   }
</style>
<body>

<div id ="comment${model.comment_no}" class="comment">
작성자:${user_id}작성시간:${model.writetime}
<br>
<h5 id="aa${model.comment_no}">내용:${model.board_Comment}</h5>
<div id="form${model.comment_no}">
<input type="button" value="수정" onclick="commentupdateform(${model.comment_no})">
<input type="button" value="삭제" onclick="commentdelete(${model.comment_no})">
</div>
<textarea
id="formtextarea${model.comment_no}" name="formtextarea1" class="abcde"
rows="3" style="display: none">${model.board_Comment}</textarea>
<div id="form1${model.comment_no}" style="display: none;">
<input type="button" id="update" value="수정완료" onclick='commentupdate(${model.comment_no})'>
<input type="button" id="delete" value="취소" onclick='commentupdatecancel(${model.comment_no})'>
</div>
<hr>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value="/resources/js/funcion.js"/>"></script>
</body>
</html>