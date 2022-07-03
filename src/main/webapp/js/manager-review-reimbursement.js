const submitReview = document.getElementById('submitReview');
const approveReimbursement = document.getElementById('approve');
const denyReimbursement = document.getElementById('deny');
const reimbID = document.getElementById('reimb-number');

const findReimbursementById = () =>{
    let reimbId = reimbID.value;
    console.log(reimbId);
    fetch(`http://localhost:8080/reimbursements/${reimbId}`,{
        method: 'GET',
        headers: {
            "Content-Type":"application/json"
        }
    }).then(function(response){
        if(!response.ok){
            throw Error(`Error retreiving reimbursement ${reimbId}`);
        }
        return response.json();
    }).then(function(data){
        console.log(data);
        let reimbursementToApprove = {
            id: data.id,
            status: data.stats.stats
        }
    })

    approveReimbursement.style.visibility = 'visible';
    denyReimbursement.style.visibility = 'visible';
}

const handleApproveReimbursement = (reimbursementToApprove) =>{
    reimbursementToApprove.status = 'approved';
    pushReimbursementChange(reimbursementToApprove);
}

const handleDenyReimbursement = (reimbursementToApprove) =>{
    reimbursementToApprove.status = 'denied';
    pushReimbursementChange(reimbursementToApprove);
}

const pushReimbursementChange = (reimbursementToApprove) =>{
    fetch(`http://localhost:8080/reimbursements/${reimbId}`,{
        method: 'POST',
        headers: {
            "Content-Type" : "application/json",
        },
        body: JSON.stringify({
            id: reimbursementToApprove.id,
            status: reimbursementToApprove.status
        })
    }).then(function(response){
        response.json();
    }).then(function(json){
        console.log(json);
    })
}

submitReview.addEventListener('click', findReimbursementById);
approveReimbursement.addEventListener('click', handleApproveReimbursement(reimbursementToApprove));
denyReimbursement.addEventListener('click', handleDenyReimbursement(reimbursementToApprove));