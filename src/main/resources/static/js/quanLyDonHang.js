function showDonHangChitiet(donHang){
	let donHangId=donHang.getAttribute("data-donhangID");
	console.log(donHangId);
	
	let trangThaiDonHang=donHang.getAttribute("data-trangThaiDonHang");
	console.log(trangThaiDonHang);
	
	let xhr=new XMLHttpRequest();
	
	xhr.open("GET","/api/donhang/chiTietDonHang?id="+donHangId,true);
	
	xhr.onload=function(e){
		if(this.status==202){
			let chiTietDonHang=JSON.parse(this.responseText);
			console.log(chiTietDonHang);
			
		}
	};
	
	xhr.send();
	
}