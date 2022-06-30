
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

managerLogin.addEventListener('click', toggleManagerLogin);
cancel.addEventListener('click', toggleManagerLogin);

employeeLogin.addEventListener('click', toggleEmployeeLogin);
empcancel.addEventListener('click', toggleEmployeeLogin);

register.addEventListener('click', toggleRegister);
regcancel.addEventListener('click', toggleRegister);