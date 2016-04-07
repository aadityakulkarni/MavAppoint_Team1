<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
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
<div class="panel-heading text-center"><h1>Customize Settings</h1></div>
<% ArrayList<AppointmentType> ats = (ArrayList<AppointmentType>)session.getAttribute("appointmenttypes");
if(ats != null){ %>
<div class="panel-body resize-body center-block">
	<form action="appointments" method="post" name="cancel">
	<div class="panel-heading text-center"><h3>Appointment Manager</h3></div>
			<input type=hidden name=cancel_button id="cancel_button"> 
			<input type=hidden name=edit_button id="edit_button">		
				<table class="table table-striped custab">
					<thead>
						<tr>
							<th><font style="color: #0" size="4">Appointment Type</font></th>
							<th><font style="color: #0" size="4">Duration</font></th>
							<th><font style="color: #0" size="4">Action</font></th>
						</tr>
					</thead>
					<%@ page import="uta.mav.appoint.beans.AppointmentType"%>
					<%@ page import="java.util.ArrayList"%>
					<%@ page import="uta.mav.appoint.beans.Appointment"%>
					<!-- begin processing appointments  -->
					<%for (int i=0;i<ats.size();i++){
						%>
					<tr>
						<td><font style="color: #0" size="3"><%=ats.get(i).getType()%></font></td>
						<td><font style="color: #0" size="3"><%=ats.get(i).getDuration()%></font></td>
						<td><a class="btn btn-link btn-xs" type="button" id="button1<%=i%>" href="/MavAppoint/cancel_edit_app_type?type=<%=ats.get(i).getType()%>&status=cancel">Cancel</a>
							<a class="btn btn-link btn-xs" type="button" id="button2_<%=i%>" onclick="edit('<%=ats.get(i).getType()%>','<%=ats.get(i).getDuration()%>')" href="#" data-toggle="modal" data-target="#editApptType">Edit</a>
						</td>
						
					</tr>
					<%	}
	    			%>
					<!-- end processing advisors -->
					</table>
				</form>
</div>
<div class="panel-footer text-center">
   	<input type="submit" class="btn-lg" value="Add Appointment Type" href="#" data-toggle="modal" data-target="#addApptType">
</div>

<form action="add_app_type" method="post" onsubmit="return false;">
	<div class="modal fade" id="addApptType" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"></button>
					<h4 class="modal-title" id="addApptTypeLabel">Add Appointment Type</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="apptypes">Appointment Type:</label>
						<input type="text" class="form-control" id="apptypes" placeholder="">
					</div>
					<div class="form-group">
						<label for="minutes">Minutes</label> <input type="number" class="form-control" id="minutes" step="5" placeholder="">
					</div>
					<div>
						<label id="result"><font style="color: #0" size="4"></font></label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" value="submit" onclick="javascript:FormSubmit();">
				</div>
			</div>
		</div>
	</div>
</form>

<!-- Edit Appointment -->

<form action="cancel_edit_app_type" method="get" onsubmit="return false;">
	<div class="modal fade" id="editApptType" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"></button>
					<h4 class="modal-title" id="addApptTypeLabel">Edit Appointment Type</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="apptypes">Appointment Type:</label>
						<input type="text" disabled class="form-control" id="apptypes_edit" placeholder="">
					</div>
					<div class="form-group">
						<label for="minutes">Minutes</label> <input type="number" class="form-control" id="minutes_edit" step="5" placeholder="">
					</div>
					<div>
						<label id="result"><font style="color: #0" size="4"></font></label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" value="submit" onclick="javascript:EditSubmit();">
				</div>
			</div>
		</div>
	</div>
</form>


<% } %>

<div class="panel-body resize-body center-block">
<form action="customize" method="POST">
<div class="panel-heading text-center"><h3>Email Notifications</h3></div>
<% String notification = (String) session.getAttribute("notification");
			if("yes".equalsIgnoreCase(notification)){
		%>
		<div class="form-group">
		<input type="radio" name="notify" id="radioyes" value="yes" checked><label for="radioyes">Yes</label>
		</div>
		<div class="form-group">
		<input type="radio" name="notify" id="radiono" value="no"><label for="radiono">No</label>
		</div>
		<%  }  else {%>
			<div class="form-group">
				<input type="radio" name="notify" id="radioyes" value="yes"><label for="radioyes">Yes</label>
			</div>
			<div class="form-group">
				<input type="radio" name="notify" id="radiono" value="no" checked><label for="radiono">No</label>
			</div>
		<% } %>
		<div class="panel-footer text-center">
			<input type="submit" class="btn-lg" value="submit"/>
		</div>
		</form>
	</div>
</div>
</div>

<script> function FormSubmit(){
									var apptype = document.getElementById("apptypes").value;
									var minutes = document.getElementById("minutes").value;
									var params = ('minutes='+minutes+'&apptypes='+apptype);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										if (xmlhttp.readyState==4){
											document.getElementById("result").innerHTML = xmlhttp.responseText;	
										}
									}
									xmlhttp.open("POST","add_app_type",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									alert(params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
									document.getElementById("result").innerHTML = "Adding appointment type...";
								}
								
		function EditSubmit(){

			var apptype = document.getElementById("apptypes_edit").value;
			var minutes = document.getElementById("minutes_edit").value;
			
			window.location = "/MavAppoint/cancel_edit_app_type?apptypes="+apptype+"&minutes="+minutes+"&status=edit";
		
		}
								
								function edit(type,duration){
									
									document.getElementById('apptypes_edit').value = type;
									document.getElementById('minutes_edit').value = duration;
									
									
								}
								</script>

<%@include file="templates/footer.jsp"%>