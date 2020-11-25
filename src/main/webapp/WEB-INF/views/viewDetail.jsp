<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="<c:url value="/resources/js/funcion.js?v=2" />"></script>
<title>글 상세보기</title>
</head>
<style>
h2 {
	text-align: center;
}

table {
	width: 100%;
}

textarea {
	width: 100%;
}

#outter {
	display: block;
	width: 70%;
	margin: auto;
}

.bo2 {
    border-bottom: 1px solid #444444;
    padding: 10px;
 }
 
 .bo3 {
    border-bottom: 1px solid #444444;
    padding: 10px;
 }
 
  .bo4 {
    border-bottom: 1px solid #444444;
    padding: 10px;
 }


</style>
<body>
	<h2>게시글 상세보기</h2>
	<br>
	<br>
	<br>

	<div id="outter">
		<table border="1">
			<tr>
				<td>제목:${board.title}</td>
			</tr>
			<tr>
				<td>작성자:${board.writer}
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;작성일:${board.regdate}
				</td>
			</tr>
			<tr>
				<td><div
						style="height: 300px; margin: 10px; display: inline-block">
						${board.content}</div></td>
			</tr>
		</table>
		<input type="button" value="닫기" style="float: right;"
			onclick='self.close()'> <input type="button" value="삭제"
			style="float: right;" onclick='boarddelete(${board.bno})'> <input
			type="button" value="수정" style="float: right;"
			onclick='boardupdateform(${board.bno})'>
	</div>

	<table style="margin-left: auto; margin-right: auto;">
		<tr>
			<td style="width: 50%;"><textarea id="comment" name="comment"
					style="height: 50px; resize: none;"></textarea></td>
			<td style="width: 20%;"><input type="button"
				onclick="comment('${board.bno}','${user_id}')" value="등록"
				style="height: 56px; margin-top: -4px;"></td>
		</tr>

	</table>

<div id="abcd${list.comment_no}">
	<c:forEach items="${comment}" var="list">
		<table class="bo" id="table${list.comment_no}">
				<c:set var="id" value="${list.user_id}" />
				<c:choose>
					<c:when test="${id eq user_id}">
						<tr>
							<td>
								작성자:${list.user_id}
								작성시간:${list.writetime}
							</td>
						</tr>
						<tr>
							<td>
								<h5 id="aa${list.comment_no}">내용:${list.board_Comment}</h5>
							</td>
						</tr>
						<tr>
							<td>
								<textarea
									id="formtextarea${list.comment_no}" name="formtextarea1" class="abcde"
									rows="3" style="display: none">${list.board_Comment}</textarea>
								<div id="form${list.comment_no}">
								<input type="button" id="update" value="수정" onclick='commentupdateform(${list.comment_no})'>
								<input type="button" id="delete" value="삭제" onclick='commentdelete(${list.comment_no})'>
								</div>
								<div id="form1${list.comment_no}" style="display: none;">
								<input type="button" id="update" value="수정완료" onclick='commentupdate(${list.comment_no})'>
								<input type="button" id="delete" value="취소" onclick='commentupdatecancel(${list.comment_no})'>
								</div>
								<div class="bo2"></div>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>
								작성자:${list.user_id} 작성시간:${list.writetime}
								<h5>내용:${list.board_Comment}</h5>
								<div class="bo3"></div>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
	</c:forEach>
</div>

</body>
</html>