<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
<%@ page import="java.util.ArrayList"%>
<%@ page import="uta.mav.appoint.login.Department"%>
<%@page import="java.util.HashMap"%>
<%   HashMap<Integer, String> advisors = (HashMap<Integer, String>) session.getAttribute("advisorDetails"); %>
<style>
.resize {
width: 60%;
}
.resize-body {
width: 80%;
}


</style>
<div class="container">
<!-- Panel -->
<div class="panel panel-default resize center-block">
<!-- Default panel contents -->
<form action="delete_advisor" method="post" name="advisor_form" id="advisor_form" onsubmit="return false;">
<div class="panel-heading text-center"><h1>Delete Advisor</h1></div>
<div class="panel-body resize-body center-block">
		<div class="form-group">
			<%
			if(!advisors.isEmpty()){
				int i = 1;
				for(Integer key: advisors.keySet()){
					String id = "advisor" + i;
					%>
					<input type="checkbox" id="<%=id%>" name="advisor" value="<%=key%>">
					<label for="<%=id%>"><font color="#0" size="4"><%=advisors.get(key)%></font></label>
					<br/>
					<%
					i = i + 1;
				}
			} else {%>
			No advisors currently available <% } %>
		</div>
		</div>
		<div class= "panel-footer text-center">
      	<input onclick="javascript:FormSubmit();" type="submit" class="btn-lg" value="Submit">
      	</div>
	</form>
	<label id="result"><font color="#0" size="4"></font></label>
</div>
</div>

<script>
function FormSubmit(){
	
	if ($("input[name=advisor]:checked").length === 0) {
		alert('Select atleast one advisor');
		return false;
	}
	document.getElementById('advisor_form').submit();
}
</script>
<%@include file="templates/footer.jsp"%>