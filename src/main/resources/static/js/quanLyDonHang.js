
$(document).ready(function(){
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
	
	$(".btnDuyetDonHang").click(function(){
	    let donHangId=this.closest("tr").getAttribute("id");
	    duyetDonHang(donHangId);
	});

	$("#btnDuyetDonHangAll").click(function(){
	    let donHangRowList=$(".donHangRow");
	    donHangRowList.each(function(){
	        duyetDonHang(this.getAttribute("id"));
	    })
	});

});



function showDonHangChitiet(donHang){
	
	
	let donHangId=donHang.getAttribute("data-donhangID");
	
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
	let userChoice=confirm("Bạn có chắc muốn hủy đơn hàng "+donHangID+"?");
	if(userChoice==false)
		return;
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

function duyetDonHang(donHangId){
	let userChoice=confirm("Bạn muốn duyệt đơn hàng có ID="+donHangId);
	if(userChoice===false)
		return;
	
    $.ajax({
        url:"/api/donhang/duyetDonHang?donHangId="+donHangId,
        type:"POST",
        async:true,
        success:function(result,status,xhr){
        	console.log("inside sucess");
        	console.log(status);	
 
            if(status==="success"){
            	console.log(200);
                if(result.status==="success"){
                	console.log("success");
               
                	console.log(result);
                	
                    let donHangRowJQuery=$('#'+donHangId);
                    donHangRowJQuery.empty();
    				console.log("HERE:"+donHangRowJQuery[0]);
    				
    				
                    donHangRowJQuery.append($(document.createElement("td")).html(result.data.id)[0]);
					donHangRowJQuery.append($(document.createElement("td")).html(result.data.hoTenNguoiNhan)[0]);
					donHangRowJQuery.append($(document.createElement("td")).html(result.data.trangThaiDonHang)[0]);
					donHangRowJQuery.append($(document.createElement("td")).html(result.data.tongGiaTri)[0]);
					donHangRowJQuery.append($(document.createElement("td")).html(result.data.ngayDatHang)[0]);
					donHangRowJQuery.append($(document.createElement("td")).html(result.data.ngayGiaoHang)[0]);
					donHangRowJQuery.append($(document.createElement("td")).html(result.data.ngayNhanHang)[0]);
                                                                            
                    console.log(result.data.shipper);
                    if(result.data.shipper!==null)
                        donHangRowJQuery.append(document.createElement("td").innerHTML=result.data.shipper.id);
                    else
                        donHangRowJQuery.append(document.createElement("td").innerHTML="");

                    let donHangBtns=document.createElement("td");
                    
                    if(result.data.trangThaiDonHang!="Hoàn thành" && result.data.trangThaiDonHang!="Đã bị hủy"){
                        let btnHuy=document.createElement("button");
                        btnHuy.setAttribute("data-donHangID", result.data.id);
                        btnHuy.classList.add("huyDonHangButton");
                        btnHuy.innerText="Hủy";
                        donHangBtns.append(btnHuy);
                    }

                    let btnChiTietDH=document.createElement("button");
                    btnChiTietDH.setAttribute("data-modal-target","modal-chitietdonhang");
                    btnChiTietDH.setAttribute("data-donhangID", result.data.id);
                    btnChiTietDH.setAttribute("data-trangThaiDonHang", result.data.trangThaiDonHang);
                    console.log(1);
                    btnChiTietDH.addEventListener("click", function(){
                    	showDonHangChitiet(this)
                    });
                    console.log(1);
                    btnChiTietDH.innerText="Chi tiết";
                    donHangBtns.append(btnChiTietDH);

                    if(result.data.trangThaiDonHang!="Đang chờ phê duyệt"){
                        let btnDuyet=document.createElement("button");
                        btnDuyet.classList.add("btnDuyetDonHang");
                        btnDuyet.innerText="Duyệt";
                        donHangBtns.append(btnDuyet);
                    }
                    
                }
            }
        },
        complete:function(xhr,status){
        	console.log(status);
        }
    });
}
