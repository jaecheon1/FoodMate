document.addEventListener("DOMContentLoaded", function() {
    // 사용자의 BMI 가져오기
    var userBMI = parseFloat(document.getElementById('userBMI').innerText);
    var userName = document.getElementById("userName").innerText;
    
    // 막대의 각 칸을 나타내는 요소들 가져오기
    var underweightBar = document.getElementById('underweight');
    var normalBar = document.getElementById('normal');
    var overweightBar = document.getElementById('overweight');
    var obeseBar = document.getElementById('obese');
    var severelyObeseBar = document.getElementById('severelyObese');

    // 사용자의 BMI에 따라 해당하는 칸에 표시 추가
   if (userBMI < 18.5) {
    underweightBar.innerHTML += userName;
} else if (userBMI < 23) {
    normalBar.innerHTML += userName;
} else if (userBMI < 25) {
    overweightBar.innerHTML += userName;
} else if (userBMI < 30) {
    obeseBar.innerHTML += userName;
} else {
    severelyObeseBar.innerHTML +=  userName;
}

});
