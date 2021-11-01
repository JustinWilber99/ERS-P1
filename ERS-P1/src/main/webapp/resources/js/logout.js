let signOff = document.getElementById("logout");

signOff.addEventListener('click', logout);

async function logout() {
	
	console.log('click');
	
		let req = await fetch('http://localhost:8080/ERS/api/logout');
		let res = req.status;
		
		// Send user back to the login screen
		if(res == 200) {
			window.location.replace('http://localhost:8080/ERS/resources/html/login.html');
		} 	
}
