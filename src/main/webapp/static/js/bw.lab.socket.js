/**
 * SockJS 필수
 */
var sock = null;
var sessionId = null;

var BLABSocket = {
		open : function(addr, userSn, sessionId) { // 소켓 오픈
			sock = new SockJS(addr);
			
			sock.onopen = function() {
		   		console.info("Socket Open");
		   		
		   		var json = new Object();
		   		
		   		json.userSn = userSn;
		   		json.type = "LOGIN";
		   		json.sessionId = sessionId;
		   		
		   		sock.send(JSON.stringify(json));
		    }
		    sock.onmessage = function(message) {
		    	var json = jQuery.parseJSON(message.data);
		    	
		    	if(json.type == "OPEN") { // 소켓 오픈시
		    		sessionId = json.sessionId;
		    		
		    		jQuery("#sessionId").val(sessionId);
		    	} else if(json.type == "CUSTOM") { //  커스텀 메시지
		    		fncNewMessage();
		    	} else if(json.type == "RESVE") { // 가예약
		    		
		    		if(json.resultCode == "000") { //  가예약 성공
			    		if(json.resveNo != "") {
			    				
			    		}
		    		} else { // 가예약 실패 
		    			// TODO 가예약 취소처리
		    		}
		    	} else if(json.type == "CLOSE") { // 소켓 외부 원인 닫힘
		    		// TODO 가예약 취소처리
		    	} else if(json.type == "LOGOUT") {
		    	 	cfDuplicationLogout();
		    	}
		    	
		    	
		  	}
		  	sock.onclose = function(e) {
		  		console.info("Socket Close");
		  	}
		},
		addResve : function(resveNo) {
			console.log("가예약 추가");
			
			var json = new Object();
	   		
	   		json.resveNo = resveNo;
	   		json.type = "RESVE";
	   		
	   		sock.send(JSON.stringify(json));
		}
		
}