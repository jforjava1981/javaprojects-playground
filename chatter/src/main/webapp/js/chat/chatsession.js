(function($){
	
	$.widget("chatter.chatdialog",$.ui.dialog,{
		options : {
			position: {
				my: 'right bottom', 
				at: 'right bottom', 
				of: window
			},
			modal:false,
			draggable:true,
			autoOpen:true
		},
		_create : function(){
			this._superApply(arguments);
			this.options.modal = false;
			this.options.draggable = false;
			$("span.ui-dialog-title").addClass(this.options.titleClassName);
			var $chatMsgContainer = $("<div id='"+this.element.attr('id')+"_chatmsgcontainer'>"),
			$chatTxtContainer =  $("<textarea id='"+this.element.attr('id')+"_txtarea'>");
			$chatMsgContainer.addClass("chatMsgContainer");
			$chatTxtContainer.addClass("chatTxtContainer");
			$that = this;
			$chatTxtContainer.on("keydown",function(e){
				$this = $(this);
				if(e.which === 13){
					$chatMsgContainer.append($("<span style='font-weight:bold;'></span>").html("me:")).append($("<span>").html($this.val()+"<br>"));
					$chatMsgContainer.scrollTop($chatMsgContainer.prop('scrollHeight'));
					var message = {}
					message.type="chat";
					message.status= chat.getCurrentStatus();
					message.msender = $that.options.senderId;
					message.message = $this.val();
					$this.val("");
					chat.send(message,"/user/"+$that.options.id+"/queue/messaging");
					e.preventDefault();
				}
			});
			$("<div>").append($chatMsgContainer).append($chatTxtContainer).appendTo(this.element);
			chat.addFriend(this.options.id);
		},
		_destroy : function(arguments){
			this._superApply(arguments);
			this.element.remove();
		},
		close : function(arguments){
			this._superApply(arguments);
			chat.removeFriend(this.options.id);
			//this._destroy(arguments);
		},
		setFocus :function(){
			this.element.find("textarea[id='"+this.element.attr('id')+"_txtarea']").focus();
		},
		updateStatus : function(status){
			$("span.ui-dialog-title").removeClass().addClass("ui-dialog-title "+status);
		},
		receiveMessage :function(message){
			var $chatMsgContainer = $("#"+this.element.attr('id')+"_chatmsgcontainer");
			$chatMsgContainer.append($("<span style='font-weight:bold;'></span>").html(this.options.title+":")).append($("<span>").html(message+"<br>"));
			$chatMsgContainer.scrollTop($chatMsgContainer.prop('scrollHeight'));
		}
	});
}($));