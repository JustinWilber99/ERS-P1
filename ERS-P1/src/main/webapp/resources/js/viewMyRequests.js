let form = document.getElementById('filter');
form.addEventListener('submit', getReimbs);

async function getReimbs(e) {
	e.preventDefault();
	
	let container = document.getElementById('output');
	container.innerHTML = "";
	
	let stats = document.getElementById('status').value;
	let url;
	
	
	if (stats == 'Pending') {
		url = 'http://localhost:8080/ERS/api/mypending';
	
	} else {
		url = 'http://localhost:8080/ERS/api/myresolved';
	}
	
	let req = await fetch(url, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	});
	
	let res = await req.json();
	console.log(res);
	
	let reimbs = new Array();
	
	for (let i = 0; i < res.length; i++) {
		
		let amount = Number(res[i].amount);
		let description = res[i].description;
		let status;
		let tempStatus = res[i].statusId;
		
		if (tempStatus == 1) {
			status = 'Pending';
		
		} else if (tempStatus == 2) {
			status = 'Approved';
		
		} else {
			status = 'Denied';
		}
		
		let timeSubmitted = new Date(res[i].timeSubmitted);
		let timeResolved = "";
		
		if (res[i].timeResolved != null) {
			timeResolved = new Date(res[i].timeResolved);
		}
		
		if (description == null) {
			description = '';
		}
		
		
		let type = null;
		let tempType = res[i].typeId;
		
		
		if (tempType == 1) {
			type = 'Food';
		
		} else if (tempType == 2) {
			type = 'Lodging';
		
		} else if (tempType == 3) {
			type = 'Travel';
		
		} else if (tempType == 4) {
			type = 'Other';
		}
		
		let obj = {
			amount,
			description,
			status,
			timeSubmitted,
			timeResolved,
			type
		}
		
		reimbs.push(obj);
	}
	
	let table = document.createElement('table');
	
	table.innerHTML = '<tr><th>Type:</th><th>Amount:</th><th>Status:</th><th>Description:</th><th>Time Submitted:</th><th>Time Resolved:</th></tr>';
	
	container.appendChild(table);
	
	for (let i = 0; i < reimbs.length; i++) {
		
		let row = document.createElement('tr');
		
		row.innerHTML = `<td>${reimbs[i].type}</td><td>${reimbs[i].amount}</td><td>${reimbs[i].status}</td><td>${reimbs[i].description}</td><td>${reimbs[i].timeSubmitted}</td><td>${reimbs[i].timeResolved}</td>`;
		
		table.appendChild(row);
	}	
}