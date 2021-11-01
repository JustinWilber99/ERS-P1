let form = document.getElementById("login");

form.addEventListener("submit", login);

async function login(e) {
	
	e.preventDefault();
	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	
	let user = {
		username,
		password
	}
	
	try {
		
		let req = await fetch('http://localhost:8080/ERS/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			
			body: JSON.stringify(user)	
		});
		
		let res = await req.json();
		
		// Send them to the appropriate home page depending on their roldId
		if (res.roleId == 1) {
			
			location.href = '../html/employee/home.html';
		
		} else if (res.roleId == 2) {
			
			location.href = '../html/manager/home.html';
		}
		
	} catch (e) {
		
		alert("Whoopsie! Your username or password is incorrect, try again!");
	}
}