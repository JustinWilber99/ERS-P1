(async function(){
	let acct = document.getElementById('account');
	acct.innerHTML = "";
	
	let req = await fetch('http://localhost:8080/ERS/api/viewacct', {
		
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	});
	
	let res = await req.json();
	
	console.log(res);
	
	let fName = res.firstName;
	let lName = res.lastName;
	let username = res.username;
	let email = res.email;
	
	let user = {
		fName,
		lName,
		username,
		email
	};
	
	//Display account info
	acct.innerHTML = `<p>First Name: ${user.fName}</p><p>Last Name: ${user.lName}</p>
					  <p>Username: ${user.username}</p><p>E-Mail: ${user.email}</p>`
})();