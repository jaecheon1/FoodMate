/**
 * 마이페이지 스크립트
 */

 /**
  * 개인정보 수정 (닉네임, 비밀번호, 이메일) 
  */
 function info_save(){
	if($("#pwd").val() == ""){
		alert("변경할 비밀번호를 입력하세요.");
		$("#pwd").focus();
		return false;
	} else if($("#pwd").val() != $("#pwdcheck").val()){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwd").focus();
		return false;
	} else if($("#name").val() == ""){
		alert("변경할 이름을 입력하세요.");
		$("#name").focus();
		return false;
	} else if($("#email").val() == ""){
		alert("변경할 이메일을 입력하세요.");
		$("#email").focus();
		return false;
	} else if($("#height").val() == ""){
		alert("키를 입력해 주세요.");
		$("#height").focus();
		return false;
	} else if($("#weight").val() == ""){
		alert("몸무게를 입력해 주세요.");
		$("#weight").focus();
		return false;
	} else if($("#age").val() == ""){
		alert("나이를 입력해 주세요.");
		$("#age").focus();
		return false;
	} else {
		$("#update_info").attr("action", "update_info").submit();
	}
 }
 

/*
** bmi 계산 버튼
*/
function my_bmi() {
    var height = document.getElementById('height').value;
    var weight = document.getElementById('weight').value;
    if (height > 0 && weight > 0) {
        var bmi = weight / ((height / 100) * (height / 100));
        document.getElementById('bmi').value = bmi.toFixed(2);
    } else {
        alert('키와 몸무게를 올바르게 입력하세요.');
    }
}
 
 
 
 /**
  * 음식 선호도 및 알레르기
  */
 function food_choice(){
	// 수정 필요
	$("#food_choice").attr("action", "food_choice").submit()
 }
 
 
 
 // main.js
function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 