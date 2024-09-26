
$(function() {

	var fileTarget = $('.filebox .upload-hidden');
	fileTarget.on('change', function(){		// 값이 변경되면
		if(window.FileReader){ // modern browser
			var filename = $(this)[0].files[0].name;
		} else { // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
		}
		// 추출한 파일명 삽입
		$(this).siblings('.upload-name').val(filename);
	});


	/* json SelectBox return
	 * param : parameter
	 * setId : innerHTML attribute id
	 * setValue : default Selected
	 * tp : radio, selectbox
	 * rdNm : 라디오 이름
	 */
	$.fnGetCodeSelectAjax = function(param, setId, setValue, tp, rdNm) {
		console.log("fnGetCodeSelectAjax======"+param+setId+setValue);
		console.log("fnGetCodedffdgf"+setValue);
		$.ajax({
			url : "/api/getCodeList.do"
			, dataType : 'json'
			, data : param
			, async : false
			, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
			, success : function(json) {
				console.log(json.list);
				var resultText = "";
				if(tp == "selectbox") {
					if($("#"+setId+" option").length){
						var defaultStr = $.trim($("#"+setId+" option:eq(0)").text());
						resultText += "<option value=''>"+defaultStr+"</option>";
					}
					$.each(json.list, function (index, value) {
						var cd = $.trim(value.cd);
						var nm = $.trim(value.cdNm);
						var selected = "";
						if(cd == setValue) {
							selected = "selected='selected'";
						}
						resultText += "<option value='"+cd+"' "+selected+">"+nm+"</option>\r\n";
					});
				} else if(tp == "radio") {
					$.each(json.list, function (index, value) {
						var cd = $.trim(value.cd);
						var nm = $.trim(value.cdNm);
						var checked = "";
						if(cd == setValue) {
							checked = "checked='checked'";
						}
						resultText += "<input type='radio' name='"+rdNm+"' id='"+rdNm+"_"+index+"' value='"+cd+"' "+checked+" /><label for='"+rdNm+"_"+index+"'>"+nm+"</label>\r\n";
					});
				}
				$("#"+setId).html(resultText);
				//return resultText;
			}
			, error : function() {
				return "";
			}
		});
	};

	$.commonSelectAjax = function(url,param, setId, setValue, tp, rdNm) {
		console.log("fnGetCodeSelectAjax======"+param+setId+setValue);
		console.log("fnGetCodedffdgf"+setValue);
		$.ajax({
			url : url
			, dataType : 'json'
			, data : param
			, async : false
			, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
			, success : function(json) {
				console.log(json);
				var resultText = "";
				if(tp == "selectbox") {
					if($("#"+setId+" option").length){
						var defaultStr = $.trim($("#"+setId+" option:eq(0)").text());
						resultText += "<option value=''>"+defaultStr+"</option>";
					}
					$.each(json, function (index, value) {
						var cd = $.trim(value);
						var nm = $.trim(value);
						var selected = "";
						if(cd == setValue) {
							selected = "selected='selected'";
						}
						resultText += "<option value='"+cd+"' "+selected+">"+nm+"</option>\r\n";
					});
				} else if(tp == "radio") {
					$.each(json.list, function (index, value) {
						var cd = $.trim(value.cd);
						var nm = $.trim(value.cdNm);
						var checked = "";
						if(cd == setValue) {
							checked = "checked='checked'";
						}
						resultText += "<input type='radio' name='"+rdNm+"' id='"+rdNm+"_"+index+"' value='"+cd+"' "+checked+" /><label for='"+rdNm+"_"+index+"'>"+nm+"</label>\r\n";
					});
				}
				$("#"+setId).html(resultText);
				//return resultText;
			}
			, error : function() {
				return "";
			}
		});
	};

	/*
	* key값 중복 검증
	* exceptVal : 수정시 제외해야할 값
	*/
	$.fnValidationkey = function(url, param, exceptVal){

		var sucessYn = false;

		$.ajax({
			url : url
			, dataType : 'text'
			, data : param
			, async : false
			, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
			, success : function(json) {

				var jText = $.parseJSON(json);
				var iCnt = jText.CNT;
				var sKey = jText.KEY;
				if(iCnt < 1){
					sucessYn = true;
				} else if(sKey == exceptVal){
					sucessYn = true;
				}
			}
			, error : function() {
				sucessYn = false;
			}
		});

		return sucessYn;
	};

	/**
	 * 데이터 초기화
	 */
	$.dataReset = function(id) {
		$("#"+id).val("");
	};
});




/**
 * 7일 getCalculatedDate(0,0,7,'-')
 * 3개월 getCalculatedDate(0,3,0,'-')
 * 6개월 getCalculatedDate(0,6,0,'-')
 * 1년 getCalculatedDate(1,0,0,'-')
 * @param iYear
 * @param iMonth
 * @param iDay
 * @param seperator
 * @returns {String}
 */

function getCalculatedDate(iYear, iMonth, iDay, seperator)
{
	//현재 날짜 객체를 얻어옴.
	var gdCurDate = new Date();
	//현재 날짜에 날짜 게산.
	gdCurDate.setYear( gdCurDate.getFullYear() + iYear );
	gdCurDate.setMonth( gdCurDate.getMonth() + iMonth );
	gdCurDate.setDate( gdCurDate.getDate() + iDay );

	//실제 사용할 연, 월, 일 변수 받기.
	var giYear = gdCurDate.getFullYear();
	var giMonth = gdCurDate.getMonth()+1;
	var giDay = gdCurDate.getDate();
	//월, 일의 자릿수를 2자리로 맞춘다.
	giMonth = "0" + giMonth;
	giMonth = giMonth.substring(giMonth.length-2,giMonth.length);
	giDay   = "0" + giDay;
	giDay   = giDay.substring(giDay.length-2,giDay.length);
	//display 형태 맞추기.
	return giYear + seperator + giMonth + seperator +  giDay;
}

// 모바일 pc 구분
function getMobilePc() {
	var UserAgent = navigator.userAgent.toLowerCase();
}

function fnChkByte(obj, maxByte){
	var str = obj.value;
	var str_len = str.length;

	var rbyte = 0;
	var rlen = 0;
	var one_char = "";
	var str2 = "";

	for(var i=0; i<str_len; i++){
		one_char = str.charAt(i);
		if(escape(one_char).length > 4){
		    rbyte += 2;		//한글2Byte
		}else{
		    rbyte++;		//영문 등 나머지 1Byte
		}

		if(rbyte <= maxByte){
		    rlen = i+1;		//return할 문자열 갯수
		}
	}

	if(rbyte > maxByte){
	    alert("내용은 "+(maxByte/2)+"자까지 입력이 가능합니다.");
    str2 = str.substr(0,rlen);	//문자열 자르기
	    obj.value = str2;
	    fnChkByte(obj, maxByte);
	}/* 	else{
	    document.getElementById('byteInfo').innerText = rbyte;
	} */
}

function getCookie( cookieName ){
	var search = cookieName + "=";
	var cookie = document.cookie;

	// 현재 쿠키가 존재할 경우
	if( cookie.length > 0 ){
		// 해당 쿠키명이 존재하는지 검색한 후 존재하면 위치를 리턴.
		startIndex = cookie.indexOf( cookieName );
		// 만약 존재한다면
		if( startIndex != -1 )
		{
			// 값을 얻어내기 위해 시작 인덱스 조절
			startIndex += cookieName.length;

			// 값을 얻어내기 위해 종료 인덱스 추출
			endIndex = cookie.indexOf( ";", startIndex );

			// 만약 종료 인덱스를 못찾게 되면 쿠키 전체길이로 설정
			if( endIndex == -1) endIndex = cookie.length;

			// 쿠키값을 추출하여 리턴
			return unescape( cookie.substring( startIndex + 1, endIndex ) );
		}else{
			// 쿠키 내에 해당 쿠키가 존재하지 않을 경우
			return false;
		}
	}else{
		// 쿠키 자체가 없을 경우
		return false;
	}
}

function setCookie( name, value, expiredays )
{
        var todayDate = new Date();
        todayDate.setDate( expiredays );
        document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}


/**
 *
 * @param n	: 날짜
 * @param m : 기준날짜
 * @param stParamID
 * @param edParamId
 * @returns
 */
function dateInput(n, m, stParamId, edParamId){
	$("#"+stParamId).val("");               // 우선 이미 들어가있는 값 초기화
    $("#"+edParamId).val("");

    var date = new Date();
    var start = new Date();
    var today = new Date(Date.parse(date)+ m * 1000 * 60 * 60 * 24);

    if(n < 7){
       start.setMonth(start.getMonth()+n);
    } else {
    	start = new Date(Date.parse(date)+ n * 1000 * 60 * 60 * 24);
    }
    var yyyy = start.getFullYear();
    var mm = start.getMonth()+1;
    var dd = start.getDate();

    var t_yyyy = today.getFullYear();
    var t_mm = today.getMonth()+1;
    var t_dd = today.getDate();

    $("#"+edParamId).val(yyyy+''+addzero(mm)+''+addzero(dd));
    $("#"+stParamId).val(t_yyyy+''+addzero(t_mm)+''+addzero(t_dd));

}


function dateInputMinus(n, m, stParamId, edParamId){
	$("#"+stParamId).val("");               // 우선 이미 들어가있는 값 초기화
    $("#"+edParamId).val("");

    var date = new Date();
    var start = new Date();
    var today = new Date(Date.parse(date)- m * 1000 * 60 * 60 * 24);

    if(n < 7){
       start.setMonth(start.getMonth()-n);
    } else {
    	start = new Date(Date.parse(date)- n * 1000 * 60 * 60 * 24);
    }
    var yyyy = start.getFullYear();
    var mm = start.getMonth()+1;
    var dd = start.getDate();

    var t_yyyy = today.getFullYear();
    var t_mm = today.getMonth()+1;
    var t_dd = today.getDate();

    $("#"+stParamId).val(yyyy+''+addzero(mm)+''+addzero(dd));
    $("#"+edParamId).val(t_yyyy+''+addzero(t_mm)+''+addzero(t_dd));

}

/**
 * 한자리 숫자에 0 넣어 2자리 수로 만들기
 * @param n
 * @returns
 */
function addzero(n){                        // 한자리가 되는 숫자에 "0"을 넣어주는 함수
    return n < 10 ? "0" + n: n;
}



/**
* 숫자만 입력
*/
function onlyNumber(obj) {
    $(obj).keyup(function(){
         $(this).val($(this).val().replace(/[^0-9]/g, '') );
    });
}

/*
* 관리자 비밀번호 패턴 체크
*/
function managerPwCheck(pw) {
	if (!(/[~|!|@|#|\\$|%|\^|&|\*]{1,}/.test(pw)
    		&& /[0-9]{1,}/.test(pw)
    		&& /[a-zA-Z]{1,}/.test(pw)
    		&& /^([0-9]|[a-zA-Z]|[~|!|@|#|\\$|%|\^|&|\*]){8,}$/.test(pw))) {
    	alert("비밀번호는 영문 소문자, 숫자, 특수문자가 조합된 형태여야 합니다.\n(사용 가능한 특수문자 9자 : ~, !, @, #, $, %, ^, &, *)",pw);
    	return false;
    }

	if (/([0]{2,})|([1]{2,})|[2]{2,}|[3]{2,}|[4]{2,}|[5]{2,}|[6]{2,}|[7]{2,}|[8]{2,}|[9]{2,}/.test(pw)
			|| /([a]{2,})|([b]{2,})|[c]{2,}|[d]{2,}|[e]{2,}|[f]{2,}|[g]{2,}|[h]{2,}|[i]{2,}|[j]{2,}|([k]{2,})|[l]{2,}|[m]{2,}|[n]{2,}|[o]{2,}|[p]{2,}|[q]{2,}|[r]{2,}|[s]{2,}|([t]{2,})|[u]{2,}|[v]{2,}|[w]{2,}|[x]{2,}|[y]{2,}|[z]{2,}/i.test(pw)
			|| /([~]{2,})|([!]{2,})|[@]{2,}|[#]{2,}|[\\$]{2,}|[%]{2,}|[\^]{2,}|[&]{2,}|[\*]{2,}/.test(pw)) {
		alert("비밀번호는 동일한 문자가 반복되지 않게 입력해 주세요.");
		return false;
	}
	return true;
}

// 회원관리 탭
function fnGoUserTab(t) {
	var url = "";

	if(t == "1") {
		url = "./detail.do";
	} else if(t == "2") {
		url = "./detailGoods.do";
	} else if(t == "3") {
		url = "./detailGoodsOrder.do";
	} else if(t == "4") {
		url = "./detailZzim.do";
	} else if(t == "5") {
		url = "./detailGass.do";
	}
	$("#adminForm").attr('action', url);
	$("#adminForm").submit();

}

function win_pop(path, win_name, width, height, scroll)
{window.open(path,win_name,'width=' + width + ', height=' + height + ', resizable=no, scrollbars=' + scroll + ', status=no, toolbar=no');}


function fnChkByte2(obj, maxByte){
	var str = $("#"+obj).val();
	var str_len = str.length;

	var rbyte = 0;
	var one_char = "";

	for(var i=0; i<str_len; i++){
		one_char = str.charAt(i);
		if(escape(one_char).length > 4){
		    rbyte += 2;		//한글2Byte
		}else{
		    rbyte++;		//영문 등 나머지 1Byte
		}

		if(rbyte <= maxByte){
		    rlen = i+1;		//return할 문자열 갯수
		}
	}

	if(rbyte > maxByte){
	    alert("내용은 "+maxByte+" byte 까지 입력이 가능합니다.");
    	return false;
	} else {
		return true;
	}
}

// 메타태그 validation
function metaValidation(IU) {

	if($.trim($("#metaTitleNm").val()) == ""){
		alert("Please enter the meta title.");
		$("#metaTitleNm").focus();
		return false;
	}

	if($.trim($("#metaDesc").val()) == ""){
		alert("Please enter the meta description.");
		$("#metaDesc").focus();
		return false;
	}

	if(IU == "I") {
		if($.trim($("#ogFile").val()) == ""){
			alert("Please select the og file.");
			$("#ogFile").focus();
			return false;
		}
	} else { // 수정일 경우
		if($("#ogFileDel").is(":checked")) {	// 삭제를 체크했을시 입력 필수이므로 반드시 업로드 해야한다.
			if($.trim($("#ogFile").val()) == ""){
				alert("Please select the og file.");
				$("#ogFile").focus();
				return false;
			}
		}
	}

	if ($("#ogFile").val() != ""){
		if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#ogFile").val().toLowerCase())){
			alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
			$("#ogFile").focus();
			return false;
		}
	}

	return true;
}



//byte check
function fnChkByteChk(obj, maxByte){
	var str = $("#"+obj).val();

	var codeByte = 0;
    for (var idx = 0; idx < str.length; idx++) {
        var oneChar = escape(str.charAt(idx));
        if ( oneChar.length == 1 ) {
            codeByte ++;
        } else if (oneChar.indexOf("%u") != -1) {
            codeByte += 2;
        } else if (oneChar.indexOf("%") != -1) {
            codeByte ++;
        }
    }

	if(codeByte > maxByte){
    	return false;
	} else {
		return true;
	}
}

//  숫자 체크
function fnNumberChk(obj){
 	var s = ''+ $("#"+obj).val(); // 문자열로 변환
  	s = s.replace(/^\s*|\s*$/g, ''); // 좌우 공백 제거
  	if (s == '' || isNaN(s)) return false;
  	return true;
}

// 이메일 체크
function fnEmailChk(obj) {
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if (!$("#"+obj).val().match(regExp)) {
    	return false;
    } else {
  		return true;
    }
}

function formByteCheck() {
	var rtf = true;

	$("input[type='text'], input[type='password'], textarea").each(function() {
		var id = $(this).attr("id");
		var maxByte = $("#"+id).attr("data-vbyte");
		var msg = $("#"+id).attr("data-vmsg");
		if(typeof maxByte == "undefined") {
			maxByte = "0";
		}
		if(typeof msg == "undefined") {
			msg = "";
		}

		if(maxByte != "0") {
			if(fnChkByteChk(id, maxByte) == false) {
				alert("Please enter the " +msg+" within "+maxByte+" bytes.");
				$("#"+id).focus();
				rtf = false;
				return false;
			}
		}
	});

	return rtf;
}



function unescapeHtml(str) {
	return str .replace(/&amp;/g, '&')
	.replace(/&gt;/g, '>')
	.replace(/&lt;/g, '<')
	.replace(/&quot;/g, '\"')
	.replace(/&#39;/g, '\'');

}
function cancel_backUp(id,from) {
	if(!$("#"+id).val()){
		history.back();
	}else{
		$("#"+from).prop("action","detail.do");
		$("#"+from).prop("method","post");
		$("#"+from).submit();
	}
}
// 링크 복사
function urlCopy(shareurl) {
	// if(shareurl == "") {
	// 	//shareurl = $('meta[property="og:url"]').attr('content');
	// 	shareurl = window.location.href;
	// } else {
	// 	var domain = location.protocol+"//"+location.hostname+(location.port ? ':'+location.port: '');
	// 	shareurl = domain + "" + shareurl;
	// }
	var t = document.createElement("textarea");
	document.body.appendChild(t);
	t.value = shareurl;
	t.select();
	document.execCommand('copy');
	document.body.removeChild(t);
	alert("copy success");
};

function export_Form_json(listForm){
	let data = {};
	let value = $(listForm).serializeArray();
	$.each(value, function (index, item) {
		data[item.name] = item.value;
	});
	let formData = JSON.stringify(data);
	return formData;
}

function export_Form(listForm){
	let formData = $(listForm).serialize();
	return formData;
}

function export_Excel(listForm,uri_hz){
	var formData = export_Form(listForm);
	console.log(formData);
	$.ajax({
		url : "/celloSquareAdmin/"+uri_hz+"/downloadCount.do"
		,type : "GET"
		,dataType : "json"
		, data :formData
		, contentType: "application/json"
		, success : function(data) {
			if(data==0)  {
				alert("暂无数据，请更换搜索条件再试。");
				return ;
			}else{
				$("#listForm").prop("method", "get");
				$("#listForm").prop("action","/celloSquareAdmin/"+uri_hz+"/download.do").submit();
			}
		}
	});
}


function date_module(){

	$("#statDate, #endDate").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"
		,changeYear:true
		,changeMonth: true
		,monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
		, maxDate: new Date()
		, defaultDate: new Date()
	});

	$("#statDate").change( function () {
		var stDt = $("#statDate").val();
		var stDtArr = stDt.split("-");
		var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
		var enDt = $("#endDate").val();
		var enDtArr = enDt.split("-");
		var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
		var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

		if (betweenDay < 0) {
			alert("开始日期要小于结束日期。");
			$("#statDate").focus();
			$("#statDate").val("");
			return false;
		}
	});

	$("#endDate").change(function () {
		var stDt = $("#statDate").val();
		var stDtArr = stDt.split("-");
		var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
		var enDt = $("#endDate").val();
		var enDtArr = enDt.split("-");
		var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
		var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

		console.log("betweenDay"+ betweenDay)

		if (betweenDay < 0) {
			alert("结束日期要大于开始日期。");
			$("#endDate").focus();
			$("#endDate").val("");
			return false;
		}
	});
	//清空日期
	if ("${vo.statDate}" == null || "${vo.endDate}" == '') {
		$("#statDate").val("");
		$("#endDate").val("");
	}
}
function date_module_new_v2_0(){

	$("#startDate, #endDate").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"
		,changeYear:true
		,changeMonth: true
		,monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
		, maxDate: new Date()
		, defaultDate: new Date()
	});

	$("#startDate").on("change keyup paste", function () {
		var stDt = $("#startDate").val();
		var stDtArr = stDt.split("-");
		var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
		var enDt = $("#endDate").val();
		var enDtArr = enDt.split("-");
		var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
		var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

		if (betweenDay < 0) {
			alert("开始日期要小于结束日期。");
			$("#startDate").focus();
			$("#startDate").val("");
			return false;
		}
	});

	$("#endDate").on("change keyup paste", function () {
		var stDt = $("#startDate").val();
		var stDtArr = stDt.split("-");
		var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
		var enDt = $("#endDate").val();
		var enDtArr = enDt.split("-");
		var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
		var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

		if (betweenDay < 0) {
			alert("结束日期要大于开始日期。");
			$("#endDate").focus();
			$("#endDate").val("");
			return false;
		}
	});
	//清空日期
	if ("${vo.startDate}" == null || "${vo.endDate}" == '') {
		$("#startDate").val("");
		$("#endDate").val("");
	}
}
/**************************** 基础函数 ***********************************/
function resetClick() {
	location.href = './list.do';
}

function fnSearch() {
	$("#page").val("1");
	$("#listForm").prop("method", "get");
	$("#listForm").attr('action', './list.do').submit();
}
//등록페이지 이동
function doRegister() {
	$("#listForm").prop("method", "post");
	$("#listForm").prop("action", "registerForm.do");
	$("#listForm").submit();
}
function excelDownLoad() {
	export_Excel("#listForm", "country");
}

function fncPage(page) {
	$("#page").val(page);
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}

//返回数组元素是否出现重复项（等于0：没有，大于0：有）
function ordb_p_x(array) {
	array.sort();  //数组排序
	var reNum = -1;  //返回结果
	//遍历整个数组对象
	for (var i = 0; i < array.length; i++) {
		//跳过最后一个元素的比较
		if (i + 1 == array.length || !array[i]) {
			continue;
		}
		//判断相邻的元素是否相同
		if (array[i] == array[i + 1]) {
			return array[i]
		}
	}
	return reNum;
}
/**************************** 基础函数 ***********************************/