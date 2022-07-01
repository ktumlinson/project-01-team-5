// get the buttons
const newReimbursement = document.getElementById('newReimbursement');
const updateUser = document.getElementById('updateUser');
const viewInfo = document.getElementById('viewInfo');
const viewOpen = document.getElementById('viewOpen');
const viewResolved = document.getElementById('viewResolved');
const submitReimbursement = document.getElementById('submitRequest');
const cancelReimbursement = document.getElementById('cancelRequest');
const update = document.getElementById('submitUpdate');
const cancelUpdate = document.getElementById('cancelUpdate');
const cancelView = document.getElementById('cancelView');

// get modals
const background = document.getElementById('background');
const reimbursementMenu = document.getElementById('reimbursementMenu');
const updatInfoMenu = document.getElementById('updateMenu');
const viewInfoMenu = document.getElementById('userInfo');

// get the tables
const employeeTable = document.getElementById('employee-open-table');

const toggleBackground = () =>{
    background.classList.toggle('visible');
}

const toggleReimbursement = () =>{
    reimbursementMenu.classList.toggle('visible');
    toggleBackground();
}

const toggleUpdateUser = () =>{
    updatInfoMenu.classList.toggle('visible');
    toggleBackground();
}

const toggleViewUser = ()=>{
    viewInfoMenu.classList.toggle('visible');
    toggleBackground();
}

const viewOpenTable = ()=>{
    fillTableOpen();
    employeeTable.style.visibility = 'visible';
}

const viewClosedTable = ()=>{
    fillTableClosed();
    employeeTable,style.visibility = 'visible';
}


const fillTableOpen = ()=>{
    // this will be a get
    fetch('https:// ourstuff.com')
}

const fillTableClosed = () =>{
    fetch('httpsL// ourstuff.com')
}

newReimbursement.addEventListener('click', toggleReimbursement);
cancelReimbursement.addEventListener('click', toggleReimbursement);

updateUser.addEventListener('click', toggleUpdateUser);
cancelUpdate.addEventListener('click', toggleUpdateUser);

viewInfo.addEventListener('click', toggleViewUser);
cancelView.addEventListener('click', toggleViewUser);

viewOpen.addEventListener('click', viewOpenTable);
viewResolved.addEventListener('click', viewClosedTable);