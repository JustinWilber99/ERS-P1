let display = document.getElementById('display');
let selected = document.getElementById('selected');
let resolution = document.getElementById('resolution');

let reimbs = new Array();

(async function(){
	
	let table = document.createElement('table');
	let headers = document.createElement('tr');
	
	headers.innerHTML = `<th>ID:</th><th>Amount:</th><th>Type:</th>
						 <th>Submitted By:</th><th>Description:</th>
						 <th>Submitted:</th><th>Status:</th><th>Receipt:</th>`;
						 
	table.appendChild(headers);
	display.appendChild(table);
	
	let req = await fetch('http://localhost:8080/ERS/api/allpending', {
		
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	});
	
	let res = await req.json();
	reimbs = res;
	
	for (let i = 0; i < reimbs.length; i++) {
		
		let temp = await fetch('http://localhost:8080/ERS/api/author', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reimbs[i])
	});
		
		
		
		let author = await temp.json();
		reimbs[i].authorId = author.username;
		
		let submitted = (new Date(reimbs[i].timeSubmitted)).toDateString();
		reimbs[i].timeSubmitted = submitted;
		
		// Read the typeId to see if the reimb is Food/Lodging/Travel
		if (reimbs[i].typeId == 1) {
			reimbs[i].typeId = 'Food';
		
		} else if (reimbs[i].typeId == 2) {
			reimbs[i].typeId = 'Lodging';
		
		} else if (reimbs[i].typeId == 3) {
			reimbs[i].typeId = 'Travel';
		
		} else if (reimbs[i].typeId == 4) {
			reimbs[i].typeId = 'Other';
		}
		
		// Fill in values with empty string if description is null
		if (reimbs[i].description == null) {
			reimbs[i].description = '';
		}
		
		if (reimbs[i].receipt == null) {
			reimbs[i].receipt = '';
		}
		
		// Read the statusId to see if the reimb is Pending/Approved/Denied
		if (reimbs[i].statusId == 1) {
			reimbs[i].statusId = 'Pending';
			
		} else if (reimbs[i].statusId == 2) {
			reimbs[i].statusId = 'Approved';
			
		} else {
			reimbs[i].statusId = 'Denied';
		}
		
		
		
		let row = document.createElement('tr');
		
		row.innerHTML = `<td>${reimbs[i].id}</td><td>${reimbs[i].amount}</td><td>${reimbs[i].typeId}</td><td>${reimbs[i].authorId}</td><td>${reimbs[i].description}</td>
						<td>${reimbs[i].timeSubmitted}</td><td>${reimbs[i].statusId}</td><td>${reimbs[i].receipt}</td>`;
		
		table.appendChild(row);
	
		row.addEventListener('click', showSelected);
	}
	})();


function showSelected(e) {
	
	selected.innerHTML = '';	
	let clicked = (e.target).parentElement;
	
	// Use cascading nextElementSibling to set vars to proper values
	let receipt = clicked.lastElementChild.innerText;
	let id = Number(clicked.firstElementChild.innerText);
	let amount = Number(clicked.firstElementChild.nextElementSibling.innerText);
	let typeId = clicked.firstElementChild.nextElementSibling.nextElementSibling.innerText;
	let authorId = clicked.firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
	let description = clicked.firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
	let timeSubmitted = clicked.firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
	
	
	let reimb = {
		id,
		amount,
		typeId,
		authorId,
		description,
		timeSubmitted
	};
	
	selected.innerHTML = `<p>You chose reimbursement ${reimb.id} for $${reimb.amount}
							which was used for ${typeId}</p><p>This reimbursement was submitted by ${reimb.authorId} on ${reimb.timeSubmitted}</p>`;
							
	resolution.innerHTML = `<button type="button" id="approve">Approve</button>&nbsp;&nbsp;&nbsp;<button type="button" id="deny">Deny</button>`;
	
	let approveButton = document.getElementById('approve');
	let denyButton = document.getElementById('deny');
	
	approveButton.addEventListener('click', () => {
		
		let obj = {
			id,
			statusId: 2
		};
		resolve(obj);
		location.href = 'home.html';
	});
	
	denyButton.addEventListener('click', () => {
		
		let obj = {
			id,
			statusId: 3
		};
		resolve(obj);
		location.href = 'home.html';
	});
}

async function resolve(obj) {
	
	let req = await fetch('http://localhost:8080/ERS/api/resolve', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(obj)
	});
	let res = await req.json();	
}





