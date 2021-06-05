<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberVO memVO = (MemberVO)request.getAttribute("memVO");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/member/update" method="post">
		<table>
			<tr>
			<!-- id는 수정되면 안되므로 보여주기만 하기 -->
			<!-- 신규는 기존데이터 필요X, update는 기존 데이터가 필요해서 select 한번 해줘야 한다 -->
			<!-- 일단 아직 안했는데, 나중에 select해주기로 하고 지금은 select했다는 가정 하에 코딩 -->
				<td>I D : </td>
				<td><%=memVO.getMemId() %>
					<input type="hidden" name="memId" value="<%=memVO.getMemId()%>">
				</td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td><input type="text" name="memName" value="<%=memVO.getMemName()%>"></td>
			</tr>
			<tr>
				<td>전화번호 : </td>
				<td><input type="text" name="memTel" value="<%=memVO.getMemTel()%>"></td>
			</tr>
			<tr>
				<td>주소 : </td>
				<td><textarea name="memAddr"><%=memVO.getMemAddr()%></textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원 등록">
	</form>
</body>
</html>