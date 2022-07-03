const viewOpen = document.getElementById('manager-pending-button');
const viewClosed = document.getElementById('manager-resolved-button');

// pending reimbursements table
const viewPending = () => {
    managerTable.innerHTML = "";
    createPendingHeader();
    fetch('http://localhost:8080/employee-servlet-app/reimbursements?status=open', {
        method: 'GET'
    }).then(function (response) {
        console.log(response);
        if (!response.ok) {
            throw Error("Error retreiving reimbursements from DB")
        }
        return response.json();
    }).then(function (data) {
        console.log(data);
        data.forEach(obj => {
            let newReimbursement = {
                id: obj.id,
                type: obj.type.type,
                status: obj.status.status,
                time: obj.timeSubmitted,
                description: obj.description,
                amount: obj.reimbursementAmt,
                user: obj.employee.username
            }
            console.log(newReimbursement);
            createPendingRow(newReimbursement);
        });
    })
    managerTable.style.visibility = 'visible';
}

const createPendingHeader = () => {

    let tags = ['ID', 'Type', 'Description', 'Amount', 'Created by', 'Time stamp']
    let tr = document.createElement('thead');
    tr.classList.add("bg-primary");
    tags.forEach(obj => {
        let header = document.createElement('th');
        let spacing = document.createElement('div');
        spacing.classList.add('spacing');
        let text = document.createTextNode(obj);
        spacing.appendChild(text);
        header.appendChild(spacing);
        tr.appendChild(header);
    })

    managerTable.appendChild(tr);
}

const createPendingRow = (newReimbursement) => {
    if (newReimbursement.status != 'pending') {
        console.log(newReimbursement.status + 'is not pending');
        return;
    }

    let row = document.createElement('tr');
    // decide the color of the row based on the type
    switch (newReimbursement.type) {
        case 'lodging': {
            row.classList.add('bg-success')
            break;
        }
        case 'travel': {
            row.classList.add('bg-warning')
            break;
        }
        case 'food': {
            row.classList.add('bg-info')
            break;
        }
        case 'other': {
            row.classList.add('bg-danger')
            break;
        }
    }

    let spacing = document.createElement('div');
    spacing.classList.add('spacing');
    let text = document.createTextNode(newReimbursement.id);
    spacing.appendChild(text);
    let idCell = document.createElement('td');
    idCell.appendChild(spacing);
    row.appendChild(idCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.type);
    spacing.appendChild(text);
    let typeCell = document.createElement('td');
    typeCell.appendChild(spacing);
    row.appendChild(typeCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.description);
    spacing.appendChild(text);
    let descriptionCell = document.createElement('td');
    descriptionCell.appendChild(spacing);
    row.appendChild(descriptionCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.amount);
    spacing.appendChild(text);
    let amountCell = document.createElement('td');
    amountCell.appendChild(spacing);
    row.appendChild(amountCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.user);
    spacing.appendChild(text);
    let userCell = document.createElement('td');
    userCell.appendChild(spacing);
    row.appendChild(userCell);

    // get the time
    let date = new Date(newReimbursement.time);
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let formatedTime = date.getMonth() + '/' + date.getDay() + '/' + date.getFullYear() + ' At: ' + hours + ':' + minutes;

    // put the time in the cell
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(formatedTime);
    spacing.appendChild(text);
    let timeCell = document.createElement('td');
    timeCell.appendChild(spacing);
    row.appendChild(timeCell);

    managerTable.appendChild(row);
}

// closed 
const viewClosedReimbursements = () => {
    managerTable.innerHTML = "";
    createClosedHeader();
    fetch('http://localhost:8080/employee-servlet-app/reimbursements', {
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (response) {
        if (!response.ok) {
            throw Error("Error retreiving reimbursements from DB")
        }
        return response.json();
    }).then(function (data) {
        console.log(data);
        data.forEach(obj => {
            let newReimbursement = {
                id: obj.id,
                type: obj.type.type,
                status: obj.status.status,
                time: obj.timeSubmitted,
                description: obj.description,
                amount: obj.reimbursementAmt,
                user: obj.employee.username,
                manager: obj.managerId.username,
                closeTime: obj.timeResolved
            }
            console.log(newReimbursement);
            createclosedRow(newReimbursement);
        });
    })
    managerTable.style.visibility = 'visible';
}

const createClosedHeader = () => {

    let tags = ['ID', 'Type', 'Description', 'Amount', 'Created By', 'Time stamp', 'Manager', 'Closed stamp']
    let tr = document.createElement('thead');
    tr.classList.add("bg-primary");
    tags.forEach(obj => {
        let header = document.createElement('th');
        let spacing = document.createElement('div');
        spacing.classList.add('spacing');
        let text = document.createTextNode(obj);
        spacing.appendChild(text);
        header.appendChild(spacing);
        tr.appendChild(header);
    })

    managerTable.appendChild(tr);
}

const createclosedRow = (newReimbursement) => {
    if (newReimbursement.status == 'pending') {
        console.log(newReimbursement.id + 'is pending');
        return;
    }

    let row = document.createElement('tr');
    // decide the color of the row based on the type
    switch (newReimbursement.status) {
        case 'approved': {
            row.classList.add('bg-success')
            break;
        }
        case 'rejected': {
            row.classList.add('bg-danger')
            break;
        }
    }

    let spacing = document.createElement('div');
    spacing.classList.add('spacing');
    let text = document.createTextNode(newReimbursement.id);
    spacing.appendChild(text);
    let idCell = document.createElement('td');
    idCell.appendChild(spacing);
    row.appendChild(idCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.type);
    spacing.appendChild(text);
    let typeCell = document.createElement('td');
    typeCell.appendChild(spacing);
    row.appendChild(typeCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.description);
    spacing.appendChild(text);
    let descriptionCell = document.createElement('td');
    descriptionCell.appendChild(spacing);
    row.appendChild(descriptionCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.amount);
    spacing.appendChild(text);
    let amountCell = document.createElement('td');
    amountCell.appendChild(spacing);
    row.appendChild(amountCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.user);
    spacing.appendChild(text);
    let userCell = document.createElement('td');
    userCell.appendChild(spacing);
    row.appendChild(userCell);

    // get the time
    let date = new Date(newReimbursement.time);
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let formatedTime = date.getMonth() + '/' + date.getDay() + '/' + date.getFullYear() + ' At: ' + hours + ':' + minutes;

    // put the time in the cell
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(formatedTime);
    spacing.appendChild(text);
    let timeCell = document.createElement('td');
    timeCell.appendChild(spacing);
    row.appendChild(timeCell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.manager);
    spacing.appendChild(text);
    let managerCell = document.createElement('td');
    managerCell.appendChild(spacing);
    row.appendChild(managerCell);

    // get the time
    date = new Date(newReimbursement.closeTime);
    hours = date.getHours();
    minutes = date.getMinutes();
    formatedTime = date.getMonth() + '/' + date.getDay() + '/' + date.getFullYear() + ' At: ' + hours + ':' + minutes;

    // put it in the cell
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(formatedTime);
    spacing.appendChild(text);
    let closedCell = document.createElement('td');
    closedCell.appendChild(spacing);
    row.appendChild(closedCell);

    managerTable.appendChild(row);
}


viewOpen.addEventListener('click', viewPending);
viewClosed.addEventListener('click', viewClosedReimbursements);