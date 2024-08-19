function showDonHangChitiet(donHang){
	console.log(donHang);
	let donHangId=donHang.getAttribute("data-donhangID");
	console.log(donHangId);
	
	let trangThaiDonHang=donHang.getAttribute("data-trangThaiDonHang");
	console.log(trangThaiDonHang);
	
	let xhr=new XMLHttpRequest();
	
	xhr.open("GET","/api/donhang/thongTinChiTietDonHang?id="+donHangId,true);
	
	xhr.onload=function(e){
		if(this.status==202){
			let thongTinChiTietDonHang=JSON.parse(this.responseText);
			console.log(thongTinChiTietDonHang);
			
			document.getElementById("hoTenNguoiNhan").innerHTML=thongTinChiTietDonHang.data.tenNguoiNhan;
			document.getElementById("diaChiNhan").innerHTML=thongTinChiTietDonHang.data.diaChiNhan;
			document.getElementById("sdtNhanHang").innerHTML=thongTinChiTietDonHang.data.sdtNguoiNhan;
			
			document.getElementById("trangThaiDonHang").innerHTML=thongTinChiTietDonHang.data.trangThaiDonHang;
			document.getElementById("ngayDatHang").innerHTML=thongTinChiTietDonHang.data.ngayDat;
			document.getElementById("ngayShipHang").innerHTML=thongTinChiTietDonHang.data.ngayGiaoHang;
			document.getElementById("ngayNhanHang").innerHTML=thongTinChiTietDonHang.data.ngayNhanHang;
			
			document.getElementById("maDonHang").innerText=thongTinChiTietDonHang.data.id;
			
			
			let danhSachChiTietDonHang=document.querySelector(".chiTietDonHang tbody");
			console.log(danhSachChiTietDonHang);
			let sum=0;
			
			let chiTietDonHangRow="";
			for(let chiTietDonHang of thongTinChiTietDonHang.data.chiTietDonHangs){
				chiTietDonHangRow+=
				'<tr>' +
					'<td>' + chiTietDonHang.id + '</td>' +
	                '<td>' + chiTietDonHang.sanPham.tenSanPham+ '</td>' +
	                '<td>' + chiTietDonHang.donGia + '</td>'+
	                '<td>' + chiTietDonHang.soLuongDat+ '</td>'
	            +'</tr>';
	            sum+=Number.parseInt(chiTietDonHang.donGia);
			}
			danhSachChiTietDonHang.innerHTML=chiTietDonHangRow;
			
			document.getElementById("nguoiDat").innerHTML=thongTinChiTietDonHang.data.tenNguoiDat;
			document.getElementById("shipper").innerHTML=thongTinChiTietDonHang.data.tenShipper;
			
			let modalTarget=document.getElementById(donHang.getAttribute("data-modal-target"));
			console.log(donHang.getAttribute("data-modal-target"));
			console.log(modalTarget);
			let overlay=document.getElementById("overlay");
			
			let orderTotal=document.getElementById("orderTotal");
			orderTotal.innerHTML=sum;
			
			openModal(modalTarget, overlay);
			
			
		}
	};
	
	xhr.send();
	
}



let closeButtonList=document.querySelectorAll(".modal-close-button");
if(closeButtonList!==null){
	closeButtonList.forEach(button=>{
		button.addEventListener("click",()=>{
			let modal=button.closest(".my-modal");
			let overlay=document.getElementById("overlay");
			closeModal(modal, overlay);
		})
	});

}

overlay.addEventListener("click",()=>{
	let modalList=document.querySelectorAll(".my-modal");
	let overlay=document.getElementById("overlay");
	modalList.forEach(modal=>{
		closeModal(modal, overlay);
	});
	
});

let huyDonHangButtonList=document.querySelectorAll(".huyDonHangButton");
if(huyDonHangButtonList!==null){
	huyDonHangButtonList.forEach(button=>{
		let donHangRow=button.closest("tr");
		let donHangID=button.getAttribute("data-donHangID");
		button.addEventListener("click", ()=>{
			huyDonHang(donHangRow, donHangID);
		})
	})
}




function openModal(modal, overlay){
	console.log(modal);
	modal.classList.add("active");
	overlay.classList.add("active");
	
}
function closeModal(modal, overlay){
	modal.classList.remove("active");
	overlay.classList.remove("active");
	
}
function huyDonHang(donHangRow, donHangID){
	alert("Bạn có chắc muốn hủy đơn hàng "+donHangID+"?");
	let xhr=new XMLHttpRequest();
	
	xhr.open("GET","/api/donhang/huyDonHang?donHangId="+donHangID, true);
	xhr.onload=function(){
		if(this.status==202){
			let ro=JSON.parse(this.responseText);
			if(ro.status=="fail"){
				alert("Hủy đơn hàng "+donHangID+" thất bại.");
			}
			else if(ro.status==="success"){
				alert("Hủy đơn hàng "+donHangID+" thành công.");
				donHangRow.remove();
			}
		}
	}
	
	xhr.send();
}
