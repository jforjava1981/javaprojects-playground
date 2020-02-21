var autocomplete = $("#searchUsers").autocomplete({
	source :"/chatter/auth/searchusers",
	minLength: 3,
	 select: function( event, ui ) {
		event.preventDefault();
	 },
     close:function(event,ui){
    	 	event.preventDefault();
 	 },
     focus: function(event, ui) {
    	 event.preventDefault();
    	 console.log("html",ui.item);
	 }
}).data("ui-autocomplete");
autocomplete._renderItem = function(ul,item){
	var $menuItem = $("<li>").addClass("ui-menu-item").data("ui-autocomplete-item",item).text( item.label ),
	$this = this.element,
	$subMenuItem = $("<ul>").append(($("<li>").addClass("ui-menu-item").data("ui-autocomplete-item",{label:"",value:"itc"}).append($("<div>").css('width','120px').text("Invite To Chat").data('id',item.userId))));
	$menuItem.append($subMenuItem);
	$menuItem.appendTo(ul);
	chat.registerChatRequestEvent($subMenuItem.find("div"));	
	return $menuItem;
};
