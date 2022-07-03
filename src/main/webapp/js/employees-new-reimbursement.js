const submitReimbursement = document.getElementById('sumbitRequest');
let amount = document.getElementById('amount');
let description = document.getElementById('amount');
let type = document.getElementById('amount');

const handleSubmitReimbursement = () =>{
    amount = document.getElementsByName('amount').value;
    description = document.getElementsByName('description').value;
    type = document.getElementsByName('type').value;
    console.log('amount');
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
    if(amount <= 0){
        return true;
    }
    if(description == '' || description == 'What is the reimbursement for?'){
        return true;
    }
    return false;
}

submitReimbursement.addEventListener('click', handleSubmitReimbursement);