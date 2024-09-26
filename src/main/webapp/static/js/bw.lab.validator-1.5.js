
//lab-bluewaves-validator 에서 ValidatorForm 을 사용할경우 true
//일반적으로 사용시 false 로 사용
//true => 다국어 지원
//false => 한글만 지원
var _isUseAnnotationValidatorForm = true;

var _isSubmit = false;

var _this = [];

var _fileFieldCount = [];

jQuery(document).ready(function() {
	
	jQuery("form").each(function(i) {
		
		this._submit = this.submit; //submit copy
		_this.push(this); //this copy
		
		//submit override
		jQuery.prototype.submit = function(submitByOriginal) {
			
			var idx = jQuery("form").index(this);
			
			if(!submitByOriginal) { //일반적인 사용
				
				if(_isSubmit) {
					//다국어 처리시 아래 문장을 다국어 js 를 활용하여 처리하세요.
					alert("잠시만 기다려 주세요");
					return;
				}
				
				_isSubmit = true;
				
				if(privateFormValidator(_this[idx], false)) {
					_this[idx]._submit(); //submit
				} else {
					_isSubmit = false;
				}
				
			} else { //강제로 true 값 사용
				
				privateFormValidator(_this[idx], true);
				
				_this[idx]._submit(); //submit
			}
		};
	});
});

function privateFormValidator(formObject, isBypass) {
	
	if(jQuery.hasOwnProperty("blabValidation")) {
		jQuery.blabValidation(); //blab validation addon
	}
	
	//helixEditor
	if(window.HELIXEDITOR) {
		if(HELIXEDITOR.instances) {
			jQuery(formObject).find('textarea').each(function() {
				
				if(String(this.id) != "undefined" && String(this.id) != "") {
					if(eval("HELIXEDITOR.instances." + this.id)) {
						
						eval("HELIXEDITOR.instances." + this.id).updateElement();
					}
				}
			});
		}
	}
	
	var isReturn = true;

	jQuery(formObject).find(':input').each(function() {
		
		if(!isBypass) {
			
			// Test for REQUIRED
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[REQUIRED') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				if(jQuery(this).attr('class').indexOf('[REQUIRED:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[REQUIRED:') + 10);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery.trim(jQuery(this).val());
				//var jQuerytext = jQuery.trim(jQuery(this).text());
				var jQueryname = jQuery(this).attr("name");
				var jQuerycheck = this.type.toLowerCase() == "checkbox" ? "" : true;
				var jQueryradio = this.type.toLowerCase() == "radio" ? "" : true;
				
				/*if(jQueryname == "contents") {
					alert(this.type.toLowerCase() + " / text : " + jQuerytext);
					alert("tagName : " + this.tagName.toLowerCase() + " / text : " + jQuerytext);
				}*/
				
				/*if(this.type.toLowerCase() == "text") {
					jQueryval = jQuerytext;
				}*/
				
				//배열형 체크박스
				jQuery("input:checkbox[name^='" + jQueryname + "']").each( function(i) {
					if(jQuery(this).prop("checked")){
						jQuerycheck = true;
						return;
					}
				});
				
				//배열형 라디오
				jQuery("input:radio[name^='" + jQueryname + "']").each( function(i) {
					if(jQuery(this).prop("checked")){
						jQueryradio = true;
						return;
					}
				});

				if (jQueryval.length == 0 || !jQuerycheck || !jQueryradio) {
					
					var _vObj = jQuery(this);
					
					if(this.tagName.toLowerCase() == "select") {
						
						if(_isUseAnnotationValidatorForm) {
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 선택해 주십시오.");
						}
						
					} else {
						if (this.type.toLowerCase() == "checkbox") {
							if(_isUseAnnotationValidatorForm) {
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 체크해 주십시오.");
							}
						} else if (this.type.toLowerCase() == "radio") {
							if(_isUseAnnotationValidatorForm) {
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 선택해 주십시오.");
							}
						} else if (this.type.toLowerCase() == "textarea") {
							if(_isUseAnnotationValidatorForm) {
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 입력해 주십시오.");
							}
							
							//helixEditor
							if(window.HELIXEDITOR) {
								if(HELIXEDITOR.instances) {
									if(eval("HELIXEDITOR.instances." + this.id)) {
										
										eval("HELIXEDITOR.instances." + this.id).focus();
										
										isReturn = false;
										
										return false;
									}
								}
							}
							
						} else {
							if(_isUseAnnotationValidatorForm) {
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 입력해 주십시오.");
							}
						}
					}
					
					//jQuery(this).focus();
					
					isReturn = false;
					
					return false;
				}
			}
		}
		
		//최소 입력 금액 확인
		if(!isBypass && isReturn){
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MINLIMITNUMBER') > -1) {
				//치환 메시지 있음
				var tmpMinLimitNumber = "";
				var _minLimitNumber = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[MINLIMITNUMBER:') > -1) {
					tmpMinLimitNumber = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MINLIMITNUMBER:') + 16);
					_minLimitNumber = tmpMinLimitNumber.substring(0, tmpMinLimitNumber.indexOf(':'));
					_klibMsg = tmpMinLimitNumber.substring(tmpMinLimitNumber.indexOf(':') + 1, tmpMinLimitNumber.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val().replace(/,/g, "");
				
				if(jQueryval != ""){
					if (Number(jQueryval) > -1 && Number(jQueryval) < Number(_minLimitNumber.replace(/,/g, ""))) {
						
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " " + _minLimitNumber + " 이상 입력 가능합니다.");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//최대 입력 금액 확인
		if(!isBypass && isReturn){
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MAXLIMITNUMBER') > -1) {
				//치환 메시지 있음
				var tmpMaxLimitNumber = "";
				var _maxLimitNumber = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[MAXLIMITNUMBER:') > -1) {
					tmpMaxLimitNumber = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MAXLIMITNUMBER:') + 16);
					_maxLimitNumber = tmpMaxLimitNumber.substring(0, tmpMaxLimitNumber.indexOf(':'));
					_klibMsg = tmpMaxLimitNumber.substring(tmpMaxLimitNumber.indexOf(':') + 1, tmpMaxLimitNumber.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val().replace(/,/g, "");
				
				if(jQueryval != ""){
					if (Number(jQueryval) > -1 && Number(jQueryval) > Number(_maxLimitNumber.replace(/,/g, ""))) {
						
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " " + _maxLimitNumber + " 이하 입력 가능합니다.");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//이전 날짜인지 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[DATEPAST') > -1) {
				
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				var _inputDate = null;
				
				if(jQuery(this).attr('class').indexOf('[DATEPAST:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[DATEPAST:') + 10);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					var isDateValid = true;
					
					if((jQueryval.length != 8 && jQueryval.length != 10)) {
						isDateValid = false;
					} else {
						
						if(jQueryval.length == 8) {
							
							var tmpYyyy = Number(jQueryval.substring(0, 4));
							var tmpMm = Number(jQueryval.substring(4, 6))-1;
							var tmpDd = Number(jQueryval.substring(6, 8));
							
							var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
							
							if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
								isDateValid = false;
							}
							
							_inputDate = tmpDate;
							
						} else if(jQueryval.length == 10 && jQueryval.indexOf('.') > -1) {
							
							var tmpDateAttr = jQueryval.split(".");
							
							if(tmpDateAttr.length != 3) {
								isDateValid = false;
							} else {
								var tmpYyyy = Number(tmpDateAttr[0]);
								var tmpMm = Number(tmpDateAttr[1])-1;
								var tmpDd = Number(tmpDateAttr[2]);
								
								var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
								
								if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
									isDateValid = false;
								}
								
								_inputDate = tmpDate;
							}
							
						} else if(jQueryval.length == 10 && jQueryval.indexOf('-') > -1) {
							
							var tmpDateAttr = jQueryval.split("-");
							
							if(tmpDateAttr.length != 3) {
								isDateValid = false;
							} else {
								var tmpYyyy = Number(tmpDateAttr[0]);
								var tmpMm = Number(tmpDateAttr[1])-1;
								var tmpDd = Number(tmpDateAttr[2]);
								
								var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
								
								if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
									isDateValid = false;
								}
								
								_inputDate = tmpDate;
							}
							
						} else {
							isDateValid = false;
						}
						
					}
					
					if(!isDateValid) {

						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 현재 날짜 부터 이전으로 가능합니다.");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					} else {
						
						var now = new Date();
						
						if(now < _inputDate) {
							
							if(_isUseAnnotationValidatorForm) {
								var _vObj = jQuery(this);
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 현재 날짜 부터 이전으로 가능합니다.");
							}
							
							//jQuery(this).focus();
							isReturn = false;
							return false;
						}
						
					}
				}
			}
		}
		
		//이후 날짜인지 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[DATEFUTURE') > -1) {
				
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				var _inputDate = null;
				
				if(jQuery(this).attr('class').indexOf('[DATEFUTURE:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[DATEFUTURE:') + 12);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					var isDateValid = true;
					
					if((jQueryval.length != 8 && jQueryval.length != 10)) {
						isDateValid = false;
					} else {
						
						if(jQueryval.length == 8) {
							
							var tmpYyyy = Number(jQueryval.substring(0, 4));
							var tmpMm = Number(jQueryval.substring(4, 6))-1;
							var tmpDd = Number(jQueryval.substring(6, 8));
							
							var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
							
							if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
								isDateValid = false;
							}
							
							_inputDate = tmpDate;
							
						} else if(jQueryval.length == 10 && jQueryval.indexOf('.') > -1) {
							
							var tmpDateAttr = jQueryval.split(".");
							
							if(tmpDateAttr.length != 3) {
								isDateValid = false;
							} else {
								var tmpYyyy = Number(tmpDateAttr[0]);
								var tmpMm = Number(tmpDateAttr[1])-1;
								var tmpDd = Number(tmpDateAttr[2]);
								
								var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
								
								if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
									isDateValid = false;
								}
								
								_inputDate = tmpDate;
							}
							
						} else if(jQueryval.length == 10 && jQueryval.indexOf('-') > -1) {
							
							var tmpDateAttr = jQueryval.split("-");
							
							if(tmpDateAttr.length != 3) {
								isDateValid = false;
							} else {
								var tmpYyyy = Number(tmpDateAttr[0]);
								var tmpMm = Number(tmpDateAttr[1])-1;
								var tmpDd = Number(tmpDateAttr[2]);
								
								var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
								
								if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
									isDateValid = false;
								}
								
								_inputDate = tmpDate;
							}
							
						} else {
							isDateValid = false;
						}
						
					}
					
					if(!isDateValid) {

						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 현재 날짜 부터 이후로 가능합니다.");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					} else {
						
						var now = new Date();
						
						if(now > _inputDate) {
							
							if(_isUseAnnotationValidatorForm) {
								var _vObj = jQuery(this);
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 현재 날짜 부터 이후로 가능합니다.");
							}
							
							//jQuery(this).focus();
							isReturn = false;
							return false;
						}
						
					}
				}
			}
		}
		
		//정규식 패턴 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[REGEXP') > -1) {
				
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				var _regexp = "";
				
				if(jQuery(this).attr('class').indexOf('[REGEXP:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[REGEXP:') + 8);
					_regexp = tmpMsg.substring(0, tmpMsg.indexOf(':'));
					_klibMsg = tmpMsg.substring(tmpMsg.indexOf(':') + 1, tmpMsg.lastIndexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != "") {
					
					var pattern = new RegExp(_regexp, "gi");

					for(var k=0; k < jQueryval.length; k++) {
						
						var ch = String(jQueryval.charAt(k));
						
						if(!ch.match(pattern)) {
							
							if(_isUseAnnotationValidatorForm) {
								var _vObj = jQuery(this);
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 형식에 맞지 않습니다.");
							}
							
							//jQuery(this).focus();
							isReturn = false;
							return false;
						}
						
					}
					
				}
			}
		}
		
		//최소 길이 입력 제한 
		if(!isBypass && isReturn){
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MINLENGTH') > -1) {
				//치환 메시지 있음
				var tmpMaxLength = "";
				var _minLength = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[MINLENGTH:') > -1) {
					tmpMaxLength = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MINLENGTH:') + 11);
					_minLength = tmpMaxLength.substring(0, tmpMaxLength.indexOf(':'));
					_klibMsg = tmpMaxLength.substring(tmpMaxLength.indexOf(':') + 1, tmpMaxLength.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					if (jQueryval.length < Number(_minLength)) {
						
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 최소 " + _minLength + " 자리 이상 입력 가능합니다.");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//최대 자리 이상 입력 확인
		if(!isBypass && isReturn){
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MAXLENGTH') > -1) {
				//치환 메시지 있음
				var tmpMinLength = "";
				var _maxLength = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[MAXLENGTH:') > -1) {
					tmpMinLength = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MAXLENGTH:') + 11);
					_maxLength = tmpMinLength.substring(0, tmpMinLength.indexOf(':'));
					_klibMsg = tmpMinLength.substring(tmpMinLength.indexOf(':') + 1, tmpMinLength.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					if (jQueryval.length > Number(_maxLength)) {
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 최대 " + _maxLength + " 자리 까지 입력 가능합니다.");
						}
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//빈값 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[BLANK') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[BLANK:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[BLANK:') + 7);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					if(jQuery.trim(jQueryval) == "" && jQueryval != "") {
						
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 빈값을 입력할 수 없습니다..");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//쉼표(,) 를 제외한 최소 자리 이상 입력 확인
		if(!isBypass && isReturn){
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MINNUMBER') > -1) {
				//치환 메시지 있음
				var tmpMinLength = "";
				var _minLength = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[MINNUMBER:') > -1) {
					tmpMinLength = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MINNUMBER:') + 11);
					_minLength = tmpMinLength.substring(0, tmpMinLength.indexOf(':'));
					_klibMsg = tmpMinLength.substring(tmpMinLength.indexOf(':') + 1, tmpMinLength.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val().replace(/,/g, "");
				
				if(jQueryval != ""){
					if(jQueryval != ""){
						if (Number(jQueryval.length) < Number(_minLength)) {
							if(_isUseAnnotationValidatorForm) {
								var _vObj = jQuery(this);
								alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
							} else {
								alert(_klibMsg + " 최소 " + _minLength + " 자리 이상 입력 가능합니다.");
							}
							//jQuery(this).focus();
							isReturn = false;
							return false;
						}
					}
				}
			}
		}
		
		//쉼표(,) 를 제외한 최대 길이 입력 제한 
		if(!isBypass && isReturn){
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[MAXNUMBER') > -1) {
				//치환 메시지 있음
				var tmpMaxLength = "";
				var _maxLength = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[MAXNUMBER:') > -1) {
					tmpMaxLength = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[MAXNUMBER:') + 11);
					_maxLength = tmpMaxLength.substring(0, tmpMaxLength.indexOf(':'));
					_klibMsg = tmpMaxLength.substring(tmpMaxLength.indexOf(':') + 1, tmpMaxLength.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val().replace(/,/g, "");
				
				if (Number(jQueryval.length) > Number(_maxLength) || jQueryval.indexOf("e") > -1) {
					if(_isUseAnnotationValidatorForm) {
						var _vObj = jQuery(this);
						alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
					} else {
						alert(_klibMsg + " 최대 " + _maxLength + " 자리 이하 입력 가능합니다.");
					}
					//jQuery(this).focus();
					isReturn = false;
					return false;
				}
			}
		}
		
		//날자 포멧 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[DATEFORMAT') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[DATEFORMAT:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[DATEFORMAT:') + 12);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					var isDateValid = true;
					
					if((jQueryval.length != 8 && jQueryval.length != 10)) {
						isDateValid = false;
					} else {
						
						if(jQueryval.length == 8) {
							
							var tmpYyyy = Number(jQueryval.substring(0, 4));
							var tmpMm = Number(jQueryval.substring(4, 6))-1;
							var tmpDd = Number(jQueryval.substring(6, 8));
							
							var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
							
							if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
								isDateValid = false;
							} else if(tmpYyyy != Number(tmpDate.getFullYear()) || tmpMm != Number(tmpDate.getMonth()) || tmpDd != Number(tmpDate.getDate())) {
								isDateValid = false;
							}
							
						} else if(jQueryval.length == 10 && jQueryval.indexOf('.') > -1) {
							
							var tmpDateAttr = jQueryval.split(".");
							
							if(tmpDateAttr.length != 3) {
								isDateValid = false;
							} else {
								var tmpYyyy = Number(tmpDateAttr[0]);
								var tmpMm = Number(tmpDateAttr[1])-1;
								var tmpDd = Number(tmpDateAttr[2]);
								
								var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
								
								if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
									isDateValid = false;
								} else if(tmpYyyy != Number(tmpDate.getFullYear()) || tmpMm != Number(tmpDate.getMonth()) || tmpDd != Number(tmpDate.getDate())) {
									isDateValid = false;
								}
							}
							
						} else if(jQueryval.length == 10 && jQueryval.indexOf('-') > -1) {
							
							var tmpDateAttr = jQueryval.split("-");
							
							if(tmpDateAttr.length != 3) {
								isDateValid = false;
							} else {
								var tmpYyyy = Number(tmpDateAttr[0]);
								var tmpMm = Number(tmpDateAttr[1])-1;
								var tmpDd = Number(tmpDateAttr[2]);
								
								var tmpDate = new Date(tmpYyyy, tmpMm, tmpDd);
								
								if(tmpDate.toString() == "NaN" || tmpDate.toString() == "Invalid Date") {
									isDateValid = false;
								} else if(tmpYyyy != Number(tmpDate.getFullYear()) || tmpMm != Number(tmpDate.getMonth()) || tmpDd != Number(tmpDate.getDate())) {
									isDateValid = false;
								}
							}
							
						} else {
							isDateValid = false;
						}
						
					}
					
					if(!isDateValid) {
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 날짜 형식에 맞지 않습니다.");
						}
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//숫자 포멧 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[NUMBERFORMAT') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[NUMBERFORMAT:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[NUMBERFORMAT:') + 14);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					var isNumberValid = true;
					
					var tmpVal = jQueryval.split(",").join("");
					
					if(Number(tmpVal).toString() == "NaN") {
						isNumberValid = false;
					}
					
					if(!isNumberValid) {
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 숫자 형식에 맞지 않습니다.");
						}
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
		//empty 값 확인
		//[EMPTY:FORKEY:title:FORVALUE:123:1번 선택시 2번 입력값은 필수 입니다.]
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[EMPTY') > -1) {
				
				var validationClasses = jQuery(this).attr('class').split("[EMPTY:");
				
				//this idx 구함
				var objectName = jQuery(this).attr("name");
				var idx = jQuery(formObject).find(":input[name='"+objectName+"']").not("[type='radio'],[type='checkbox']").index(this);
				
				if(idx == -1) {
					var thisValue = jQuery(this).val();
					idx = jQuery(formObject).find(":input[name='"+objectName+"'][value='"+thisValue+"']").index(this);
				}
				
				var forKey = "";
				
				var _klibMsg = "";
				
				var isValid = true;
				
				for(var k=0; k < validationClasses.length; k++) {
					
					if(validationClasses[k].indexOf("FORKEY") != 0) {
						continue;
					}
					
					//치환 메시지 있음
					_klibMsg = validationClasses[k].substring(0, validationClasses[k].indexOf("]"));
					_klibMsg = _klibMsg.substring(_klibMsg.lastIndexOf(":") + 1);
					
					var offerValue = ""; //입력된 대상값(내값)
					var forValue = ""; //정의한 대상값
					
					if(validationClasses[k].indexOf('FORVALUE:') > -1) {
						var offerText = validationClasses[k].substring(validationClasses[k].indexOf('FORVALUE:') + 9);
						forValue = offerText.substring(0, offerText.indexOf(":"));
					}
					
					if(jQuery(this).prop("type").toLowerCase() == "radio" || jQuery(this).prop("type").toLowerCase() == "checkbox") {
						offerValue = jQuery(this).filter(":checked").val(); 
					} else {
						offerValue = jQuery(this).val();
					}
					
					forKey = validationClasses[k].substring(validationClasses[k].indexOf('FORKEY:') + 7);
					forKey = forKey.substring(0, forKey .indexOf(":")); 
					
					if(forValue != "" ) {
						if(offerValue == forValue && 
								(jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").size() == 0 
										|| (jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").size() > 0 && jQuery.trim(jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").val()) == ""))) {
							isValid = false;
							break;
						}
					} else {
						if(offerValue != "" &&
								(jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").size() == 0 
										|| (jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").size() > 0 && jQuery.trim(jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").val()) == ""))) {
							isValid = false;
							break;
						}
					}
				}
				
				if(!isValid) {
					if(_isUseAnnotationValidatorForm) {
						alert(_klibMsg.split("_").join(" "), function() { jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").focus(); });
					} else {
						alert(_klibMsg + " 존재해야 합니다.");
					}
					//jQuery(this).focus();
					//jQuery(formObject).find(":input[name='"+forKey+"']:eq("+idx+")").focus();
					isReturn = false;
					return false;
				}
			}
		}
		
		//유니크한 값 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[UNIQUE') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				
				if(jQuery(this).attr('class').indexOf('[UNIQUE:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[UNIQUE:') + 8);
					_klibMsg = tmpMsg.substring(0, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					var isBreak = false;
					
					var objectValue = jQuery(this).val();
					var objectName = jQuery(this).attr("name");
					
					jQuery(formObject).find(":input[name='"+objectName+"']").not(this).each(function() {
						
						if(isBreak) {
							return false;
						}
						
						if(objectValue != "" && jQuery(this).val() != "" && objectValue == jQuery(this).val()) {
							isBreak = true;
						}
					});
					
					if(isBreak) {
						
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 유일한 값을 가져야 합니다.");
						}
						
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
					
				}
			}
		}
		
		//파일 필수 확인
		if(!isBypass && isReturn) {
			
			var fileFieldName = "";
			
			// Test for FILEREQUIRED
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[FILEREQUIRED') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				var fieldMinLen = 0;
				if(jQuery(this).attr('class').indexOf('[FILEREQUIRED:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[FILEREQUIRED:') + 14);
					fieldMinLen = tmpMsg.substring(0, tmpMsg.indexOf(':'));
					var tmpFileFieldName = tmpMsg.substring(tmpMsg.indexOf(String(fieldMinLen)+':')+String(fieldMinLen).length+1, tmpMsg.indexOf(']'));
					fileFieldName = tmpFileFieldName.substring(0, tmpFileFieldName.indexOf(':'));
					_klibMsg = tmpFileFieldName.substring(tmpFileFieldName.indexOf(':')+1);
				}
				
				if(_fileFieldCount[fileFieldName] == null) {
					_fileFieldCount[fileFieldName] = 0;
				}
				
				var attachFileSize = jQuery("ul[item='"+fileFieldName+"'] > li[id^='attachFileInfo-delete-']:visible").size();
				_fileFieldCount[fileFieldName] += attachFileSize;
				
				var jQueryval = jQuery.trim(jQuery(this).val());
				var jQueryname = jQuery(this).attr("name");
				
				if ((jQueryval.length == 0 && _fileFieldCount[fileFieldName] < fieldMinLen) ||
						(jQueryval.length == 0 && fieldMinLen <= 0)) {
					
					if(_isUseAnnotationValidatorForm) {
						var _vObj = jQuery(this);
						alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
					} else {
						alert(_klibMsg + " 선택해 주십시오.");
					}
					
					//jQuery(this).focus();
					
					isReturn = false;
					
					return false;
				} else {
					if(_fileFieldCount[fileFieldName] == null) {
						_fileFieldCount[fileFieldName] = 0;
					} else {
						_fileFieldCount[fileFieldName] += 1;
					}
				}
			}
		}
		
		//시간 포멧 확인
		if(!isBypass && isReturn) {
			
			if(String(jQuery(this).attr('class')) != "undefined" && jQuery(this).attr('class').indexOf('[TIMEFORMAT') > -1) {
				//치환 메시지 있음
				var tmpMsg = "";
				var _klibMsg = "";
				var timeFormat = "";
				
				if(jQuery(this).attr('class').indexOf('[TIMEFORMAT:') > -1) {
					tmpMsg = jQuery(this).attr('class').substring(jQuery(this).attr('class').indexOf('[TIMEFORMAT:') + 12);
					timeFormat = tmpMsg.substring(0, tmpMsg.indexOf(':'));
					_klibMsg = tmpMsg.substring(tmpMsg.indexOf(timeFormat+':')+timeFormat.length+1, tmpMsg.indexOf(']'));
				}
				
				var jQueryval = jQuery(this).val();
				
				if(jQueryval != ""){
					
					var isTimeValid = true;
					
					var tmpVal = jQueryval.split(":").join("").split(".").join("");
					
					if(Number(tmpVal).toString() == "NaN") {
						isTimeValid = false;
					}
					
					//우선 숫자형식 통과하면 시간 형식을 검사
					if(isTimeValid) {
						
						if(timeFormat == "HHMISS") {
							
							if(tmpVal.length == 6) {
								
								var times = new Array();
								times[0] = tmpVal.substring(0, 2);
								times[1] = tmpVal.substring(2, 4);
								times[2] = tmpVal.substring(4, 5);
								
								try {
									
									if(Number(times[0]) >= 24 || //시
											Number(times[1]) >= 60 || //분
											Number(times[2]) >= 60) { //초
										isTimeValid = false;
									}
									
								} catch(e) {
									isTimeValid = false;
								}
							} else {
								isTimeValid = false;
							}
							
						} else if(timeFormat == "HHMI") {
							
							if(tmpVal.length == 4) {
								
								var times = new Array();
								times[0] = tmpVal.substring(0, 2);
								times[1] = tmpVal.substring(2, 4);
								
								try {
									
									if(Number(times[0]) >= 24 || //시
											Number(times[1]) >= 60) { //분 
										isTimeValid = false;
									}
									
								} catch(e) {
									isTimeValid = false;
								}
							} else {
								isTimeValid = false;
							}
							
						} else if(timeFormat == "HH") {
							
							if(tmpVal.length == 2) {
								
								var times = tmpVal;
								
								try {
									
									if(Number(times) >= 24) { //시 
										isTimeValid = false;
									}
									
								} catch(e) {
									isTimeValid = false;
								}
							} else {
								isTimeValid = false;
							}
							
						} else if(timeFormat == "MISS") {
							
							if(tmpVal.length == 4) {
								
								var times = new Array();
								times[0] = tmpVal.substring(0, 2);
								times[1] = tmpVal.substring(2, 4);
								
								try {
									
									if(Number(times[0]) >= 60 || //분 
											Number(times[1]) >= 60) { //초
										isTimeValid = false;
									}
									
								} catch(e) {
									isTimeValid = false;
								}
							} else {
								isTimeValid = false;
							}

						} else if(timeFormat == "MI") {
							
							if(tmpVal.length == 2) {
								
								var times = tmpVal;
								
								try {
									
									if(Number(times) >= 60) { //분
										isTimeValid = false;
									}
									
								} catch(e) {
									isTimeValid = false;
								}
							} else {
								isTimeValid = false;
							}

						} else if(timeFormat == "SS") {
							
							if(tmpVal.length == 2) {
								
								var times = tmpVal;
								
								try {
									
									if(Number(times) >= 60) { //초
										isTimeValid = false;
									}
									
								} catch(e) {
									isTimeValid = false;
								}
							} else {
								isTimeValid = false;
							}
							
						} else {
							isTimeValid = false;
						}
					}
					
					if(!isTimeValid) {
						if(_isUseAnnotationValidatorForm) {
							var _vObj = jQuery(this);
							alert(_klibMsg.split("_").join(" "), function() { jQuery(_vObj).focus(); });
						} else {
							alert(_klibMsg + " 시간 형식에 맞지 않습니다.");
						}
						//jQuery(this).focus();
						isReturn = false;
						return false;
					}
				}
			}
		}
		
	});
	
	//숫자 필드 숫자만
	if(isReturn) {
		jQuery("input[class*='[NUMERIC]']").each(function() {
			jQuery(this).val(jQuery(this).getOnlyNumeric());
		});
	}
	
	//날짜 필드 숫자만
	if(isReturn) {
		jQuery("input[class*='[DATE]']").each(function() {
			jQuery(this).val(jQuery(this).getOnlyNumeric());
		});
	}
	
	//시간 필드 숫자만
	if(isReturn) {
		jQuery("input[class*='[TIME]']").each(function() {
			jQuery(this).val(jQuery(this).getOnlyNumeric());
		});
	}
	
	return isReturn;
}