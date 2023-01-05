function validateLoginForm() {

    var error = "";
    var filter = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var email = document.getElementById('email').value;
    var pass = document.getElementById('password').value;

    if (email.trim() === "") {
        error += "Email can't be blank! \n";
    } else {
        if (!email.match(filter)) {
            error += "Invalid Email address! \n";
        }
    }
    if (pass.trim() === "") {
        error += "Password can't be blank! \n";
    }
    if (error === "") {
        return true;
    } else {
        alert(error);
        return false;
    }

}

function validateForm() {

    var error = "";
    var filter = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var num = /\D+$/;
    var email = document.getElementById('reEmail').value;
    var pass = document.getElementById('rePass').value;
    var conPass = document.getElementById('reConPass').value;
    var name = document.getElementById('reName').value;


    if (email.trim() === "") {
        error += "Email can't be blank! \n";
    } else {
        if (!email.match(filter)) {
            error += "Invalid Email address! \n";
        }
    }

    if (pass.trim() === "") {
        error += "Password can't be blank! \n";
    }

    if (conPass.trim() === "") {
        error += "Confirm can't be blank! \n";
    } else {
        if (!conPass.match(pass) || !pass.match(conPass))
        {
            error += "Password not match! \n";
        }
    }

    if (name.trim() === "") {
        error += "Name can't be blank! \n";
    } else {
        if (!name.match(num)) {
            error += "Name can't have a number! \n";
        }
    }
    if (error === "") {
        alert("Register Success!");
        return true;
    } else {
        alert(error);
        return false;
    }
}

