function changeInformation(){
	let xhr=new XMLHttpRequest();
	let name=document.getElementById("name").value;
	let phone=document.getElementById("phone").value;
	let address=document.getElementById("address").innerHTML;
	xhr.open("GET","/updateProfile?name="+name+"&phone="+phone+"&address="+address,true);
	
	xhr.onload=function(e){
		console.log(this.status);
		
		if(this.status==202){
			console.log(this.responseText);
			console.log(this.responseText===null);
			let ro=JSON.parse(this.responseText);
			
			
			
			if(ro.status=="fail"){
				let errorContainer;
				for(let error of ro.errors){
					if(error=="Số điện thoại không phù hợp"){
						errorContainer=document.getElementById("phoneWarning");
						errorContainer.innerHTML=error;
					}
				}
			}
			else if(ro.status=="success"){
				alert("Cập nhật thông tin thành công");
				window.location.href="/profile";
			}	
			
			
		}
		
	}
	
	xhr.send();
	
}