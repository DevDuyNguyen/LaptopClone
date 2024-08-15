//input: /home,name=duy&age=12,div, callBack:a function to call when a button is click, this 
//method take url + reqeuestParam=> 
/*output:
<div>
	<ul>
		<li><a href="/home?name=duy&age=12">....</li>
		....
	</ul>
<div>
*/
function pagination(url, requestParam, divContainer, noPage, currentPage, totalPage, callBack){
	divContainer.innerHTML="";
	let content="<ul>";
	
	currentPage=parseInt(currentPage);
	totalPage=parseInt(totalPage);
	noPage=parseInt(noPage);
	let halfPage=parseInt(Math.floor(noPage/2));
	
	
	
	if(currentPage!=1){
		let back=parseInt(currentPage)-1;
		content+='<li class="page-item "><a class="pageNumber"  data-page='+back+'>'+'back'+'</a></li>';
	}
	if(currentPage>=1 && currentPage<5){
		for(let i=1; i<=noPage && i<=totalPage; ++i){
			content+='<li class="page-item "><a class="pageNumber" data-page='+i+'>'+i+'</a></li>';
		}
	}
	else if(currentPage==totalPage){
		for(let i=currentPage; i>currentPage-noPage&&i>0; --i){
			content+='<li class="page-item "><a class="pageNumber" data-page='+i+'>'+i+'</a></li>';
		}
	}
	else{
		//left side
		for(let i=currentPage-halfPage; i<currentPage; ++i){

			content+='<li class="page-item "><a class="pageNumber" data-page='+i+'>'+i+'</a></li>';
		}
		content+='<li class="page-item "><a class="pageNumber" data-page='+currentPage+'>'+currentPage+'</a></li>';
		//right side
		for(let i=1; i<=halfPage; ++i){
			let page=currentPage+i;
			content+='<li class="page-item "><a class="pageNumber" data-page='+page+'>'+page+'</a></li>';
		}
		
	}
	if(currentPage!=totalPage){
		let next=parseInt(currentPage)+1;
		
		content+='<li class="page-item "><a class="pageNumber" data-page='+next+'>'+'next'+'</a></li>';
	}
//	console.log(content);
//	console.log(currentPage);
//	console.log(noPage); 
//	console.log(halfPage);	
	
	content+="</ul>";
	divContainer.innerHTML+=content;
	
	let pageNumberList=document.querySelectorAll(".pageNumber");
	pageNumberList.forEach((pageButton)=>{
		let pageNumberValue=pageButton.getAttribute("data-page");
		let newRequestParam=requestParam+"&currentPage="+pageNumberValue;
		pageButton.addEventListener("click",()=>{
			callBack(url, newRequestParam);
		})
	});
	
}

