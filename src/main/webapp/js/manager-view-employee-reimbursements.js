const submitSearch = document.getElementById('submitSearch');
const empWarningText = document.getElementById('emp-warning-text')

const employeeTable = document.getElementById('employee-Reimbursements-table');

const getEmployeeReimbursements = () => {
    employeeTable.innerHTML = "";
    let employeeId = document.getElementById('username').value;
    empWarningText.innerHTML = "";
    employeeHeader();
    fetch(`http://ec2-3-93-20-196.compute-1.amazonaws.com:8080/employee-servlet-app/reimbursements?username=${employeeId}`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (response) {
        if (!response.ok) {
            empWarningText.classList.add('bg-info');
            empWarningText.innerHTML = `<p style="color: red"><b>Failed to retreive reimbursements! Are you logged in as a manager?</b></p>`
            throw Error('Error getting reimbursements from username ' + employeeId);
        }
        return response.json();
    }).then(function (data) {
        console.log(data);
        console.log(data.length);
        if (data.length == 0) {
            console.log('length is 0')
            empWarningText.classList.add('bg-info');
            empWarningText.innerHTML = `<p style="color: red"><b>Failed to retreive reimbursements! User may not exist in DB or has no reimbursements</b> <br>`
        }
        data.forEach(obj => {
            newReimbursement = {
                id: obj.id,
                status: obj.status.status,
                type: obj.type.type,
                description: obj.description,
                amount: obj.reimbursementAmt,
                manager: obj.managerId.username
            }
            employeeRow(newReimbursement);
        })
    }).catch(error => {
        console.warn(error);
        empWarningText.classList.add('bg-info');
        empWarningText.innerHTML = `<p style="color: white"><b>Failed to retreive reimbursements! Are you logged in as a manager?</b></p>`
    })
    employeeTable.style.visibility = 'visible';
}

const employeeHeader = () => {
    let tags = ['ID', 'Status', 'Type', 'Description', 'Amount', 'Manager']
    let tr = document.createElement('thead');
    tr.classList.add('bg-primary');
    tags.forEach(obj => {
        let header = document.createElement('th')
        let spacing = document.createElement('div');
        spacing.classList.add('spacing');
        let text = document.createTextNode(obj);
        spacing.appendChild(text);
        header.appendChild(spacing);
        tr.appendChild(header);
    })
    employeeTable.appendChild(tr);
}

const employeeRow = (newReimbursement) => {
    let row = document.createElement('tr');

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
    let cell = document.createElement('td');
    cell.appendChild(spacing);
    row.appendChild(cell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.status);
    spacing.appendChild(text);
    cell = document.createElement('td');
    cell.appendChild(spacing);
    row.appendChild(cell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.type);
    spacing.appendChild(text);
    cell = document.createElement('td');
    cell.appendChild(spacing);
    row.appendChild(cell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.description);
    spacing.appendChild(text);
    cell = document.createElement('td');
    cell.appendChild(spacing);
    row.appendChild(cell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.amount);
    spacing.appendChild(text);
    cell = document.createElement('td');
    cell.appendChild(spacing);
    row.appendChild(cell);

    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.manager);
    spacing.appendChild(text);
    cell = document.createElement('td');
    cell.appendChild(spacing);
    row.appendChild(cell);

    employeeTable.appendChild(row);
}

submitSearch.addEventListener('click', getEmployeeReimbursements);