let checkbox = document.getElementById('update');

checkbox.addEventListener('change', showForm);

function showForm() {
	
	let updateInfo = document.getElementById('updateInfo');
	updateInfo.innerHTML = '';
	
	if (this.checked) {
		
		updateInfo.innerHTML = 
		`<form id="update-form" method="post">
			<label for="password">New password: </label>
			<input type="password" required id="password" name="password">
			<br/>
			<label for="confirm-password">Confirm new password: </label>
			<input type="password" required id="confirm-password" name="confirm-password">
			<br/>
			<input type="submit" value="Update">
		</form>`;
		
		let form = document.getElementById('update-form');
		form.addEventListener('submit', updateAcct);
		
		async function updateAcct(e) {
			
			e.preventDefault(e);
			
			let password = document.getElementById('password').value;
			let confirm = document.getElementById('confirm-password').value;
			
			if (confirm != password) {
				alert('Error: Passwords do not match!');
				return;
			}
			
			let info = {
				password
			};
			
			console.log(info);
			
			let req = await fetch('http://localhost:8080/ERS/api/updateacct', {
				
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(info)
			});
			
			
			let res = await req.json();
			
			if (res['password'] != password) {
				alert('Unable to update account');
				return;
			}
			
			alert('Account info has been updated');
			window.location.reload(true);
		}
	}
}