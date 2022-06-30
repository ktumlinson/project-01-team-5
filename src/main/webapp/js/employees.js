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

// get modals
const background = document.getElementById('background');
const reimbursementMenu = document.getElementById('reimbursementMenu');
const updatInfoMenu = document.getElementById('updateMenu');

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

newReimbursement.addEventListener('click', toggleReimbursement);
cancelReimbursement.addEventListener('click', toggleReimbursement);

updateUser.addEventListener('click', toggleUpdateUser);
cancelUpdate.addEventListener('click', toggleUpdateUser);