<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
<style>
.custab {
	border: 1px solid #ccc;
	padding: 5px;
	margin: 5% 0;
	box-shadow: 3px 3px 2px #ccc;
	transition: 0.5s;
	background-color: #e67e22;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}
</style>


<div class="container">
	<div class="btn-group">
			<div class="row col-md-16  custyle">
				<table class="table table-striped custab">
					<thead>
						<tr>
							<th>Advisor Name</th>
							<th>Appointment Date</th>
							<th>Start Time</th>
							<th>End Time</th>
							<th>Advising Type</th>
							<th>Advising Email</th>
							<th>Description</th>
							<th>UTA Student ID</th>
							<th>Student Email</th>
							
							
							
							
							<!-- This adds "Phone Number" to the table  -->
							<th>Phone Number</th> 
							
							
							
							
							
						</tr>
					</thead>
					<%@ page import="java.util.ArrayList"%>
					<%@ page import="uta.mav.appoint.beans.Appointment"%>
					<!-- begin processing appointments  -->
					<% Appointment array = (Appointment)session.getAttribute("appointment");
		    			if (array != null){%>
					<%-- <%for (int i=0;i<array.size();i++){ %> --%>
					<tr>
						<td><%=array.getPname()%></td>
						<td><%=array.getAdvisingDate()%></td>
						<td><%=array.getAdvisingStartTime()%></td>
						<td><%=array.getAdvisingEndTime()%></td>
						<td><%=array.getAppointmentType()%></td>
						<td><%=array.getAdvisorEmail()%></td>
						<td><%=array.getDescription() %></td>
						<td><%=array.getStudentId()%></td>
						<td><%=array.getStudentEmail()%></td>
						
						
						
						<!-- =array.get(i).getstudentPhoneNumber() -->
						<td><%=array.getStudentPhoneNumber()%></td>
						
						
						
						
						
						
					</tr>
					</div>
					<%	
		    			}
		    			%>
					<!-- end processing advisors -->
				</table>
	</div>
</div>
<%@include file="templates/footer.jsp"%>