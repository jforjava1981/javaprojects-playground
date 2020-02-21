(function($){
	$("#loginSubmitBtn").click(function(){
		$("#loginForm").submit();
	});
	
	$("#loginRegistertBtn").click(function(){
		document.location.href = "/chatter/auth/showregister"
	});
}($));


