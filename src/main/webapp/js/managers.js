// get buttons
const reviewReimbursement = document.getElementById('reviewReimbursement');
const viewByEmployee = document.getElementById('viewReimbursementByEmployee');
const cancelReview = document.getElementById('cancelReview');
const cancelSearch = document.getElementById('cancelSearch');
const logoutButton = document.getElementById('logout-button');

// get modals
const background = document.getElementById('background');
const reviewMenu = document.getElementById('reviewModal');
const findByEmp = document.getElementById('findEmployeeModal');

const toggleBackground = () => {
    background.classList.toggle('visible');
}

const toggleReview = () => {
    reviewMenu.classList.toggle('visible');
    toggleBackground();
}

const toggleFindByEmp = () => {
    findByEmp.classList.toggle('visible');
    toggleBackground();
}


// add event listeners
reviewReimbursement.addEventListener('click', toggleReview);
cancelReview.addEventListener('click', toggleReview);

viewByEmployee.addEventListener('click', toggleFindByEmp);
cancelSearch.addEventListener('click', toggleFindByEmp);
