let form = document.getElementById('form');

form.addEventListener('submit', viewReq);

let users = new Array();

(async function(){
	
	let dropDown = document.getElementById('username');
	let req = await fetch('http://localhost:8080/ERS/api/employees', {
		
		method: 'GET',
		headers: {
			'Content-Type' : 'application/json'
		}
	});
	
	let res = await req.json();
	
	for (let i = 0; i < res.length; i++) {
		
		let opt = document.createElement('option');
		let username = res[i].username;
		opt.setAttribute('value', i);
		opt.innerHTML= `${username}`;
		dropDown.appendChild(opt);
		users.push(res[i]);
	}
})();

async function viewReq(event) {
	event.preventDefault();
	
	let uname = document.getElementById('username').value;
	let user = users[uname];
	
	let req = await fetch('http://localhost:8080/ERS/api/userrequests', {
		
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(user)
	});
	
	let res = await req.json();
	let reimbs = res;
	
	let output = document.getElementById('output');
	let table = document.createElement('table');
	output.innerHTML = '';
	
	let headers = document.createElement('tr');
	
	headers.innerHTML = `<th>Amount</th><th>Type</th><th>Description</th><th>Submitted</th>
						 <th>Status</th><th>Resolved By</th><th>Resolved</th><th>Receipt</th>`;
						 
	table.appendChild(headers);
	
	for (let i = 0; i < reimbs.length; i++) {
		if (!(reimbs[i].resolverId)) {
			reimbs[i].resolverId = '';
		
		} else {
			req = await fetch('http://localhost:8080/ERS/api/resolver', {
				
				method: 'POST',
				headers: {
					'Content-Type' : 'application/json'
				},
				body: JSON.stringify(reimbs[i])
			});
			
			let resolver = await req.json();
			reimbs[i].resolverId = resolver.username;
		}
		
		reimbs[i].timeSubmitted = (new Date(Number(reimbs[i].timeSubmitted))).toDateString();
		
		if (!(reimbs[i].timeResolved)) {
			reimbs[i].timeResolved = '';
		
		} else {
			reimbs[i].timeResolved = (new Date(Number(reimbs[i].timeResolved))).toDateString();
		}
		
		if (reimbs[i].description == null) {
			reimbs[i].description = '';
		}
		
		if (reimbs[i].receipt == null) {
			reimbs[i].receipt = '';
		}
		
		if (reimbs[i].statusId == 1) {
			reimbs[i].statusId = 'Pending';
		
		} else if(reimbs[i].statusId == 2) {
			reimbs[i].statusId = 'Approved';
		
		} else {
			reimbs[i].statusId = 'Denied';
		}
		
		if (reimbs[i].typeId == 1) {
			reimbs[i].typeId = 'Food';
		
		} else if (reimbs[i].typeId == 2) {
			reimbs[i].typeId = 'Lodging';
		
		} else if (reimbs[i].typeId == 3) {
			reimbs[i].typeId = 'Travel';
		
		} else if (reimbs[i].typeId == 4) {
			reimbs[i].typeId = 'Other';
		}
		
		
		let row = document.createElement('tr');
		
		row.innerHTML = `<td>${reimbs[i].amount}</td><td>${reimbs[i].typeId}</td><td>${reimbs[i].description}</td>
						 <td>${reimbs[i].timeSubmitted}</td><td>${reimbs[i].statusId}</td><td>${reimbs[i].resolverId}</td>
						 <td>${reimbs[i].timeResolved}</td><td>${reimbs[i].receipt}</td>`;
		
		table.appendChild(row);
	}
	
	output.appendChild(table)
}