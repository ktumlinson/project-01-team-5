const viewInfo = document.getElementById('viewInfo');
const viewOpen = document.getElementById('viewOpen');

// get the table
const employeeTable = document.getElementById('employee-table');

const viewOpenTable = ()=>{
    employeeTable.innerHTML = "";
    createOpenHeader();
    fetch('http://localhost:8080/employee-servlet-app/employees/openrequests',{
        method: 'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    }).then(function(response){
        if(!response.ok){
            throw Error('Error retreiving open requests from DB');
        }
        return response.json();
    }).then(function(data){
        console.log(data);
        data.forEach(obj =>{
            let newReimbursement = {
                id: obj.id,
                status: obj.status.status,
                type: obj.type.type,
                description: obj.description,
                amount: obj.reimbursementAmt,
                time: obj.timeSubmitted
            }
            createOpenRow(newReimbursement);
        })
    })
    employeeTable.style.visibility = 'visible';
}

const createOpenHeader = () =>{

    const openTags = ['ID', 'Type', 'Description', 'Amount', 'Time']
    console.log('creating header')
    let tr = document.createElement('tr');
    tr.classList.add('bg-primary');
    openTags.forEach(obj => {
        // id, type, description, amount, time
        let spacing = document.createElement('div');
        spacing.classList.add('spacing');
        let text = document.createTextNode(obj);
        let header = document.createElement('th');
        spacing.appendChild(text);
        header.appendChild(spacing);
        tr.appendChild(header);
    })
    employeeTable.appendChild(tr);


}

const createOpenRow = (newReimbursement) =>{
    if(newReimbursement.status != 'pending'){
        console.log(newReimbursement.id + ' is not pending')
        return;
    }
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

    // id, type, description, amount, time
    let spacing = document.createElement('div');
    let text = document.createTextNode(newReimbursement.id);
    spacing.classList.add('spacing');
    let idCell = document.createElement('td');
    spacing.appendChild(text)
    idCell.appendChild(spacing);
    row.appendChild(idCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.type);
    spacing.classList.add('spacing');
    let typeCell = document.createElement('td');
    spacing.appendChild(text)
    typeCell.appendChild(spacing);
    row.appendChild(typeCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.description);
    spacing.classList.add('spacing');
    let descriptionCell = document.createElement('td');
    spacing.appendChild(text)
    descriptionCell.appendChild(spacing);
    row.appendChild(descriptionCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.amount);
    spacing.classList.add('spacing');
    let amountCell = document.createElement('td');
    spacing.appendChild(text)
    amountCell.appendChild(spacing);
    row.appendChild(amountCell);

    // get the time
    let date = new Date(newReimbursement.time);
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let formatedTime = date.getMonth() + '/' + date.getDay() + '/' + date.getFullYear() + ' At: ' + hours + ':' + minutes;

    spacing = document.createElement('div');
    text = document.createTextNode(formatedTime);
    spacing.classList.add('spacing');
    let timeCell = document.createElement('td');
    spacing.appendChild(text)
    timeCell.appendChild(spacing);
    row.appendChild(timeCell);

    employeeTable.appendChild(row);
}

const viewClosedTable = ()=>{
    employeeTable.innerHTML = "";
    createClosedHeader();

    fetch('http://localhost:8080/employee-servlet-app/employees/closedrequests',{
        method: 'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    }).then(function(response){
        if(!response.ok){
            throw Error('Error retreiving open requests from DB');
        }
        return response.json();
    }).then(function(data){
        console.log(data);
        data.forEach(obj =>{
            let newReimbursement = {
                id: obj.id,
                status: obj.status.status,
                type: obj.type.type,
                description: obj.description,
                amount: obj.reimbursementAmt,
                time: obj.timeResolved,
                manager: obj.managerId.username
            }
            createClosedRow(newReimbursement);
        })
    })

    employeeTable.style.visibility = 'visible';
}

const createClosedHeader = () =>{
    const openTags = ['ID', 'Type', 'Description', 'Amount', 'Time Closed', 'Reviewing Manager']
    console.log('creating header')
    let tr = document.createElement('tr');
    tr.classList.add('bg-primary');
    openTags.forEach(obj => {
        // id, type, description, amount, time
        let spacing = document.createElement('div');
        spacing.classList.add('spacing');
        let text = document.createTextNode(obj);
        let header = document.createElement('th');
        spacing.appendChild(text);
        header.appendChild(spacing);
        tr.appendChild(header);
    })
    employeeTable.appendChild(tr);
}

const createClosedRow = (newReimbursement) =>{
    if(newReimbursement.status == 'pending'){
        console.log(newReimbursement.id + ' is pending')
        return;
    }
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

    // id, type, description, amount, time, manager
    let spacing = document.createElement('div');
    let text = document.createTextNode(newReimbursement.id);
    spacing.classList.add('spacing');
    let idCell = document.createElement('td');
    spacing.appendChild(text)
    idCell.appendChild(spacing);
    row.appendChild(idCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.type);
    spacing.classList.add('spacing');
    let typeCell = document.createElement('td');
    spacing.appendChild(text)
    typeCell.appendChild(spacing);
    row.appendChild(typeCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.description);
    spacing.classList.add('spacing');
    let descriptionCell = document.createElement('td');
    spacing.appendChild(text)
    descriptionCell.appendChild(spacing);
    row.appendChild(descriptionCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.amount);
    spacing.classList.add('spacing');
    let amountCell = document.createElement('td');
    spacing.appendChild(text)
    amountCell.appendChild(spacing);
    row.appendChild(amountCell);

    // get the time
    let date = new Date(newReimbursement.time);
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let formatedTime = date.getMonth() + '/' + date.getDay() + '/' + date.getFullYear() + ' At: ' + hours + ':' + minutes;

    spacing = document.createElement('div');
    text = document.createTextNode(formatedTime);
    spacing.classList.add('spacing');
    let timeCell = document.createElement('td');
    spacing.appendChild(text)
    timeCell.appendChild(spacing);
    row.appendChild(timeCell);

    spacing = document.createElement('div');
    text = document.createTextNode(newReimbursement.manager);
    spacing.classList.add('spacing');
    let managerCell = document.createElement('td');
    spacing.appendChild(text)
    managerCell.appendChild(spacing);
    row.appendChild(managerCell);

    employeeTable.appendChild(row);
}




viewOpen.addEventListener('click', viewOpenTable);
viewResolved.addEventListener('click', viewClosedTable);