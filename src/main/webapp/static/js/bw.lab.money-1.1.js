(function(jQuery) {
	jQuery.fn.toPrice = function(cipher) {
		
		var strb, len, revslice, sign;
		
		sign = "";
		
		strb = jQuery(this).val().toString();
		strb = strb.replace(/,/g, '');
		
		sign = strb.charAt(0);
		sign = sign == "-" ? sign : "";
		
		strb = jQuery(this).getOnlyNumeric2();
		strb = parseInt(strb, 10);
		if(isNaN(strb)) {
			if(sign == "-") {
				return jQuery(this).val(sign);
			} else {
				return jQuery(this).val('');
			}
		}
			
		strb = strb.toString();
		
		if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MAXNUMBER') > -1) {
			//치환 메시지 있음
			var tmpMsg = "";
			var _maxLength = "";
			if(jQuery(this).attr('class').indexOf('[MAXNUMBER:') > -1) {
				tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MAXNUMBER:') + 11);
				_maxLength = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				if(String(Number(_maxLength)) == 'NaN') {
					_maxLength = tmpMsg.substring(0, tmpMsg.indexOf(':'));
				}
				strb = strb.substring(0, _maxLength);
			}
		}
		
		len = strb.length;
		
		if(len < 4) {
			//return jQuery(this).val(strb);
			return jQuery(this).val(sign.concat(strb));
		}
	
		if(cipher == undefined || !isNumeric(cipher))
			cipher = 3;
	
		count = len/cipher;
		slice = new Array();
	
		for(var i=0; i<count; ++i) {
			if(i*cipher >= len)
				break;
			slice[i] = strb.slice((i+1) * -cipher, len - (i*cipher));
		}
	
		revslice = slice.reverse();
		//return jQuery(this).val(revslice.join(','));
		return jQuery(this).val(sign.concat(revslice.join(',')));
	};
	
	jQuery.fn.getOnlyNumeric = function(data) {
		var chrTmp, strTmp;
		var len, str, sign;
		
		sign = "";
		
		if(data == undefined) {
			jQuery(this).css('ime-mode', 'disabled');
			str = jQuery(this).val();
		}
		else {
			str = data;
		}
		
		len = str.length;
		
		if(len > 0) {
			sign = str.charAt(0);
			sign = sign == "-" ? sign : "";
		}
		
		strTmp = '';
		
		for(var i=0; i<len; ++i) {
			chrTmp = str.charCodeAt(i);
			if((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58) {
				strTmp = strTmp + String.fromCharCode(chrTmp);
			}
		}
		
		if(data == undefined)
			return sign.concat(strTmp);
		else 
			return jQuery(this).val(sign.concat(strTmp));
	};

	var isNumeric = function(data) {
		var len, chrTmp;

		len = data.length;
		for(var i=0; i<len; ++i) {
			chrTmp = str.charCodeAt(i);
			if((chrTmp <= 47 && chrTmp > 31) || chrTmp >= 58) {
				return false;
			}
		}

		return true;
	};
	
	jQuery.fn.getOnlyNumeric2 = function(data) {
		var chrTmp, strTmp;
		var len, str;
		
		if(data == undefined) {
			jQuery(this).css('ime-mode', 'disabled');
			str = jQuery(this).val();
		}
		else {
			str = data;
		}
	
		len = str.length;
		
		strTmp = '';
		
		for(var i=0; i<len; ++i) {
			chrTmp = str.charCodeAt(i);
			if((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58) {
				strTmp = strTmp + String.fromCharCode(chrTmp);
			}
		}
		
		if(data == undefined)
			return strTmp;
		else 
			return jQuery(this).val(strTmp);
	};

	var isNumeric = function(data) {
		var len, chrTmp;

		len = data.length;
		for(var i=0; i<len; ++i) {
			chrTmp = str.charCodeAt(i);
			if((chrTmp <= 47 && chrTmp > 31) || chrTmp >= 58) {
				return false;
			}
		}

		return true;
	};
})(jQuery);

var getOnlyNumeric = function(str) {
	var chrTmp, strTmp, sign;
	var len;
	
	sign = "";

	len = str.length;
	
	if(len > 0) {
		sign = str.charAt(0);
		sign = sign == "-" ? sign : "";
	}

	strTmp = '';
	
	for(var i=0; i<len; ++i) {
		chrTmp = str.charCodeAt(i);
		if((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58) {
			strTmp = strTmp + String.fromCharCode(chrTmp);
		}
	}

	return sign.concat(strTmp);
};

var getOnlyNumeric2 = function(str) {
	var chrTmp, strTmp;
	var len;

	len = str.length;

	strTmp = '';
	
	for(var i=0; i<len; ++i) {
		chrTmp = str.charCodeAt(i);
		if((chrTmp > 47 || chrTmp <= 31) && chrTmp < 58) {
			strTmp = strTmp + String.fromCharCode(chrTmp);
		}
	}

	return strTmp;
};

var toPrice = function(strb, cipher) {
	
	var len, revslice, sign;
	
	sign = "";
	
	strb = strb.toString();
	strb = strb.replace(/,/g, '');
	
	sign = strb.charAt(0);
	sign = sign == "-" ? sign : "";
	
	strb = getOnlyNumeric2(strb);
	strb = parseInt(strb, 10);
	
	if(isNaN(strb)) {
		
		strb = '';
		
		return sign + strb;
	}
		
	strb = strb.toString();
	len = strb.length;
	
	if(len < 4) {
		
		return sign.concat(strb);
	}

	if(cipher == undefined || !isNumeric(cipher))
		cipher = 3;

	count = len/cipher;
	slice = new Array();
	
	for(var i=0; i<count; ++i) {
		if(i*cipher >= len)
			break;
		slice[i] = strb.slice((i+1) * -cipher, len - (i*cipher));
	}

	revslice = slice.reverse();
	
	return sign.concat(revslice.join(','));
	
};

