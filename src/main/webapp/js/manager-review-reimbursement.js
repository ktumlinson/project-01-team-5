const submitReview = document.getElementById('submitReview');
const approveReimbursement = document.getElementById('approve');
const denyReimbursement = document.getElementById('deny');
const reimbID = document.getElementById('reimb-number');
let reimbId = 0;
let reimbursementToApprove = {id: 0, status: 'pending', amount: 0, description: '', user: '', type: ''};

const reviewTable = document.getElementById('review-table');

const findReimbursementById = () => {
    let reimbId = reimbID.value;
    console.log(reimbId);
    createHeader();
    fetch(`http://localhost:8080/employee-servlet-app/reimbursements/${reimbId}`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (response) {
        if (!response.ok) {
            throw Error(`Error retreiving reimbursement ${reimbId}`);
        }
        return response.json();
    }).then(function (data) {
        console.log(data);
        reimbursementToApprove.id = data.id;
        reimbursementToApprove.status = data.status.status;
        reimbursementToApprove.amount = data.reimbursementAmt;
        reimbursementToApprove.description = data.description;
        reimbursementToApprove.user = data.employee.username;
        reimbursementToApprove.type = data.type.type;
        createRow(reimbursementToApprove);
    })
    reviewTable.style.visibility = 'visible';
    approveReimbursement.style.visibility = 'visible';
    denyReimbursement.style.visibility = 'visible';
    return reimbursementToApprove;
}

const createHeader = () =>{
    console.log('creating header');
    const tags = ['ID', 'Status', 'Amount', 'Description', 'Type', 'User']
    let tr = document.createElement('tr');
    tr.classList.add('bg-primary');
    tags.forEach(obj => {
        let spacing = document.createElement('div');
        spacing.classList.add('spacing');
        let text = document.createTextNode(obj);
        let header = document.createElement('th');
        spacing.appendChild(text);
        header.appendChild(spacing);
        tr.appendChild(header);
    })
    reviewTable.appendChild(tr);
}

const createRow = (reimbursementToApprove) =>{
    //const tags = ['ID', 'Status', 'Amount', 'Description', 'Type', 'User']
    let Row = document.createElement('tr');
    if(reimbursementToApprove.type == 'pending'){
        Row.classList.add('bg-warning');
    } else{
        Row.classList.add('bg-info');
    }

    let spacing = document.createElement('div');
    let text = document.createTextNode(reimbursementToApprove.id);
    spacing.classList.add('spacing');
    let cell = document.createElement('td');
    spacing.appendChild(text);
    cell.appendChild(spacing);
    Row.appendChild(cell);

    spacing = document.createElement('div');
    text = document.createTextNode(reimbursementToApprove.status);
    spacing.classList.add('spacing');
    cell = document.createElement('td');
    spacing.appendChild(text);
    cell.appendChild(spacing);
    Row.appendChild(cell);

    spacing = document.createElement('div');
    text = document.createTextNode(reimbursementToApprove.amount);
    spacing.classList.add('spacing');
    cell = document.createElement('td');
    spacing.appendChild(text);
    cell.appendChild(spacing);
    Row.appendChild(cell);

    spacing = document.createElement('div');
    text = document.createTextNode(reimbursementToApprove.description);
    spacing.classList.add('spacing');
    cell = document.createElement('td');
    spacing.appendChild(text);
    cell.appendChild(spacing);
    Row.appendChild(cell);

    spacing = document.createElement('div');
    text = document.createTextNode(reimbursementToApprove.type);
    spacing.classList.add('spacing');
    cell = document.createElement('td');
    spacing.appendChild(text);
    cell.appendChild(spacing);
    Row.appendChild(cell);

    reviewTable.appendChild(Row);
}

const handleApproveReimbursement = (reimbursementToApprove) => {
    console.log('started handle approve')
    reimbursementToApprove.status = '2';
    pushReimbursementChange(reimbursementToApprove);
}

const handleDenyReimbursement = (reimbursementToApprove) => {
    reimbursementToApprove.status = '3';
    pushReimbursementChange(reimbursementToApprove);
}

const pushReimbursementChange = (reimbursementToApprove) => {
    fetch(`http://localhost:8080/employee-servlet-app/reimbursements/${reimbId}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            status: reimbursementToApprove.status
        })
    }).then(function (response) {
        if(!response.ok){
            throw Error("Unable to push the change");
        }
        return response.json();
    }).then(function (json) {
        console.log(json);
    })
}

submitReview.addEventListener('click', findReimbursementById);
approveReimbursement.addEventListener('click', function() {handleApproveReimbursement(reimbursementToApprove)});
denyReimbursement.addEventListener('click', function() {handleDenyReimbursement(reimbursementToApprove)});