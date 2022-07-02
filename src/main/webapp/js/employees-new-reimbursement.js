const submitReimbursement = document.getElementById('submitRequest');

const handleSubmitReimbursement = () =>{
    let userId = sessionStorage.getItem('the-user');
    let amount = getElementByName('amount').value;
    let description = getElementByName('description').value;
    let type = getElementByName('type').value;
    if(!findUnansweredField()){
        fetch('http://localhost:8080/employee-servlet-app/reimbursements',{
            method: 'POST',
            headers: {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify({
                amount: amount,
                description: description,
                type: type
            })
        }).then(function(response){
            if(!response.ok){
                throw Error("unable to push request");
            }
            return response.json();
        }).then(function(data){
            console.log(data);
        })
    } else{
        console.log('Could not upload, need more information');
    }
}

const findUnansweredField = () =>{
    if(userId == null){
        return true;
    }
    if(amount <= 0){
        return true;
    }
    if(description == '' || description == 'What is the reimbursement for?'){
        return true;
    }
    return false;
}

submitReimbursement.addEventListener('click', handleSubmitReimbursement);