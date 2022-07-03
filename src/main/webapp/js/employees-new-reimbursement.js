const submitReimbursement = document.getElementById('sumbitRequest');
const warningText = document.getElementById('warning-text');
let amount = document.getElementById('amount');
let description = document.getElementById('description');
let type = document.getElementById('type');

const handleSubmitReimbursement = () => {
    warningText.innerHTML = "";
    amount = document.getElementById('amount').value;
    description = document.getElementById('description').value;
    type = document.getElementById('type').value;
    console.log('amount');
    if (!findUnansweredField()) {
        fetch('http://localhost:8080/employee-servlet-app/reimbursements', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                amount: amount,
                description: description,
                type: type
            })
        }).then(function (response) {
            if (!response.ok) {
                throw Error("unable to push request");
            }
            return response.json();
        }).then(function (data) {
            console.log(data);
            warningText.innerHTML = `<p><b>Successfully created new reimbursement with id ${data.id}</b></p>`;
            clearFormFields();
        })
    } else {
        console.log('Could not upload, need more information');
    }
}

const clearFormFields = () => {
    document.getElementById('amount').value = '';
    document.getElementById('description').value = '';
    document.getElementById('type').selectedIndex = 0;
}

const findUnansweredField = () => {
    if (amount <= 0) {
        warningText.classList.add('bg-info');
        warningText.innerHTML = `<p style"color: red><b>Unable to process request 'Amount' is invalid</b></p>"`
        return true;
    }
    if (description == '' || description == 'What is the reimbursement for?') {
        warningText.classList.add('bg-info');
        warningText.innerHTML = `<p style"color: red><b>Unable to process request 'desciption' is invalid</b></p>"`
        return true;
    }
    if (!isValid(type)) {
        warningText.classList.add('bg-info');
        warningText.innerHTML = `<p style"color: red><b>Unable to process request 'type' is invalid make sure it is one of the following: "food", "lodging", "travel", or "other" </b></p>"`
        return true;
    }
    return false;
}

const isValid = (type) => {
    switch (type) {
        case 'food': {
            return true;
        }
        case 'lodging': {
            return true;
        }
        case 'travel': {
            return true;
        }
        case 'other': {
            return true;
        }
        default: {
            return false;
        }
    }
}

submitReimbursement.addEventListener('click', handleSubmitReimbursement);