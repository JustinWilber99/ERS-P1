(async function(){
	let req = await fetch('http://localhost:8080/ERS/api/employees', {
		
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	});
	
	let res = await req.json();
	
	
	let container = document.getElementById('container');
	
	container.innerHTML = '';
	
	let table = document.createElement('table');
	
	table.innerHTML = `<tr><th>Username:</th><th>Name:</th><th>E-Mail:</th></tr>`
	
	container.appendChild(table);
	
	
	for (let i = 0; i < res.length; i++) {
		
		let username = res[i].username;
		let fullName = `${res[i].firstName} ${res[i].lastName}`;
		let email = res[i].email;
		
		let row = document.createElement('tr');
		
		row.innerHTML = `<td>${username}</td><td>${fullName}</td><td>${email}</td>`
		table.appendChild(row);	
	}
})();