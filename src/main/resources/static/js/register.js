const registerButton = document.querySelector(".login-button");
const registerInputs = document.querySelectorAll(".login-input");

for(let i = 0; i < registerInputs.length; i++) {
    registerInputs[i].onkeyup = () => {
        if(window.event.keyCode == 13){
            if(i != 3) {
                registerInputs[i + 1].focus();
            }else {
                registerButton.click();
            }
        }
    }
}


registerButton.onclick = () => {
    let registerInfo = {
        lastName: registerInputs[0].value,
        firstName: registerInputs[1].value,
        email: registerInputs[2].value,
        password:  registerInputs[3].value
    }

    $.ajax({
        async: false,
        type: "post",
        url: "/api/account/register",
        contentType: "application/json",
        data: JSON.stringify(registerInfo), // obj -> json
        dataType: "json",
        success: (response) => {
            // console.log(response);
            location.replace("/account/login"); //이전기록 날려야함.
        },
        error: (error) => {
            console.log(error);
            validationError(error.responseJSON.data);
        }
    })
}

function validationError(error){
    const accountErrors = document.querySelector(".account-errors");
    const accountErrorList = accountErrors.querySelector("ul");

    const errorValues = Object.values(error); // list로 가지고 온다.
    //values는 key랑 value 중 value만 리스트 형태로 가져오는 것 

    accountErrorList.innerHTML = "";

    errorValues.forEach((value) => {
        accountErrorList.innerHTML += `
            <li>${value}</li>
        `;
    });

    accountErrors.classList.remove("errors-invisible");
}