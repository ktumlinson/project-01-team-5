
// get the buttons
const managerLogin = document.getElementById('manager-login');
const employeeLogin = document.getElementById('employee-login');
const login = document.getElementById('manager-login-button');
const cancel = document.getElementById('manager-cancel-button');
const emplogin = document.getElementById('employee-login-button');
const empcancel = document.getElementById('employee-cancel-button');
    
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

    fetch('http://localhost:8080/employee-servlet-app/LoginManagers',{
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
            throw Error("Unable to retreive manager");
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
            sessionStoreage.setItem('the-man', data.id);
    
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
    console.log('started');
    ev.preventDefault();
    let username = document.getElementById('employee-username').value;
    let password = document.getElementById('employee-password').value;
    console.log(username);
    console.log(password);
    let request = {
        username: username,
        password: password
    }
    fetch('http://localhost:8080/employee-servlet-app/LoginEmployees',{
        // make a post request to try to login with the given information
        method: 'POST',
        body: JSON.stringify({request}),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(json => console.log('request: ', request, json))
    
    .then(function (response) {
        if(!response.ok){
            throw Error("Unable to find employee");
        }
        let childDiv = document.getElementById('warningText');
        childDiv.innerHTML = '<p style="color:red"><b>Username or Password is incorrect<b></p>'

        return response.json;
    })
    
    .then(function(data){
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
    })
    .catch(error => {
        console.log(error);
    })
}


managerLogin.addEventListener('click', toggleManagerLogin);
cancel.addEventListener('click', toggleManagerLogin);

employeeLogin.addEventListener('click', toggleEmployeeLogin);
empcancel.addEventListener('click', toggleEmployeeLogin);