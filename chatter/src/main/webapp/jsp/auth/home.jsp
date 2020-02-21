<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/chatter-dashboard.css" />

<div class="container-fluid">

	<div class="row">

		<div class="col-sm-3 col-md-2 sidebar">
			<div class="row">
				<div class="col-xs-1">

					<form class="navbar-form navbar-left">
						<div id="searchMenuContainer">
							<input type="text" class="form-control" placeholder="Search..."
								id="searchUsers">
						</div>
					</form>
				</div>
			</div>
			<ul class="nav nav-sidebar" id="friendList">
				<!-- <li class="online"><span>Overview</span></li>
				<li class="idle"><span>Reports</span></li>
				<li class="busy"><span>Analytics</span></li>
				<li><span>Export</span></li> -->
			</ul>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header"></h1>
			<form:form commandName="user">
				
				<div class="bg-info border-left-radius border-right-radius text-center invisible" id="msgDiv">				
					<label id="msgLabel" class="text-info"></label>
				</div>
				<div class="bg-danger border-left-radius border-right-radius invisible" id="errDiv">
					<label id="errLabel" class="text-danger"></label>
				</div>	
				<div id="requestTableContainer">
					<c:choose>
						<c:when test="${user.chatRequestBeans.size() == 0}">
							<div class="bg-info border-left-radius border-right-radius text-center" id="noRequestInfoDiv">
								<label class="text-info">No Chat Requests found</label>
							</div>
						</c:when>
						<c:otherwise>
							<table class="table table-bordered"
								style="width: 40%; margin-left: 25%;" id="requestTable">
								<tr>
									
									<td>User Name</td>	
									<td>Accept/Reject</td>
									
								</tr>
	
								<c:forEach items="${user.chatRequestBeans}" var="chatRequest"
									varStatus="rc">
						 	 <tr>
						 	 	<td>
						 	 		<c:out value="${chatRequest.sender.userName}"></c:out>
						 	 	</td>
						 	 	<td>
						 	 		<div class="row">
						 	 			<div class="col-xs-3">
						 	 				<input type="button" value="Accept"
													id="chatReqAcceptBtn_${ rc.count}"
													class="btn btn-sm btn-primary" />
						 	 			</div>
						 	 		<div  class="col-xs-3">
												<input type="button" value="Reject"
													id="chatReqRejectBtn_${rc.count}"
													class="btn btn-sm btn-primary" />
											</div>
						 	 		</div>
						 	 		<input type="hidden" id="senderUserId_${rc.count}"
											value="${chatRequest.sender.userId}" />
						 	 		<input type="hidden" id="chatRequestId_${rc.count}"
											value="${chatRequest.id}" />
									<input type="hidden" id="senderName_${rc.count}"
											value="${chatRequest.sender.userName}}" />		
						 	 	</td>	
							 </tr>
						  </c:forEach>
						</table>
						</c:otherwise>
					</c:choose>
				</div>	 	
			</form:form>
		</div>
	</div>

</div>
<script src="${pageContext.request.contextPath}/js/common/sockjs.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/stomp.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery/ui/jquery-ui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chat/chatsession.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chat/chat.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/chat/searchusers.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/home/homesidebar.js" type="text/javascript"></script>