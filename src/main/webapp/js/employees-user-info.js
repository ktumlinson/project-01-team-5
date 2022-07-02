const update = document.getElementById('submitUpdate');

const updateUserInfo = () =>{
    let firstName = document.getElementsByName('update-firstname').value;
    let lastName = document.getElementsByName('update-lastname').value;
    let password = document.getElementsByName('update-password').value;
    let email = document.getElementsByName('update-email').value;
    
    if(!checkEmptyFields){
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

}

update.addEventListener('click', updateUserInfo);