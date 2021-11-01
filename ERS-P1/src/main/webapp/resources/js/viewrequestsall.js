let form = document.getElementById('form');

form.addEventListener('submit', viewReq);

async function viewReq(e) {
	e.preventDefault();
	
	let container = document.getElementById('container');
	container.innerHTML = '';
	
	let table = document.createElement('table');
	let headers = document.createElement('tr');
	headers.innerHTML = `<th>Amount</th><th>Type</th><th>Submitted By</th><th>Description</th><th>Submitted</th>
						 <th>Status</th><th>Resolved By</th><th>Resolved</th><th>Receipt</th>`;
	table.appendChild(headers);
	container.appendChild(table);
	
	let url;
	let filter = document.getElementById('status').value;
	
	if (filter == 'Pending') {
		url = 'http://localhost:8080/ERS/api/allpending';
	
	} else {
		url = 'http://localhost:8080/ERS/api/allresolved';
	}
	
	let req = await fetch(url, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	});
	
	let res = await req.json();
	
	for (let i = 0; i < res.length; i++) {
		let temp = await fetch('http://localhost:8080/ERS/api/author', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(res[i])
		});
		
		let author = await temp.json();
		res[i].authorId = author.username;
		
		if (Number(res[i].resolverId)) {
			temp = await fetch('http://localhost:8080/ERS/api/resolver', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(res[i])
			});
			
			let resolver = await temp.json();
			res[i].resolverId = resolver.username;
			
		} else {
			res[i].resolverId = '';
		}
		
		let submitted = (new Date(res[i].timeSubmitted)).toDateString();
		res[i].timeSubmitted = submitted;
		
		if (res[i].timeResolved) {
			let resolved = (new Date(res[i].timeResolved)).toDateString();
			res[i].timeResolved = resolved;
		
		} else {
			res[i].timeResolved = '';
		}
		
		if (res[i].description == null) {
			res[i].description = '';
		}
		
		if (res[i].receipt == null) {
			res[i].receipt = '';
		}
		
		if (res[i].statusId == 1) {
			res[i].statusId = 'Pending';
		
		} else if(res[i].statusId == 2) {
			res[i].statusId = 'Approved';
		
		} else {
			res[i].statusId = 'Denied';
		}
		
		if (res[i].typeId == 1) {
			res[i].typeId = 'Food';
		
		} else if (res[i].typeId == 2) {
			res[i].typeId = 'Lodging';
		
		} else if (res[i].typeId == 3) {
			res[i].typeId = 'Travel';
		
		} else if (res[i].typeId == 4) {
			res[i].typeId = 'Other';
		}
		
		let row = document.createElement('tr');
		
		row.innerHTML = `<td>${res[i].amount}</td><td>${res[i].typeId}</td><td>${res[i].authorId}</td><td>${res[i].description}</td>
						 <td>${res[i].timeSubmitted}</td><td>${res[i].statusId}</td><td>${res[i].resolverId}</td>
						 <td>${res[i].timeResolved}</td><td>${res[i].receipt}</td>`;	
						 					 
		table.appendChild(row);
	}
}