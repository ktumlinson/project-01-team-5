const submitSearch = document.getElementById('submitSearch');

const employeeTable = document.getElementById('employee-Reimbursements-table');

const getEmployeeReimbursements = () =>{
    employeeTable.innerHTML = "";
    sessionStorage.setItem('the-user', document.getElementById('username').value);
    let employeeId = sessionStorage.getItem('the-user');
    employeeHeader();
    fetch(`http://localhost:8080/reimbursements?username=${employeeId}`,{
        method: 'GET',
        headers:{
            "Content-Type" : "application/json"
        }
    }).then(function(response){
        if(!response.ok){
            throw Error('Error getting reimbursements from username ' + employeeId);
        }
        return response.json();
    }).then(function(data){
        console.log(data);
        data.forEach(obj=>{
            newReimbursement = {
                id: obj.id,
                status: obj.status.status,
                type: obj.type.type,
                description: obj.description,
                amount: obj.reimbursementAmt
            }
            employeeRow(newReimbursement);
        })
    })
    employeeTable.style.visibility = 'visible';
}

const employeeHeader = () =>{
    let tags = ['ID', 'Status', 'Type', 'Description', 'Amount']
    let tr = document.createElement('thead');
    tr.classList.add('bg-primary');
    tags.forEach(obj =>{
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

const employeeRow = (newReimbursement) =>{
    let row = document.createElement('tr');

    switch(newReimbursement.type){
        case 'lodging':{
            row.classList.add('bg-success')
            break;
        }
        case 'travel':{
            row.classList.add('bg-warning')
            break;
        }
        case 'food' :{
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

    employeeTable.appendChild(row);
}

submitSearch.addEventListener('click', getEmployeeReimbursements);