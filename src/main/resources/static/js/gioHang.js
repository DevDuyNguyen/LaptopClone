

function addToCart(sp_id){
	let xhr=new XMLHttpRequest();
	console.log("asfadf");
	xhr.open("GET", "/api/gio-hang/addToCart?sp_id="+sp_id, true);
	
	xhr.onload=function(e){
		let response=this.responseText;
		response=JSON.parse(response);
		if(response.status==="fail"){
			for(let error of response.errors){
				alert(error);
			}
		}
		else if(response.status==="success"){
			alert("Thêm sản phẩm thành công");
		}
		console.log(this.responseText);
		
	}
	
	xhr.send();
	
}

function changeQuanity(sanPham){
	let xhr=new XMLHttpRequest();
	let sp_id=sanPham.getAttribute("data-sp_id");
	let sp_donGia=sanPham.getAttribute("data-sp_donGia");
	xhr.open("GET","/api/gio-hang/changeQuantity?sp_id="+sp_id+"&soLuong="+sanPham.value, true);
	
	xhr.onload=function(e){
		let responeObject=JSON.parse(this.responseText);
		if(responeObject.status==="fail"){
			for(let error of responeObject.errors){
				alert(error);
			}
		}
		else if(responeObject.status==="success"){
			let sanphamTotal=document.getElementById("sanpham"+sp_id);
			sanphamTotal.innerHTML=sp_donGia*sanPham.value;
			sanphamTotal.setAttribute("data-total",sp_donGia*sanPham.value);
			calculateCartTotal();
		}
	}
	
	xhr.send();
	
}

function deleteFromCart(sanPham){
	let xhr=new XMLHttpRequest();
	let sp_id=sanPham.getAttribute("data-sp_id");
	xhr.open("GET", "/api/gio-hang/deleteSanPham?sp_id="+sp_id, true);
	
	xhr.onload=function(e){
		let responseObject=JSON.parse(this.responseText);
		if(responseObject.status==="fail"){
			for(let error of responseObject.errors){
				alert(error);
			}
		}
		else if(responseObject.status==="success"){
			let sanPhamInfo=document.getElementById("sanpham_info"+sp_id);
			sanPhamInfo.remove();
			calculateCartTotal();
			
		}
	}
	
	xhr.send();
}

function calculateCartTotal(){
	let ordertotal=document.getElementById("ordertotal");
	if(ordertotal===null)
		return;
	console.log(ordertotal);
	
	let sanpham_total_list=document.getElementsByClassName("sanpham_total");
	let sum=0;
	for(let sanpham_total of sanpham_total_list){
		sum+=parseInt(sanpham_total.getAttribute("data-total"));
	}
	ordertotal.innerHTML=sum;
	
}

setTimeout(()=>{
	calculateCartTotal()
},800);