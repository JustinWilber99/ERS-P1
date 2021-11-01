let form = document.getElementById("requestApp");

form.addEventListener('submit', submitRequest);

async function submitRequest(e) {
	
	e.preventDefault();
	
	let amount = document.getElementById("amount").value;
	let type = document.getElementById("type").value;
	let description = document.getElementById("desc").value;
	
	let reimb = {
		amount,
		type,
		description
	}
	
	try {
		
		let req = await fetch('http://localhost:8080/ERS/api/request', {
			
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reimb)
		});
		
		let res = await req.json();	
			
	} catch (e) {
	
	}
}