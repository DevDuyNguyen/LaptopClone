function getInfoFromAccount(){
	let hoTen=document.getElementById("hoTen");
	let soDienThoai=document.getElementById("soDienThoai");
	let diaChi=document.getElementById("diaChi");
	
	let hoTenNguoiNhan=document.getElementById("hoTenNguoiNhan");
	let sdtNhanHang=document.getElementById("sdtNhanHang");
	let diaChiNhan=document.getElementById("diaChiNhan");
	
	hoTenNguoiNhan.value=hoTen.value;
	sdtNhanHang.value=soDienThoai.value;
	diaChiNhan.value=diaChi.value;
	
}

function calculateOrderTotal(){
	let sanpham_total_list=document.getElementsByClassName("total");
	let sum=0;
	for(let sanpham_total of sanpham_total_list){
		sum+=parseInt(sanpham_total.innerText);
		console.log(parseInt(sanpham_total.innerText));
	}
	
	console.log(sum);
	console.log("sdfadsf");
	let ordertotal=document.getElementById("ordertotal");
	ordertotal.innerHTML=sum; 
}

setTimeout(()=>{
	calculateOrderTotal()
},500);