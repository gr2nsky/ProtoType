<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="0">
		<form action="modify.do" method="post">
			<!-- <input type="hidden" name="bId" value="${content_view.bId }"> -->
			<tr>
				<td>번호</td>
				<td><input type="text" name="no" readonly="readonly" value="${content.no }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" szie="20" value="${content.writer }"></td>
			</tr> 
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" szie="50" value="${content.title }"></td>
			</tr> 
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content">${content.content }</textarea></td>
			</tr> 
			<tr>
				<td colspan="2">
					<input type="submit" value="수정"> &nbsp; &nbsp; &nbsp;<a href="list.do?page=${courrentPage}">목록보기</a>
					&nbsp;&nbsp;&nbsp;<a href="delete.do?no=${content_view.bId }">삭제</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>