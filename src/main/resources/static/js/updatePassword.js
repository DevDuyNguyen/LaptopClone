function changePass(){
	let oldPassword=document.querySelector("#old-password").value;
	let newPassword=document.querySelector("#new-password").value;
	let reNewPassword=document.querySelector("#re-new-password").value;
	
	let xhr=new XMLHttpRequest();
	
	xhr.open("post","/updatePassword?old-password="+oldPassword+"&new-password="+newPassword+"&re-new-password="+reNewPassword, true);
	
	xhr.onload=function(){
		if(this.status==202){
			let ro=JSON.parse(this.responseText);
			console.log(ro);
			if(ro.status=="fail"){
				for(let error of ro.errors){
					if(error=="Mật khẩu không chính xác"){
						let oldWarning=document.querySelector("#oldWarning");
						oldWarning.innerHTML=error;
					}
					if(error=="Mật khẩu mới không được để trống"){
						let new1Warning=document.querySelector("#new1Warning");
						new1Warning.innerHTML=error;
					}
					if(error=="Nhập lại mật khẩu mới không được để trống"||error=="Mật khẩu mới không đồng bộ"){
						let new2Warning=document.querySelector("#new2Warning");
						new2Warning.innerHTML=error;
					}
				}
			}
			else if(ro.status=="success"){
				alert("Cập nhật mật khẩu thành công");
				window.location.href="/profile";
			}
		}
	}
	
	xhr.send();
	
}