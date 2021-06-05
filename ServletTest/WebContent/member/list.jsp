<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
	String msg = 
			request.getParameter("msg") == null ? "" : request.getParameter("msg"); 
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>주소</td>
		</tr>
		
		<%
			int memSize = memList.size();
		
			if(memSize > 0) {
				for(int i=0; i<memSize; i++) {
		%>
		
		<tr>
			<td><%=memList.get(i).getMemId() %></td>
			<!-- detail앞에 아무것도 없어서 상대경로 -->
			<td><a href="detail?memId=<%=memList.get(i).getMemId() %>"><%=memList.get(i).getMemName() %></a></td>
			<td><%=memList.get(i).getMemTel() %></td>
			<td><%=memList.get(i).getMemAddr() %></td>
		</tr>
		
		<%
				}
			}else { //회원정보가 존재하지 않으면...
		%>
		<tr>
			<td colspan="4">회원 정보가 없습니다.</td>
		<tr>
		<%		
			}
		%>
		<tr align="center">
			<td colspan="4"><a href="insert">[회원 등록]</a></td>
		</tr>
	</table>	
<%
if(msg.equals("성공")) {
%>
	<script type="text/javascript">
		alert('정상적으로 처리되었습니다.');
	</script>
<%
}else if(msg.equals("실패")) {
%>
	<script type="text/javascript">
		alert('요청 작업 수행에 실패했습니다.');
	</script>
<%
}
%>
</body>
</html>