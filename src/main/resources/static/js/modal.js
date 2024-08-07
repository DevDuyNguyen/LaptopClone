let openModalButtons=document.querySelectorAll("[data-modal-target]");
let overlay=document.getElementById("overlay");
let closeModalButtons=document.querySelectorAll(".modal-close-button");


if(openModalButtons!==null && overlay!=null && closeModalButtons!==null){
	openModalButtons.forEach(button => {
	    button.addEventListener("click",()=>{
	        let modal=document.getElementById(button.getAttribute("data-modal-target"));
	        openModal(modal);
	    })
	});
	
	closeModalButtons.forEach(button=>{
	    button.addEventListener("click",()=>{
	        let modal=button.closest(".modal");
	        closeModal(modal);
	    });
	});
	
	overlay.addEventListener("click",()=>{
	    let modalList=document.querySelectorAll(".modal");
	    modalList.forEach((modal)=>{
	        closeModal(modal);
	    })
	});
}





function openModal(modal){
    modal.classList.add("active");
    overlay.classList.add("active");
}
function closeModal(modal){
    modal.classList.remove("active");
    overlay.classList.remove("active");
}