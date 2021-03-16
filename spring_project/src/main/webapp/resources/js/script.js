/**
 * password and phone number verify 
 */

function checkJoinForm(){
	var pw = joinForm.pw.value;
	var confirm_pw = joinForm.confirm_pw.value;
	var tel = joinForm.phone_number1.value + joinForm.phone_number2.value + joinForm.phone_number3.value;

	if(joinForm.id.value == ""){
		alert("아이디를 입력해주세요 !!");
		joinForm.id.focus();
	} else if(joinForm.nickname.value == ""){
		alert("닉네임을 입력해주세요 !!");
		joinForm.nickname.focus();
	} else if(joinForm.pw.value == ""){
		alert("패스워드를 입력해주세요 !!");
		joinForm.pw.focus();
	} else if(joinForm.user_email1.value == ""){
		alert("이메일을 입력해주세요 !!");
		joinForm.user_email1.focus();
	} else{
		var regex = /^[0-9]+$/;
		
		if(joinForm.phone_number2.value.length != 4 ||
				joinForm.phone_number3.value.length != 4){
			alert("연락처 형식이 잘못되었습니다 !!");
			joinForm.phone_number2.value = "";
			joinForm.phone_number3.value = "";
			joinForm.phone_number1.focus();
		}
		else if(!regex.test(tel)){
			alert("연락처는 숫자만 입력하셔야 합니다.")
			joinForm.phone_number1.focus();
		}
		else if(pw != confirm_pw){
			alert("두 비밀번호가 일치하지 않습니다.");
			joinForm.pw.value = "";
			joinForm.confirm_pw.value = "";
			joinForm.pw.focus();
		}else{
			joinForm.submit();
		}
	}
}
