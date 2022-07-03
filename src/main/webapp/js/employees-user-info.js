const update = document.getElementById('submitUpdate');
const updateText = document.getElementById('update-text');

let firstName = document.getElementById('update-firstname');
let lastName = document.getElementById('update-lastname');
let password = document.getElementById('update-password');
let email = document.getElementById('update-email');

const updateUserInfo = () => {
    updateText.innerHTML = "";
    firstName = document.getElementById('update-firstname').value;
    lastName = document.getElementById('update-lastname').value;
    password = document.getElementById('update-password').value;
    email = document.getElementById('update-email').value;

    if (!checkEmptyFields()) {
        fetch('http://ec2-3-93-20-196.compute-1.amazonaws.com:8080/employee-servlet-app/employees/update', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                firstname: firstName,
                lastname: lastName,
                password: password,
                email: email
            })
        }).then(function (response) {
            if (!response.ok) {
                updateText.classList.add('bg-info');
                updateText.innerHTML = `<p style="color: red"><b>Unable to update your information</b></p>`
                throw Error("Unable to update user information");
            }
            return response.json();
        }).then(function (data) {
            console.log(data);
            updateText.classList.add('bg-info');
            updateText.innerHTML = `<p style="color: green"><b>Succesfully updated your information</b></p>`
        })
    } else {
        console.log('Information is missing in the update')
    }

}

const checkEmptyFields = () => {
    console.log('checking cells');
    if (firstName == '') {
        console.log('firstname');
        updateText.classList.add('bg-info');
        updateText.innerHTML = `<p style="color: red"><b>Unable to update your information first name is invalid</b></p>`;
        return true;
    }
    if (lastName == '') {
        console.log('lastname');
        updateText.classList.add('bg-info');
        updateText.innerHTML = `<p style="color: red"><b>Unable to update your information last name is invalid</b></p>`;
        return true;
    }
    if (password == '') {
        console.log('password');
        updateText.classList.add('bg-info');
        updateText.innerHTML = `<p style="color: red"><b>Unable to update your information password is invalid</b></p>`;
        return true;
    }
    if (email == '') {
        console.log('email');
        updateText.classList.add('bg-info');
        updateText.innerHTML = `<p style="color: red"><b>Unable to update your information email is invalid</b></p>`;
        return true;
    }
    console.log('returning false');
    return false;
}

update.addEventListener('click', updateUserInfo);