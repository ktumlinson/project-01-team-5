// get the buttons
const newReimbursement = document.getElementById('newReimbursement');
const updateUser = document.getElementById('updateUser');
const viewResolved = document.getElementById('viewResolved');
const cancelReimbursement = document.getElementById('cancelRequest');
const cancelUpdate = document.getElementById('cancelUpdate');
const cancelView = document.getElementById('cancelView');

// get modals
const background = document.getElementById('background');
const reimbursementMenu = document.getElementById('reimbursementMenu');
const updatInfoMenu = document.getElementById('updateMenu');
const viewInfoMenu = document.getElementById('userInfo');


const toggleBackground = () => {
    background.classList.toggle('visible');
}

const toggleReimbursement = () => {
    reimbursementMenu.classList.toggle('visible');
    toggleBackground();
}

const toggleUpdateUser = () => {
    let firstName = document.getElementById('update-firstname');
    let lastName = document.getElementById('update-lastname');
    let password = document.getElementById('update-password');
    let email = document.getElementById('update-email');
    if (!updatInfoMenu.classList.contains('visible')) {
        fetch('http://ec2-3-93-20-196.compute-1.amazonaws.com:8080/employee-servlet-app/employees/info', {
            method: 'GET',
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (response) {
            if (!response.ok) {
                throw Error("Unable to pull info from DB");
            }
            return response.json();
        }).then(function (data) {
            console.log(data);
            firstName.value = data.firstname;
            lastName.value = data.lastname;
            password.value = data.password;
            email.value = data.email;
        })
    }
    updatInfoMenu.classList.toggle('visible');
    toggleBackground();
}

const toggleViewUser = () => {
    let firstName = document.getElementById('view-first');
    let lastName = document.getElementById('view-last');
    let password = document.getElementById('view-pass');
    let email = document.getElementById('view-email');

    if (!viewInfoMenu.classList.contains('visible')) {
        fetch('http://ec2-3-93-20-196.compute-1.amazonaws.com:8080/employee-servlet-app/employees/info', {
            method: 'GET',
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (response) {
            if (!response.ok) {
                throw Error("Unable to pull info from DB");
            }
            return response.json();
        }).then(function (data) {
            console.log(data);
            firstName.value = data.firstname;
            lastName.value = data.lastname;
            password.value = data.password;
            email.value = data.email;
        })
    }

    viewInfoMenu.classList.toggle('visible');
    toggleBackground();
}

const cancelReimbursementFunction = () => {
    document.getElementById('warning-text').innerHTML = '';
    toggleReimbursement();
}

newReimbursement.addEventListener('click', toggleReimbursement);
cancelReimbursement.addEventListener('click', cancelReimbursementFunction);

updateUser.addEventListener('click', toggleUpdateUser);
cancelUpdate.addEventListener('click', toggleUpdateUser);

viewInfo.addEventListener('click', toggleViewUser);
cancelView.addEventListener('click', toggleViewUser);