let sanPhamTableBody=document.querySelector(".sanPhamTable tbody");
console.log(sanPhamTableBody);
let url="/api/sanpham/searchSanPham";
let global_requestParam;
fillGlobalRequestParam();
let paginationContainer=document.querySelector("#pagination");
let noPage=5;

$(document).ready(function(){
    $(".btnTimSanPham").click(function(){
		fillGlobalRequestParam();
		searchSanPham(url, global_requestParam);
    })
    
     $("#quickIdSearchForm").on( "submit", function( event ) {

		event.preventDefault();
		fillGlobalRequestParam();
		searchSanPham(url, global_requestParam);
	});
	
	$("#danhMucDropdown").on("input", function(){
	  if(this.value==1){
	    let modal=document.getElementById("lapTopModal");
	    openModal(modal);
	  }
	  else{
	    let modal=document.getElementById("notLaptopModal");
	    openModal(modal);
	    console.log(modal);
	  }
	
	});
	
	
	$("#overlay").on("click",function(){
	  $(".my-modal").each(function(){
	    closeModal(this);
	  })
	});
	
	$(".closeModalButton").on("click",function(){
	  let modal=this.closest(".my-modal");
	  closeModal(modal);
	
	});
	
	//for test only
	
	$("#forTestLaptopForm").on("click", function(){
	    $("#laptopForm").find("[name='tenSanPham']").attr("value", "sp1");
	    $("#laptopForm").find("[name='donGia']").attr("value", 1222);
	    $("#laptopForm").find("[name='hsxID']").attr("value", "2");
	    $("#laptopForm").find("[name='ram']").attr("value", "4gb");
	    $("#laptopForm").find("[name='manHinh']").attr("value", "12pixel");
	    $("#laptopForm").find("[name='cpu']").attr("value", "intelcore5");
	    $("#laptopForm").find("[name='thietKe']").attr("value", "asdfadfaf");
	    $("#laptopForm").find("[name='thongTinChung']").html("asdfadfaf");
	    $("#laptopForm").find("[name='dungLuongPin']").attr("value", "asdfadfaf");
	    $("#laptopForm").find("[name='thongTinBaoHanh']").attr("value", "asdfadfaf");
	    $("#laptopForm").find("[name='donViKho']").attr("value", 12);
    	$("#laptopForm").find("[name='thongTinBaoHanh']").html("asdfasdf");
	});
	
	
	$("#laptopForm").on("submit", function(e){
	    e.preventDefault();
	    let formData=new FormData();
	    $(this).find("input").each(function(){
	        let name=this.getAttribute("name");
	        let inputType=this.getAttribute("type");
	       
	        
	        if(inputType!=="submit"){
	        	
	        	console.log(inputType);
	        	console.log(name);
	        	
		        if(inputType!=="file"){
		            formData.append(name, this.value);
		            console.log("not file");
		            console.log(this.value);
		            
		            
		        }
		        else{
		            formData.append(name, this.files[0]);
		            console.log("file");
		            
		        }
		    }
		   
	        
	
	    });
	    
		$(this).find("textarea").each(function(){
				let name=this.getAttribute("name");
		    	formData.append(name, this.innerHTML);
		});
		
		formData.append("danhMuc",document.getElementById("danhMucDropdown").value);
	
	    $.ajax({
	        url:"/api/sanpham/addSanPham",
	        async:true,
	        success:function(result, status, xhr){
	          console.log(result);
	        },
	        data:formData,
	        processData: false, // Prevent jQuery from automatically transforming the data into a query string
            contentType: false, // Set contentType to false as jQuery will tell the server it is a multipart form data request
	       	type:"POST"
	    });
	    
	    
	
	    //textarea
	
	
	});
    
});

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
	console.log("fillGlobalRequestParam");
	global_requestParam="";
	global_requestParam+="danhMucId="+document.querySelector("#danhMuc").value;
	global_requestParam+="&hangSXId="+document.querySelector("#hangSanXuat").value;
	global_requestParam+="&minValue="+document.querySelector("#minValue").value;
	global_requestParam+="&maxValue="+document.querySelector("#maxValue").value;
	global_requestParam+="&searchByName="+document.querySelector("#searchByName").value;
	global_requestParam+="&sapXepTheo="+document.querySelector("#sapXepTheo").value;
	global_requestParam+="&sanPhamId="+document.querySelector("#searchById").value;
	
	console.log(document.querySelector("#searchById").value);
	
}

function openModal(modal){
  modal.classList.add("active");
  document.getElementById("overlay").classList.add("active");
}

function closeModal(modal){
  modal.classList.remove("active");
  document.getElementById("overlay").classList.remove("active");
}

