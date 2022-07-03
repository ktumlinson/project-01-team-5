const viewEmployees = document.getElementById('manager-employees-button');
const viewAllReimbursements = document.getElementById('manager-reimbursements-button');

// get the dunamic table
const managerTable = document.getElementById('manager-table');

// employee table
const viewEmps = () => {
    // clear the table
    managerTable.innerHTML = "";

    // fill out the header
    createEmpsHeader();

    // send get request to the DB for all employees
    fetch('http://ec2-3-93-20-196.compute-1.amazonaws.com:8080/employee-servlet-app/employees', {
        method: 'GET',
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (response) {
        if (!response.ok) {
            throw Error("Error retreiving employees from DB")
        }
        return response.json();
    }).then(function (data) {
        console.log(data);
        // add the elements to the table
        data.forEach(obj => {
            let newEmployee = {
                id: obj.id,
                username: obj.username,
                password: obj.password,
                firstName: obj.firstname,
                lastName: obj.lastname,
                email: obj.email,
                role: obj.role.role
            }

            createEmpRow(newEmployee);
        });
    })

    managerTable.style.visibility = 'visible';
}

const createEmpsHeader = () => {

    let tags = ['ID', 'Role', 'Username', 'Password', 'First Name', 'Last name', 'Email']
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

const createEmpRow = (newEmployee) => {
    let row = document.createElement('tr');
    row.classList.add("bg-info");
    let idCell = document.createElement('td');
    let text = document.createTextNode(newEmployee.id);
    idCell.appendChild(text);
    row.appendChild(idCell);

    let roleCell = document.createElement('td');
    text = document.createTextNode(newEmployee.role);
    roleCell.appendChild(text);
    row.appendChild(roleCell);

    let usernameCell = document.createElement('td');
    text = document.createTextNode(newEmployee.username);
    usernameCell.appendChild(text);
    row.appendChild(usernameCell);

    let passwordCell = document.createElement('td');
    text = document.createTextNode(newEmployee.password);
    passwordCell.appendChild(text);
    row.appendChild(passwordCell);

    let firstnameCell = document.createElement('td');
    text = document.createTextNode(newEmployee.firstName);
    firstnameCell.appendChild(text);
    row.appendChild(firstnameCell);

    let lastnameCell = document.createElement('td');
    text = document.createTextNode(newEmployee.lastName);
    lastnameCell.appendChild(text);
    row.appendChild(lastnameCell);

    let emailCell = document.createElement('td');
    text = document.createTextNode(newEmployee.email);
    emailCell.appendChild(text);
    row.appendChild(emailCell);


    managerTable.appendChild(row);
}

// all rembursements 
const viewReiembursements = () => {
    managerTable.innerHTML = "";
    createReimbHeader();
    fetch('http://ec2-3-93-20-196.compute-1.amazonaws.com:8080/employee-servlet-app/reimbursements', {
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
                description: obj.description,
                amount: obj.reimbursementAmt,
                user: obj.employee.username,
            }

            createReimRow(newReimbursement);
        })
    })
    managerTable.style.visibility = 'visible';
}

const createReimbHeader = () => {
    console.log('creating header')

    let tags = ['ID', 'Type', 'Status', 'Description', 'Amount', 'Created by']
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

const createReimRow = (newReimbursement) => {
    let row = document.createElement('tr');
    if (newReimbursement.status == 'pending') {
        row.classList.add("bg-warning");
    }
    else {
        row.classList.add('bg-info');
    }


    let idCell = document.createElement('td');
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    let text = document.createTextNode(newReimbursement.id);
    spacing.appendChild(text);
    idCell.append(spacing);
    row.append(idCell);

    let typeCell = document.createElement('td');
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.type);
    spacing.appendChild(text);
    typeCell.append(spacing);
    row.append(typeCell);

    let statusCell = document.createElement('td');
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.status);
    spacing.appendChild(text);
    statusCell.append(spacing);
    row.append(statusCell);

    let descriptionCell = document.createElement('td');
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.description);
    spacing.appendChild(text);
    descriptionCell.append(spacing);
    row.append(descriptionCell);

    let amountCell = document.createElement('td');
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.amount);
    spacing.appendChild(text);
    amountCell.append(spacing);
    row.append(amountCell);

    let userCell = document.createElement('td');
    spacing = document.createElement('div');
    spacing.classList.add('spacing');
    text = document.createTextNode(newReimbursement.user);
    spacing.appendChild(text);
    userCell.append(spacing);
    row.append(userCell);

    managerTable.appendChild(row);
}

viewEmployees.addEventListener('click', viewEmps);
viewAllReimbursements.addEventListener('click', viewReiembursements);