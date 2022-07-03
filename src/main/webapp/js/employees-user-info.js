const update = document.getElementById('submitUpdate');

let firstName = document.getElementsByName('update-firstname');
let lastName = document.getElementsByName('update-lastname');
let password = document.getElementsByName('update-password');
let email = document.getElementsByName('update-email');

const updateUserInfo = () =>{
    firstName = document.getElementsByName('update-firstname').value;
    lastName = document.getElementsByName('update-lastname').value;
    password = document.getElementsByName('update-password').value;
    email = document.getElementsByName('update-email').value;
    
    if(!checkEmptyFields()){
        fetch('http://localhost:8080/employee-servlet-app/employees/update',{
            method: 'POST',
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                firstname: firstName,
                lastname: lastName,
                password: password,
                email: email
            })
        }).then(function(response){
            if(!response.ok){
                throw Error("Unable to update user information");
            }
            return response.json();
        }).then(function(data){
            console.log(data);
        })
    } else{
        console.log('Information is missing in the update')
    }
    
}

const checkEmptyFields = () =>{
    console.log('checking cells');
    if(firstName == ''){
        console.log('firstname');
        return true;
    }
    if(lastName == ''){
        console.log('lastname');
        return true;
    }
    if(password == ''){
        console.log('password');
        return true;
    }
    if(email == ''){ 
        console.log('email');
        return true;
    }
    console.log('returning false');
    return false;
}

update.addEventListener('click', updateUserInfo);