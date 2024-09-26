jQuery(document).ready(function() {
	//select2 적용
	jQuery(".select2").select2();
});

var CMMN = {
	select2 : function() {
		jQuery(".select2").select2('destroy');
		jQuery(".select2").select2();
	}
}
