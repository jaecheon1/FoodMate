
/*
** 약관 동의 여부 확인
*/
function go_next() {
	if ($(".agree")[0].checked == true) {  // 동의함이 체크됨
		$("#join").attr("action", "join_form").submit();  // 서버로 URL 전송
	} else if($(".agree")[1].checked == true) {
		alert("약관에 동의하셔야 가입할 수 있습니다.")
	}
}

/*
 * 회원 가입시, 필수 입력 항목 확인
 */
function go_save() {
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요");
		$("#id").focus();
		return false;
	} else if($("#id").val() != $("#reid").val()) {
		alert("아이디 중복 체크를 해주세요.");
		$("#id").focus();
		return false;
	} else if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		$("#pwd").focus();
		return false;
	} else if($("#pwd").val() != $("#pwdCheck").val()) {
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwd").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("이름을 입력해 주세요.");
		return false;
	} else if ($("#nickname").val() == "") {
		alert("닉네임을 입력해 주세요 ");
		return false;
	} else if ($("#email").val() == "") {
		alert("이메일을 입력해 주세요.");
		return false;
	} else {
		// 회원 가입 요청
		$("#join").attr("action", "join").submit();
	}
}
    function validateLoginForm() {
        var id = document.getElementById("id").value.trim();
        var password = document.getElementById("password").value.trim();
        
        if (id === '' || password === '') {
            alert("아이디와 비밀번호를 입력해주세요.");
            return false;
        }
        return true;
    }

/*
** id 중복확인 화면 출력요청
*/
function idcheck() {
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#id").focus();
		return false;
	}
	
	// id 중복확인 창 오픈
	var url = "id_check_form?id="+$("#id").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=350, height=200");
}

    if ($('.username_input').val() == '') {
      alert('이메일을 입력해주세요.')
      return;
    }

/*
** 아이디, 비밀번호 찾기 화면 요청
*/
function find_id_form() {
	var url = "find_id_form";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
		"resizable=yes, width=550, height=450");
}


/*
** 아이디 찾기 요청
*/
function findMemberId() {
	if ($("#name").val() == "") {
		alert("이름을 입력해 주세요.");
		$("#name").focus();
		return false;
	} else if ($("#email").val() == "") {
		alert("이메일을 입력해 주세요.");
		$("#email").focus();
		return false;
	} else {
		var form = $("#findId");
		form.action = "find_id";  // 컨트롤러 요청 URL
		form.submit();  // 컨트롤러로 전송
	}
}

/*
** 비밀번호 찾기 요청
*/
function findPassword() {
	if ($("#id2").val() == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	} else if ($("#name2").val() == "") {
		alert("이름을 입력해 주세요.");
		return false;
	} else if ($("#email2").val() == "") {
		alert("이메일을 입력해 주세요.");
		return false;
	} else {
		$("#findPW").action = "find_pwd";  // 비밀번호 찾기 URL
		$("#findPW").submit();
	}
}

/*
** 비밀번호 변경
*/
function changePassword() {
	if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		$("#pwd").focus();
		return false;
	} else if ($("#pwd").val() != $("#pwdcheck").val()) {
		$("#pwd").focus();
		alert("비밀번호가 맞지 않습니다. 다시 입력해 주세요");
	} else {
		$("#pwd_form").action = "change_pwd";
		$("#pwd_form").submit();
	}
}

function validatePassword(password) {
 // 비밀번호가 6자 이상인지 확인
     if (password.length < 6) {
 return false;
  }
     // 비밀번호에 알파벳 대소문자가 포함되어 있는지 확인
   var hasLowerCase = /[a-z]/.test(password);
   var hasUpperCase = /[A-Z]/.test(password);
       if (!(hasLowerCase && hasUpperCase)) {
         return false;
}

   // 비밀번호에 숫자가 포함되어 있는지 확인
var hasNumber = /\d/.test(password);
if (!hasNumber) {
 return false;
 }

// 모든 조건을 만족하면 유효한 비밀번호로 간주
 return true;
}

 function validateForm() {
      var password = document.getElementById("password").value;
         if (!validatePassword(password)) {
          alert("비밀번호는 6자 이상이어야 하며, 대소문자와 숫자를 포함해야 합니다.");
          return false; // 폼 제출 중지
      }
        return true; // 폼 제출 진행
        }






