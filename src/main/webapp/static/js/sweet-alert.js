/**
 * 일반 alert
 */
window.alert = function(message, callback) {
	
	callback = callback || function(){};
	
	swal({
			text: message,
			onClose: callback
		});
}

/**
 * 성공 alert
 */
window.alert2 = function(message) {
	swal("Success", message, "success")
}

/**
 * 실패 alert
 */
window.alert3 = function(message) {
	swal("Failed", message, "error")
}

/**
 * 컨펌
 */
window.confirm = function(message, callback, callback2) {
	
	callback = callback || function(){};
	callback2 = callback2 || function(){};
	
	swal({
		//title: message,
		text: message,
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: 'btn-warning',
		confirmButtonText: "Yes, Do it!",
	}).then(callback
			, function(dismiss) {
		if(dismiss == "cancel") {
			callback2();
		}
	});
}