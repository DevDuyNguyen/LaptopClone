let sanPhamTableBody=document.querySelector(".sanPhamTable tbody");
console.log(sanPhamTableBody);
let url="/api/sanpham/searchSanPham";
let global_requestParam;
fillGlobalRequestParam();
let paginationContainer=document.querySelector("#pagination");
let noPage=5;

let global_xhr=new XMLHttpRequest();
global_xhr.open("GET", "/api/sanpham/searchSanPham", true);
global_xhr.onload=function(){
	if(this.status===202){
		let ro=JSON.parse(this.responseText);
		console.log(ro);
		if(ro.status==="success"){
			for(let sanPham of ro.data)
			sanPhamTableBody.innerHTML+=
				'<tr>' +
				  '<td>' + sanPham.id + '</td>' +
                  '<td>' + '<img src="/laptopshop/img/'+sanPham.id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
                  '<td>' + sanPham.tenSanPham + '</td>' +
                  '<td>' + sanPham.danhMuc+ '</td>' +
                  '<td>' + sanPham.hangSanXuat+ '</td>' +
                  '<td>' + sanPham.donGia + '</td>' +
                  '<td>' + sanPham.donViKho + '</td>' +
                  '<td> <button class="btn btn-warning btnChiTiet" data-sanPhamId='+sanPham.id+'style="margin-right: 6px">Chi tiết</button>';
			sanPhamTableBody.innerHTML+='</tr>';
			
			pagination(url, global_requestParam, paginationContainer, noPage, ro.currentPage, ro.totalPage, searchSanPham);
		}
	}
}
global_xhr.send();

function searchSanPham(url, requestParam){
	sanPhamTableBody.innerHTML="";
	let xhr=new XMLHttpRequest();
	xhr.open("GET", url+"?"+requestParam, true);
	xhr.onload=function(){
		if(this.status===202){
			let ro=JSON.parse(this.responseText);
			console.log(ro);
			if(ro.status==="success"){
				for(let sanPham of ro.data)
				sanPhamTableBody.innerHTML+=
					'<tr>' +
					  '<td>' + sanPham.id + '</td>' +
	                  '<td>' + '<img src="/laptopshop/img/'+sanPham.id+'.png" class="img-responsive" style="height: 50px; width: 50px" />'+'</td>' +
	                  '<td>' + sanPham.tenSanPham + '</td>' +
	                  '<td>' + sanPham.danhMuc + '</td>' +
	                  '<td>' + sanPham.hangSanXuat + '</td>' +
	                  '<td>' + sanPham.donGia + '</td>' +
	                  '<td>' + sanPham.donViKho + '</td>' +
	                  '<td> <button class="btn btn-warning btnChiTiet" data-sanPhamId='+sanPham.id+'style="margin-right: 6px">Chi tiết</button>';
				sanPhamTableBody.innerHTML+='</tr>';
				
				pagination(url, global_requestParam, paginationContainer, noPage, ro.currentPage, ro.totalPage, searchSanPham);
			}
		}
	}
	xhr.send();	
}

function fillGlobalRequestParam(){
	global_requestParam="";
	global_requestParam+="danhMuc="+document.querySelector("#danhMuc").value;
	global_requestParam+="&hangSanXuat="+document.querySelector("#hangSanXuat").value;
	global_requestParam+="&minValue="+document.querySelector("#minValue").value;
	global_requestParam+="&maxValue="+document.querySelector("#maxValue").value;
	global_requestParam+="&searchByName="+document.querySelector("#searchByName").value;
	global_requestParam+="&sapXepTheo="+document.querySelector("#sapXepTheo").value;
//	console.log(global_requestParam);
	
}

