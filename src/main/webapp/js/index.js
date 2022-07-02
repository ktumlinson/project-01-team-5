
// get the buttons
const managerLogin = document.getElementById('manager-login');
const employeeLogin = document.getElementById('employee-login');
const register = document.getElementById('register');
const login = document.getElementById('manager-login-button');
const cancel = document.getElementById('manager-cancel-button');
const emplogin = document.getElementById('employee-login-button');
const empcancel = document.getElementById('employee-cancel-button');
const completeregister = document.getElementById('complete-registration');
const regcancel = document.getElementById('registration-cancel-button');
    
// get the modals
const background = document.getElementById('background');
const managerModal = document.getElementById('managerModal');
const employeeModal = document.getElementById('employeeModal');
const registerModal = document.getElementById('registerModal');

const toggleBackground = () => {
    background.classList.toggle('visible');
}

const toggleManagerLogin = () =>{
    managerModal.classList.toggle('visible');
    toggleBackground();
}

const toggleEmployeeLogin = () =>{
    employeeModal.classList.toggle('visible');
    toggleBackground();
}

const toggleRegister = () =>{
    registerModal.classList.toggle('visible');
    toggleBackground();
}

const managerAccountLogin = (ev) =>{
    ev.preventDefault();

    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    console.log(username);
    console.log(password);

    fetch('http://localhost:8080/employee-servlet-app/manager-login',{
        // make a post request to try to login with the given information
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    }).then(function (response) {
        if(!response.ok){
            throw Error("Error");
        }

        let childDiv = document.getElementById('warningText');
        childDiv.innerHTML = '<p style="color:red"><b>Username or Password is incorrect<b></p>'

        return response.json;
    }).then(function(data){
        console.log(data);

        if(data.id > 0){
            console.log('Success');
            // set the current user to be the ID of the manager returned
            sessionStorage.setItem('the-user', data.id);
    
            // go to the managers landing page
            window.location.href = 'http://localhost:8080/employee-servlet-app/managers.html';
        } else{
            let childDiv = document.getElementById('warningText');
            childDiv.innerHTML = '<p style="color:red"><b>Username or Password is incorrect<b></p>'
        }
    }).catch(error => {
        console.log(error);
    })
}

const employeeAccountLogin = (ev) =>{
    ev.preventDefault();

    let username = document.getElementById('employee-username').value;
    let password = document.getElementById('employee-password').value;
    console.log(username);
    console.log(password);
    fetch('http://localhost:8080/employee-servlet-app/employee-login',{
        // make a post request to try to login with the given information
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    }).then(function (response) {
        if(!response.ok){
            throw Error("Error");
        }
        let childDiv = document.getElementById('warningText');
        childDiv.innerHTML = '<p style="color:red"><b>Username or Password is incorrect<b></p>'

        return response.json;
    }).then(function(data){
        console.log(data);

        if(data.id > 0){
            console.log('Success');
            // set the current user to be the ID of the manager returned
            sessionStorage.setItem('the-user', data.id);
    
            // go to the employees landing page
            window.location.href = 'http://localhost:8080/employee-servlet-app/employees.html';
        } else{
            let childDiv = document.getElementById('warningText');
            childDiv.innerHTML = '<p style="color:red"><b>Username or Password is incorrect<b></p>'
        }
    }).catch(error => {
        console.log(error);
    })
}

const registerEmployee = () =>{
    
}

managerLogin.addEventListener('click', toggleManagerLogin);
cancel.addEventListener('click', toggleManagerLogin);
//login.addEventListener('click', managerAccountLogin);

employeeLogin.addEventListener('click', toggleEmployeeLogin);
empcancel.addEventListener('click', toggleEmployeeLogin);
//emplogin.addEventListener('click', employeeAccountLogin);

/*
register.addEventListener('click', toggleRegister);
regcancel.addEventListener('click', toggleRegister);
completeregister.addEventListener('click', registerEmployee);
*/