/**
 * 마이페이지 스크립트
 */

 /**
  * 개인정보 수정 (닉네임, 비밀번호, 이메일)
  */
 function info_save(){
	if($("#nickname").val() == ""){
		alert("닉네임을 입력해주세요.");
		$("#nickname").focus();
		return false;
	} else if($("#nickname").val() != $("#renick").val()){
		alert("닉네임 중복체크를 해주세요.");
		return false;
	} else if($("#pw1").val() == ""){
		alert("비밀번호를 입력해주세요.");
		$("#pw1").focus();
		return false;
	} else if($("#pw1").val() != $("#pw2").val()){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pw1").focus();
		return false;
	} else if($("#email").val() == ""){
		alert("이메일을 입력해주세요.");
		$("#email").focus();
		return false;
	} else {
		$("#update_info").attr("action", "update_info").submit();
	}
 }
 

/*
** 닉네임 중복확인 화면 출력요청
*/
function nickcheck() {
	if ($("#nickname").val() == "") {
		alert("닉네임을 입력해 주세요!");
		$("#nickname").focus();
		return false;
	}
	
	// 닉네임 중복확인 창 오픈
	var url = "nickname_check_form?nickname="+$("#nickname").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=350, height=200");
}
 
 
 /**
  * 바디데이터 수정
  */
 function body_save(){
	if($("#height").val() == ""){
		alert("키를 입력해주세요.");
		$("#height").focus();
		return false;
	} else if($("#weight").val() == ""){
		alert("몸무게를 입력해주세요.");
		$("#weight").focus();
		return false;
	} else{
		$("#update_body").attr("action", "update_body").submit();
	}
 }
 
 
 /**
  * 음식 선호도 및 알레르기
  */
 function food_choice(){
	// 수정 필요
	$("#food_choice").attr("action", "food_choice").submit()
 }
 
 
 
