var chat = (function(){
	var client,
	selfUserId,
	selfCurrentStatus = "online",
	selfpreviousStatusBeforeIdle = "online",
	idleTimer,
	chatters = {};
	_createNewClient = function(){
		var socket = new SockJS("/chatter/chat");
		client = Stomp.over(socket);
		client.heartbeat.outgoing = 0;
		client.heartbeat.incoming = 0;
 	},
 	_registerChatRequestEvent = function($submenuitem){
 		$submenuitem.on("click",function(e){
 			var message = {};
 			message.type = "chatrequest";
 			message.message = selfCurrentStatus;
 			message.status = selfCurrentStatus;
 			message.msender = selfUserId;
 			message.recepient = $(this).data('id');
 			$.post('/chatter/chat/invite',message,function(data){
 				if(data === 'request sent'){
 					//chat request sent
 					
 				}
 			});
 		});
 	},
 	_registerChatOpenEvent = function(){
 		$("#friendList").on("dblclick", "li>span",function(e,messageObj){
			//create chat widget
 			var $this = $(e.target),
			fId = $this.attr('id'),
			dId = 'cd_'+fId;
 			if($("#"+dId).length === 1 && $("#"+dId).chatdialog('isOpen')){
				//set focus to that dialog
				console.log("before setting focus"+dId);
				$("#"+dId).chatdialog("setFocus");
			}else{
				$('body').append($("<div>").attr('id',dId));
				$("#"+dId).chatdialog({
					title : $this.text(),
					titleClassName :$this.attr('class'),
					senderId:selfUserId,
					id: fId.substr(fId.indexOf('f_')+2)
				});
			}
			if(chatters[fId.substr(fId.indexOf('f_')+2)] && messageObj && messageObj.message){
				$("#"+dId).chatdialog('receiveMessage',messageObj.message);
			}
		});
 	},
 	_registerAcceptRequestEvent = function(){
 		$("#requestTableContainer").on('click',"input[type='button'][id^='chatReqAcceptBtn_']",function(e){
 			var $this = $(e.target),
 			message = {},
 			id = $this.attr('id'),
 			senderId = $("#senderUserId"+id.substr(id.indexOf('_'))).val(),
 			chatReqId = $("#chatRequestId"+id.substr(id.indexOf('_'))).val();
 			message.type = "chatinviteaccpted";
 			message.message = "accepted request";
 			message.status = selfCurrentStatus;
 			message.msender = senderId;
 			message.requestId = chatReqId;
 			message.recepient = selfUserId;
 			$.post('/chatter/chat/acceptinvite',message,function(data){
 				if(data === 'request accepted'){
 					//chat request accepted
 					$("#msgLabel").text("Chat Request Accepted Successfully");
 					$("#msgDiv").removeClass('invisible').animate({opacity:1},4000,"linear",function(){
 						$(this).animate({opacity:0},2800,"linear",function(){
 							$(this).addClass("invisible");
 						});
 					});
 					
 					message.type = "statusinquiry";
		 			message.message = "send me your status";
		 			message.status = selfCurrentStatus;
		 			message.msender = selfUserId;
		 			message.recepient = senderId;
		 			var fName = $("#senderName"+id.substr(id.indexOf('_'))).val(), 
 					$fElement = $("<li>").append($("<span>").attr('id','f_'+senderId).addClass('offline').text(fName));
		 			$("#friendList").append($fElement);
					_sendMessage(message,"/user/"+senderId+"/queue/messaging");
				}
 			});
 		});
 	},
 	_registerRejectRequestEvent = function(){
 		$("#requestTableContainer").on('click',"input[type='button'][id^='chatReqRejectBtn_']",function(e){
 			var $this = $(e.target),
 			message = {},
 			id = $this.attr('id'),
 			senderId = $("senderUserId"+id.substr(id.indexOf('_'))).val();
	 		message.type = "chatinviterejected";
			message.message = "please reject request";
			message.status = selfCurrentStatus;
			message.msender = senderId;
			message.recepient = selfUserId;
			$.post('/chatter/chat/rejectinvite',message,function(data){
				if(data === 'request rejected'){
					//chat request accepted
					$("#msgLabel").text("Chat Request Rejected Successfully");
 					$("#msgDiv").removeClass('invisible').animate({opacity:1},4000,"linear",function(){
 						$(this).animate({opacity:0},2800,"linear",function(){
 							$(this).addClass("invisible");
 						});
 					});
				}
			});
 		});	
 	},
 	_registerLogoutEvent = function(){
 		$("#logoutAnchor").on("click",function(e){
 			selfCurrentStatus = "offline";
 			var message = {};
 			message.type = "statusannouncement";
 			message.message = selfCurrentStatus;
 			message.status = selfCurrentStatus;
 			message.msender = selfUserId;
 			message.recepient = "all";
 			_sendMessage(message,"/topic/logintracker");
 		});
 	},
 	_registerIdleTimer = function(){
 		var _sendMsg = _sendMessage,
 		idleTracker = function(){
 			return function(){
	 			if(idleTimer){
	 				clearTimeout(idleTimer);
	 			}
	 			idleTimer = window.setTimeout(function(){
	 				console.log("timout switching to idle"+selfCurrentStatus);
	 				selfpreviousStatusBeforeIdle = selfCurrentStatus;
	 				selfCurrentStatus = "idle";
	 	 			var message = {};
	 	 			message.type = "statusannouncement";
	 	 			message.message = selfCurrentStatus;
	 	 			message.status = selfCurrentStatus;
	 	 			message.msender = selfUserId;
	 	 			message.recepient = "all";
	 	 			_sendMsg(message,"/topic/logintracker");
	 			},30000);
 			};
 		}();
 		idleTracker();
 		$(document).on("mouseover",function(){
 			if(selfCurrentStatus === 'idle'){
 				console.log("timout coming out of idle");
 				selfCurrentStatus = selfpreviousStatusBeforeIdle; 
 				var message = {};
 	 			message.type = "statusannouncement";
 	 			message.message = selfCurrentStatus;
 	 			message.status = selfCurrentStatus;
 	 			message.msender = selfUserId;
 	 			message.recepient = "all";
 	 			_sendMessage(message,"/topic/logintracker");
 				idleTracker();
 			}
 		});
 	},
	_subscribe = function(dest,callback){
		client.subscribe(dest,callback);
	},
	_sendMessage = function(message,dest){
		client.send(dest,{},JSON.stringify(message));
	},
	_handleStatusAnnouncement = function(announcement){
		//alert($("#friendList").find("li>span[id='f_"+announcement.msender+"']").length);
		var fId = 'f_'+announcement.msender;
		$("#friendList").find("li>span[id='"+fId+"']").removeClass().addClass(announcement.status);
		if(chatters[announcement.msender]){
			$("#cd_"+fId).chatdialog('updateStatus',announcement.status);
		}
	},
	_handleLoginAnnouncement = function(announcement){
		$("#friendList").find("li>span[id='f_"+announcement.msender+"']").removeClass().addClass(announcement.status);
		var message = {};
		message.type = "statusannouncement";
		message.message = selfCurrentStatus;
		message.status = selfCurrentStatus;
		message.msender = selfUserId;
		message.recepient = "all";
		_sendMessage(message,"/topic/logintracker");
	},
	_broadCastMessageHandler = function(message){
		var messageObj = JSON.parse(message.body);
		if(messageObj.msender != selfUserId){
			if(messageObj.type == "loginannouncement"){
				 //console.log("login announcement!!"+ message);
				_handleLoginAnnouncement(messageObj);
			}else if(messageObj.type == "statusannouncement"){
				console.log("status announcement!! from"+ messageObj.msender);
				_handleStatusAnnouncement(messageObj);
			}
		}else{
			console.log("self announcement!!(ignore)");//+ message);
		}
	},
	_privateMessageHandler = function(message){
		var messageObj = JSON.parse(message.body),
		message = {};
		if(messageObj.type == "chat"){
			console.log("chat message received!!"+ messageObj.message + ":" + $("#f_" + messageObj.msender).length);
			$("#f_" + messageObj.msender).trigger("dblclick",{"message":messageObj.message});	
		}else if(messageObj.type == "statusinquiry"){
			console.log("status enquiry!! from"+ messageObj.msender);
			message.type = "statusannouncement";
			message.message = selfCurrentStatus;
			message.status = selfCurrentStatus;
			message.msender = selfUserId;
			message.recepient = messageObj.msender;
			_sendMessage(message,"/user/"+messageObj.msender+"/queue/messaging");	
		}else if(messageObj.type == "chatrequest"){
			var rc = $("input[type='button'][id^='chatReqRejectBtn_']").length,
			rc = (rc === 0 ? 1 :rc),
			$td = $("<td>").append('<div class="row"><div class="col-xs-3"><input type="button" value="Accept" id="chatReqAcceptBtn_' 
					+ rc
					+'" class="btn btn-sm btn-primary"></div><div  class="col-xs-3">	<input type="button" value="Reject"	id="chatReqRejectBtn_' 
					+ rc 
					+ '" class="btn btn-sm btn-primary"></div></div><input type="hidden" id="senderUserId_' 
					+ rc 
					+ '"  value="' 
					+ messageObj.msender 
					+'"><input type="hidden" id="chatRequestId_' 
					+ rc 
					+ '" value="'
					+  messageObj.requestId
					+ '" ><input type="hidden" id="senderName_'
					+ rc
					+ '" value="'
					+ messageObj.userInfo.userName
					+ '" />'),
			$td1 = "<td>" + messageObj.userInfo.userName + "</td>",
			$tr = $("<tr>").append($td1).append($td);
			if($("#requestTable").length  === 0){
				$("#noRequestInfoDiv").remove();
				$("<table class='table table-bordered' style='width: 40%; margin-left: 25%;' id='requestTable'><tr>"
						+"<td>User Name</td><td>Accept/Reject</td>"
						+"</tr>")
				.attr('id','requestTable').append($tr).appendTo($("#requestTableContainer"));
			}else{
				$("#requestTable").append($tr);
			}
			$("#msgLabel").text("You have new chat Request");
			$("#msgDiv").removeClass('invisible').animate({opacity:1},3000,"linear",function(){
				$(this).animate({opacity:0},2100,"linear",function(){
					$(this).addClass("invisible");
				});
			});
			$tr.switchClass("","bg-success",600,'linear',function(){
				$(this).switchClass("bg-success","",2100,'linear');
			});
		}else if(messageObj.type === 'chatinviteaccpted'){
			message.type = "statusinquiry";
 			message.message = "send me your status";
 			message.status = selfCurrentStatus;
 			message.msender = selfUserId;
 			message.recepient = messageObj.msender;
 			var fName = messageObj.userInfo.userName, 
			$fElement = $("<li>").append($("<span>").attr('id','f_'+messageObj.recepient).addClass(messageObj.status).text(fName));
			$("#friendList").append($fElement);
			_sendMessage(message,"/user"+messageObj.msender+"/queue/messaging");
		}else if(messageObj.type === 'statusannouncement'){
			var fId = 'f_'+messageObj.msender;
			$("#friendList").find("li>span[id='"+fId+"']").removeClass().addClass(messageObj.status);
			if(chatters[messageObj.msender]){
				$("#cd_"+fId).chatdialog('updateStatus',messageObj.status);
			}
		}
		
	},
	_announceLoginEvent = function(){
		var message = {};
		message.type = "loginannouncement";
		message.message = "Online";
		message.status = "online";
		message.msender = selfUserId;
		message.recepient = "all";
		_sendMessage(message,"/topic/logintracker");
	},
	_connect = function(){
		_createNewClient();
		client.connect({}, function(frame) {
			selfUserId = frame.headers['user-name'];
			_subscribe("/topic/logintracker",_broadCastMessageHandler);
  			_subscribe("/user/queue/messaging",_privateMessageHandler);
  			_announceLoginEvent();
		 },function(errframe){
			 console.log("frame:"+errframe);
		 });
	};
	return {
		init : function(){
			$.get("/chatter/auth/friends",function(data){
				var friends = data;
				for(var fc = 0; fc < friends.length; fc++){
					var $fElement = $("<li>").append($("<span>").attr('id','f_'+friends[fc].userId).addClass('offline').text(friends[fc].userFirstName + " " +friends[fc].userLastName));
					$("#friendList").append($fElement);
				}
				_registerChatOpenEvent();
				_registerAcceptRequestEvent();
				_registerRejectRequestEvent();
				_connect();
				_registerIdleTimer();
				_registerLogoutEvent();
				$("#friendList").removeClass('hidden').addClass('show');
			});
			
		},
		send :function(message,dest){
			_sendMessage(message,dest);
		},
		getCurrentStatus : function(){
			return selfCurrentStatus;   
		},
		addFriend :function(friendId){
			chatters[friendId] = true;
		},
		removeFriend :function(friendId){
			delete chatters.friendId;
		},
		registerChatRequestEvent :function($submenuitem){
			_registerChatRequestEvent($submenuitem);
		}
		
	};
}($));