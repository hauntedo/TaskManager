document.addEventListener('DOMContentLoaded', () => {
    let password = document.querySelector("#password"),
        checkPassword = document.querySelector("#checkPassword");

    checkPassword.addEventListener('input', () => {
        checkConfirmPassword(password, checkPassword);
    });

    password.addEventListener('input', () => {
        checkConfirmPassword(password, checkPassword);
    });

    function checkConfirmPassword(password, checkPassword) {
        if (password.value !== checkPassword.value) {
            checkPassword.style.borderColor = 'red';
            document.querySelector(".btn").disabled = true;
        } else {
            checkPassword.style.borderColor = 'black';
            document.querySelector(".btn").disabled = false;
        }
    }
})