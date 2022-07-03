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

const toggleBackground = () =>{
    background.classList.toggle('visible');
}

const toggleReimbursement = () =>{
    reimbursementMenu.classList.toggle('visible');
    toggleBackground();
}

const toggleUpdateUser = () =>{
    let firstName = document.getElementsByName('update-firstname');
    let lastName = document.getElementsByName('update-lastname');
    let password = document.getElementsByName('update-password');
    let email = document.getElementsByName('update-email');
    if(!updatInfoMenu.classList.contains('visible')){
        fetch('http://localhost:8080/employee-servlet-app/employees/info',{
                method: 'GET',
                headers: {
                    "Content-Type":"application/json"
                }
            }).then(function(response) {
                if(!response.ok){
                    throw Error("Unable to pull info from DB");
                }
                return response.json();
            }).then(function(data){
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

const toggleViewUser = ()=>{
    let firstName = document.getElementById('view-first');
    let lastName = document.getElementById('view-last');
    let password = document.getElementById('view-pass');
    let email = document.getElementById('view-email');

    if(!viewInfoMenu.classList.contains('visible')){
        fetch('http://localhost:8080/employee-servlet-app/employees/info',{
            method: 'GET',
            headers: {
                "Content-Type":"application/json"
            }
        }).then(function(response) {
            if(!response.ok){
                throw Error("Unable to pull info from DB");
            }
            return response.json();
        }).then(function(data){
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

newReimbursement.addEventListener('click', toggleReimbursement);
cancelReimbursement.addEventListener('click', toggleReimbursement);

updateUser.addEventListener('click', toggleUpdateUser);
cancelUpdate.addEventListener('click', toggleUpdateUser);

viewInfo.addEventListener('click', toggleViewUser);
cancelView.addEventListener('click', toggleViewUser);